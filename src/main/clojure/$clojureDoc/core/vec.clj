(ns $clojureDoc.core.vec)
;; 使用方式：
;; Creates a new vector containing the contents of coll. Java arrays
;; will be aliased and should not be modified.
;; 创建一个包含coll元素的新向量
;; java数组将被重命名且不应该被修改，因为clojure操作的是不可变数据，若java数组改变，那么vec创建的新的vector将会跟着改变

(println (vec {:a 1 :b 2 :c 3}))
;; => [[:a 1] [:b 2] [:c 3]]
(println (vector {:a 1 :b 2 :c 3}))
;; => [{:a 1, :b 2, :c 3}]

(println (vec [1 2 3]))
;; => [1 2 3]
(println (vector [1 2 3]))
;; => [[1 2 3]]

(def java-array (to-array (list 1 2 3 4)))
(def v-vec (vec java-array))
(println v-vec)
;; => [1 2 3 4]
(aset java-array 0 5)             ;; aset应用于java等数组这样的引用类型，对clojure类型不适用：把指定索引处的值修改掉
(println v-vec)
;; => [5 2 3 4]

(def a (vec (aclone java-array))) ;; aclone应用于java数组，返回一个java数组的克隆版本，返回的不可变
(println a)
;; => [5 2 3 4]
(aset java-array 0 10)
;; 现在的a是不可变类型了
(println a "|" v-vec)
;; => [5 2 3 4] | [10 2 3 4]
