(ns clojure4j.explorer.examples.main
  (:gen-class))

(defn hello-world [a b]
  ;(def x (atom 1))
  ;(println (swap! x inc))
  (println "Hello World")
  (println (+ a b))
  (println (+ 1 3)))
(hello-world 1 2)

(defn demo [message & other]
  (println (str message (clojure.string/join " " other)))
  )
(demo "Hello" " Clojure" "Programming")