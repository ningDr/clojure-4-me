(ns clojure4j.explorer.questions.$61to70.#63-group-a-sequence)
(defn group-a-sequence
  "
   Given a function f and a sequence s, write a function which returns a map.
   The keys should be the values of f applied to each item in s.
   The value at each key should be a vector of corresponding items in the order they appear in s.
  "
  [arg]
  (println "===============")
  (println (arg #(> % 5) [1 3 6 8]))
  (println (arg #(apply / %) [[1 2] [2 4] [4 6] [3 6]]))
  (println (= (arg #(> % 5) [1 3 6 8]) {false [1 3], true [6 8]}))
  (println (= (arg #(apply / %) [[1 2] [2 4] [4 6] [3 6]]) {1/2 [[1 2] [2 4] [3 6]], 2/3 [[4 6]]}))
  (println (= (arg count [[1] [1 2] [3] [1 2 3] [2 3]]) {1 [[1] [3]], 2 [[1 2] [2 3]], 3 [[1 2 3]]}))
  )
(group-a-sequence group-by)

;; 先获取所有函数f应用到coll后的值组成的seq：key
;; 计算distinct key：distinct
;; 初始化map：distinct没一项为key，其余为空的vector
;; 遍历每个key，然后把map中相同的key的值取出来和当前coll的第一项拼接
;; 递归后就是结果了
;(println (zipmap [1 2 3 4 5] (take 5 (cycle (vector [])))) )
(group-a-sequence (fn [f coll] (loop [key (map f coll), distinct (distinct key), coll coll, map (zipmap distinct (take (count distinct) (cycle (vector []))))]
                                 (if (empty? key)
                                   map
                                   (recur (rest key) (rest distinct) (rest coll) (assoc map (nth key 0) (conj (get map (nth key 0)) (nth coll 0))))
                                   )
                                 )
                    )
                  )


(group-a-sequence (fn [f col] (reduce #(update-in %1 [(f %2)] (fn [v] (conj (or v []) %2)))
                                      {}
                                      col)))

(group-a-sequence #(reduce (fn [res x] (assoc res (% x) (conj (get res (% x) []) x))) {} %2))

(group-a-sequence #(apply merge-with concat (map (fn [x] {(%1 x) [x]}) %2)))

(group-a-sequence #(loop [result {}, coll %2]
                     (if-let [item (first coll)]
                       (let [k (%1 item) v (result k [])]
                         (recur (assoc result k (conj v item)) (next coll)))
                       result)))

(group-a-sequence (fn [f s]
                    (apply merge-with concat (map #(hash-map (f %1) [%1]) s))))

(group-a-sequence (fn [f s] (reduce (fn [acc v]
                                      (merge-with
                                        concat
                                        acc
                                        {(f v) [v]}))
                                    {}
                                    s)))

(group-a-sequence (comp #(zipmap (map ffirst %) (map (partial map peek) %)) (partial partition-by first) sort (fn [f x] (map-indexed #(vector (f %2) %1 %2) x))))