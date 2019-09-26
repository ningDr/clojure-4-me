(ns $4Clojure.$91to$100.$99-math-seqs-e-product-digits)
(defn product-digits
  "Write a function which multiplies two numbers and returns
   the result as a sequence of its digits."
  [arg]
  (println "==============")
  (println (= (arg 1 1) [1]))
  (println (= (arg 99 9) [8 9 1]))
  (println (= (arg 999 99) [9 8 9 0 1])))

(product-digits (fn [m n] (mapv #(Integer/parseInt (str %)) (seq (str (* m n))))))

;; =======================
(product-digits #(->> %&
                      (apply *)
                      str
                      seq
                      (map str)
                      (map read-string)))

(product-digits #(->> (* % %2)
                      str
                      (re-seq #"\d")
                      (map read-string)))

(product-digits #(reduce (fn [x y] (into x (vector (- (int y) 48)))) [] (str (* %1 %2))))

(product-digits (fn [x y]
                  (map #(- (int %) (int \0)) (seq (str (* x y))))))

(product-digits (fn [x y] (map #(Integer/parseInt (.toString %)) (flatten (partition 1 (str (* x y)))))))

(product-digits (comp (partial map #(- (int %) 48)) str *))