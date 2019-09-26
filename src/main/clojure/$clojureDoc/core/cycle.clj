(ns $clojureDoc.core.cycle)
;; 使用方法：(cycle coll)
;; Returns a lazy (infinite!) sequence of repetitions of the items in coll.
;; 返回一个惰性序列：无限重复原始序列

;; 无限重复(1 2 3 1 2 3 1 2 3 1 2 3 1 2 3 1 ...)，只曲前10个，所以只需计算到10
(println (take 10 (cycle '(1 2 3))))
;; => (1 2 3 1 2 3 1 2 3 1)