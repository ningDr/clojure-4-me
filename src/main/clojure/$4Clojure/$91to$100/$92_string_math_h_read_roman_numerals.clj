(ns $4Clojure.$91to$100.$92-string-math-h-read-roman-numerals)
(defn read-roman-numerals
  "Roman numerals are easy to recognize,
   but not everyone knows all the rules necessary to work with them.
   Write a function to parse a Roman-numeral string and return the number it represents.
   You can assume that the input will be well-formed,
   in upper-case, and follow the subtractive principle.
   You don't need to handle any numbers greater than MMMCMXCIX (3999),
   the largest number representable with ordinary letters."
  [arg]
  (println "**********************")
  (println (= 14 (arg "XIV")))
  (println (= 827 (arg "DCCCXXVII")))
  (println (= 3999 (arg "MMMCMXCIX")))
  (println (= 48 (arg "XLVIII"))))

(read-roman-numerals (fn [roman] (let [num-map {\I 1, \X 10, \C 100, \M 1000 \V 5, \L 50, \D 500}
                                       num-seq (reverse (mapv #(get num-map %) (seq roman)))]
                                   (loop [x num-seq, y (first num-seq)]
                                     (if (empty? x)
                                       y
                                       (recur (rest x)
                                              (if (second x)
                                                (if (> (first x) (second x))
                                                  (- y (second x))
                                                  (+ y (second x)))
                                                y)))))))


;; ------------------------------------
(read-roman-numerals #(let [order {\I 1 \V 5 \X 10 \L 50 \C 100 \D 500 \M 1000 nil 0}
                            fold (fn fold [[x & [y & tail :as y-tail] :as col]]
                                   (let [xo (order x) yo (order y)]
                                     (cond (empty? col) nil
                                           (< xo yo) (cons (- yo xo) (fold tail))
                                           :else (cons xo (fold y-tail)))))]
                        (apply + (fold (seq %)))))

(read-roman-numerals (fn [x]
                       (let [num {\M 1000 \D 500 \C 100 \L 50 \X 10 \V 5 \I 1}
                             f (fn [[a b]] (if (and b (< a b)) (- a) a))]
                         (->> (map num x)
                              (partition-all 2 1)
                              (map f)
                              (apply +)))))

(read-roman-numerals (fn f
                       ([s] (f (reverse (seq s)) 0))
                       ([s r]
                        (let [numbers {\O 0 \I 1 \V 5 \X 10 \L 50 \C 100 \D 500 \M 1000}
                              f-element (first s)
                              s-element (if (nil? (second s)) \O (second s))
                              fnumber (get numbers f-element)
                              snumber (get numbers s-element)]
                          (if (empty? s)
                            r
                            (if (> fnumber snumber)
                              (recur (drop 2 s) (+ r (- fnumber snumber)))
                              (recur (drop 2 s) (+ r fnumber snumber))))))))

(read-roman-numerals (fn [x]
                       (let [R {\I 1, \V 5, \X 10, \L 50, \C 100, \D 500, \M 1000}]
                         (reduce +
                                 (map
                                  (partial reduce #(- (R %2) %1) 0)
                                  (re-seq #"IV|IX|XL|XC|XM|CD|CM|[IVXLCDM]" x))))))