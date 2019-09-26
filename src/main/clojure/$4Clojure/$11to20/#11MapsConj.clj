(ns $4Clojure.$2to20.#11MapsConj)
(defn mapsConj
  ; When operating on a map, the conj function returns a new map with one or more key-value pairs "added".
  "map的conj类似于union，将多个集合合并；操作map和vector"
  [arg]
  (println (= arg (conj {:a 1} [:c 3] {:b 2})))
  )
(mapsConj {:a 1, :b 2, :c 3} )