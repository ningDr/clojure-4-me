(ns clojure4j.explorer.core.update)
;; 使用方式：(update m k f) (update m k f x) (update m k f x y) (update m k f x y z) (update m k f x y z & more)
;; 'Updates' a value in an associative structure, where k is a
;; key and f is a function that will take the old value
;; and any supplied args and return the new value, and returns a new
;; structure.  If the key does not exist, nil is passed as the old value.
;; 更新嵌套结构中的值
;; k是一个键，f是一个能接受旧值和提供的若干个参数的函数，并返回一个新的数据结构
;; 如果键k不存在，nil将替代旧值参与函数f运算

(println (update {:a 1} :a inc))
;; => {:a 2}

(println (update {:a 1} :b #(if (nil? %) true false)))
;; => {:a 1, :b true}

(println (update {:a 1} :a (fn [x y z] (+ x y z)) 2 3))
;; => {:a 6}