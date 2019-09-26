(ns examples.hello.Seq)
(defn fSeq
  "clojure seq"
  []
  (def x (seq [1 3 2 4]))
  (println x)
  (println (take-last 2 x))
  (println x)

  (println (take 2 x))
  (println x)

  (println (split-at 2 x))
  (println x)
  )
(fSeq)