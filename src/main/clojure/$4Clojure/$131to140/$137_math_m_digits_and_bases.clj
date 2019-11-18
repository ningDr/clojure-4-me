(ns $4Clojure.$131to140.$137-math-m-digits-and-bases)
(defn digits-and-bases
  "Write a function which returns a sequence of digits of a non-negative number
   (first argument) in numerical system with an arbitrary base (second argument).
   Digits should be represented with their integer values, e.g.
   15 would be [1 5] in base 10, [1 1 1 1] in base 2 and [15] in base 16."
  [arg]
  (println "========================")
  (println (= [1 2 3 4 5 0 1] (arg 1234501 10)))
  (println (= [0] (arg 0 11)))
  (println (= [1 0 0 1] (arg 9 2)))
  (println (= [1 0] (let [n (rand-int 100000)](arg n n))))
  (println (= [16 18 5 24 15 1] (arg Integer/MAX_VALUE 42))))

(digits-and-bases (fn [your-num n]
                    (loop [quotient (int (/ your-num n)), bin-seq (list (mod your-num n))]
                      (if (zero? quotient)
                        bin-seq
                        (recur (int (/ quotient n)) (conj bin-seq (mod quotient n)))))))

;; ========================
(digits-and-bases (fn [i-v base]
                    (loop [agg () v i-v]
                      (if (< v base)
                        (cons v agg)
                        (let [d (mod v base)
                              rest (/ (- v d) base)]
                          (recur (cons d agg) rest))))))

(digits-and-bases (fn base [x b]
                    (if (< x b) [x]
                                (conj (base (quot x b) b) (mod x b)))))

(digits-and-bases (fn [n b]
                    (if (zero? n) [0]
                                  (loop [rst n result '()]
                                    (if (zero? rst)
                                      result
                                      (recur (quot rst b) (conj result (mod rst b))))))))

(digits-and-bases #(loop [number %1 base %2 result '()]
                     (if (< number base) (conj result number)
                                         (recur (quot number base) base (conj result (rem number base))))))

(digits-and-bases (fn [x b]
                    (reverse
                     (map #(mod (quot x %) b) (cons 1 (take-while #(<= % x) (iterate #(* b %) b)))))))

(digits-and-bases (fn [n b]
                    (if (zero? n) [0]
                                  (reverse (map second
                                                (rest
                                                 (take-while #(not= % [0 0])
                                                             (iterate
                                                              (fn [[q r]] [(quot q b) (rem q b)])
                                                              [n 0]))))))))