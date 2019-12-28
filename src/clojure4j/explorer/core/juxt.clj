(ns clojure4j.explorer.core.juxt)
;; 使用方法：(juxt f) (juxt f g) (juxt f g h) (juxt f g h & fs)
;; Takes a set of functions and returns a fn that is the juxtaposition
;; of those fns.  The returned fn takes a variable number of args, and
;; returns a vector containing the result of applying each fn to the
;; args (left-to-right).
;; ((juxt a b c) x) => [(a x) (b x) (c x)]
;; 接受多个函数作为参数，这些参数是并列的（地位相同，只分计算的先后顺序）
;; 每个函数接受的参数个数应该都与提供的参数个数相同
;; 返回的是一个由参数参与各个函数运算后的结果组成的vector

;;所有提供的参数参与juxt的每一个函数的运算，将结果组成一个vector

;; 把参数1放进去运算：inc 1和dec 1两个结果组合成一个vector
(println ((juxt inc dec) 1))
;; => [2 0]

;;
(println ((juxt + * min max) 3 4 6))
;; => [13 72 3 6]
;; (+ 3 4 6)
;; (* 3 4 6)
;; (min 3 4 6)
;; (max 3 4 6)

;; take和drop都接受两个参数：n coll
;; take获取coll的前n项，其余丢掉
;; drop让coll丢掉前n项，获取剩下的
(println ((juxt take drop) 2 [0 1 2 3 4 5 6]))
;; => [(0 1) (2 3 4 5 6)]

;; Extract values from a map, treating keywords as functions.
;; 从map中提取值，键在此视为函数的作用
(println ((juxt :a :b) {:a 1 :b 2 :c 3 :d 4}))
;;=> [1 2]

;; 根据运算结果排序原序列
(println (sort-by (juxt :a :b) [{:a 1 :b 3} {:a 1 :b 2} {:a 2 :b 1}]))
;; => ({:a 1, :b 2} {:a 1, :b 3} {:a 2, :b 1})
(println ((juxt :a :b) {:a 1 :b 3}))
;; => [1 3]