(ns clojure4j.explorer.questions.$51to60.#53-longest-increasing-sub-seq)
(defn longest-increasing-sub-seq
  "Given a vector of integers, find the longest consecutive sub-sequence of increasing numbers.
   If two sub-sequences have the same length, use the one that occurs first.
   An increasing sub-sequence must have a length of 2 or greater to qualify."
  [arg]
  (println "================")
  (println (arg [1 0 1 2 3 0 4 5]))
  (println (= (arg [1 0 1 2 3 0 4 5]) [0 1 2 3]))
  (println (= (arg [5 6 1 3 2 7]) [5 6]))
  (println (= (arg [2 3 3 4 5]) [3 4 5]))
  (println (= (arg [7 6 5 4]) []))
  )

;; 这是内层函数
;; 接受一个coll
;; 查找coll只中递增的序列并返回
(println ((fn [coll] (let [len (count (take-while true? (map #(= (inc %1) %2) coll (rest coll))))]
                       (do
                         (println len)
                         (if (= (count (rest coll)) len)
                           coll
                           (take (inc len) coll)
                           )
                         )
                       )
            ) [0 2 2 3 0 4 5]
          )
         )

;; 结合内层函数，每次返回的序列与上一次返回的做比较，获取最长的序列返回，一个元素或者没元素的，直接返回[]，题目要求
(longest-increasing-sub-seq (fn [coll] (loop [res [], res' [], sub-coll coll]
                                         (cond
                                           (empty? sub-coll)
                                           (if (> (count res) 1)
                                             res
                                             []
                                             )
                                           :else
                                           (recur (cond
                                                    (< (count res) (count res'))
                                                    res'
                                                    :else
                                                    res
                                                    ) ((fn [coll] (let [len (count (take-while true? (map #(= (inc %1) %2) coll (rest coll))))]
                                                                    (if (= (count (rest coll)) len)
                                                                      coll
                                                                      (take (inc len) coll)
                                                                      )
                                                                    )
                                                         ) sub-coll
                                                       ) (rest sub-coll))
                                           )
                                         )
                              )
                            )

(longest-increasing-sub-seq (fn [xs] (->> (map #(vector %1 (- %1 %2)) (rest xs) xs)
                                          (cons [(first xs) -1])
                                          (#(loop [agg () [x & tail] %]
                                              (if (empty? tail)
                                                agg
                                                (let [[ys y-tail] (split-with (comp pos? second) tail)
                                                      zs (cons x ys)]
                                                  (if (> (count zs) (count agg))
                                                    (recur zs y-tail)
                                                    (recur agg y-tail))))))
                                          (map first)
                                          (#(if (seq (rest %)) % ())))))

(longest-increasing-sub-seq (fn [x] (->> (reductions #(if (= %2 (inc (last %))) (conj % %2) [%2]) [-2] x)
                                         (filter #(< 1 (count %)))
                                         (reduce #(if (< (count %) (count %2)) %2 %) []))))
