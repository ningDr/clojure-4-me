(ns $clojureDoc.core.frequencies)
;; 使用方法：(frequencies coll)
;; Returns a map from distinct items in coll to the number of times they appear.
;; 返回一个map：集合中每一个唯一项作为key，出现的次数作为value

(println (frequencies [1 1 1 2 2 3]))
;; => {1 3, 2 2, 3 1}

;; 反转frequencies的操作
;; Turn a frequency map back into a coll.
(println (mapcat (fn [[x n]] (repeat n x)) {:a 2 :b 1 :c 3}))
;;=> (:a :a :b :c :c :c)