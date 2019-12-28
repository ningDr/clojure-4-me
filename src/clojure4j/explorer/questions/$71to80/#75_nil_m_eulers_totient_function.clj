(ns clojure4j.explorer.questions.$71to80.#75-nil-m-eulers-totient-function)
(defn eulers-totient-function
  "
  Two numbers are coprime if their greatest common divisor equals 1.
  Euler's totient function f(x) is defined as the number of positive integers less than x which are coprime to x.
  The special case f(1) equals 1. Write a function which calculates Euler's totient function.
  "
  [arg]
  (println "================")
  (println (= (arg 1) 1))
  (println (= (arg 10) (count '(1 3 7 9)) 4))
  (println (= (arg 40) 16))
  (println (= (arg 99) 60))
  )

(eulers-totient-function (fn [num] (if (= num 1)
                                     1
                                     ;; 计算count
                                     (loop [v-num (dec num), res [1]]
                                       (if (= v-num 1)
                                         (count res)
                                         (recur (dec v-num) (if (= 1 ((fn [ma mo] (loop [ma ma, mo mo]
                                                                                    (if (zero? mo)
                                                                                      ma
                                                                                      (recur mo (mod ma mo))
                                                                                      )
                                                                                    )
                                                                        ) num v-num)
                                                                   )
                                                              (conj res v-num)
                                                              res
                                                              )
                                                )
                                         )
                                       )
                                     )
                           )
                         )

(eulers-totient-function (fn [n] (letfn [(gdc [a r]
                                           (cond (< a r) (recur r a)
                                                 (not= 0 (mod a r)) (recur r (mod a r))
                                                 :ese r))]
                                   (if (= n 1)
                                     1
                                     (->> (range 1 n)
                                          (filter #(= 1 (gdc n %)))
                                          (count))))))

(eulers-totient-function (fn phi [n] (letfn [(gcd [x y] (if (zero? y) x (recur y (mod x y))))]
                                       (if (= 1 n) 1
                                                   (->> (range 1 n)
                                                        (filter #(= 1 (gcd n %)))
                                                        count)))))
(eulers-totient-function #(letfn [(gcd [x y] (cond
                                               (= x y) x
                                               (< x y) (recur x (- y x))
                                               :else (recur (- x y) y)))]
                            (count (filter (fn [x] (= x 1)) (map (fn [x] (gcd % x)) (range 1 (inc %)))))))

(eulers-totient-function (fn [x] (let [gcd (comp (partial apply +) first (partial drop-while (comp (partial < 0) (partial apply min))) #(iterate (fn [[x1 x2]] (vector (mod x1 x2) (mod x2 x1))) [%1 %2]))]
                                   (if (= x 1) 1 (count (filter (comp (partial = 1) (partial gcd x)) (range 1 x)))))
                           )
                         )

(eulers-totient-function (fn [x] (count (filter #(= 1 (loop [a x b %]
                                                        (if (zero? b)
                                                          a
                                                          (recur b (mod a b))
                                                          )
                                                        )
                                                    ) (range 1 (inc x))
                                                )
                                        )
                           )
                         )