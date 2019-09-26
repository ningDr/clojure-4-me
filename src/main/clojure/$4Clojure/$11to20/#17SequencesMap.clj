(ns $4Clojure.$2to20.#17SequencesMap)
(defn sequenceMap
  "The map function takes two arguments: a function (f) and a sequence (s).
  Map returns a new sequence consisting of the result of applying f to each item of s.
  Do not confuse the map function with the map data structure."
  [arg]
  ; (println '(1 2 3))
  ; (println (map #(+ % 5) '(1 2 3)))

  (println (= arg (map #(+ % 5) '(1 2 3))))                 ;map函数根据函数计算序列后，序列类型不变
  )
(sequenceMap (list 6 7 8))                                  ;参与map运算的本身是list，故与list相等
(sequenceMap '(6 7 8))                                      ;list的另一种定义方式
(sequenceMap (vector 6 7 8))                                ;vector向量是有序的，故与list相等

