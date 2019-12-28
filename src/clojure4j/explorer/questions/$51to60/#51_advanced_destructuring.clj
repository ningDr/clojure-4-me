(ns clojure4j.explorer.questions.$51to60.#51-advanced-destructuring)
(defn advanced-destructuring
  "Here is an example of some more sophisticated destructuring."
  [arg]
  (println "=================")
  (println (let [[a b & c :as d] arg] [a b c d]))
  (println (= [1 2 [3 4 5] [1 2 3 4 5]] (let [[a b & c :as d] arg] [a b c d])))
  )

(advanced-destructuring [1 2 3 4 5])