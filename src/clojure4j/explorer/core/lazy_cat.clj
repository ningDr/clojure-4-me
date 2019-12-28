(ns clojure4j.explorer.core.lazy-cat)
;; 使用方法：(lazy-cat & colls)
;; Expands to code which yields a lazy sequence of the concatenation
;; of the supplied colls.  Each coll expr is not evaluated until it is needed.
;;  (lazy-cat xs ys zs) === (concat (lazy-seq xs) (lazy-seq ys) (lazy-seq zs))
;; 返回多个惰性序列连接后的惰性序列

(println (take 10 (range 10)))
;; => (0 1 2 3 4 5 6 7 8 9)
(println (take 10 (lazy-cat (range 6) (range 6 10))))
;; => (0 1 2 3 4 5 6 7 8 9)

;; fib-seq为：[1 1] .....[x ...]
(def fib-seq (lazy-cat [1 1] (map + (rest fib-seq) fib-seq)))
;; 第一次，拿前1个，返回1
(println (take 1 fib-seq))
;; => (1)
;; 第二次，拿前2个返回1 1
(println (take 2 fib-seq))
;; => (1 1)
;; 第三次，拿前3个，那么map需要计算一次
;; 此时fib-seq为[1 1]
;; 那么map表达式为：(map + [1] [1 1]) => [2]
;; 那么fib-seq就为：(lazy-seq [1 1] [2])
(println (take 3 fib-seq))
;; => (1 1 2)
;; 第4次，需要获取前4个
;; 从第三个知道，fib-seq为(1 1 2)
;; 那么map表达式为：(map + [2] [1 1 2]) => [3]
;; 那么返回的fib-seq的值就为：(lazy-seq [1 1] [2] [3])
(println (take 4 fib-seq))
;; => (1 1 2 3)
;; 依次类推
(println (take 10 fib-seq))
;; => (1 1 2 3 5 8 13 21 34 55)
