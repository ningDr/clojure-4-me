(ns $4Clojure.$1to10.#7VectorsConj)
(defn vectorsConj
  "vector conj : 与list的conj不同，vector的conj是直接将参数加到向量后"
  [x]
  (println (= x (conj (vector 1 2 3) 4)))
  (println (= x (conj (vector 1 2) 3 4)))
  )
(vectorsConj [1 2 3 4])