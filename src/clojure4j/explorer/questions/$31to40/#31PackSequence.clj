(ns clojure4j.explorer.questions.$31to40.#31PackSequence)
(defn pack-a-sequence
  "Write a function which packs consecutive duplicates into sub-lists."
  [arg]
  (println (arg [1 1 2 1 1 1 3 3]))
  (println (arg [:a :a :b :b :c]))
  (println (arg [[1 2] [1 2] [3 4]]))
  )

(pack-a-sequence #(partition-by identity %1))

(println "===================")
(pack-a-sequence #(->> %
                       (reduce (fn [[agg prev-l] n]
                                 (if (= (first prev-l) n)
                                   [agg (cons n prev-l)]
                                   [(conj agg prev-l) (list n)])) [[] ()])
                       (apply conj)
                       (drop 1)))