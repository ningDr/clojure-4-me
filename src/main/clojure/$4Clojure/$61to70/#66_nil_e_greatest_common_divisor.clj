(ns $4Clojure.$61to70.#66-nil-e-greatest-common-divisor)
(defn greatest-common-divisor
  "Given two integers, write a function which returns the greatest common divisor."
  [arg]
  (println (= (arg 2 4) 2))
  (println (= (arg 10 5) 5))
  (println (= (arg 5 7) 1))
  (println (= (arg 1023 858) 33))
  )

;; 使用辗转相除法
(greatest-common-divisor #(loop [ma (max %1 %2), mo (min %1 %2)]
                            (if (zero? mo)
                              ma
                              (recur mo (mod ma mo))
                              )
                            )
                         )

(greatest-common-divisor (fn gdc [a r] (cond
                                         (< a r) (recur r a)
                                         (not= 0 (mod a r)) (recur r (mod a r))
                                         :ese r
                                         )
                           )
                         )
(greatest-common-divisor (fn [x y] (if (zero? y) x (recur y (mod x y)))))
(greatest-common-divisor (fn gcd [a b] (cond
                                         (= a b) a
                                         (> a b) (recur (- a b) b)
                                         :else (recur a (- b a))
                                         )
                           )
                         )
(greatest-common-divisor (fn [x y] (apply max (filter #(= 0 (mod x %) (mod y %)) (range 1 (+ 1 (max (/ x 2) (/ y 2))))))))
(greatest-common-divisor (fn [v1 v2] (apply max (filter #(and (zero? (mod v1 %)) (zero? (mod v2 %))) (range 1 (max v1 v2))))))
(greatest-common-divisor (comp (partial apply +)
                               first
                               (partial drop-while (comp (partial < 0)
                                                         (partial apply min)
                                                         )
                                        )
                               #(iterate (fn [[x1 x2]] (vector (mod x1 x2) (mod x2 x1))) [%1 %2])
                               )
                         )