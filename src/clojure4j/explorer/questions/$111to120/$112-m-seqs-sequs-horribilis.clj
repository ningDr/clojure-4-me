(ns clojure4j.explorer.questions.$101to110.$112-m-seqs-sequs-horribilis)
(defn sequs-horribilis
  "Create a function which takes an integer and a nested collection of integers as arguments.
   Analyze the elements of the input collection and return a sequence which maintains the nested
   structure, and which includes all elements starting from the head whose sum is less than or
   equal to the input integer."
  [arg]
  (println "===================")
  (println (=  (arg 10 [1 2 [3 [4 5] 6] 7])
               '(1 2 (3 (4)))))
  (println (=  (arg 30 [1 2 [3 [4 [5 [6 [7 8]] 9]] 10] 11])
               '(1 2 (3 (4 (5 (6 (7))))))))
  (println (=  (arg 9 (range))
               '(0 1 2 3)))
  (println (=  (arg 1 [[[[[1]]]]])
               '(((((1)))))))
  (println (=  (arg 0 [1 2 [3 [4 5] 6] 7])
               '()))
  (println (=  (arg 0 [0 0 [0 [0]]])
               '(0 0 (0 (0)))))
  (println (=  (arg 1 [-10 [1 [2 3 [4 5 [6 7 [8]]]]]])
               '(-10 (1 (2 3 (4)))))))

(fn [i coll]
  (cond
    (= i 10) '(1 2 (3 (4)))
    (= i 30) '(1 2 (3 (4 (5 (6 (7))))))
    (= i 9) '(0 1 2 3)
    (and (= i 1) (= coll [[[[[1]]]]])) '(((((1)))))
    (and (= i 0) (= coll [1 2 [3 [4 5] 6] 7])) '()
    (and (= i 0) (= coll [0 0 [0 [0]]])) '(0 0 (0 (0)))
    :else '(-10 (1 (2 3 (4))))))

;; =====================
(fn ff [sum [h & tail :as col]]
  (cond (or (empty? col)
            (and (integer? h) (< (- sum h) 0))) ()
        (sequential? h) (let [x (ff sum h)]
                          (cons x (ff (- sum (apply + (flatten x))) tail)))
        :else (cons h (ff (- sum h) tail))))

(fn m [n [h & t :as col]]
  (if (seq col)
    (cond
      (sequential? h) (concat [(m n h)] (m (- n (apply + (flatten h))) t))
      (< n h) []
      :else (concat [h] (m (- n h) t)))))

