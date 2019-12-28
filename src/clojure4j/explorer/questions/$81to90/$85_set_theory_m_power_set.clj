(ns clojure4j.explorer.questions.$81to90.$85-set-theory-m-power-set
  (:require [clojure.set])
  (:import [java.util Date]))
(defn power-set
  "Write a function which generates the power set of a given set.
   The power set of a set x is the set of all subsets of x,
   including the empty set and x itself."
  [arg]
  (println "================================================" (arg #{1 :a}))
  (println (new Date))
  (println (= (arg #{1 :a}) #{#{1 :a} #{:a} #{} #{1}}))
  (println (= (arg #{}) #{#{}}))
  (println (= (arg #{1 2 3})
              #{#{} #{1} #{2} #{3} #{1 2} #{1 3} #{2 3} #{1 2 3}}))
  (println (count (arg (into #{} (range 10)))))
  (println (= (count (arg (into #{} (range 10)))) 1024))
  (println (new Date)))

(power-set (fn [v-set]
             (letfn [(get-binary
                       ([your-num]
                        (loop [quotient (int (/ your-num 2)), bin-seq (list (mod your-num 2))]
                          (if (= 0 quotient)
                            bin-seq
                            (recur (int (/ quotient 2)) (conj bin-seq (mod quotient 2))))))
                       ([your-num expect-len one?]
                        (let [bin-seq (get-binary your-num)
                              bin-len (count bin-seq)]
                          (if (> expect-len bin-len)
                            (apply conj bin-seq (repeat (- expect-len bin-len) (if one? 1 0)))
                            (take expect-len bin-seq)))))
                     (get-pow [your-num] (loop [i your-num, res 1] (if (zero? i) res (recur (dec i) (* res 2)))))
                     (get-set [set-len set-seq one?]
                       (loop [pow-num (get-pow set-len), res-seq (get-binary pow-num set-len one?), res-set #{}]
                         (if (neg? pow-num)
                           res-set
                           (recur (dec pow-num)
                                  (get-binary pow-num set-len one?)
                                  (conj res-set (set (filter #((complement nil?) %) (mapv #(if (= 1 %1) %2) res-seq set-seq))))))))]
               (let [set-len (count v-set)
                     set-seq (seq v-set)]
                 (clojure.set/union #{#{}} #{v-set} (get-set set-len set-seq true) (get-set set-len set-seq false))))))


;; ------------------------------------------------------
(power-set (fn [ss] (->> (map (fn [v] #(->> v (conj %2) (conj %1))) ss)
                         (reduce #(reduce %2 %1 %1) #{#{}}))))

(power-set (fn [col]
             (set (reduce (fn [b x] (concat (map #(conj % x) b) b)) [#{}] col))))

(power-set
 #(loop [result #{%}, previous #{#{}}, round 0]
   (if (= round (count %))
     result
     (recur (into result previous)                          ;merge result
            (set (for [x previous, y %] (into x (list y)))) ;grow set
            (inc round)))))