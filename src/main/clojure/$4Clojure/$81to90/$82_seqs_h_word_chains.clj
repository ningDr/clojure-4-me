(ns $4Clojure.$81to90.$82-seqs-h-word-chains
  (:require [clojure.set]))
(defn word-chains
  "A word chain consists of a set of words ordered so that each word differs
   by only one letter from the words directly before and after it.
   The one letter difference can be either an insertion, a deletion, or a substitution.
   Here is an example word chain:
   cat -> cot -> coat -> oat -> hat -> hot -> hog -> dog
   Write a function which takes a sequence of words,
   and returns true if they can be arranged into one continous word chain,
   and false if they cannot."
  [arg]
  (println "*************************************************")
  (println (= true (arg #{"hat" "coat" "dog" "cat" "oat" "cot" "hot" "hog"})))
  (println (= false (arg #{"cot" "hot" "bat" "fat"})))
  (println (= false (arg #{"to" "top" "stop" "tops" "toss"})))
  (println (= true (arg #{"spout" "do" "pot" "pout" "spot" "dot"})))
  (println (= true (arg #{"share" "hares" "shares" "hare" "are"})))
  (println (= false (arg #{"share" "hares" "hare" "are"}))))
"解题思路：
1、我们需要一个方法判断字符串是否相似
2、然后根据该方法计算出各个顶点（字符串）的边数（该字符串相似的字符串数目）
3、存在边数0的顶点，或边数1的顶点的数量大于2，则结果为false
4、若有边数1的顶点，从其中任意一个开始找，找不到结果为false，因为这个点必定是一个起点
5、若没有边数1的顶点，则需要遍历全部顶点，分别作为起点进行寻找（例子中未实现，因为测试没这种情况，这就是测试驱动开发嘛）"

(word-chains (fn [o-set]
               (let [familiar? (fn [str1 str2]
                                 (let [len1 (count str1)
                                       len2 (count str2)
                                       sub-len (- len1 len2)
                                       set1 (clojure.core/set str1)
                                       set2 (clojure.core/set str2)
                                       dif-set1 (clojure.set/difference set1 set2)
                                       dif-set2 (clojure.set/difference set2 set1)]
                                   (cond
                                     (zero? sub-len)        ;; substitution
                                     (loop [seq1 (seq str1), seq2 (seq str2), i 0]
                                       (if (empty? seq1)
                                         (if (= 1 i) 1 0)
                                         (recur (rest seq1) (rest seq2) (if (= (first seq1) (first seq2)) i (inc i)))))
                                     (and (= 1 sub-len (count dif-set1)) (= (count set1) (count str1)) (= (count set2) (count str2)))  ;; deletion new letter
                                     1
                                     (and (= 1 (- sub-len) (count dif-set2)) (= (count set1) (count str1)) (= (count set2) (count str2))) ;; insertion new letter
                                     1
                                     (and (= 1 sub-len) (zero? (count dif-set1)) )
                                     1
                                     (and (= -1 sub-len) (zero? (count dif-set2)))
                                     1
                                     :else 0)))
                     seq-set (seq o-set)
                     res-seq (mapv (fn [word] (mapv (fn [item] (familiar? item word)) seq-set)) seq-set)
                     str-count (mapv (fn [item] (apply + item)) res-seq)
                     res-map (reduce conj (mapv (fn [key val] (hash-map key val)) seq-set str-count))
                     one-count (filter (fn [item] (= 1 item)) (vals res-map))
                     zero-count (filter zero? (vals res-map))]
                 (if (or (< 0 (count zero-count)) (< 2 (count one-count)))
                   false
                   (do (println res-seq "=====" seq-set "+++++++++++" str-count)
                       true)))))

;; -------------------------------------------------------------
(word-chains (fn [xs]
               (letfn [(one-d [[a & a-rest] [b & b-rest :as bs]]
                         (if (= a b)
                           (recur a-rest b-rest)
                           (= (apply str a-rest) (apply str bs))))
                       (chains? [l r]
                         (let [[count-l count-r] (map count [l r])]
                           (cond
                             (= count-l count-r) (= 1 (apply + (map #(if (= %1 %2) 0 1) l r)))
                             (= 1 (- count-l count-r)) (one-d l r)
                             (= -1 (- count-l count-r)) (one-d r l)
                             :else false)))
                       (transitions []
                         (->>
                          (for [x xs y xs :when (chains? x y)] [x y])
                          (group-by first)
                          (reduce-kv #(assoc %1 %2 (map second %3)) {})))
                       (moves
                         ([trs] (moves trs (keys trs)))
                         ([trs keys]
                          (if (empty? trs)
                            true
                            (some true? (map #(if-let [chain (get trs %1)]
                                                (moves (dissoc trs %1) chain))
                                             keys)))))]
                 (or (moves (transitions)) false))))

(word-chains (fn [xs]
               (letfn [(perm [s r]
                         (if (seq s)
                           (mapcat (fn [t] (perm (remove #(= t %) s) (cons t r))) s) [r]))
                       (ld [[xh & xt :as x]
                            [yh & yt :as y]]
                         (cond
                           (empty? x) (count y)
                           (empty? y) (count x)
                           (= xh yh) (ld xt yt)
                           :else (+ 1 (min (ld x yt) (ld xt y) (ld xt yt)))))]
                 (->> (perm xs [])
                      (map #(partition 2 1 %))
                      (map #(map (fn[[a b]] (ld a b)) %))
                      (some #(every? (fn [x] (= 1 x)) %))
                      true?))))