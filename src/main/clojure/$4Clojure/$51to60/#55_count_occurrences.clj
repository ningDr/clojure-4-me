(ns $4Clojure.$51to60.#55-count-occurrences)
(defn count-occurrences
  "Write a function which returns a map containing the number of occurences of each distinct item in a sequence."
  [arg]
  (println "==================")
  (println (arg [1 1 2 3 2 1 1]))
  (println (= (arg [1 1 2 3 2 1 1]) {1 4, 2 2, 3 1}))
  (println (= (arg [:b :a :b :a :b]) {:a 2, :b 3}))
  (println (= (arg '([1 2] [1 3] [1 3])) {[1 2] 1, [1 3] 2}))
  )
(count-occurrences (fn [coll] (loop [map (group-by identity coll), keys (keys map), res {}]
                                (if (empty? keys)
                                  res
                                  (recur map (rest keys) (merge res (hash-map (nth keys 0) (count (get map (first keys))))))
                                  )
                                )
                     )
                   )

(count-occurrences (fn [col] (reduce #(update-in %1 [%2] (fn [v] (inc (or v 0)))) {} col)))
(count-occurrences #(let [x (group-by identity (sort %))] (apply hash-map (interleave (keys x) (map count (vals x))))))
(count-occurrences #(reduce (fn [m v] (update-in m [v] (fn [x] (if (nil? x)
                                                                 1
                                                                 (inc x)
                                                                 )
                                                         )
                                                 )
                              ) {} %)
                   )
(count-occurrences (fn [xs] (apply hash-map (apply concat (map #(list % (count (filter #{%} xs))) (set xs))))))
(count-occurrences #(let [instances (group-by identity %)]
                      (reduce (fn [acc v] (assoc acc v (-> (instances v) count)))
                              {}
                              (keys instances))))
(count-occurrences (comp #(zipmap (keys %) (map count (vals %))) (partial group-by identity)))