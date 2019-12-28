(ns clojure4j.explorer.core.partial
  [:require  clojure.pprint])
;; 用法：(partial f) (partial f arg1) (partial f arg1 arg2) (partial f arg1 arg2 & more)
;; Takes a function f and fewer than the normal arguments to f, and
;; returns a fn that takes a variable number of additional args. When
;; called, the returned function calls f with args + additional args.
;; 接受一个函数和少于等于函数f参数个数的参数，返回一个带有可变参数个数的函数，
;; 该函数可以接受外部参数，但外部参数和内部参数个数的和，应为函数f可以接受的参数个数和类型
;; 当被调用时，返回函数f，带有内部参数+外部参数的一个调用

(def add-num (partial + 100))
(println (add-num 1 99 200))
;; => 400 (100 + 1 + 99 + 200)

(def sub-num (partial - 100))
(println  (sub-num 10 20 30))
;; => 40 (100 - 10 - 20 -30)

(def hundred-times (partial * 100))
(println (hundred-times 4 5 6))
;; => 12000 (100 * 4 * 5 * 6)

(def vowel? #(some (partial = %) "aiueo"))      ;; 寻找字母是否在给定的字符串中，存在为true，不存在为nil，这是some的用法
(println (vowel? \e))
;; => true
(println (vowel? \a))
;; => true
(println (vowel? \b ))
;; => nil

(def to-english (partial clojure.pprint/cl-format nil "~@(~@[~R~]~^ ~A.~)"))
(println (to-english 1234567890))
;; => One billion, two hundred thirty-four million, five hundred sixty-seven thousand, eight hundred ninety
(println (to-english 1000))
;; => One thousand
