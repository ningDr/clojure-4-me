(ns clojure4j.explorer.core.assoc-in)
;; 使用方式：(assoc-in m [k & ks] v)
;; Associates a value in a nested associative structure, where ks is a
;; sequence of keys and v is the new value and returns a new nested structure.
;; If any levels do not exist, hash-maps will be created.
;;

;; 替换map中键为:a值中键为:b的值中键为:c的值，一层一层深入
(println (assoc-in {:a {:b {:c 1}}} [:a :b :c] 3))
;; => {:a {:b {:c 3}}}

;; 替换索引为1的map中键为:b的值
(println (assoc-in [{:a 1} {:b 2} {:c 3}] [1 :b] 4))
;; => [{:a 1} {:b 4} {:c 3}]

;; 替换索引为3的元素([4 5 6 [7 8 9]])下索引为3的元素([7 8 9])下索引为2的元素([9])的值
(println (assoc-in [1 2 3 [4 5 6 [7 8 9]]] [3 3 2] 0))
;; => [1 2 3 [4 5 6 [7 8 0]]]

;; 索引4的元素不存在，可以增加
(println (assoc-in [1 2 3 4] [4] 5))
;; => [1 2 3 4 5]
;; 但不能“过分了”
;; (println (assoc-in [1 2 3 4] [5] 6))
;; java.lang.IndexOutOfBoundsException
