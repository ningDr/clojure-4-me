(ns clojure4j.explorer.questions.$31to40.#32DuplicateSequence)
(defn duplicate-a-sequence
  "Write a function which duplicates each element of a sequence.\n"
  [arg]
  (println (arg [1 2 3]))
  (println (arg [:a :a :b :b]))
  (println (arg [[1 2] [3 4]]))
  )

(duplicate-a-sequence #(loop [x %1 y '()]
                         (if (empty? x)
                           (reverse y)
                           (recur (rest x) (conj y (first x) (first x)))
                           )
                         ))
(println "==================")
(duplicate-a-sequence #(reduce (fn [xs x] (concat xs (cons x (cons x ())))) '() %))
(duplicate-a-sequence #(interleave % %))
(duplicate-a-sequence #(sort (into % %)))
(duplicate-a-sequence #(reduce (fn [x y] (conj x y y)) [] %1))
(duplicate-a-sequence (fn [s] (loop [s s r '()]
                                 (if (seq s)
                                   (recur (rest s) (concat r [(first s)
                                                              (first s)]))

                                   r)
                                 )
                         ))
(duplicate-a-sequence #(mapcat (fn [x] (list x x)) %1))