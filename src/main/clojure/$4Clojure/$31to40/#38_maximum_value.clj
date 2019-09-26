(ns $4Clojure.$31to40.#38-maximum-value)
(defn maximum-value
  "Write a function which takes a variable number of parameters and returns the maximum value.\n"
  [arg]
  (println "============")
  (println (arg 1 8 3 4))
  (println (arg 30 20))
  (println (arg 45 67 11))
  )

(maximum-value (fn [& args] (loop [seq (seq args), i (- (count seq) 1), maximum (nth seq 0)]
                              (if (zero? i)
                                maximum
                                ;; seq每次会变，故每次取seq第一项即可
                                (recur (rest seq) (dec i) (if (> maximum (nth seq 0))
                                                            maximum
                                                            (nth seq 0)
                                                            )
                                       )
                                )
                              )
                 )
               )

(maximum-value (fn [& params] (reduce (fn [a b] (if (< a b) b a)) 0 params)))
(maximum-value #(last (sort %&)))
(maximum-value (fn [x & y]
                 (cond
                   (nil? y) x
                   (> x (first y)) (recur x (next y))
                   :else (recur (first y) (next y)))))
(maximum-value (comp - (partial apply min) (partial map -) list))