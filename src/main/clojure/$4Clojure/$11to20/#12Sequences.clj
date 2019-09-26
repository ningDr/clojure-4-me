(ns $4Clojure.$2to20.#12Sequences)
(defn defnSequence
  "All Clojure collections support sequencing. You can operate on sequences with functions like first, second, and last."
  [arg]
  (println (= arg (first '(1 2 3))))                        ;列表
  (println (= arg (second [2 1 3])))                        ;向量
  (println (= arg (last (list 2 3 1))))                     ;列表
  )
(defnSequence 1)