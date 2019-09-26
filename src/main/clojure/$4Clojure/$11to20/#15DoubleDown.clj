(ns $4Clojure.$2to20.#15DoubleDown)
(defn doubleDown
  "Write a function which doubles a number."
  [arg]
  (println (= (arg 3) 6))
  )
(doubleDown (fn [x] (* x 2)))