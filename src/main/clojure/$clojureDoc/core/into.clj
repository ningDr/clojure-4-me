(ns $clojureDoc.core.into)
;; http://clojuredocs.org/clojure.core/into
;; 使用示例 (into) (into to) (into to from) (into to xform from)

;; Returns a new coll consisting of to-coll with all of the items of
;; from-coll conjoined. A transducer may be supplied.
;; 返回一个由to-coll集合和from-coll集合的每一项组合后的新集合，需要提供一个转换器
;; into接收一个参数时，原样返回。
(println (into []))
;; => []
(println (into [1 2 3 4]))
;; => [1 2 3 4]
;; into接收两个参数时，合并两个集合。
(println (into [1 2 3] [4 5 6 7]))
;; => [1 2 3 4 5 6 7]
;; into接收三个参数时，第一个是集合，第二个是转化器，第三个是集合，第三个集合经过转换器后输出的集合与第一个集合合并
(println (into [1 2 3] (comp (filter odd?) (take 10)) (range 30)))
;; => [1 2 3 1 3 5 7 9 11 13 15 17 19]

(println "-------")

;; Maps can be constructed from a sequence of 2-vectors or a sequence of maps
;; maps集合可以由含有2个元素的向量的序列或者maps序列构造得到
(println (into (sorted-map) [[:a 1] [:c 3] [:b 2]]))
;; => {:a 1, :b 2, :c 3}
(println (into (sorted-map) [[7 2] [5 6]]))
;; => {5 6, 7 2}

;; When maps are the input source, they convert into an unordered sequence of key-value pairs, encoded as 2-vectors
;; 当into的输出参数为maps，输出参数是sequence时，maps将会转换为每个sequence元素为一个键值对
(println (into [] {1 2 3 4 5 nil}))
;; => [[1 2] [3 4] [5 nil]]
(println (into (vector []) {1 2 3 4 5 nil}))
;; => [[] [1 2] [3 4] [5 nil]]
(println (into '() {1 2 3 4 5 nil}))
;; => ([5 nil] [3 4] [1 2])
(println (into (list ()) {1 2 3 4 5 nil}))
;; => ([5 nil] [3 4] [1 2] ())