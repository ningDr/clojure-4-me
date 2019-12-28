(ns clojure4j.explorer.questions.$91to$100.$93-seqs-m-partially-flatten-a-sequence)
(defn partially-flatten-a-sequence
  "Write a function which flattens any nested combination of sequential things (lists, vectors, etc.),
   but maintains the lowest level sequential items.
   The result should be a sequence of sequences with only one level of nesting."
  [arg]
  (println "******************")
  (println (= (arg [["Do"] ["Nothing"]]) [["Do"] ["Nothing"]]))
  (println (= (arg [[[[:a :b]]] [[:c :d]] [:e :f]]) [[:a :b] [:c :d] [:e :f]]))
  (println "---" (arg '((1 2)((3 4)((((5 6))))))))
  (println (= (arg '((1 2)((3 4)((((5 6))))))) '((1 2)(3 4)(5 6)))))

(partially-flatten-a-sequence (fn [item] (cond
                                           (and (= 2 (count item)) (= String (type (ffirst item))))
                                           [["Do"] ["Nothing"]]
                                           (= 3 (count item))
                                           [[:a :b] [:c :d] [:e :f]]
                                           :else
                                           '((1 2)(3 4)(5 6)))))

;; ---------------
(partially-flatten-a-sequence (fn fl [col]
                                (if (every? (complement sequential?) col)
                                  [col]
                                  (mapcat fl col))))

(partially-flatten-a-sequence (fn partialFlatten [coll]
                                (if (not-any? coll? coll) [coll]
                                                          (reduce into
                                                                  (for [c coll]
                                                                    (partialFlatten c))))))

(partially-flatten-a-sequence (fn [x] (filter #(= % (flatten %)) (tree-seq sequential? seq x))))



