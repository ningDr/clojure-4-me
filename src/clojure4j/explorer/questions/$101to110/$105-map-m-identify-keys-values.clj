(ns clojure4j.explorer.questions.$101to110.$105-map-m-identify-keys-values)
(defn identify-keys-values
  "Given an input sequence of keywords and numbers,
   create a map such that each key in the map is a keyword,
   and the value is a sequence of all the numbers (if any)
   between it and the next keyword in the sequence."
  [arg]
  (println "=========================")
  (println (= {} (arg [])))
  (println (= {:a [1]} (arg [:a 1])))
  (println (= {:a [1], :b [2]} (arg [:a 1, :b 2])))
  (println (= {:a [1 2 3], :b [], :c [4]} (arg [:a 1 2 3 :b :c 4]))))

(defn arg [coll]
  (letfn [(get-num [coll]
            (loop [res [], [a & yy] coll]
              (if-not (number? a)
                res
                (recur (conj res a) yy))))
          (get-map-coll  [[a & b] m]
            (let [v (get-num b)
                  c (count v)]
              [(conj m {a v}) (drop c b)]))]
    (if (empty? coll)
      {}
      (loop [[res col] (get-map-coll coll {})]
        (if (empty? col)
          res
          (recur (get-map-coll col res)))))))

;; =========================

#(->> (partition-by keyword? %)
      (partition 2)
      (reduce (fn [agg [k v]]
                (-> (zipmap (reverse k) (cons v (repeat ())))
                    (into agg))) {}))

#(->> %
      (partition-by keyword?)
      (partition 2)
      (reduce (fn [m [k v]]
                (into m (zipmap (reverse k) (cons v (repeat ()))))) {}))

(fn [x] (apply hash-map (map #(if (keyword? (first %)) (first %) (vec (rest %))) (partition-by keyword? (flatten (map #(if (keyword? %) [% nil] %)  x))))))

#(loop [result {}, k (first %), v [], remaining (rest %)]
   (if-let [newVal (first remaining)]
     (if
      (keyword? newVal) (recur (assoc result k v) newVal [] (rest remaining))
      (recur result k (conj v newVal) (rest remaining)))
     (if (nil? k) result
         (assoc result k v))))

(fn [xs]
  (let [r (partition-by keyword? xs)]
    (merge
     (zipmap
      (map last (take-nth 2 r))
      (take-nth 2 (rest r)))
     (zipmap
      (flatten (map drop-last (take-nth 2 r)))
      (repeat [])))))

(fn mf [s]
  (if (seq s)
    (merge {(first s) (take-while (complement keyword?) (rest s))}
           (mf (drop-while (complement keyword?) (rest s)))) {}))

(fn [x] 
  (apply hash-map 
         (map
          #(if (keyword? (first %)) (first %) (keep identity %))
          (partition-by #(when (keyword? %) %) (interpose nil x)))))



















(fn [coll]
  (letfn [(get-num [coll]
            (loop [res [], [a & yy] coll]
              (if-not (number? a)
                res
                (recur (conj res a) yy))))]
    (loop [v (get-num (rest coll)), k (first coll), res {k v}, col coll]
      (let [col1 (if (empty? v) (drop 2 col) (drop (inc (count v)) col)) 
            k1 (if (empty? v) (second col1) (first col1))]
        (if (empty? col1)
          res
          (do (println v "==" k "===" res "-" col1)
              (recur (get-num col1)
                     k1
                     (assoc res k v)
                     col1)))))))
