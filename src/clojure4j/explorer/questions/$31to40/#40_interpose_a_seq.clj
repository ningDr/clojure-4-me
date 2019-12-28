(ns clojure4j.explorer.questions.$31to40.#40-interpose-a-seq)
(defn interpose-a-seq
  "Write a function which separates the items of a sequence by an arbitrary value."
  [arg]
  (println "===================")
  (println (= (arg 0 [1 2 3]) [1 0 2 0 3]))
  (println (= (apply str (arg ", " ["one" "two" "three"])) "one, two, three"))
  (println (= (arg :z [:a :b :c :d]) [:a :z :b :z :c :z :d]))
  )

(interpose-a-seq #(loop [x %1, coll %2, res '()]
                    (if (empty? coll)
                      (reverse (rest res))
                      (recur x (rest coll) (conj res (first coll) x))
                      )
                    )
                 )

(interpose-a-seq #(butlast (interleave %2 (repeat %1))))
(interpose-a-seq #(rest (interleave (repeat (count %2) %1) %2)))
(interpose-a-seq #(drop-last (interleave %2 (repeat %1))))
(interpose-a-seq (fn [s x] (cons (first x) (mapcat #(list s %) (next x)))))