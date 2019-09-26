(ns $clojureDoc.core.partition-all)
;; 使用方法：(partition-all n) (partition-all n coll) (partition-all n step coll)
;; Returns a lazy sequence of lists like partition, but may include
;; partitions with fewer than n items at the end.
;; Returns a stateful transducer when no collection is provided.
;; 返回一个类似partition函数的惰性序列，但是最后的分组有可能比每组数量n小
;; 当没有集合作为参数时，返回一个有状态的转换器

;; 来看看partition和partition-all的区别
(println (partition 4 '(0 1 2 3 4 5 6 7 8 9)))
;; => ((0 1 2 3) (4 5 6 7))
(println (partition-all 4 '(0 1 2 3 4 5 6 7 8 9)))
;; => ((0 1 2 3) (4 5 6 7) (8 9))

;; 提供step参数时，下一个分组的第一项的起始索引值相对于当前索引值的步进
(println (partition-all 2 3 '(0 1 2 3 4 5 6 7 8 9)))
;; => ((0 1) (3 4) (6 7) (9))
(println (partition-all 4 3 '(0 1 2 3 4 5 6 7 8 9)))
;; => ((0 1 2 3) (3 4 5 6) (6 7 8 9) (9))
;; 当n与step相等，相当于无step参数
(println (partition-all 3 3 '(0 1 2 3 4 5 6 7 8 9)))
;; => ((0 1 2) (3 4 5) (6 7 8) (9))
