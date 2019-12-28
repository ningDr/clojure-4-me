(ns clojure4j.explorer.core.every-q)
;; 使用方式：(every? pred coll)
;; Returns true if (pred x) is logical true for every x in coll, else false.
;; 如果coll中的每一项对于断言/决策都为真，则返回逻辑真，否则返回假

(println (every? #(pos? %) [1 2 3]))
;; => true
(println (every? #(pos? %) [1 2 -3]))
;; => false

;; hash-map以key作为目标元素匹配
(println (every? {:A 1 :b 2 3 "aa"} [:A :b 3]))
;; => true

;; every?源码中对空coll进行了区分对待，如果coll为空，直接返回true，不会进行(pred x)运算
(println (every? true? '()))
;; => true
(println (every? false? '()))
;; => true
