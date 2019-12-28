(ns clojure4j.explorer.questions.$81to90.$86-math-m-happy-numbers)
(defn happy-numbers
  "Happy numbers are positive integers that follow a particular formula: 
   take each individual digit, square it,
   and then sum the squares to get a new number.
   Repeat with the new number and eventually, 
   you might get to a number whose squared sum is 1.
   This is a happy number. 
   An unhappy number (or sad number) is one that loops endlessly. 
   Write a function that determines if a number is happy or not."
  [arg]
  (println "===========================")
  (println (= (arg 7) true))
  (println (= (arg 986543210) true))
  (println (= (arg 2) false))
  (println (= (arg 3) false)))

(defn ha
  [your-number]
  (letfn[(aa [your-number] (reduce + (mapv #(* % %) (mapv #(Integer/parseInt (str %)) (seq (str your-number)))))
          )]
    (loop [bb (aa your-number), res-set #{}]
      (if (or (= java.lang.Boolean (type bb)) (contains? res-set bb))
        (if (= java.lang.Long (type bb)) false bb)
        (recur (cond
                 (= 1 bb) true
                 (= your-number bb) false
                 :else (aa bb))
               (conj res-set bb))))))

(happy-numbers ha)

;; -------------------------
(happy-numbers (fn [i-n]
                 (letfn [(sq-sum
                           [x]
                           (->> (str x) (re-seq #"\d")
                                (map read-string)
                                (#(map * % %))
                                (apply +)))]
                   (loop [found #{} n i-n]
                     (let [x (sq-sum n)]
                       (cond (= 1 x) true
                             (found x) false
                             :else (recur (conj found x) x)))))))

(happy-numbers (fn [n]
                 (letfn [(ss [x]
                           (->> (str x)
                                (re-seq #"\d")
                                (map read-string)
                                (map #(* % %))
                                (reduce +)))]
                   (loop [n n res #{}]
                     (let [x (ss n)]
                       (cond
                         (= 1 x) true
                         (res x) false
                         :else (recur x (conj res x))))))))
