(ns $4Clojure.$141to150.$146-seqs-maps-e-trees-into-tables)
(defn trees-into-tables
  "Because Clojure's for macro allows you to 'walk' over multiple
   sequences in a nested fashion, it is excellent for transforming
   all sorts of sequences. If you don't want a sequence as your final
   output (say you want a map), you are often still best-off using for,
   because you can produce a sequence and feed it into a map, for example.
   For this problem, your goal is to 'flatten' a map of hashmaps.
   Each key in your output map should be the 'path'1 that you would have to
   take in the original map to get to a value, so for example {1 {2 3}} should
   result in {[1 2] 3}. You only need to flatten one level of maps: if one of
   the values is a map, just leave it alone.
   1 That is, (get-in original [k1 k2]) should be the same as (get result [k1 k2])"
  [arg]
  (println "=====================")
  (println (= (arg '{a {p 1, q 2}
                     b {m 3, n 4}})
              '{[a p] 1, [a q] 2
                [b m] 3, [b n] 4}))
  (println (= (arg '{[1] {a b c d}
                     [2] {q r s t u v w x}})
              '{[[1] a] b, [[1] c] d,
                [[2] q] r, [[2] s] t,
                [[2] u] v, [[2] w] x}))
  (println (= (arg '{m {1 [a b c] 3 nil}})
              '{[m 1] [a b c], [m 3] nil})))

(trees-into-tables (fn [m] (let []
                             (loop [res {}, k1 (keys m), k11 (keys (get m (first k1)))]
                               (if (and (empty? k1) (empty? k11))
                                 res
                                 (recur (if (first k11)
                                          (conj res {[(first k1) (first k11)] (get (get m (first k1)) (first k11))})
                                          res)
                                        (if (empty? k11)
                                          (rest k1)
                                          k1)
                                        (if (empty? k11)
                                          (keys (get m (second k1)))
                                          (rest k11))))))))

;;=====================
(trees-into-tables (fn split-map
                     ([map]
                      (split-map [] {} map))
                     ([prefix acc item]
                      (if (map? item)
                        (reduce-kv #(split-map (conj prefix %2) %1 %3) acc item)
                        (assoc acc prefix item)))))

(trees-into-tables #(into {} (for [[x y] % [z w] y] [[x z] w])))

(trees-into-tables #(reduce (partial apply assoc) {}
                            (for [keyVal %
                                  value (second keyVal)]
                              (list [(first keyVal) (first value)] (second value)))))

(trees-into-tables (fn [xs] (apply merge (for [x xs, y (val x)] (hash-map [(key x) (key y)] (val y))))))

(trees-into-tables (fn [m]
                     (->>
                      (for [[k1 v1] m
                            [k2 v2] v1]
                        {[k1 k2] v2})
                      (apply merge))))

(trees-into-tables (fn [x] (into {} (for [[k v] x [vk vv] v] [[k vk] vv]))))