(ns clojure4j.explorer.questions.$91to$100.$91-graph-theory-h-graph-connectivity
  (:require [clojure.set]))
(defn graph-connectivity
  "Given a graph, determine whether the graph is connected.
   A connected graph is such that a path exists between any two given nodes.
   -Your function must return true if the graph is connected and false otherwise.
   -You will be given a set of tuples representing the edges of a graph.
    Each member of a tuple being a vertex/node in the graph.
   -Each edge is undirected (can be traversed either direction)."
  [arg]
  (println "++++++++++++++++++++++++++")
  (println (= true (arg #{[:a :a]})))
  (println (= true (arg #{[:a :b]})))
  (println (= false (arg #{[1 2] [2 3] [3 1] [4 5] [5 6] [6 4]})))
  (println (= true (arg #{[1 2] [2 3] [3 1] [4 5] [5 6] [6 4] [3 4]})))
  (println (= false (arg #{[:a :b] [:b :c] [:c :d]
                           [:x :y] [:d :a] [:b :e]})))
  (println (= true (arg #{[:a :b] [:b :c] [:c :d]
                          [:x :y] [:d :a] [:b :e] [:x :a]}))))

(graph-connectivity (fn [a] (let [item-seq (reduce #(clojure.set/union %1 (set %2)) #{} a)
                                  item-set (reduce #(conj %1 (set %2)) #{} a)
                                  item-map (reduce #(merge %1 %2)
                                                   {}
                                                   (mapv #(hash-map %1 %2)
                                                         item-seq
                                                         (mapv (fn [item] (apply clojure.set/union
                                                                                 (filter (fn [i-set] (contains? i-set item)) item-set)
                                                                                 )) item-seq)))]
                              (letfn [(get-result [e] (reduce (fn [x y] (conj x y)) {} (for [x e]
                                                                                         {(get x 0) (clojure.set/union (get x 1)
                                                                                                                       (reduce #(clojure.set/union %1 (get e %2)) #{} (get x 1)))}
                                                                                         )))]
                                (loop [e item-map, f (get-result e)]
                                  (if (= e f)
                                    (do
                                      (print "item-seq=" e)
                                      (println "(first (seq item-map))=" (get f (first (seq item-seq))))
                                      (= item-seq (get f (first (seq item-seq)))))
                                    (recur f (get-result f))))))))
;; ----------------------------------------------
(graph-connectivity (fn [edges]
                      (let [adj-list (reduce (fn [agg [x y]]
                                               (merge-with into agg (into {} [[x #{y}] [y #{x}]]))) {}
                                             edges)
                            vs (into #{} (keys adj-list))
                            linked-cmp (fn ch [v adj-list visited]
                                         (if (empty? adj-list)
                                           visited
                                           (->> (for [v-adj (adj-list v)
                                                      :when (not (visited v-adj))]
                                                  (ch v-adj (dissoc adj-list v) (into visited [v v-adj])))
                                                (reduce into visited))))]

                        (-> vs first
                            (linked-cmp adj-list #{})
                            (= vs)))))

(graph-connectivity (fn [s] (apply = (vals (reduce
                                            (fn [g [a b]]
                                              (let [r (apply conj
                                                             (g a #{a})
                                                             (g b #{b}))]
                                                (reduce #(assoc % %2 r)
                                                        g
                                                        r))) {} s)))))

(graph-connectivity (fn [x] (letfn [(adjlist [e] (let [ee (concat e (map reverse e))
                                                       ve (group-by first ee)]
                                                   (into {} (for [[k v] ve] [k (map second v)]))))
                                    (connected? [g] (letfn [(add-vertex [s v] (if (s v) s
                                                                                        (reduce add-vertex (conj s v) (g v))))]
                                                      (= (count (add-vertex #{} (ffirst g))) (count g))))]
                              (connected? (adjlist x)))))