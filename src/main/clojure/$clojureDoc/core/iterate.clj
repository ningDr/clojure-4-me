(ns $clojureDoc.core.iterate)
;; 使用方法 (iterate f x)
;; Returns a lazy sequence of x, (f x), (f (f x)) etc. f must be free of side-effects
;; 返回一个惰性序列：第一项是x，第二项是(f x)的结果，第三项是(f (f x))的结果，即每一次的计算结果参与函数f运算后得到下一项
;; 无副作用：不会读写数据库、文件、socket、以及全局变量等。

(println (take 10 (iterate inc 1)))
;; => (1 2 3 4 5 6 7 8 9 10)

(println (take 10 (iterate (partial + 2) 2)))
;; => (2 4 6 8 10 12 14 16 18 20)

;; nth是从零开始计数，获取元素的本身类型；take按1开始计数，返回和参数类型一样的类型
(println (take 1 '(1 2 3)) (nth '(1 2 3) 1))
;; => (1) 2

;; 斐波那契数列
(def fib (map first (iterate (fn [[a b]] [b (+' a b)]) [0 1])))
;; (iterate (fn [[a b]] [b (+' a b)]) [0 1])
;; 第一项：[0 1]
;; 第二项：[1 1]
;; 第三项：[1 2]
;; 第四项：[2 3]
;; 第五项：[3 5]
;; 第六项：[5 8]
;; map first获得的参数就是 [0 1] [1 1] [1 2] [2 3] [3 5] [5 8] ...
;; map的使用方法是拿每个集合对应项参与函数的运算，故取后面每个集合的第一项：0 1 1 2 3 5 ...
;; 由于iter返回惰性序列，因此take指定前10个，计算10次即可
(println (take 10 fib))
;; => (0 1 1 2 3 5 8 13 21 34)