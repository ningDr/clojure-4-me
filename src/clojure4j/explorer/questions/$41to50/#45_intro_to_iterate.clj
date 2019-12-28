(ns clojure4j.explorer.questions.$41to50.#45-intro-to-iterate)
(defn intro-to-iterate
  "The iterate function can be used to produce an infinite lazy sequence."
  [arg]
  (println "======================")
  (println (= arg (take 5 (iterate #(+ 3 %) 1))))
  )
(intro-to-iterate '(1 4 7 10 13))