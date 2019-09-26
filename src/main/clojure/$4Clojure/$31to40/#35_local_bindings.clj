(ns $4Clojure.$31to40.#35-local-bindings)
(defn local-bindings
  "Clojure lets you give local names to values using the special let-form.\n"
  [arg]
  (println "===============")
  (println (= arg (let [x 5] (+ 2 x))))
  (println (= arg (let [x 3, y 10] (- y x))))
  (println (= arg (let [x 21] (let [y 3] (/ x y)))))

  )

(local-bindings 7)