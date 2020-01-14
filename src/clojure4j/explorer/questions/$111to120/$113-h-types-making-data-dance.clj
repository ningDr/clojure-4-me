(ns clojure4j.explorer.questions.$101to110.$113-h-types-making-data-dance)
(defn making-data-dance
  "Write a function that takes a variable number of integer arguments.
   If the output is coerced into a string, it should return a comma (and space)
   separated list of the inputs sorted smallest to largest.
   If the output is coerced into a sequence, it should return a seq of unique input
   elements in the same order as they were entered."
  [arg]
  (println "=============")
  (println (= "1, 2, 3" (str (arg 2 1 3))))
  (println (= '(2 1 3) (seq (arg 2 1 3))))
  (println (= '(2 1 3) (seq (arg 2 1 3 3 1 2))))
  (println (= '(1) (seq (apply arg (repeat 5 1)))))
  (println (= "1, 1, 1, 1, 1" (str (apply arg (repeat 5 1)))))
  (println (and (= nil (seq (arg)))
                (= "" (str (arg))))))



(fn [& args]
  (deftype aaa [args])
  
  )



