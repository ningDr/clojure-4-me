(ns $4Clojure.$1to10.#10Maps)
(println (hash-map :a 10, :b 20, :c 30) :b)

(defn defMaps
  "map操作, clojure的map和java类似，也是键值对，定义方式不同；逗号可要可不要，加了增加可读性"
  [arg]
  (println (= ((hash-map :a 10, :b 20, :c 30) :b) arg))
  (println (= arg ({:a 10 :b 20 :c 30} :b)))
  )
(defMaps (:b {:a 10 :b 20 :c 30}))