(ns $4Clojure.$81to90.$83-nil-e-a-half-truth)
(defn a-half-truth
  "Write a function which takes a variable number of booleans.
   Your function should return true if some of the parameters are true,
   but not all of the parameters are true.
   Otherwise your function should return false."
  [arg]
  (println "******************************")
  (println (= false (arg false false)))
  (println (= true (arg true false)))
  (println (= false (arg true)))
  (println (= true (arg false true false)))
  (println (= false (arg true true true)))
  (println (= true (arg true true true false)))
  (println (= false (arg true true))))

(a-half-truth (fn [& boolean-seq] (= 2 (count (set boolean-seq)))))
;; -------------------------------------------

(a-half-truth #(cond
                 (every? identity %&) false
                 (some identity %&) true
                 :else false))

(a-half-truth not=)

(a-half-truth (fn [& s]
                (if (some #(= true %) (seq s))
                  (if (some #(= false %) (seq s))
                    true
                    false)
                  false)))

(a-half-truth (fn[& args]
                (and (boolean (some true? args))
                     (not-every? true? args))))

(a-half-truth (fn [& vs]
                (true? (and (some not vs)
                            (some identity vs)))))