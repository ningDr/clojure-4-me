(ns clojure4j.explorer.questions.$31to40.#33ReplicateSequence)
(defn replicate-a-sequence
  "Write a function which replicates each element of a sequence a variable number of times.\n"
  [arg]
  (println "==========")
  (println (arg [1 2 3] 2))
  (println (arg [:a :b] 4))
  (println (arg [4 5 6] 1))
  (println (arg [[1 2] [3 4]] 2))
  (println (arg [44 33] 2))
  )
(replicate-a-sequence #(loop [x %1 y %2 res [] count %2] (if (empty? x)
                                                  (mapcat identity res)
                                                  (recur (rest x) (dec y) (conj res (repeat count (first x))) count)
                                           )))

(replicate-a-sequence (fn [xs n] (reduce concat (map #(repeat n %) xs))))
(replicate-a-sequence #(mapcat (fn [x] (repeat %2 x)) %))
(replicate-a-sequence #(if(= %2 1)
                         %1
                         (apply interleave (take %2 (repeat %1)))))
(replicate-a-sequence (fn [s n] (if (= n 1)
                                  s
                                  (apply interleave (repeat n s)))))
(replicate-a-sequence (fn [x t] (mapcat (partial repeat t) x)))