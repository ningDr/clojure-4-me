(ns $4Clojure.$2to20.#18SequenceFilter)
(defn sequenceFilter
  "The filter function takes two arguments: a predicate function (f) and a sequence (s).
  Filter returns a new sequence consisting of all the items of s for which (f item) returns true."
  [arg]
  (println (= arg (filter #(> % 5) '(3 4 5 6 7))))          ;#() 匿名函数的另一种写法
  )
(sequenceFilter '(6 7))