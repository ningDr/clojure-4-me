(ns $4Clojure.$71to80.#78-core-functions-m-reimplement-trampoline)
(defn reimplement-trampoline
  "Reimplement the function described in \"Intro to Trampoline\".\n"
  [arg]
  (println "============")
  (println (letfn [(triple [x] #(sub-two (* 3 x)))
                   (sub-two [x] #(stop? (- x 2)))
                   (stop? [x] (if (> x 50) x #(triple x)))]
             (arg triple 2)
             )
           )


  (println (= (letfn [(triple [x] #(sub-two (* 3 x)))
                      (sub-two [x] #(stop? (- x 2)))
                      (stop? [x] (if (> x 50) x #(triple x)))]
                (arg triple 2))
              82))

  (println (= (letfn [(my-even? [x] (if (zero? x) true #(my-odd? (dec x))))
                      (my-odd? [x] (if (zero? x) false #(my-even? (dec x))))]
                (map (partial arg my-even?) (range 6)))
              [true false true false true false]))
  )
(reimplement-trampoline trampoline)
(println (apply (fn [] (+ 1 2)) '()))
;; => 3
(println (apply (fn [] (+ 1 2)) ()))
;; => 3
(println (type ()))
;; => clojure.lang.PersistentList$EmptyList
(println (type '()))
;; => clojure.lang.PersistentList$EmptyList
;(println (type (1)))
;; => java.lang.ClassCastException: java.lang.Long cannot be cast to clojure.lang.IFn
(println (type '(1)))
;; => clojure.lang.PersistentList
;; 看下边，((fn x))是定义+执行函数x，而(fn x)只是定义不执行
(println (fn [] (+ 1 3)))
;; => #object[$4Clojure.$71to80._SHARP_78_core_functions_m_reimplement_trampoline$eval211$fn__212 0x4426bff1 $4Clojure.$71to80._SHARP_78_core_functions_m_reimplement_trampoline$eval211$fn__212@4426bff1]
(println ((fn [] (+ 1 3))))
;; => 4
(reimplement-trampoline (fn aa
                          ([f]
                           (let [ret (f)]
                             (if (fn? ret)
                               (recur ret)
                               ret)))
                          ([f & args]
                           (aa #(apply f args))
                           )
                          )
                        )

(reimplement-trampoline #(->> (%1 %2)
                              (iterate (fn [f] (f)))
                              (drop-while fn?)
                              (first)))

(reimplement-trampoline (fn tr
                          [f & args]
                          (let [x (apply f args)]
                            (if (fn? x)
                              (recur x ())
                              x))))

(reimplement-trampoline (fn [& as] (let [r (apply (first as) (rest as))]
                                     (if (fn? r)
                                       (recur [r])
                                       r
                                       )
                                     )
                          )
                        )

(reimplement-trampoline (fn [f x] (loop [x (f x)]
                                    (if (fn? x)
                                      (recur (x))
                                      x
                                      )
                                    )
                          )
                        )

