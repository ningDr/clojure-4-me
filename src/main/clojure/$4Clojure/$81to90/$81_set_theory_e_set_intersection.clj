(ns $4Clojure.$81to90.$81-set-theory-e-set-intersection
  (:require [clojure.set :as c-set]))
(defn set-intersection
  "Write a function which returns the intersection of two sets.
   The intersection is the sub-set of items that each set has in common"
  [arg]
  (println "+++++++++++++++++++++++++++++++++++")
  (println (arg #{0 1 2 3} #{2 3 4 5}))
  (println (= (arg #{0 1 2 3} #{2 3 4 5}) #{2 3}))
  (println (= (arg #{0 1 2} #{3 4 5}) #{}))
  (println (= (arg #{:a :b :c :d} #{:c :e :a :f :d}) #{:a :c :d})))

;(set-intersection c-set/intersection)
(set-intersection (fn [a b] (set (filter (fn [item] (contains? b item)) (seq a) ))))
;; ---------------------------------------------------------------------------------------------------------------------
(set-intersection #(set (for [x %1 :when (%2 x)] x)))

(set-intersection (comp set filter))

(set-intersection #(clojure.set/difference %1 (clojure.set/difference %1 %2)))

(set-intersection (fn [x y] (reduce #(if (contains? x %2) (conj %1 %2) %1) #{}  y)))