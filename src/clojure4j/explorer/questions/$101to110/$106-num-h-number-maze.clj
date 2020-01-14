(ns clojure4j.explorer.questions.$101to110.$106-num-h-number-maze)
(defn number-maze
  "Given a pair of numbers, the start and end point, find a path between
   the two using only three possible operations:
   double
   halve (odd numbers cannot be halved)
   add 2
   Find the shortest path through the 'maze'.
   Because there are multiple shortest paths, you must return the length
   of the shortest path, not the path itself."
  [arg]
  (println "======================")
  (println (= 1 (arg 1 1))  ); 1
  (println (= 3 (arg 3 12)) ); 3 6 12
  (println (= 3 (arg 12 3)) ); 12 6 3
  (println (= 3 (arg 5 9))  ); 5 7 9
  (println (= 9 (arg 9 2))  ); 9 18 20 10 12 6 8 4 2
  (println (= 5 (arg 9 12)) ); 9 11 22 24 12
 )

(defn gen-num-set [num]
  (let [res (atom #{})]
    (when-not (odd? num)
      (swap! res conj (/ num 2)))
    (swap! res conj (* num 2))
    (swap! res conj (+ num 2))
    @res))

(defn gen-num-map [num-set round]
  (zipmap num-set (repeat round)))

(defn gen-num-coll [nums]
  (map #(gen-num-set %) nums))


(defn fn-half [n] (if-not (odd? n) (/ n 2) n))
(defn fn-double [n] (+ n n))
(defn fn-add2 [n] (+ 2 n))
(defn gen-set [i col]
  [(inc i) (into #{} (remove nil? (flatten (map #((juxt fn-half fn-double fn-add2) %) col))))])

(defn cal-times [a b]
  (if (= a b)
    1
    (loop [[i coll] (gen-set 1 [a])]
      (if (contains? coll b)
        i
        (do (println i "========" coll)
            (recur (gen-set i coll)))))))

(fn [a b]
  (letfn [(fn-half [n] (if-not (odd? n) (/ n 2) n))
          (fn-double [n] (+ n n))
          (fn-add2 [n] (+ 2 n))
          (gen-set [i col]
                   [(inc i) (into #{} (remove nil? (flatten (map #((juxt fn-half fn-double fn-add2) %) col))))])]
    (if (= a b)
      1
      (loop [[i coll] (gen-set 1 [a])]
        (if (contains? coll b)
          i
          (do (println i "========" coll)
              (recur (gen-set i coll))))))))

;; ========================
(fn [x-i y]
  (letfn [(spread [x]
            (concat (when (even? x) [(/ x 2)]) [(* 2 x) (+ 2 x)]))]
    (loop [i 1 xs [x-i]]
      (if (some (partial = y) xs)
        i
        (recur (inc i) (mapcat spread xs))))))

(fn [s g]
  (let [f (juxt #(+ % 2) #(* % 2) #(if (even? %) (/ % 2) %))]
    (loop [ns [s] c 1]
      (if (some #{g} ns)
        c
        (recur (mapcat f ns) (inc c))))))

(fn [s e]
  (letfn [(step [xs] (set (mapcat #(list (* 2 %) (+ 2 %) (if (even? %) (/ % 2) %)) xs)))]
    (if (= s e) 1
        (inc (count (take-while #(not (some #{e} %)) (iterate step #{s})))))))

(letfn [(op [s] (for [si s x [(+ si si)
                              (when (zero? (mod si 2)) (/ si 2))
                              (+ si 2)]
                      :when x] x))
        (maze [x1 x2] (inc (count (take-while (partial not-any? #{x2}) (iterate op [x1])))))]
  maze)


