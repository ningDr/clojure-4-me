(ns $4Clojure.$131to140.$135-order-function-math-e-infix-calculator)
(defn infix-calculator
  "Your friend Joe is always whining about Lisps using the prefix notation for math.
   Show him how you could easily write a function that does math using the infix notation.
   Is your favorite language that flexible, Joe?
   Write a function that accepts a variable length mathematical expression consisting of
   numbers and the operations +, -, *, and /.
   Assume a simple calculator that does not do precedence and instead just calculates left to right."
  [arg]
  (println "=====================")
  (println (= 7 (arg 2 + 5)))
  (println (= 42 (arg 38 + 48 - 2 / 2)))
  (println (= 8 (arg 10 / 2 - 1 * 2)))
  (println (= 72 (arg 20 / 2 + 2 + 4 + 8 - 6 - 10 * 9))))

(infix-calculator (fn a [& args]
                    (loop [res args]
                      (if (= 1 (count res))
                        (first res)
                        (recur (conj (drop 3 res) ((second res) (first res) (nth res 2))))))))

;; =================
(infix-calculator (fn ff [x f y & z]
                    (if (seq z)
                      (apply ff (cons (f x y) z))
                      (f x y))))

(infix-calculator (fn [a1 & args]
                    (if (empty? args)
                      a1
                      (recur ((first args) a1 (second args)) (drop 2 args)))))

(infix-calculator (fn [& args]
                    (loop [result (first args), opSeq (next args)]
                      (if (empty? opSeq) result
                                         (recur ((first opSeq) result (second opSeq)) (drop 2 opSeq))))))

(infix-calculator (fn [& xs]
                    (reduce #((first %2) %1 (last %2)) (first xs) (partition 2 (rest xs)))))

(infix-calculator (fn [firstlhs & t]
                    (let [oprhs (partition 2 t)]
                      (reduce (fn [lhs [op rhs]] (op lhs rhs)) firstlhs oprhs))))

(infix-calculator (fn infix [init & rawops] (let [
                                                  ops (partition 2 rawops)
                                                  do-op (fn [v op] ((first op) v (second op)))]
                                              (reduce do-op init ops))))