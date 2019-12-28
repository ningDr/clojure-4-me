(ns clojure4j.explorer.questions.$41to50.#49-split-a-sequence)
(defn split-a-sequence
  "Write a function which will split a sequence into two parts."
  [arg]
  (println "==================")
  (println (arg 3 [1 2 3 4 5 6]))
  (println (= (arg 3 [1 2 3 4 5 6]) [[1 2 3] [4 5 6]]))
  (println (= (arg 1 [:a :b :c :d]) [[:a] [:b :c :d]]))
  (println (= (arg 2 [[1 2] [3 4] [5 6]]) [[[1 2] [3 4]] [[5 6]]]))
  )

(split-a-sequence #(conj [] (take %1 %2) (take-last (- (count %2) %1) %2)))

(split-a-sequence (fn [n xs] (list (take n xs) (drop n xs))))
(split-a-sequence (juxt take drop))
(split-a-sequence #(into (into [] (vector (take %1 %2))) (vector (drop %1 %2))))
(split-a-sequence (fn[pos, col] [(subvec col 0 pos)(subvec col pos)]))
(split-a-sequence (comp #(list (first %) (apply concat (next %))) partition-all))