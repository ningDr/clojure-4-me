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







