(ns clojure4j.explorer.questions.$71to80.#79-graph-theory-h-triangle-minimal-path)
(defn triangle-minimal-path
  "
    Write a function which calculates the sum of the minimal path through a triangle.
    The triangle is represented as a collection of vectors.
    The path should start at the top of the triangle and move to an adjacent number
    on the next row until the bottom of the triangle is reached.
  "
  [arg]
  ;; 全局最优解
  (println "================")

  ;(println (= 7 (arg '([1]
  ;                    [2 4]
  ;                   [5 1 4]
  ;                  [2 3 4 5])))                         ; 1->2->1->3
  ;         )
  ;(println (arg '([3]
  ;                [2 4]
  ;                [1 9 3]
  ;                [9 9 2 4]
  ;                [4 6 6 7 8]
  ;                [5 7 3 5 1 4])))
  ;(println (= 20 (arg '([3]
  ;                     [2 4]
  ;                    [1 9 3]
  ;                   [9 9 2 4]
  ;                  [4 6 6 7 8]
  ;                 [5 7 3 5 1 4])))                    ; 3->4->3->2->7->1
  ;         )
  (println (arg '([1]
                  [2 4]
                  [5 1 4]
                  ;[2 3 4 5]
                  )))
  )
;(println ((fn [sub-coll index number] (vector (+ number (nth sub-coll index)) (+ number (nth sub-coll (inc index))))) [2 4] 0 1))
(triangle-minimal-path #(loop [coll %, i 0, len (count coll), res []]
                          (if (= i (dec len))
                            res
                            (recur (rest coll) (inc i) len (concat [] (loop [j 0, sub-res res]
                                                                        (if (= j (dec (count (second coll))))
                                                                          (do
                                                                            (println (second coll))
                                                                            (println i)
                                                                            (println j)
                                                                            (println sub-res)
                                                                            sub-res
                                                                            )
                                                                          (do
                                                                            (println (second coll) "-")
                                                                            (println i "-")
                                                                            (println j "-")
                                                                            (println sub-res "-")
                                                                            (recur (inc j)
                                                                                   (conj sub-res
                                                                                         (+ (nth sub-res i) (nth (second coll) j))
                                                                                         (+ (nth sub-res i) (nth (second coll) (inc j)))
                                                                                         )
                                                                                   )
                                                                            )
                                                                          )
                                                                        )))
                            )
                          )
                       )


;(triangle-minimal-path #(letfn [(get-res [sub-coll index number] (vector (+ number (nth sub-coll index)) (+ number (nth sub-coll (inc index)))))]
;                          (loop [coll (rest %), i 0, res (vector (ffirst %))]
;                            (if (= i (count coll))
;                              res
;                              (recur coll (inc i) (conj res (loop [sub-coll (nth coll i), j 0, sub-res []]
;                                                              (if (= (inc j) (count sub-coll))
;                                                                sub-res
;                                                                (recur sub-coll (inc j) (conj sub-res (get-res sub-coll j (nth res j))))
;                                                                )
;                                                              ))
;                                     )
;                              )
;                            )
;                          )
;                       )

(triangle-minimal-path (fn [xxs] (letfn [(red [down up]
                                           (map + up (map min down (rest down))))]
                                   (->> xxs reverse (reduce red) first))))

(triangle-minimal-path (fn [x]
                         (first
                           (reduce
                             #(map + (map min (butlast %) (rest %)) %2) (reverse x)))))

(triangle-minimal-path (fn f ([s] (f s 0))
                         ([s i]
                          (if (empty? (rest s))
                            (get (first s) i)
                            (min
                              (+ (get (first s) i) (f (rest s) i))
                              (+ (get (first s) i) (f (rest s) (inc i))))))))

(triangle-minimal-path (letfn [(minPaths [triangle, row]
                                 (let [currentRow (nth triangle row)]
                                   (if (= (inc row) (count triangle)) currentRow ;last row is the always the minimal
                                                                      (let [nextMinimal (map #(apply min %) (partition 2 1 (minPaths triangle (inc row))))] ;[2 3 4 5]->((2 3) (3 4) (4 5))->(2 3 4)
                                                                        (map + currentRow nextMinimal)))))] ;get the current minimal path
                         (fn [triangle]
                           (first (minPaths triangle 0))))  ;minPaths returns a sequence, but only a single value is needed
                       )

;; 2016:
(triangle-minimal-path (fn [rows]
                         (let [expand #(concat (take 1 %), (map min (butlast %) (rest %)), (take-last 1 %))
                               combine #(map + (expand %1) %2)]
                           (->> (reduce combine rows)
                                (apply min)))))

;; 2012:
;; (fn [rows]
;;  (apply min (flatten (reduce
;;    (fn [t s]
;;  		(map-indexed
;;				(fn [i v]
;;					(mapcat (fn [vs] (map #(+ % v) vs)) (take (min (inc i) 2) (drop (max (dec i) 0) t))))
;;			s))
;;
;;    [[0]] rows))))

(triangle-minimal-path #(apply min
                               (reduce
                                 (fn [a v] (map min
                                                (map + (cons Double/POSITIVE_INFINITY a) v)
                                                (map + (concat a [Double/POSITIVE_INFINITY]) v)))
                                 %)))

(triangle-minimal-path (fn [x] (apply min (reduce (comp #(map min (concat % [100000]) (concat [100000] %)) (partial map +)) [0] x))))