(ns clojure4j.explorer.questions.$91to$100.$100-math-e-least-common-multiple)
(defn least-common-multiple
  "Write a function which calculates the least common multiple.
   Your function should accept a variable number of positive integers or ratios."
  [arg]
  (println "===========")
  (println (== (arg 2 3) 6))
  (println (== (arg 5 3 7) 105))
  (println (== (arg 1/3 2/5) 2))
  (println (== (arg 3/4 1/6) 3/2))
  (println (== (arg 7 5/7 2 3/5) 210)))

(least-common-multiple
 (fn [& args]
   (letfn [(gcd [x y]
            (loop [ma (max x y)
                   mo (min x y)]
              (if (zero? mo)
                ma
                (recur mo (mod ma mo)))))
           (lcm [x y]
            (/ (* x y) (gcd x y)))]
     (reduce lcm args))))

;; ==================
(least-common-multiple #(letfn [(gdc [a r]
                                  (cond
                                    (< a r) (recur r a)
                                    (not= 0 (mod a r)) (recur r (mod a r))
                                    :ese r))]
                          (/ (apply * %&) (reduce gdc %&))))

