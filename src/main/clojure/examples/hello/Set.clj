(ns examples.hello.Set
  (:require [clojure.set :as set]))

(defn fSet
  "clojure set"
  []
  ; 元素唯一无序
  (def x (set '(1 2 3 4 4 1)))
  (println x)
  (println (sorted-set 2 3 4 5 1))
  (println (get x 2))
  (println (contains? x 6))
  (println (conj x 6))
  (println x)

  (println (disj x 3))
  (println x)

  (println (set/union x (conj x 6)))
  (println (set/difference #{1 2 3 5} #{4 2 3}))
  (println (set/intersection #{1 2 3 5} #{4 2 3}))

  (println (set/subset? #{1 2 3 5} #{1 5 4 2 3}))
  (println (set/superset? #{1 2 3 5} #{1 5 4 2 3}))
  )
(fSet)