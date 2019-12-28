(ns clojure4j.explorer.questions.$1to10.#6Vectors)
(println (vec '(:a :b :c)))
(println (vector :a :b :c))
(defn defnVectors
  "vec接收一个单独的参数，可能是任何Clojure或Java的数据类型，然后将其元素的值作为参数组成一个新的向量 "
  []

  (println (vec '(:a :b :c)))
  (println (+ 1 1 1 1 1 1 1 1))
  (println (= 1 1 1 1 1 1 1 2))
  ;; 定义向量(Vector)的几种方式
  (println (= [:a :b :c] (list :a :b :c) (vec '(:a :b :c)) (vector :a :b :c)))
  )
(defnVectors)