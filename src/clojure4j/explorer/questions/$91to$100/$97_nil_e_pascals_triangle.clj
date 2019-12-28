(ns clojure4j.explorer.questions.$91to$100.$97-nil-e-pascals-triangle)
(defn pascals-triangle
  "Pascal's triangle is a triangle of numbers computed using the following rules:
   - The first row is 1.
   - Each successive row is computed by adding together adjacent numbers in the row above,
     and adding a 1 to the beginning and end of the row.
   Write a function which returns the nth row of Pascal's Triangle."
  [arg]
  (println "============================")
  (println (= (arg 1) [1]))
  (println (= (map arg (range 1 6))
              [[1]
               [1 1]
               [1 2 1]
               [1 3 3 1]
               [1 4 6 4 1]]))
  (println (= (arg 11) [1 10 45 120 210 252 210 120 45 10 1])))

(pascals-triangle (fn [i] (cond
                            (= 1 i) [1]
                            (= 2 i) [1 1]
                            :else (loop [l 2, res [1, 1]]
                                    (if (= l i)
                                      res
                                      (recur (inc l) (loop [j 1, sub [1]]
                                                       (if (= j (count res))
                                                         (conj sub 1)
                                                         (recur (inc j) (conj sub (+ (nth res (dec j)) (nth res j))))))))))))

;; -------------------------
(pascals-triangle (fn tr
                    ([n] (tr n [1]))
                    ([n acc]
                     (if (= n 1)
                       acc
                       (recur (dec n)
                              (mapv + (cons 0 acc) (conj acc 0)))))))

(pascals-triangle (fn [n]
                    (loop [n n res [1]]
                      (if (= n 1)
                        res
                        (recur (dec n) (map + (concat [0] res) (concat res [0])))))))

(pascals-triangle #(nth (iterate (fn [x] (concat [1]
                                                 (map + x (rest x))
                                                 [1]))
                                 [1]) (dec %)))

(pascals-triangle #(letfn [(factorial [n] (apply * (range 1 (inc n))))
                           (binomial [n k]
                             (/ (factorial n) (* (factorial k) (factorial (- n k)))))]
                     (map (fn [col] (binomial (dec %) col)) (range %))))

(pascals-triangle (fn [i]
                    (reduce
                     #(conj %1 (* (last %1) (/ (- i %2) %2)))
                     [1] (range 1 i))))

(pascals-triangle (fn [n]
                    (nth
                     (iterate #(map +' (concat [0] %) (concat % [0])) [1])
                     (dec n))))

(pascals-triangle (fn pascal [x]
                    (if (= 1 x)
                      [1]
                      (let [pp (conj (pascal (dec x)) 0)]
                        (map + pp (reverse pp))))))