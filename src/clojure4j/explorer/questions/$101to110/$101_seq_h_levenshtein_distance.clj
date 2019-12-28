(ns clojure4j.explorer.questions.$101to110.$101-seq-h-levenshtein-distance)
(defn levenshtein-distance
  "Given two sequences x and y, calculate the Levenshtein distance of x and y,
   i.e. the minimum number of edits needed to transform x into y.
   The allowed edits are:
   - insert a single item
   - delete a single item
   - replace a single item with another item

   WARNING: Some of the test cases may timeout if you write an inefficient solution!"
  [arg]
  (println "==================")
  (println (= (arg "kitten" "sitting") 3))
  (println (= (arg "closure" "clojure") (arg "clojure" "closure") 1))
  (println (= (arg "xyx" "xyyyx") 2))
  (println (= (arg "" "123456") 6))
  (println (= (arg "Clojure" "Clojure") (arg "" "") (arg [] []) 0))
  (println (= (arg [1 2 3 4] [0 2 3 4 5]) 2))
  (println (= (arg '(:a :b :c :d) '(:a :d)) 2))
  (println (= (arg "ttttattttctg" "tcaaccctaccat") 10))
  (println (= (arg "gaattctaatctc" "caaacaaaaaattt") 9)))
;; 结果猜想：1、最后的步数为最后一个数字的值；2、只需要计算对角线及正负一距离的平行区域的值即可。
;; 转换路径为（反转）：10 > 上9 > 对角9 > 对角9 > 对角8 > 对角7 > 对角6 > 对角6 > 对角5 > 对角4 > 对角3 > 对角2 > 左1 > 左0
;; "ttttattttctg" > "tcaaccctaccat"
;; 每步转换字符：t > 2t变c > 3t变a > 4t变a > 5a变c > 6t变c > 7t变c > 8t > 9t变a > 10c > 11t变c > 12g变a >13增t
[[" 0" " 1" " 2" " 3" " 4" " 5" " 6" " 7" " 8" " 9" "10" "11" "12" "13"]
 [" 1" " 0" " 1" " 2" " 3" " 4" " 5" " 6" " 7" " 8" " 9" "10" "11" "12"]
 [" 2" " 1" " 1" " 2" " 3" " 4" " 5" " 6" " 6" " 7" " 8" " 9" "10" "11"]
 [" 3" " 2" " 2" " 2" " 3" " 4" " 5" " 6" " 6" " 7" " 8" " 9" "10" "10"]
 [" 4" " 3" " 3" " 3" " 3" " 4" " 5" " 6" " 6" " 7" " 8" " 9" "10" "10"]
 [" 5" " 4" " 4" " 3" " 3" " 4" " 5" " 6" " 7" " 6" " 7" " 8" " 9" "10"]
 [" 6" " 5" " 5" " 4" " 4" " 4" " 5" " 6" " 6" " 7" " 7" " 8" " 9" " 9"]
 [" 7" " 6" " 6" " 5" " 5" " 5" " 5" " 6" " 6" " 7" " 8" " 8" " 9" " 9"]
 [" 8" " 7" " 7" " 6" " 6" " 6" " 6" " 6" " 6" " 7" " 8" " 9" " 9" " 9"]
 [" 9" " 8" " 8" " 7" " 7" " 7" " 7" " 7" " 6" " 7" " 8" " 9" "10" " 9"]
 ["10" " 9" " 8" " 8" " 8" " 7" " 7" " 7" " 7" " 7" " 7" " 8" " 9" "10"]
 ["11" "10" " 9" " 9" " 9" " 8" " 8" " 8" " 7" " 8" " 8" " 8" " 9" " 9"]
 ["12" "11" "10" "10" "10" " 9" " 9" " 9" " 8" " 8" " 9" " 9" " 9" "10"]]

(defn minimum-distance
  [x y]
  (let [table ((fn [m n]
                 (let [row (vec (range (inc n)))]
                   (loop [res [], x m]
                     (if (zero? x)
                       (vec (reverse (conj res row)))
                       (recur (conj res (into [] (concat [x] (repeat n 0)))) (dec x))))))
               (count x) (count y))
        assoc-table (fn [x y i j table]
                      (if (= (nth x i) (nth y j))
                        (assoc table i (assoc (nth table i) j (nth (nth table (dec i)) (dec j))))
                        (assoc table i (assoc (nth table i) j (inc (min (nth (nth table (dec i)) j)
                                                                        (nth (nth table (dec i)) (dec j))
                                                                        (nth (nth table i) (dec j))))))))
        x (vec (concat [0] (seq x)))
        y (vec (concat [0] (seq y)))
        res (loop [res-x table, i 1]
              (if (= i (count x))
                res-x
                (recur (loop [res-y res-x, j 1]
                         (if (= j (count y))
                           res-y
                           (recur (assoc-table x y i j res-y)
                                  (inc j))))
                       (inc i))))]
    (last (last res))))
(levenshtein-distance minimum-distance)
;; ===========================

(levenshtein-distance (fn [a b]
                        (letfn [(lcs [[x-h & x-t :as x] [y-h & y-t :as y]]
                                  (cond
                                    (empty? x) (count y)
                                    (empty? y) (count x)
                                    (= x-h y-h) (lcs x-t y-t)
                                    :else (+ 1 (min (lcs x y-t) (lcs x-t y) (lcs x-t y-t)))))]
                          (lcs a b))))

(levenshtein-distance (fn ld [[xh & xt :as x]
                              [yh & yt :as y]]
                        (cond
                          (empty? x) (count y)
                          (empty? y) (count x)
                          (= xh yh) (ld xt yt)
                          :else (+ 1 (min (ld x yt) (ld xt y) (ld xt yt))))))