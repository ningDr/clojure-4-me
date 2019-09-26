(ns $4Clojure.$81to90.$84-set-theory-h-transitive-closure
  (:require [clojure.set]))
(defn transitive-closure
  "Write a function which generates the transitive closure of a binary relation.
   The relation will be represented as a set of 2 item vectors."
  [arg]
  (println (let [divides #{[8 4] [9 3] [4 2] [27 9]}]
             (= (arg divides) #{[4 2] [8 4] [8 2] [9 3] [27 9] [27 3]})))
  (println (let [more-legs
                 #{["cat" "man"] ["man" "snake"] ["spider" "cat"]}]
             (= (arg more-legs)
                #{["cat" "man"] ["cat" "snake"] ["man" "snake"]
                  ["spider" "cat"] ["spider" "man"] ["spider" "snake"]})))
  (println (let [progeny
                 #{["father" "son"] ["uncle" "cousin"] ["son" "grandson"]}]
             (= (arg progeny)
                #{["father" "son"] ["father" "grandson"]
                  ["uncle" "cousin"] ["son" "grandson"]}))))

(transitive-closure #(let [vv-seq (seq %)
                           fn-seq (fn [vv-seq]
                                    (filter (complement nil?)
                                            (seq
                                             (set
                                              (apply concat
                                                     ((fn [v-seq]
                                                        (mapv (fn [item]
                                                                (mapv (fn [s-item]
                                                                        (if (and (= 3 (count (clojure.set/union (set item) (set s-item)))) (= (last item) (first s-item)))
                                                                          (vector (first item) (last s-item)))) v-seq)) v-seq)) vv-seq))))))]
                       (loop [l-seq (fn-seq vv-seq), res-set %, i (count res-set)]
                         (if (= i (count l-seq))
                           res-set
                           (recur (fn-seq (seq (apply conj res-set l-seq))) (apply conj res-set l-seq) (count l-seq))))))

;; *************************************************************
(transitive-closure (fn [edges]
                      (let [adj-list (reduce (fn [agg [x y]]
                                               (merge-with into agg {x #{y}})) {} edges)
                            vs (into #{} (keys adj-list))
                            linked-cmp (fn ch [v adj-list visited]
                                         (if (empty? adj-list)
                                           visited
                                           (->> (for [v-adj (adj-list v)
                                                      :when (not (visited v-adj))]
                                                  (ch v-adj (dissoc adj-list v) (into visited [v v-adj])))
                                                (reduce into visited))))

                            vs-cmp (for [v vs]
                                     [v (disj (linked-cmp v adj-list #{}) v)])]

                        (reduce (fn [agg [v cmp]]
                                  (into agg (map #(vector v %) cmp))) #{} vs-cmp))))

;; 解构用的很彻底
(transitive-closure #(loop [s %]
                       (let [n (into s
                                     (for [[a b] s [c d] s
                                           :when (= b c)]
                                       [a d]))]
                         (println "s=" s ";n=" n)
                         (if (= n s)
                           n
                           (recur n)))))

(transitive-closure (letfn [(update [e x] (let [in  (for [ei e :when (= x (second ei))] (first  ei))
                                                out (for [ei e :when (= x (first  ei))] (second ei))]
                                            (into e (for [v1 in v2 out] [v1 v2]))))
                            (trans [e] (reduce update e (distinct (flatten (vec e)))))]
                      trans))
