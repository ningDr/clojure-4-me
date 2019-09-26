(ns $4Clojure.$61to70.#65-seqs-m-black-box-testing)
(defn black-box-testing
  "
    Clojure has many sequence types, which act in subtly different ways.
    The core functions typically convert them into a uniform \"sequence\" type and work with them that way,
    but it can be important to understand the behavioral and performance differences so that you know which kind is appropriate for your application.

    Write a function which takes a collection and returns one of :map, :set, :list, or :vector - describing the type of collection it was given.
    You won't be allowed to inspect their class or use the built-in predicates like list? -
    the point is to poke at them and understand their behavior.
  "
  [arg]
  (println "=========")
  (println (arg {:a 1, :b 2}))
  (println (arg (range (rand-int 20))))
  (println (arg [1 2 3 4 5 6]))
  (println (arg #{10 (rand-int 5)}))
  (println (map arg [{} #{} [] ()]))

  (println (= :map (arg {:a 1, :b 2})))
  (println (= :list (arg (range (rand-int 20)))))
  (println (= :vector (arg [1 2 3 4 5 6])))
  (println (= :set (arg #{10 (rand-int 5)})))
  (println (= [:map :set :vector :list] (map arg [{} #{} [] ()]))))

;
;(black-box-testing #(let [coll %] (if (contains? (merge coll {:a 4 :b 5}) {:a 4 :b 5})
;                                    :set
;                                    :map
;                                    )
;                                  )
;                   )

(black-box-testing #(let [x (first (seq (str %)))]
                      (cond (= x \{) :map
                            (= x \#) :set
                            (= x \[) :vector
                            :else :list)))

(black-box-testing (fn [col] (let [x (gensym), y (gensym), map-col (into col [[x y]]), n-col #(into col [x y])]
                               (cond
                                 (= y (x map-col)) :map
                                 (= (count (n-col)) (-> (into (n-col) [x]) count)) :set
                                 (= (first (n-col)) y) :list
                                 :else :vector))))

(black-box-testing #({{} :map #{} :set} (empty %) (if (reversible? %) :vector :list)))

(black-box-testing #(let [r (str %)]
                      (if (.contains r "#")
                        :set
                        (if (.contains r "{")
                          :map
                          (if (.contains r "[")
                            :vector
                            :list)))))

(black-box-testing #(if (= % (merge % %)) :map
                                          (let [extended (conj % :first :second)]
                                            (cond
                                              (= extended (into extended extended)) :set
                                              (= (first extended) :second) :list
                                              :else :vector))))

;(black-box-testing #(if (and (associative? %) (< 1 (count (flatten (list (last (seq (assoc % 0 1))))))))
;                      :map
;                      (let [c (conj % :a :b :c)]
;                        (cond
;                          (= (take-last 3 c) [:a :b :c]) :vector
;                          (= (take 3 c) [:c :b :a]) :list
;                          true :set))))

;(black-box-testing #(condp = (nth (str %) 0)
;                      \{ :map
;                      \c :list
;                      \[ :vector
;                      \# :set)
;                   )

;(black-box-testing (comp #(cond (= % {}) :map (= % #{}) :set (= (conj % 1 2) [1 2]) :vector true :list) empty))