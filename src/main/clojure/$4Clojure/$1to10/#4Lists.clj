(ns $4Clojure.$1to10.#4Lists)
(println '(:a :b :c))
(println (list :a :b :c))
(println "===========")
(defn defnList
  "list定义的方式"
  []
  (def x '(:a :b :c))
  (def y (list :a :b :c))
  (def z (list ':a ':b ':c))
  (println x)
  (println y)
  (println z)
  (println (= x y)
           (println (= x z))))
(defnList)