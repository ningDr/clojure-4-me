(ns $4Clojure.$61to70.#61-map-construction)
(defn map-construction
  "Write a function which takes a vector of keys and a vector of values and constructs a map from them."
  [arg]
  (println "====================")
  (println (arg [:a :b :c] [1 2 3]))
  (println (= (arg [:a :b :c] [1 2 3]) {:a 1, :b 2, :c 3}))
  (println (= (arg [1 2 3 4] ["one" "two" "three"]) {1 "one", 2 "two", 3 "three"}))
  (println (= (arg [:foo :bar] ["foo" "bar" "baz"]) {:foo "foo", :bar "bar"}))
  )
(map-construction zipmap)
(map-construction #(apply merge {} (map hash-map %1 %2)))


(map-construction #(apply array-map (interleave %1 %2)))
(map-construction #(apply hash-map (interleave % %2)))
(map-construction (fn [ks vs]
                    (reduce merge (map (fn [k v] {k v}) ks vs))
                    )
                  )
(map-construction (comp (partial apply sorted-map) interleave))