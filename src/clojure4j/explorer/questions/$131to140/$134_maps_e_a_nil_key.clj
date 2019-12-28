(ns clojure4j.explorer.questions.$131to140.$134-maps-e-a-nil-key)
(defn a-nil-key
  "Write a function which, given a key and map,
   returns true iff the map contains an entry with that key
   and its value is nil"
  [arg]
  (println "=====================")
  (println (true?  (arg :a {:a nil :b 2})))
  (println (false? (arg :b {:a nil :b 2})))
  (println (false? (arg :c {:a nil :b 2}))))

(a-nil-key (fn [k m]
             (and (contains? m k)
                  (nil? (get m k)))))

;; =========
(a-nil-key #(not (%2 %1 true)))