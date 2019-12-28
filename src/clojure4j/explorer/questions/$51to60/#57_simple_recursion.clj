(ns clojure4j.explorer.questions.$51to60.#57-simple-recursion)
(defn simple-recursion
  "
   A recursive function is a function which calls itself.
   This is one of the fundamental techniques used in functional programming.
  "
  [arg]
  (println "===================")
  (println (= arg ((fn foo [x] (when (> x 0) (conj (foo (dec x)) x))) 5)))
  )
(simple-recursion [5 4 3 2 1])