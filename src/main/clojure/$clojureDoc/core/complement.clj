(ns $clojureDoc.core.complement)
;; Takes a fn f and returns a fn that takes the same arguments as f,
;; has the same effects, if any, and returns the opposite truth value.
;; complement接受一个f函数作为参数，并返回一个和f函数具有相同参数列表的g函数（暂且这么叫吧），
;; 若f函数返回的不为空，那么g函数返回的是f函数结果的相反结果，即f函数的结果取非运算
;; 接收一个函数，返回一个非函数，再拿参数运算
;; not是拿到参数运算，并不接受函数作为参数

;; a simple not-empty? predicate 定义一个not-empty?断言
;; (complement empty?)视为整体是一个运算符，将not-empty?定义为一个运算符
(def not-empty? (complement empty?))
;; #'user/not-empty?
(not-empty? [])
;; => false
(not-empty? [1 2])
;; => true

(map (complement even?) '(1 2 3 4))
;; => (true false true false)
(map even? '(1 2 3 4))
;; => (false true false true)

;; (map (not even?) '(1 2 3 4))

(map #(not (even? %)) '(1 2 3 4))
;; => (true false true false)