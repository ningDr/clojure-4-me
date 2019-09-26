(ns $4Clojure.$41to50.#41-drop-every-nth-item)
(defn drop-every-nth-item
  "Write a function which drops every Nth item from a sequence."
  [arg]
  (println "==================")
  (println (arg [1 2 3 4 5 6 7 8] 3))
  (println (= (arg [1 2 3 4 5 6 7 8] 3) [1 2 4 5 7 8]))
  (println (= (arg [:a :b :c :d :e :f] 2) [:a :c :e]))
  (println (= (arg [1 2 3 4 5 6] 4) [1 2 3 5 6]))
  )

(drop-every-nth-item #(loop [coll %1, nth %2, res '(), i 0, y #{}]
                        (if (empty? coll)
                          (reverse res)
                          (recur (rest coll) nth (if ((complement contains?) y i)
                                                   (conj res (first coll))
                                                   res
                                                   )
                                 (inc i) (conj y (+ (- nth 1) (* i nth)))
                                 )
                          )
                        )
                     )

(drop-every-nth-item #(apply concat (partition-all (dec %2) %2 %1)))
(drop-every-nth-item (fn [xs n] (flatten (map #(if (= (count %) n) (take (- n 1) %) %) (partition-all n xs)))))
(drop-every-nth-item (fn f ([s c] (f s c [])) ([s c r] (if (empty? s)
                                                         (flatten r)
                                                         (recur (drop c s) c (conj r (take (- c 1) s)))))))
(drop-every-nth-item #(loop [result [], index %2, input %1]
                        (cond
                          (nil? input) result
                          (= index 1) (recur result %2 (next input))
                          :else (recur (conj result (first input)) (dec index) (next input)))))
(drop-every-nth-item (fn [coll n]
                       (->> (partition-all n coll)
                            (map (partial take (dec n)))
                            (flatten))))
(drop-every-nth-item (fn [x s] (remove nil? (map #(if %2 %1 nil) x (cycle (concat (repeat (dec s) true) '(false)))))))