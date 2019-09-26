(ns $4Clojure.$2to20.#16HelloWorld)
(defn helloWorld
  "Write a function which returns a personalized greeting."
  [arg]
  (println (= (arg "Dave") "Hello, Dave!"))
  )
(helloWorld (fn [name] (str "Hello, " name "!")))