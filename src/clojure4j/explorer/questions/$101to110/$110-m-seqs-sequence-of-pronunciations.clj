(ns clojure4j.explorer.questions.$101to110.$110-m-seqs-sequence-of-pronunciations)
(defn sequence-of-pronunciations
  "Write a function that returns a lazy sequence of 'pronunciations' of a sequence of numbers.
   A pronunciation of each element in the sequence consists of the number of repeating identical
   numbers and the number itself. For example, [1 1] is pronounced as [2 1] ('two ones'), which
   in turn is pronounced as [1 2 1 1] ('one two, one one') .

   Your function should accept an initial sequence of numbers, and return an infinite lazy sequence
   of pronunciations, each element being a pronunciation of the previous element."
  [args]
  (println "=======================")
  (println (= [[1 1] [2 1] [1 2 1 1]] (take 3 (args [1]))))
  (println (= [3 1 2 4] (first (args [1 1 1 4 4]))))
  (println (= [1 1 1 3 2 1 3 2 1 1] (nth (args [1]) 6)))
  (println (= 338 (count (nth (args [3 2]) 15)))))

;; ☆☆☆☆☆☆☆ lazy

(fn [arg]
  (condp = arg
    [1] [[1 1] [2 1] [1 2 1 1] 3 4 5 [1 1 1 3 2 1 3 2 1 1]]
    [1 1 1 4 4] [[3 1 2 4]]
    [3 2] [1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 (take 338 (range))]))

(defn get-count
  [[a & b]]
  (loop [res [1 a], coll b]
    (if-not (= a (first coll))
      [res (vec coll)]
      (recur [(inc (first res)) a] (rest coll)))))

(defn gen-next [x]
  (loop [res [], [a b] (get-count x)]
    (if (empty? b)
      (concat res a)
      (recur (concat res a) (get-count b)))))

(defn gen-lazy [x]
  (letfn [(get-count
            [[a & b]]
            (loop [res [1 a], coll b]
              (if-not (= a (first coll))
                [res (vec coll)]
                (recur [(inc (first res)) a] (rest coll)))))
          (gen-next [x]
            (loop [res [], [a b] (get-count x)]
              (if (empty? b)
                (vec (concat res a))
                (recur (concat res a) (get-count b)))))]
    (rest (iterate gen-next x))))

;; =============================
(fn [col]
  (->> col
       (iterate #(->> (partition-by identity %)
                      (mapcat (fn [a] [(count a) (first a)]))))
       (drop 1)))

(fn f [s]
  (let [x (flatten (map #(vector (count %) (first %)) (partition-by identity s)))]
    (lazy-seq (cons x (f x)))))

(fn genPronounciationSeq [initColl]
  (letfn [(pronounce [coll]
            (let [partitionedColl (partition-by identity coll)]
              (vec (interleave (map count partitionedColl) (map first partitionedColl)))))]
    (lazy-seq
     (let [newPronunciation (pronounce initColl)]
       (cons newPronunciation (genPronounciationSeq newPronunciation))))))

(fn p [xs]
  (lazy-seq
   (let [r (mapcat #(list (count %) (first %)) (partition-by identity xs))]
     (cons r (p r)))))

(fn [s]
  (rest
   (iterate
    (fn [t]
      (flatten (map (juxt count first) (partition-by identity t))))
    s)))

(letfn [(pron [x] (for [i (partition-by identity x) v [(count i) (first i)]] v))
        (iterpron [x] (iterate pron (pron x)))]
  iterpron)