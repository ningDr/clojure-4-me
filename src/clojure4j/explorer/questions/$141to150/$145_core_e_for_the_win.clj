(ns clojure4j.explorer.questions.$141to150.$145-core-e-for-the-win)
(defn for-the-win
  "Clojure's for macro is a tremendously versatile mechanism for
   producing a sequence based on some other sequence(s).
   It can take some time to understand how to use it properly,
   but that investment will be paid back with clear, concise
   sequence-wrangling later. With that in mind,read over these
   for expressions and try to see how each of them produces the same result."
  [arg]
  (println "==================")
  (println (= arg (for [x (range 40)
                        :when (= 1 (rem x 4))]
                    x)))
  (println (= arg (for [x (iterate #(+ 4 %) 0)
                        :let [z (inc x)]
                        :while (< z 40)]
                    z)))
  (println (= arg (for [[x y] (partition 2 (range 20))]
                    (+ x y)))))
(for-the-win [1 5 9 13 17 21 25 29 33 37])