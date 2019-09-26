(ns $4Clojure.$21to30.#25FindTheOddNumbers)
(defn findTheOddNumbers
  "Write a function which returns only the odd numbers from a sequence.\n"
  [arg]
  (println (arg #{1 2 3 4 5}))
  (println (arg [4 2 1 6]))
  (println (arg [2 2 4 6]))
  (println (arg [1 1 1 3]))
  )
(findTheOddNumbers #(filter odd? %))
(findTheOddNumbers #(filter (fn [x] (= 1 (rem x 2))) %1))
