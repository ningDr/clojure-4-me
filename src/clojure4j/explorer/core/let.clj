(ns clojure4j.explorer.core.let)
;; 使用示例：(let [bindings*] exprs*)
;; let接受一个键值对的向量作为参数，向量可以有多个键值对，个数不限(let takes a vector of symbol value pairs followed by a variable number of expressions.)
;; 一个向量包含3个键值对
(let [x 1 y 2 z 3]
  (println x y z))
;; 一个向量包含6个键值对
(let [a 1 b 2 c 3 x 6 y 7 z 8]
  (println a b c)
  (println x y z)
  ;; let创建的绑定，"变量"也是不可变的
  (println (inc x))
  (println x))
;; let嵌套
(let [x 1 y 2]
  (println x y)
  (let [x 3 y 4]
    (println x y))
  (println x y))