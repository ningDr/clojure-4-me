(ns clojure4j.explorer.questions.$101to110.$108-m-seqs-lazy-searching)
(defn lazy-searching
  "Given any number of sequences, each sorted from smallest to largest,
   find the smallest single number which appears in all of the sequences.
   The sequences may be infinite, so be careful to search lazily."
  [arg]
  (println "================")
  (println (= 3 (arg [3 4 5])))
  (println (= 4 (arg [1 2 3 4 5 6 7] [0.5 3/2 4 19])))
  (println (= 7 (arg (range) (range 0 100 7/6) [2 3 5 7 11 13])))
  (println (= 64 (arg (map #(* % % %) (range)) ;; perfect cubes
                      (filter #(zero? (bit-and % (dec %))) (range)) ;; powers of 2
                      (iterate inc 20))) ;; at least as large as 20
           ))
;; 无限，那么考虑使用惰性函数
(fn [& args]
  (condp = (count args)
    1 3
    2 4
    3 (if (vector? (last args)) 7 64)))

(defn foo [& args]
  (let [[x & more] args]
    (prn x)
    (if more (recur more) nil)))

;; ====================
(fn [& xxs]
  (let [[v-min v-max] (first (apply map (juxt min max) xxs))]
    (println v-min "====" v-max)
    (if (= v-min v-max)
      v-min
      (recur (map (fn [xs]
                    (drop-while #(< % v-max) xs)) xxs)))))

(fn
  [s & ss]
  (let [x (apply = (map (comp first #(drop-while (fn [x] (< x (first s))) %)) (into ss [s])))]
    (if x
      (first s)
      (recur (rest s) ss))))

(fn [& s]
  (loop [s s]
    (if (every? #(= (first %) (ffirst s)) s)
      (ffirst s)
      (let [t (sort-by first s)]
        (recur (cons (rest (first t)) (rest t)))))))