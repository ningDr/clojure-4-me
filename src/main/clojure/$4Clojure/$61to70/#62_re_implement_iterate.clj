(ns $4Clojure.$61to70.#62-re-implement-iterate)
(defn re-implement-iterate
  "
   Given a side-effect free function f and an initial value x
   write a function which returns an infinite lazy sequence of x, (f x), (f (f x)), (f (f (f x))), etc.
  "
  [arg]
  (println "================")
  (println (= (take 5 (arg #(* 2 %) 1)) [1 2 4 8 16]))
  (println (= (take 100 (arg inc 0)) (take 100 (range))))
  (println (= (take 9 (arg #(inc (mod % 3)) 1)) (take 9 (cycle [1 2 3]))))
  )
(re-implement-iterate iterate)
(re-implement-iterate (fn myFn [f x] (lazy-seq (cons x (myFn f (f x))))))


(re-implement-iterate #(letfn [(ff
                                 ([] (ff %2))
                                 ([n] (lazy-seq (cons n (ff (%1 n))))))]
                         (ff)))
(re-implement-iterate #(reductions (fn [i _] (%1 i)) (repeat %2)))
(re-implement-iterate (fn [f x] (reductions #(%2 %1) x (repeat f))))
(re-implement-iterate (fn [f x]
                        (map (fn [n]
                               (loop [n n
                                      fx x]
                                 (if (zero? n) fx
                                               (recur (dec n) (f fx))))
                               )
                             (range))))