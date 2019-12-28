(ns clojure4j.explorer.core.mapv)
;; 使用方法：(mapv f coll) (mapv f c1 c2) (mapv f c1 c2 c3) (mapv f c1 c2 c3 & colls)
;; Returns a vector consisting of the result of applying f to the
;; set of first items of each coll, followed by applying f to the set
;; of second items in each coll, until any one of the colls is
;; exhausted.  Any remaining items in other colls are ignored. Function
;; f should accept number-of-colls arguments.
;; 返回一个由每一个集合对应的项参与函数f运算后结果组成的向量，直到某一个集合项数用尽，其它集合的其余项将被忽略
;; 函数f应该接受的参数与提供的集合个数相同

(println (mapv #(- (* 3 %1) 1) (range 1 3)))
;; => [2 5]

(println (mapv + [1 2 3] (iterate inc 1)))
;; => [2 4 6]