(ns $4Clojure.$21to30.#24SumItAllUp)
(defn sumItAllUp
  "Write a function which returns the sum of a sequence of numbers."
  [arg]
  (println (arg [1 2 3]))
  (println (arg (list 0 -2 5 5)))
  (println (arg #{4 2 1}))
  )

;为什么sum和seq不能交换位置？
(sumItAllUp #((fn [sum seq] (if (empty? seq)
                              sum
                              (recur (+ sum (first seq)) (drop 1 seq))
                            )
                ) 0 %1
              )
            )

(sumItAllUp #((fn [x seq] (if (empty? seq)
                            x
                            (recur (+ x (first seq)) (rest seq)))) 0 %1
              )
            )

(sumItAllUp #(reduce + 0 %1))
(println "============")

(sumItAllUp #(apply + %1))