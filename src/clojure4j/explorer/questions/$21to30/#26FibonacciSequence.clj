(ns clojure4j.explorer.questions.$21to30.#26FibonacciSequence)
(defn fibonacciSequence
  "Write a function which returns the first X fibonacci numbers."
  [arg]
  (println (arg 3))
  (println (arg 6))
  (println (arg 8))
  )
(fibonacciSequence #((fn [seq x] (if (zero? (- x 2)) (reverse seq) (recur (conj seq (+ (first seq) (second seq))) (dec x)))) '(1 1) %1))


(println "==============")
(fibonacciSequence (fn fib [n]
                     ((fn iter [c res]
                        (if (= c n)
                          (reverse res)
                          (let [next (if (< c 2) 1 (reduce + 0 (take 2 res)))]
                            (iter (inc c) (conj res next)))
                          )) 0 ())))
(fibonacciSequence #(take %
                          (map first
                               (iterate (fn [[i1 i2]]
                                          [i2 (+ i1 i2)])
                                        [1 1]))))
(fibonacciSequence #((apply comp (repeat (- % 2) (fn [x] (conj x (+ (peek x) (peek (pop x))))))) [1 1]))