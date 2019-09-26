(ns $4Clojure.$21to30.#22CountSequence)
(defn countSequence
  "Write a function which returns the total number of elements in a sequence."
  [arg]
  (println (arg '(5 6 7)))
  )
(countSequence (fn [x] (let [seq (vec x)]
                         (loop [i 0]
                           (if (= nil (get seq i))
                             i
                             (recur (+ i 1))
                             )
                           )
                         )
                 )
               )

(countSequence #(reduce (fn [cc _] (inc cc)) 0 %1))

(countSequence (fn [x] (loop [x x cnt 0]
                         (if (seq x)
                           (recur (rest x) (inc cnt))
                           cnt
                           )
                         )
                 )
               )
(countSequence (fn f ([s] (f s 0)) ([s r]
                                    (if (empty? s)
                                      r
                                      (recur (rest s) (inc r))))))
(countSequence #(loop [l %, r 0]
                  (if (nil? l)
                    r
                    (recur (next l) (inc r)))))

(countSequence (comp (partial apply +) (partial map #(do % 1))))