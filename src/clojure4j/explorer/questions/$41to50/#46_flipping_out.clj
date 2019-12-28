(ns clojure4j.explorer.questions.$41to50.#46-flipping-out)
(defn flipping-out
  "Write a higher-order function which flips the order of the arguments of an input function."
  [arg]
  (println "========================")
  (println ((arg nth) 2 [1 2 3 4 5]))
  (println (= 3 ((arg nth) 2 [1 2 3 4 5])))
  (println (= true ((arg >) 7 8)))
  (println (= 4 ((arg quot) 2 8)))
  (println (= [1 2 3] ((arg take) [1 2 3 4 5] 3)))
  )
(flipping-out #(let [f %1] (fn [x y] (f y x))))

(flipping-out (fn [f] #(apply f (reverse %&))))
(flipping-out #(comp (partial apply %) reverse list))