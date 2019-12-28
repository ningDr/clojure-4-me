(ns clojure4j.explorer.questions.$51to60.#58-function-composition)
(defn function-composition
  "
    Write a function which allows you to create function compositions.
    The parameter list should take a variable number of functions,
    and create a function that applies them from right-to-left.
  "
  [arg]
  (println "=================")
  (println ((arg rest reverse) [1 2 3 4]))
  (println (= [3 2 1] ((arg rest reverse) [1 2 3 4])))
  (println (= 5 ((arg (partial + 3) second) [1 2 3 4])))
  (println (= true ((arg zero? #(mod % 8) +) 3 5 7 9)))
  (println (= "HELLO" ((arg #(.toUpperCase %) #(apply str %) take) 5 "hello world")))
  )
(println (apply + [1 2 3 4 5 6 7]))

(function-composition (fn ([f g] (fn [& arg] (f (g (seq (apply map identity arg))))))
                        ([f g z] (fn [& arg] (f (g (nth (apply map z (partition-all 1 arg)) 0))))
                         )
                        )
                      )

(function-composition (fn [& fs]
                        (fn [& args]
                          (first (reduce #(list (apply %2 %1)) args (reverse fs))))))

(function-composition (fn ([f g] #(f (apply g %&)))
                        ([f g h] #(f (g (apply h %&))))))

(function-composition (fn [& x] (reduce (fn [f g] (fn [& a] (f (apply g a)))) x)))

(function-composition (fn [& xs] (fn [& ys] (reduce #(%2 %1) (apply (last xs) ys) (rest (reverse xs))))))

(function-composition (fn [& fns] (fn [& args] (loop [rfns (rest (reverse fns)), v (apply (last fns) args)]
                                                 (if (seq rfns)
                                                   (recur (rest rfns) ((first rfns) v))
                                                   v
                                                   )
                                                 )
                                    )
                        )
                      )