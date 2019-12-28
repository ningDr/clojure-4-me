(ns clojure4j.explorer.questions.$71to80.#80-nil-m-perfect-numbers)
(defn perfect-numbers
  "A number is \"perfect\" if the sum of its divisors equal the number itself.
   6 is a perfect number because 1+2+3=6.
  Write a function which returns true for perfect numbers and false otherwise.
  从1开始的因数总和等于它本身，就是完美数，因此10不是"
  [arg]
  (println "================================")
  (println (= (arg 6) true))
  (println (= (arg 7) false))
  (println (= (arg 10) false))
  (println (= (arg 496) true))
  (println (= (arg 500) false))
  (println (= (arg 8128) true)))

#_(perfect-numbers (fn [num] (loop [list (range 1000), sum 0]
                             (if (<= num sum)
                               (= num sum)
                               (recur (rest list) (+ sum (first list)))))))
;; 连续自然数相加等于本身的话适用，本题不适用
(perfect-numbers (fn [num] (loop [i 1]
                             (let [sum (apply + (range i))]
                               (if (>= sum num)
                                 (= sum num)
                                 (recur (inc i)))))))

(perfect-numbers (fn [num] (= num (apply + (filter #(zero? (mod num %)) (range 1 (inc (/ num 2))))))))
;; ---------------------------------------------------------------------------------------------------------------------
(perfect-numbers (fn [n]
                   (->> (range 1 n)
                        (filter #(zero? (mod n %)))
                        (apply +)
                        (= n))))

(perfect-numbers (fn [x]
                   (->> (range 1 (inc (/ x 2)))
                        (filter #(= 0 (mod x %)))
                        (reduce +)
                        (= x))))

(perfect-numbers #(= (apply + (filter (comp zero? (partial rem %)) (range 1 %))) %))

(perfect-numbers (fn perfect? [x]
                   (= x (apply + ;sum is x
                               (filter #(= 0 (rem x %));find divisor
                                       (range 1 x))))));from 1 inclusive to x exclusive