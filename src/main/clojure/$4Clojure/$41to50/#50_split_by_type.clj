(ns $4Clojure.$41to50.#50-split-by-type)
(defn split-by-type
  "Write a function which takes a sequence consisting of items with different types
   and splits them up into a set of homogeneous sub-sequences.
   The internal order of each sub-sequence should be maintained,
   but the sub-sequences themselves can be returned in any order
   (this is why 'set' is used in the test cases)."
  [arg]
  (println "=================")
  (println (arg [1 :a 2 :b 3 :c]))
  (println (= (set (arg [1 :a 2 :b 3 :c])) #{[1 2 3] [:a :b :c]}))
  (println (= (set (arg [:a "foo" "bar" :b])) #{[:a :b] ["foo" "bar"]}))
  (println (= (set (arg [[1 2] :a [3 4] 5 6 :b])) #{[[1 2] [3 4]] [:a :b] [5 6]}))
  )
;(split-by-type #(partition-by  %1))
(println (keyword? :a))
(println (filter #(keyword? (type %1)) [1 :a 2 :b 3 :c]))
(split-by-type #(loop [coll %1]
                  (filter not-empty (concat
                                      (vector (filter keyword? coll))
                                      (vector (filter string? coll))
                                      (vector (filter number? coll))
                                      (vector (filter vector? coll))
                                      )
                          )
                  )
               )

(split-by-type #(->> % (group-by type) (vals)))
(split-by-type (comp vals (partial group-by type)))
(split-by-type #(map reverse (vals (reduce (fn [m v] (update-in m [(class v)] (fn [x] (conj x v)))
                                             ) {} %)
                                   )
                     )
               )