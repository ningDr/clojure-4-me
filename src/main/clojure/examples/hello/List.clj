(ns examples.hello.List)
(defn fList
  "clojure list"
  []
  (def x (list 1 2 3 4))
  (println x)

  (def y (list 'a 'b 'c 'd))
  (println y)
  (def c (list* x y (list 5 6 7 8)))
  (println (first c) )
  (println (nth c 2) )
  (println (rest c))
  )
(fList)