(ns $clojureDoc.core.drop-while)
;; 使用方式：(drop-while pred) (drop-while pred coll)
;; Returns a lazy sequence of the items in coll starting from the
;; first item for which (pred item) returns logical false.  Returns a
;; stateful transducer when no collection is provided.
;; 返回集合中使第一个断言/决策为假的元素及之后的元素
(println (drop-while #(< % 8) [9 1 2 10]))
;; => (9 1 2 10)
(println (drop-while #(< % 8) [9 1 2 10]))

