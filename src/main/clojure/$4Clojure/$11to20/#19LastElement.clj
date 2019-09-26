(ns $4Clojure.$2to20.#19LastElement)
(defn lastElement
  "Write a function which returns the last element in a sequence."
  [arg]
  (println (= (last [1 2 3 4 5]) 5))
  (println (= (#(first (reverse %)) [1 2 3 4 5]) 5))
  (println (= ((comp first reverse) [1 2 3 4 5]) 5))
  (println (= (#(nth % (dec (count %))) [1 2 3 4 5]) 5))
  ;(fn dosia [xs] (if (empty? (rest xs)) (first xs) (dosia (rest xs))))
 )
(lastElement 1)