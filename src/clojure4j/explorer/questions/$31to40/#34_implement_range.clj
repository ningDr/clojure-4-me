(ns clojure4j.explorer.questions.$31to40.#34-implement-range)
(defn implement-range
  "Write a function which creates a list of all integers in a given range."
  [arg]
  (println "==============")
  (println (arg 1 4))
  (println (arg -2 2))
  (println (arg 5 8))
  )

(implement-range #(loop [x %1 y %2 s '()]
                    (if (= x y)
                      (reverse s)
                      (recur (inc x) y (conj s x))
                      )
                    )
                 )

(implement-range (fn [from to] (
                                (fn iter [from to res]
                                  (if (= from to)
                                    res
                                    (iter from (dec to) (conj res (dec to)))
                                    )
                                  ) from to ()
                                )
                   )
                 )
(implement-range #(take (- %2 %1) (iterate inc %1)))
(implement-range (fn [from to] (take-while #(< % to) (iterate inc from))))
(implement-range (fn [s t] (reductions + (merge (repeat (dec (- t s)) 1) s))))