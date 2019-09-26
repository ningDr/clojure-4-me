(ns $4Clojure.$41to50.#47-contain-yourself)
(defn contain-yourself
  "The contains? function checks if a KEY is present in a given collection.
   This often leads beginner clojurians to use it incorrectly with numerically indexed collections like vectors and lists."
  [arg]
  (println "=================")
  ;; (contains? coll key)
  ;; set的元素即为键
  (println (contains? #{4 5 6} arg))
  ;; vector的索引即为键
  (println (contains? [1 1 1 1 1] arg))
  ;; 对map来说，是正宗的“包含关系”，contains?不能用于list
  (println (contains? {4 :a 2 :b} arg))
  ;; vector的索引为键，故[1 2 4]不包含为4的索引，取反为真
  (println (not (contains? [1 2 4] arg)))
  )

(contain-yourself 4)
;; 对map来说，就是键-值对，谁在前，谁就是键
(println (contains? {4 :a 2 :b} :a))
(println (get {4 :a 2 :b} 4))
;; contains? not supported on type: clojure.lang.PersistentList，contains?不能用于list
(println (contains? '(0 1 2 3 4) 4))