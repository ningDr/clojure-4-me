(ns $4Clojure.$91to$100.$95-trees-e-to-tree-or-not-to-tree)
(defn to-tree-or-not-to-tree
  "Write a predicate which checks whether or not a given sequence represents a binary tree.
   Each node in the tree must have a value, a left child, and a right child."
  [arg]
  (println "==============================")
  (println (= (arg '(:a (:b nil nil) nil))
              true))
  (println (= (arg '(:a (:b nil nil)))
              false)) ;; 只有左
  (println (= (arg [1 nil [2 [3 nil nil] [4 nil nil]]])
              true))
  (println (= (arg [1 [2 nil nil] [3 nil nil] [4 nil nil]])
              false)) ;; 3个子节点
  (println (= (arg [1 [2 [3 [4 nil nil] nil] nil] nil])
              true))
  (println (= (arg [1 [2 [3 [4 false nil] nil] nil] nil])
              false)) ;; 节点值为false
  (println (= (arg '(:a nil ()))
              false))) ;; 右为空

(defn tree? [coll] (letfn [(node? [e] (cond
                                        (nil? e) true
                                        (number? e) true
                                        (sequential? e) (tree? e)
                                        :else false))
                           (check [[n l r]] (if n
                                              (and (node? l) (node? r))
                                              false))]
                     (if (= 3 (count coll))
                         (check coll)
                         false)))

(to-tree-or-not-to-tree tree?)

;; ----------------------------------

(to-tree-or-not-to-tree (fn ff [[root l r :as tree]]
                          (and (= 3 (count tree))
                               (not (sequential? root))
                               (or (nil? l) (and (sequential? l) (ff l)))
                               (or (nil? r) (and (sequential? r) (ff r))))))

(to-tree-or-not-to-tree (fn istree? [root]
                          (or (nil? root)
                              (and (sequential? root)
                                   (= 3 (count root))
                                   (every? istree? (rest root))))))

(to-tree-or-not-to-tree (fn tree? [s]
                          (cond
                            (or (seq? s) (vector? s))
                            (and (= (count s) 3) (tree? (second s)) (tree? (last s)))
                            (nil? s) true
                            :else false)))

(to-tree-or-not-to-tree (fn tree? [coll]
                          (if (coll? coll)
                            (if (= (count coll) 3)
                              (and (tree? (second coll)) (tree? (last coll)))
                              false)
                            (not (false? coll)))))

(to-tree-or-not-to-tree (fn tree? [t]
                          (if (= 3 (count t))
                            (every? #(or (nil? %) (and (sequential? %) (tree? %))) (rest t))
                            false)))

(to-tree-or-not-to-tree (fn [root] (every? #(or (nil? %) (and (sequential? %) (= 3 (count %))))
                                           (tree-seq #(and (sequential? %)
                                                           (= (count %) 3))
                                                     rest root))))

(to-tree-or-not-to-tree (fn is-tree [x] (or (nil? x) (and
                                                      (sequential? x)
                                                      (= 3 (count x))
                                                      (is-tree (nth x 1))
                                                      (is-tree (nth x 2))))))