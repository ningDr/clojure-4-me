(ns $4Clojure.$51to60.#54-partition-a-sequence)
(defn partition-a-sequence
  "Write a function which returns a sequence of lists of x items each. Lists of less than x items should not be returned.\n"
  [arg]
  (println "==============")
  (println (arg 3 (range 9)))
  (println (= (arg 3 (range 9)) '((0 1 2) (3 4 5) (6 7 8))))
  (println (= (arg 2 (range 8)) '((0 1) (2 3) (4 5) (6 7))))
  (println (= (arg 3 (range 8)) '((0 1 2) (3 4 5))))
  )
;(partition-a-sequence #(partition %1 %2))
(partition-a-sequence (fn [n coll] (apply map list (vals (group-by #(rem % n) coll)))))
(partition-a-sequence (fn [n coll]
                        (->> (group-by #(rem % n) coll)
                             vals
                             (apply map list)
                             )
                        )
                      )

(partition-a-sequence (fn q [n coll]
                        (when-let [s (seq coll)]
                          (let [p (take n s)]
                            (when (= n (count p))
                              (cons p (q n (drop n s)))
                              )
                            )
                          )
                        )
                      )
(partition-a-sequence (fn f ([n col] (f n col n () ()))
                        ([n [h & tail :as col] left m-head agg]
                         (let [head (cons h m-head)]
                           (cond
                             (not (seq col))
                             (reverse agg)
                             (= left 1)
                             (recur n tail n () (-> (reverse head) (cons agg)))
                             :else
                             (recur n tail (dec left) head agg)
                             )
                           )
                         )
                        )
                      )
(partition-a-sequence (fn f ([c s] (f c s '()))
                        ([c s r]
                         (if (empty? s)
                           (reverse r)
                           (recur c (drop c s) (if (= (count (take c s)) c) (conj r (take c s)) r)
                                  )
                           )
                         )
                        )
                      )
(partition-a-sequence #(loop [result [], remaining %2]
                         (if (>= (count remaining) %1)
                           (recur (conj result (take %1 remaining)) (drop %1 remaining))
                           result
                           )
                         )
                      )