(ns $clojureDoc.core.reduce-kv)
;; 使用方式：(reduce-kv f init coll)
;; Reduces an associative collection. f should be a function of 3
;; arguments. Returns the result of applying f to init, the first key
;; and the first value in coll, then applying f to that result and the
;; 2nd key and value, etc. If coll contains no entries, returns init
;; and f is not called. Note that reduce-kv is supported on vectors,
;; where the keys will be the ordinals.
;; 生成一个嵌套的集合
;; f是一个三参数函数
;; 返回一个由init、coll的第一个kev-val键值对参与函数f运算的结果
;; 然后是计算结果与第二个key-val参与函数f运算的结果，依次类推
;; 如果集合coll为空，那么直接返回init，函数f不会被调用
;; reduce-kv可以运用到vector上，此时的key就是索引

(println (reduce-kv + 0 [1 2 3 4 5]))
;; => 25
(println (reduce-kv str 0 [1 2 3 4 5]))
;; => 00112233445

(println (reduce-kv str "init" {:a 1 :b 2}))
;; => init:a1:b2

(println (reduce-kv (fn [init k v] (println "----") (str init k v)) "init" {:a 1}))
;; => ----
;;    init:a1
(println (reduce-kv (fn [init k v] (println "----") (str init k v)) "init" {}))
;; => init