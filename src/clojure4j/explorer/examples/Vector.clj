(ns clojure4j.explorer.examples.Vector)
(defn fVector
  "clojure vector"
  []
  (def x (vector 1 2 3 4))
  (println x)

  (println (vector-of :int 1 2 3 4 5 6 7))
  (println (vector-of :char 97 65))
  )
(fVector)