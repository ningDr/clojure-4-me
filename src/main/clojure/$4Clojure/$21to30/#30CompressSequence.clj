(ns $4Clojure.$21to30.#30CompressSequence)
(defn compress-a-sequence
  "Write a function which removes consecutive duplicates from a sequence."
  [arg]
  (println (apply str (arg "Leeeeeerrroyyy")))
  (println (arg [1 1 2 3 3 2 2 3]))
  (println (arg [[1 2] [1 2] [3 4] [1 2]]))
  )

(compress-a-sequence #(loop [x %1 y []]
                        (if (empty? x)
                          y
                          (recur (rest x) (if (not= (first x) (last y))
                                            (conj y (first x))
                                            y
                                            )
                           )
                          )
                        )
 )

(compress-a-sequence #(map first (partition-by identity %)))
(compress-a-sequence (fn [x] (remove nil? (map #(if (= %1 %2) nil %1) x (cons nil x)))))
(compress-a-sequence #(reduce (fn [a b] (if (= (last a) b) a (conj a b))) [] %))