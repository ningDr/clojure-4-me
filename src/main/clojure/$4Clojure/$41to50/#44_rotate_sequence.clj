(ns $4Clojure.$41to50.#44-rotate-sequence)
(defn rotate-sequence
  "Write a function which can rotate a sequence in either direction.\n"
  [arg]
  (println "=============")
  (println (= (arg 2 [1 2 3 4 5]) '(3 4 5 1 2)))
  (println (= (arg -2 [1 2 3 4 5]) '(4 5 1 2 3)))
  (println (= (arg 6 [1 2 3 4 5]) '(2 3 4 5 1)))
  (println (= (arg 1 '(:a :b :c)) '(:b :c :a)))
  (println (= (arg -4 '(:a :b :c)) '(:c :a :b)))
  )

(rotate-sequence #(let [x %1, coll %2]
                    (cond
                      (pos? x)
                      (let [y (- (count coll) (rem x (count coll)))]
                        (concat (take-last y coll) (drop-last y coll))
                        )
                      :else
                      (let [y (rem (- x) (count coll))]
                        (concat (take-last y coll) (drop-last y coll))
                        )
                      )
                    )
                 )

(rotate-sequence (fn [n ns] (let [ct (count ns) n-take (mod (if (< 0 n) n (+ ct n)) ct)]
                              (concat (drop n-take ns) (take n-take ns)))))

(rotate-sequence (fn [z xs] (let [m (mod z (count xs))]
                              (concat (drop m xs) (take m xs)))))

(rotate-sequence #(let [x (rem (+ (count %2) (rem %1 (count %2))) (count %2))]
                    (flatten [(drop x %2) (take x %2)])))

(rotate-sequence (fn [m x]
                   (let [x (vec x) n (count x)]
                     (map x
                          (map
                            #(mod (+ m %) n)
                            (range n)
                            )
                          )
                     )
                   ))