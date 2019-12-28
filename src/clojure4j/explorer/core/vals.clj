(ns clojure4j.explorer.core.vals)
;; 使用方法：(vals map)
;; Returns a sequence of the map's values, in the same order as (seq map).
;; 返回一个由map的值组成的序列，序列的顺序和(seq map)的顺序一致

(println (vals {true [1 2 3 4], false [:a :b :c]}))
;; => ([1 2 3 4] [:a :b :c])

(println (vals {1 #{1 2 3}, 2 #{4 5 6}, 3 #{9 8 7}}))
;; => (#{1 3 2} #{4 6 5} #{7 9 8})