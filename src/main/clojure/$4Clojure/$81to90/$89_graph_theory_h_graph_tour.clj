(ns $4Clojure.$81to90.$89-graph-theory-h-graph-tour)
(defn graph-tour
  "Starting with a graph you must write a function that returns true
   if it is possible to make a tour of the graph in which every edge is visited exactly once.
   The graph is represented by a vector of tuples,
   where each tuple represents a single edge.
   The rules are:
   - You can start at any node.
   - You must visit each edge exactly once.
   - All edges are undirected."
  [arg]
  (println "============================================")
  (println (= true (arg [[:a :b]])))
  (println (= false (arg [[:a :a] [:b :b]])))
  (println (= false (arg [[:a :b] [:a :b] [:a :c] [:c :a] [:a :d] [:b :d] [:c :d]])))
  (println (= true (arg [[1 2] [2 3] [3 4] [4 1]])))
  (println (= true (arg [[:a :b] [:a :c] [:c :b] [:a :e]
                         [:b :e] [:a :d] [:b :d] [:c :e]
                         [:d :e] [:c :f] [:d :f]])))
  (println (= false (arg [[1 2] [2 3] [2 4] [2 5]]))))

;; (filter (fn [item] (= 1 (count item))) (mapv set [[:a :a] [:b :b]]))
;; (filter (fn [item] (= 1 (count item))) (vals (group-by identity (apply concat [[1 2] [2 3] [2 4] [2 5]]))))
;; (not= (count %) (count (set (mapv set [[:a :b] [:a :b] [:a :c] [:c :a] [:a :d] [:b :d] [:c :d]]))))
(graph-tour (fn [edge]
              (letfn [(none-top? [edge] (zero? (count (filter #(= 1 (count %)) (mapv set edge)))))
                      (less-3-top? [edge] (> 3 (count (filter #(= 1 (count %)) (vals (group-by identity (apply concat edge)))))))
                      (none-twice-edge? [edge] ((complement not=) (count edge) (count (set (mapv set edge)))))]
                (and (none-top? edge) (less-3-top? edge) (none-twice-edge? edge)))))

;; ----------------------------------------
(graph-tour (fn [edges]
              (let [adj-list (reduce (fn [agg [x y]]
                                       (merge-with into agg (into {} [[x #{y}] [y #{x}]]))) {}
                                     edges)
                    vs (into #{} (keys adj-list))
                    even-count? #(-> % count (mod 2) (= 0))
                    nodes-has-even-edges? (->> (vals adj-list) (every? even-count?))
                    linked-cmp (fn ch [v adj-list visited]
                                 (if (empty? adj-list)
                                   visited
                                   (->> (for [v-adj (adj-list v)
                                              :when (not (visited v-adj))]
                                          (ch v-adj (dissoc adj-list v) (into visited [v v-adj])))
                                        (reduce into visited))))]
                (or (< (count edges) 2)
                    (and nodes-has-even-edges?
                         (-> vs first
                             (linked-cmp adj-list #{})
                             (= vs)))))))

(graph-tour (fn eulerian [edges]
              (let [degrees (fn [edges] (apply merge-with + {} (for [[a b] edges :when (not= a b)] {a 1 b 1})))
                    gdeg (degrees edges)]
                (and
                 (not (empty? gdeg))
                 (->> (vals gdeg)
                      (filter odd?)
                      count
                      (#(or (= 2 %) (= 0 %))))))))

(graph-tour (fn
              f
              [g]
              (letfn [(rf [s e]
                        (let [p (split-with #(not= e %) s)]
                          (concat
                           (-> p first)
                           (-> p second rest))))
                      (t [current seen remaining]
                        (let [nexts (filter #(or (= current (first %)) (= current (last %))) remaining)]
                          (cond (empty? remaining) true
                                (empty? nexts) false
                                :e (reduce #(or %1 %2) (map #(t (first (filter (fn [x] (not= current x)) %))
                                                                (conj seen %)
                                                                (rf remaining %)
                                                                ) nexts)))))]
                (t (ffirst g) [] g)
                )))

(graph-tour (letfn [(connected? [e] (if (>= 1 (count e)) true (let [v1 (ffirst e)
                                                                    in (for [ei e :when (= v1 (second ei))] (first ei))
                                                                    out (for [ei e :when (= v1 (first ei))] (second ei))
                                                                    e1 (filter (partial not= v1) (concat in out))]
                                                                (if (empty? e1)
                                                                  false
                                                                  (recur (remove #(some #{v1} %) e))))))
                    (even-degree? [e] (>= 2 (count (remove even? (vals (frequencies (flatten e)))))))
                    (tour? [e] (and (connected? e) (even-degree? e)))]
              tour?))