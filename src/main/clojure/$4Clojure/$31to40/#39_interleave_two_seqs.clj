(ns $4Clojure.$31to40.#39-interleave-two-seqs)
(defn interleave-two-seqs
  "Write a function which takes two sequences and returns the first item from each, then the second item from each, then the third, etc.\n"
  [arg]
  (println "===============")
  (println (= (arg [1 2 3] [:a :b :c]) '(1 :a 2 :b 3 :c)))
  (println (= (arg [1 2] [3 4 5 6]) '(1 3 2 4)))
  (println (= (arg [1 2 3 4] [5]) [1 5]))
  (println (= (arg [30 20] [25 15]) [30 25 20 15]))
  )

(interleave-two-seqs #(loop [x %1, y %2, z '()]
                       (if (or (empty? x) (empty? y))
                         (reverse z)
                         (recur (rest x) (rest y) (conj z (first x) (first y)))
                         )
                       )
                     )

(interleave-two-seqs (fn [xs ys]
                       ((fn iter [xs ys res]
                          (if (or (empty? xs) (empty? ys))
                            res
                            (iter (rest xs)
                                  (rest ys)
                                  (concat res (cons (first xs) (cons (first ys) ()))) ))) xs ys ())))

(interleave-two-seqs #(mapcat list %1 %2))
(interleave-two-seqs #(flatten (map vector %1 %2)))
(interleave-two-seqs #(mapcat vector %1 %2))
(interleave-two-seqs (fn [s1 s2]
                       (flatten (map list s1 s2))))
(interleave-two-seqs #(mapcat (fn [x y] (list x y)) %1 %2))