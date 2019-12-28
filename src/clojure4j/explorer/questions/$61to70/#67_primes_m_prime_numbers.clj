(ns clojure4j.explorer.questions.$61to70.#67-primes-m-prime-numbers)
(defn prime-numbers
  "Write a function which returns the first x number of prime numbers."
  [arg]
  (println "================")
  (println (= (arg 2) [2 3]))
  (println (= (arg 5) [2 3 5 7 11]))
  (println (= (last (arg 100)) 541))
  )
;; 计算一个数是不是质数(由于考虑不周，若去掉3的边界，将变成死循环，注意边界问题)
(println (#(loop [x %, mo -1, i 2]
             (cond
               (or (= x 2) (= x 3))
               true
               (= x 4)
               false
               (and (= 0 mo) (<= i (int (/ x 2))))
               false
               (and (not= 0 mo) (= i (int (/ x 2))))
               true
               :else
               (recur x (mod x i) (inc i))
               )
             ) 6))
;; 获取前n个质数
(prime-numbers #(loop [res [], i 2]
                  (if (= % (count res))
                    res
                    (recur
                      (if ((fn [x] (loop [mo -1, j 2]
                                     (cond
                                       (or (= x 2) (= x 3))
                                       true
                                       (= x 4)
                                       false
                                       (and (= 0 mo) (<= j (int (/ x 2))))
                                       false
                                       (and (not= 0 mo) (= j (int (/ x 2))))
                                       true
                                       :else
                                       (recur (mod x j) (inc j))
                                       )
                                     )
                             ) i
                           )
                        (conj res i)
                        res
                        )
                      (inc i)
                      )
                    )
                  )
               )

(prime-numbers #(loop [v 2 agg () n-last %] (cond (zero? n-last)
                                                  (reverse agg)
                                                  (some (fn [p] (zero? (mod v p))) agg)
                                                  (recur (inc v) agg n-last)
                                                  :else
                                                  (recur (inc v) (cons v agg) (dec n-last))
                                                  )
                                            )
               )

(prime-numbers (fn [n] (->> (range)
                            (drop 2)
                            (filter (fn [x] (not-any? #(zero? (mod x %)) (range 2 x))))
                            (take n)
                            )
                 )
               )

(prime-numbers #(take % ((fn sieve [s]
                           (cons
                             (first s)
                             (lazy-seq (sieve (filter (fn [xx] (not= 0 (mod xx (first s)))) (rest s))))
                             )
                           ) (iterate inc 2))))

(prime-numbers (fn [n] (->> (range)
                            (drop 2)
                            (filter (fn [x] (every? #(< 0 (mod x %)) (range 2 x))))
                            (take n))))

(prime-numbers (fn [x] (take x (filter #(= (inc (mod (apply * (range 1N %)) %)) %) (iterate inc 2)))))