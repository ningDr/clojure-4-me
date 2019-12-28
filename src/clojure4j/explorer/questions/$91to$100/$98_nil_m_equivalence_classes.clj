(ns clojure4j.explorer.questions.$91to$100.$98-nil-m-equivalence-classes)
(defn equivalence-classes
  "A function f defined on a domain D induces an equivalence relation on D,
   as follows: a is equivalent to b with respect to f if and only if (f a) is equal to (f b).
   Write a function with arguments f and D that computes the equivalence classes of D with respect to f."
  [arg]
  (println "===================")
  (println (= (arg #(* % %) #{-2 -1 0 1 2})
              #{#{0} #{1 -1} #{2 -2}}))
  (println (= (arg #(rem % 3) #{0 1 2 3 4 5})
              #{#{0 3} #{1 4} #{2 5}}))
  (println (= (arg identity #{0 1 2 3 4})
              #{#{0} #{1} #{2} #{3} #{4}}))
  (println (= (arg (constantly true) #{0 1 2 3 4})
              #{#{0 1 2 3 4}})))

(equivalence-classes (fn [fn arg] (reduce #(conj %1 (set %2)) #{} (vals (group-by  fn (seq arg))))))

;; ===============
(equivalence-classes #(->> (group-by %1 %2)
                           (vals)
                           (map set)
                           (set)))

(equivalence-classes (comp set (partial map set) vals group-by))
