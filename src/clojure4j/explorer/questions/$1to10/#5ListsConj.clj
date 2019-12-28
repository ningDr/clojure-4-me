(ns clojure4j.explorer.questions.$1to10.#5ListsConj)
(defn listConj
  "conj 将元素按前一个list‘翻折’过去 "
  [z]
  (def x (conj '(2 3 4) 1))
  (def y (conj '(3 4) 2 1))
  (println x)
  (println y)
  (println (list 1 2 3 4))
  (println z)
  (println (= z x))
  (println (= z y))
  (println (= (list 1 2 3 4) (conj '(2 3 4) 1)))
  )
(listConj (list 1 2 3 4))
