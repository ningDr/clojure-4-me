(ns $4Clojure.$2to20.#20PenultimateElement)
(defn penultimateElement
  "Write a function which returns the second to last element from a sequence."
  [arg]
  (println (first (pop (reverse (list 1 2 3)))))
  (println (= (arg (list 1 2 3 4 5)) 4))
  (println (= (arg ["a" "b" "c"]) "b"))
  (println (= (arg [[1 2] [3 4]]) [1 2]))
  )
(penultimateElement #(first (pop (reverse %))))

(penultimateElement #(second (reverse %)))
(penultimateElement (comp second reverse))
(penultimateElement (comp first rest reverse))
(penultimateElement (comp first next reverse))