(ns clojure4j.explorer.questions.$61to70.#69-core-m-merge-with-a-function)
(defn merge-with-a-function
  "
    Write a function which takes a function f and a variable number of maps.
    Your function should return a map that consists of the rest of the maps conj-ed onto the first.
    If a key occurs in more than one map,
    the mapping(s) from the latter (left-to-right) should be combined with the mapping in the result by calling (f val-in-result val-in-latter).
  "
  [arg]
  (println "================")
  (println (arg * {:a 2, :b 3, :c 4} {:a 2} {:b 2} {:c 5}))
  (println (= (arg * {:a 2, :b 3, :c 4} {:a 2} {:b 2} {:c 5}) {:a 4, :b 6, :c 20}))
  (println "--------------")
  (println (arg - {1 10, 2 20} {1 3, 2 10, 3 15}))
  (println (= (arg - {1 10, 2 20} {1 3, 2 10, 3 15}) {1 7, 2 10, 3 15}))
  (println "--------------")
  (println (= (arg concat {:a [3], :b [6]} {:a [4 5], :c [8 9]} {:b [7]}) {:a [3 4 5], :b [6 7], :c [8 9]}))
  )

(merge-with-a-function merge-with)

(println (apply + (filter (complement nil?) (map #(get % :a) [{:a 2, :b 3, :c 4} {:a 2} {:b 2} {:c 5}]))))

(merge-with-a-function (fn [f & maps] (loop [keys (distinct (flatten (map keys maps))), v-map {}]
                                        (if (empty? keys)
                                          v-map
                                          (recur (rest keys)
                                                 (merge v-map
                                                        (hash-map (first keys)
                                                                  (let [v (apply f
                                                                                 (filter (complement nil?)
                                                                                         (map #(get % (first keys)) maps)
                                                                                         )
                                                                                 )]
                                                                    (if (and (number? v) (neg? v))
                                                                      (- v)
                                                                      v
                                                                      )
                                                                    )
                                                                  )
                                                        )
                                                 )
                                          )
                                        )
                         )
                       )

(merge-with-a-function (fn [op & maps] (let [upd-val #(fn [v] (if (nil? v) % (op v %))), upd #(update-in %1 [%2] (upd-val %3)), join #(reduce-kv upd %1 %2)]
                                         (reduce join {} maps)
                                         )
                         )
                       )

(merge-with-a-function #(reduce (fn [m1 m2] (reduce (fn [m [k v]] (if (m k)
                                                                    (assoc m k (% (get m k) v))
                                                                    (assoc m k v))) m1 m2)) %&))

(merge-with-a-function (fn [f & s] (apply hash-map (interleave (keys (apply conj (seq s)))
                                                               (map #(reduce f %)
                                                                    (map #(filter (comp not nil?) %)
                                                                         (map #(map (fn [m] (get m %)) (seq s))
                                                                              (keys (apply conj (seq s))))))))))

(merge-with-a-function (fn myMergeWith ([f m1 m2]
                                        (loop [result m1, remaining m2]
                                          (if-let [[k v] (first remaining)]
                                            (if (contains? result k)
                                              (recur (assoc result k (f (result k) v)) (rest remaining))
                                              (recur (assoc result k v) (rest remaining)))
                                            result)))
                         ([f m1 m2 & ms]
                          (apply myMergeWith f (myMergeWith f m1 m2) (seq ms)))))

(merge-with-a-function (fn [f & x] ((comp #(zipmap (keys %) (map (comp (partial reduce f) (partial map last)) (vals %))) (partial group-by first) (partial apply concat)) x)))

(merge-with-a-function (fn [op & maps] (reduce
                                         (fn [m1 m2]
                                           (let [keys1 (set (keys m1))
                                                 keys2 (set (keys m2))
                                                 shared (clojure.set/intersection keys1 keys2)
                                                 unchanged-part (conj
                                                                  (select-keys m1 keys1)
                                                                  (select-keys m2 keys2))
                                                 ]
                                             (reduce (fn [m k]
                                                       (assoc m k (op (m1 k) (m2 k))))
                                                     unchanged-part
                                                     shared)))
                                         maps)))