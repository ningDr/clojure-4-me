(ns clojure4j.explorer.core.map-indexed)
;; 使用方法：(map-indexed f) (map-indexed f coll)
;; Returns a lazy sequence consisting of the result of applying f to 0
;; and the first item of coll, followed by applying f to 1 and the second
;; item in coll, etc, until coll is exhausted. Thus function f should
;; accept 2 arguments, index and item. Returns a stateful transducer when
;; no collection is provided.
;; 返回一个惰性序列：由两个参数参与f运算的结果：一个是序列的索引，一个是序列的值，直到序列的最后一项
;; 函数f接受两个参数：索引和序列的项
;; 如果没有集合参数，就返回一个与状态转换器

(println (map-indexed + '(0 1 2 3 4 5)))
;; => (0 2 4 6 8 10)

(println (map-indexed str '(0 1 2 3 4 5)))
;; => (00 11 22 33 44 55)

(println (map-indexed str '("a" "b" "c" "d" "e" "f")))
;; => (0a 1b 2c 3d 4e 5f)