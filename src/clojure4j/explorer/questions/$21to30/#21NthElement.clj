(ns clojure4j.explorer.questions.$21to30.#21NthElement)
(defn nthElement
  "Write a function which returns the Nth element from a sequence."
  [arg]
  (println (arg [2 3 6 3] 2))
  (println (= (arg [2 3 6 3] 2) 6))
  )

(nthElement #(first (last (split-at %2 %1))))

(nthElement #(first (drop %2 %1)))
(nthElement (fn do [xs count] (if (= count 0) (first xs) (do (rest xs) (- count 1)))))
(nthElement #(loop [x %1 y %2] (if (zero? y) (first x) (recur (rest x) (dec y)))))
(nthElement #((vec %1) %2))
