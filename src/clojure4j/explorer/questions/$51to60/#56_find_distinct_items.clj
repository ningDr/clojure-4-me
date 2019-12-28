(ns clojure4j.explorer.questions.$51to60.#56-find-distinct-items)
(defn find-distinct-items
  "
  Write a function which removes the duplicates from a sequence. Order of the items must be maintained.
  Special Restrictions  distinct
  "
  [arg]
  (println "===================")
  (println (arg [1 2 1 3 1 2 4]))
  (println (= (arg [1 2 1 3 1 2 4]) [1 2 3 4]))
  (println (= (arg [:a :a :b :b :c :c]) [:a :b :c]))
  (println (= (arg '([2 4] [1 2] [1 3] [1 3])) '([2 4] [1 2] [1 3])))
  (println (= (arg (range 50)) (range 50)))
  )
(find-distinct-items distinct)
(find-distinct-items #(keys (group-by identity %)))
(find-distinct-items #(keys (frequencies %)))
(find-distinct-items (fn [coll] (loop [coll coll x {}, y '()]
                                  (if (empty? coll)
                                    (reverse y)
                                    (recur (rest coll) (merge x (hash-map (nth coll 0) nil)) (if (contains? x (nth coll 0))
                                                                                               y
                                                                                               (conj y (nth coll 0))
                                                                                               )
                                           )
                                    )
                                  )
                       )
                     )

(find-distinct-items #(-> (fn [[agg s] v] (if (s v)
                                            [agg s]
                                            [(conj agg v) (conj s v)]
                                            )
                            )
                          (reduce [[] #{}] %)
                          (first)
                          )
                     )
(find-distinct-items (fn [col] (reduce (fn [xs x] (if (some #(= x %) xs) xs (conj xs x))) [] col)))
(find-distinct-items (fn f ([s] (f s []))
                        ([s r]
                        (if (empty? s)
                          r
                          (if (contains? (apply hash-map (interleave r (repeat (count r) 0))) (first s))
                            (recur (rest s) r)
                            (recur (rest s) (into r ((comp vector first) s))))))))
(find-distinct-items #(loop [seen #{} result [] remaining %]
                        (if-let [value (first remaining)]
                          (if (seen value)
                            (recur seen result (rest remaining))
                            (recur (conj seen value) (conj result value) (rest remaining)))
                          result)))
(find-distinct-items #(sort-by (fn [i] (.indexOf % i)) (map first (group-by identity %))))
(find-distinct-items (comp keys (partial sort-by (comp first first last)) (partial group-by last) (partial map-indexed list)))