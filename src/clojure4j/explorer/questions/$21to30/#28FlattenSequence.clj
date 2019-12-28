(ns clojure4j.explorer.questions.$21to30.#28FlattenSequence)
(defn flattenSequence
  "Write a function which flattens a sequence."
  [arg]
  (println "=================")
  (println (= (arg '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6)))
  (println (arg '((1 2) 3 [4 [5 6]])))

  (println (= (arg ["a" ["b"] "c"]) '("a" "b" "c")))
  (println (= (arg '((((:a))))) '(:a)))
  )

(flattenSequence (fn [x] (filter (complement sequential?) (rest (tree-seq sequential? seq x)))))

(flattenSequence (fn flat [x] (cond
                                (not (coll? x))
                                [x]                         ;; 最底层时返回一个向量
                                (not (empty? x))
                                (into (flat (first x)) (flat (rest x))) ;; 递归调用自身；into接收两个参数时，合并两个集合。
                                )
                   )
                 )

(flattenSequence (fn iter [xs] (reduce (fn [acc x]
                                         (if (coll? x)
                                           (concat acc (iter x))
                                           (concat acc (cons x ())))) () xs)
                   ))

(flattenSequence (fn flatten* [x]
                   (if (coll? x)
                     (mapcat flatten* x)
                     [x])))

(flattenSequence (fn [s]
                   (loop [s s]
                     (if (some sequential? s)
                       (recur (reduce (fn [acc v]
                                        (if (sequential? v)
                                          (concat acc v)
                                          (concat acc [v])
                                          ))
                                      []
                                      s))
                       s)
                     )
                   ))
