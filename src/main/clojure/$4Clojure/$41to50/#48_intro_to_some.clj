(ns $4Clojure.$41to50.#48-intro-to-some)
(defn intro-to-some
  "The some function takes a predicate function and a collection.
   It returns the first logical true value of (predicate x) where x is an item in the collection."
  [arg]
  (println "============")
  (println (= arg (some #{2 7 6} [5 6 7 8])))
  (println (= arg (some #(when (even? %) %) [5 6 7 8])))
  )

(intro-to-some 6)