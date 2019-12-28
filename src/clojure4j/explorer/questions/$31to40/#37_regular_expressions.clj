(ns clojure4j.explorer.questions.$31to40.#37-regular-expressions)
(defn regular-expressions
  "Regex patterns are supported with a special reader macro.\n"
  [arg]
  (println (= arg (apply str (re-seq #"[A-Z]+" "bA1B3Ce "))))
  )
(regular-expressions "ABC")