(ns $4Clojure.$41to50.#42-factorial-fun)
(defn factorial-fun
  "Write a function which calculates factorials."
  [arg]
  (println "============")
  (println (arg 1))
  (println (arg 3))
  (println (arg 5))
  (println (arg 8))
  )

(factorial-fun #(loop [n %1, res 1]
                  (if (zero? n)
                    res
                    (recur (dec n) (* res n))
                    )
                  )
               )


;; A和B的解法如出一辙，B的更复杂
(factorial-fun #(reduce * (range 1 (inc %))))               ;; A
(factorial-fun #(reduce * (take-while pos? (iterate dec %)))) ;; B
(factorial-fun (fn [x] (reduce #(* %1 %2) (map inc (range x))))) ;; C
