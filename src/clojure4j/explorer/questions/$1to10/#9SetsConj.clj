(ns clojure4j.explorer.questions.$1to10.#9SetsConj)
(defn setsConj
  "set的conj，类似于union合并操作，只不过参数不是集合"
  [arg]
  (println (conj (set '(:a :b :c)) :d :f :e))

  (println (= #{1 2 3 4} (conj #{2, 3, 1} arg)))
)
(setsConj 4)