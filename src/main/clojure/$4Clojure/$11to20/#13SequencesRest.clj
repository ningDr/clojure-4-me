(ns $4Clojure.$2to20.#13SequencesRest
  (:require [clojure.core :as core]))

(defn sequenceRest
  "The rest function will return all the items of a sequence except the first."
  ;rest返回sequence的除了第一个元素的其他所有
  [arg]
  (println (= arg (core/rest (list 10 20 30 40))))
  (println (= arg (core/rest (vector 10 20 30 40))))
  (println (core/rest (set '(10 20 30 40))))                ;set集合无序
  (println (= arg (core/rest (set '(10 20 30 40)))))
  )
(sequenceRest [20 30 40])
