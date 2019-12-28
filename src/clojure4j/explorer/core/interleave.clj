(ns clojure4j.explorer.core.interleave)
;; Returns a lazy seq of the first item in each coll, then the second etc.
;; 返回一个由每一个集合的第一个元素，接着第二个元素等等组成的惰性序列（）
(println (interleave [1 2 3]))
;; => (1 2 3)
(println (interleave [1 2 3] [1 2 3]))
;; => (1 1 2 2 3 3)
(println (interleave [1 2 3] [1 2]))
;; => (1 1 2 2)
(println (interleave [1 2 3] [1 2 3] [1 2 3 4]))
;; => (1 1 1 2 2 2 3 3 3)