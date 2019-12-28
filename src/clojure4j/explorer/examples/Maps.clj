(ns clojure4j.explorer.examples.Maps)
(defn fMaps
  "clojure maps"
  []
  (def x (hash-map "a" 1 "b" "2" "c" 1.23))
  (println x)
  (println (get x "a"))
  (println (get x "d"))
  )
(fMaps)