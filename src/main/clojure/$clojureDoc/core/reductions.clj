(ns $clojureDoc.core.reductions)
;; 使用方法：(reductions f coll)(reductions f init coll)
;; Returns a lazy seq of the intermediate values of the reduction (as per reduce) of coll by f, starting with init.
;; 返回一个惰性序列：由init开始，若init不存在，由coll的第一项担任，集合每项参与函数f运算结果组成的序列

(println (reductions + [1 2 3 4]))
;; => (1 3 6 10)
;; 第一项：+ 1
;; 第二项：+ 1 2
;; 第三项：+ 1 2 3
;; 第四项：+ 1 2 3 4

(println (reductions + 1 [2 3 4]))
;; => (1 3 6 10)

(println (reductions conj [4] '(1 2 3)))
;; => ([4] [4 1] [4 1 2] [4 1 2 3])

(println (conj [1] '(2 3)))
;; => [1 (2 3)]
