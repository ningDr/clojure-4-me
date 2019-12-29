(ns clojure4j.explorer.questions.$101to110.$103-m-seqs-combinatorics-generating-k-combinations)
(defn generating-k-combinations
  "Given a sequence S consisting of n elements generate all k-combinations of S,
   i. e. generate all possible sets consisting of k distinct elements taken from S.
   The number of k-combinations for a sequence is equal to the binomial coefficient."
  [arg]
  (println "================")
  (println (= (arg 1 #{4 5 6}) #{#{4} #{5} #{6}}))
  (println (= (arg 10 #{4 5 6}) #{}))
  (println (= (arg 2 #{0 1 2}) #{#{0 1} #{0 2} #{1 2}}))
  (println (= (arg 3 #{0 1 2 3 4}) #{#{0 1 2} #{0 1 3} #{0 1 4} #{0 2 3} #{0 2 4}
                                     #{0 3 4} #{1 2 3} #{1 2 4} #{1 3 4} #{2 3 4}}))
  (println (= (arg 4 #{[1 2 3] :a "abc" "efg"}) #{#{[1 2 3] :a "abc" "efg"}}))
  (println (= (arg 2 #{[1 2 3] :a "abc" "efg"}) #{#{[1 2 3] :a} #{[1 2 3] "abc"} #{[1 2 3] "efg"}
                                                  #{:a "abc"} #{:a "efg"} #{"abc" "efg"}})))

(def arg (fn [len coll]
           (letfn [(gen-set [a b]
                     (set (for [x a, y b]
                            (if (set? y)
                              (conj y x)
                              (into #{} [x y])))))]
             (cond
               (> len (count coll)) #{}
               (= len (count coll)) #{coll}
               :else
               (do (println "11111")
                   (loop [res #{}, a coll, b (gen-set coll coll), i 1]
                     (if (= len (count (first res)))
                       (into #{} res)
                       (do (println "22222==" i)
                           (recur (filter #(= i (count %)) b), a, (gen-set a b), (inc i))))))))))

(generating-k-combinations arg)
;; =====================
(generating-k-combinations (fn ff [n xs]
                             (if (zero? n)
                               #{#{}}
                               (set (for [x xs
                                          y (ff (dec n) (disj xs x))]
                                      (conj y x))))))

(generating-k-combinations (fn [c s]
                             (if (< (count s) c)
                               #{}
                               (if (= (count s) c)
                                 (conj #{} s)
                                 (loop [s s result (set (map (comp set vector) s))]
                                   (if (empty? s)
                                     (set (filter #(= (count %) c) result))
                                     (recur (rest s) (into result (map #(into % (vector (first s))) result)))))))))



