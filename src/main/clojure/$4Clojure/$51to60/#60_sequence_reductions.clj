(ns $4Clojure.$51to60.#60-sequence-reductions)
(defn sequence-reductions
  "
    Write a function which behaves like reduce,
    but returns each intermediate value of the reduction.
    Your function must accept either two or three arguments,
    and the return sequence must be lazy.
  "
  [arg]
  (println "=====================")
  (println (= (take 5 (arg + (range))) [0 1 3 6 10]))
  (println (= (arg conj [1] [2 3 4]) [[1] [1 2] [1 2 3] [1 2 3 4]]))
  (println (= (last (arg * 2 [3 4 5])) (reduce * 2 [3 4 5]) 120))
  )

;(sequence-reductions reductions)
;
;(sequence-reductions (fn ([f coll] (loop [coll coll, res (vector (nth coll 0)), i 0]
;                                     (if (= 6 i)
;                                       (rest res)
;                                       (recur (rest coll) (conj res (f (nth res (- (count res) 1)) (nth coll 0))) (inc i))
;                                       )
;                                     )
;                          )
;                       ([f init coll] (loop [coll coll, res (vector init)]
;                                        (if (empty? coll)
;                                          (sort res)
;                                          (recur (drop-last coll) (conj res (reduce f init coll)))
;                                          )
;                                        )
;                        )
;                       )
;                     )

;; => (arg + (range))
;; => (arg * 2 [3 4 5])
;(sequence-reductions (fn m-reductions
;                       ([op v col] (println op v (take 15 col))
;                        (m-reductions op (cons v col)))
;                       ([op [h & tail]]
;                        (->>
;                          (iterate (fn [[v [h & tail :as col]]]
;                                     (if (empty? col)
;                                       [v]
;                                       [(op v h) tail]
;                                       )
;                                     ) [h tail]
;                                   )
;                          (take-while next)
;                          (map first)
;                          )
;                        )
;                       )
;                     )

;; => (arg + (range))
;; => (arg * 2 [3 4 5])
(sequence-reductions (fn r
                       ([f xs] (r f (first xs) (rest xs)))
                       ([f init xs] (cons init (lazy-seq (when-let [s (seq xs)] (r f (f init (first s)) (rest s))))))))

(sequence-reductions (fn myFn
                       ([f xs] (myFn f (first xs) (rest xs)))
                       ([f init xs] (cons init (lazy-seq (when-let [s (seq xs)] (myFn f (f init (first s)) (rest s))))))))

;; => (arg + (range))
;; => (arg * 2 [3 4 5])
;(sequence-reductions (fn myReductions
;                       ([f preVal coll]
;                        (lazy-seq
;                          (if-let [firstVal (first coll)];if there is still some items left
;                            (cons preVal (myReductions f (f preVal firstVal) (rest coll)));lazy recipe
;                            (list preVal))));last item shall be containted by a seq
;                       ([f coll]
;                        (myReductions f (first coll) (rest coll)))))
;
;(sequence-reductions (fn r
;                       ([f xs] (r f (first xs) (rest xs)))
;                       ([f v xs]
;                        (lazy-seq
;                          (cons v
;                                (if (empty? xs) [] (r f (f v (first xs)) (rest xs))))))))
;(sequence-reductions (fn r ([f v s]
;                            (lazy-seq
;                              (cons v
;                                    (if (seq s)
;                                      (r f (f v (first s)) (rest s))))))
;                       ([f s] (r f (first s) (rest s)))))
;
;(sequence-reductions (fn red
;                       ([f coll]
;                        (lazy-seq
;                          (if-let [s (seq coll)]
;                            (red f (first s) (rest s))
;                            (list (f)))))
;                       ([f init coll]
;                        (cons init
;                              (lazy-seq
;                                (when-let [s (seq coll)]
;                                  (red f (f init (first s)) (rest s))))))))