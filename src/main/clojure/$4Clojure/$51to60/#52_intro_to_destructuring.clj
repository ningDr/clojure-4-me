(ns $4Clojure.$51to60.#52-intro-to-destructuring)
(defn intro-to-destructuring
  "Let bindings and function parameter lists support destructuring."
  []
  (println (= [2 4] (let [[a b c d e] [0 1 2 3 4]] [c e])))
  ;(println (= [2 4] (let [[a b c d e] [0 1 2 3 4]] __)))
  )

(intro-to-destructuring)