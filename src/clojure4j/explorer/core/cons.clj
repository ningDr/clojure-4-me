(ns clojure4j.explorer.core.cons)
;; (cons x seq)
;; Returns a new seq where x is the first element and seq is the rest.
;; 把x作为元素放在序列seq的第一位并返回一个新序列
(println (cons 1 [2 3 4]))
;; => (1 2 3 4)
(println (cons [0 1] [2 3 4]))
;; => ([0 1] 2 3 4)
(println (cons 1 '(2 3 4)))
;; => (1 2 3 4)
;; map
(println (cons {:a 1} {:b 2 :c 3}))
;; => ({:a 1} [:b 2] [:c 3])
;; 向量
(println (cons #{:a 1} #{:b 2 :c 3}))
;; => (#{1 :a} :c 3 2 :b)
