(ns clojure4j.explorer.questions.$91to$100.$96-trees-e-beauty-is-symmetry)
(defn beauty-is-symmetry
  "Let us define a binary tree as 'symmetric'
   if the left half of the tree is the mirror image of the right half of the tree.
   Write a predicate to determine whether or not a given binary tree is symmetric. (see To Tree, or not to Tree for a reminder on the tree representation we're using)."
  [arg]
  (println "=============================")
  (println (= (arg '(:a (:b nil nil) (:b nil nil))) true))
  (println (= (arg '(:a (:b nil nil) nil)) false))
  (println (= (arg '(:a (:b nil nil) (:c nil nil))) false))
  (println (= (arg [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
                    [2 [3 nil [4 [6 nil nil] [5 nil nil]]] nil]])
              true))
  (println (= (arg [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
                    [2 [3 nil [4 [5 nil nil] [6 nil nil]]] nil]])
              false))
  (println (= (arg [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
                    [2 [3 nil [4 [6 nil nil] nil]] nil]])
              false)))

(defn mirror [[n l r]]
  (vector n
          (if (sequential? r) (mirror r) r)
          (if (sequential? l) (mirror l) l)))

(beauty-is-symmetry (fn [coll] (letfn [(mirror [[n l r]]
                                         (vector n
                                                 (if (sequential? r) (mirror r) r)
                                                 (if (sequential? l) (mirror l) l)))]
                                 (= (last coll) (last (mirror coll))))))

;; ================================
(beauty-is-symmetry #((fn ff [[l-root ll lr :as left] [r-root rl rr :as right]]
                        (or (= nil left right)
                            (and (= l-root r-root) (ff ll rr) (ff lr rl)))) % %))

(beauty-is-symmetry #(letfn [(flip [[v l r]]
                               (when (or v l r)
                                 [v (flip r) (flip l)]))]
                       (= % (flip %))))

(beauty-is-symmetry (fn tree? ([s] (tree? s s))
                      ([s1 s2] (cond
                                 (and (or (seq? s1) (vector? s1)) (or (seq? s2) (vector? s2)))
                                 (and (= (count s1) 3) (= (count s2) 3) (= (first s1) (first s2)) (tree? (second s1) (last s2)) (tree? (last s1) (second s2)))
                                 (and (nil? s1) (nil? s2))  true
                                 :else false))))

(beauty-is-symmetry (fn [[v l r]]
                      (letfn[(mirror?[lb rb]
                               (cond
                                 (not= (sequential? lb) (sequential? rb)) false
                                 (sequential? lb) (let [[lv ll lr] lb [rv rl rr] rb]
                                                    (and (= lv rv) (mirror? ll rr) (mirror? lr rl)))
                                 :else (= lb rb)))]
                        (mirror? l r))))

(beauty-is-symmetry (fn [[_ L R]]
                      (letfn
                       [(flip [[v l r]] (list v (if (coll? r) (flip r) r) (if (coll? l) (flip l) l)))]
                        (= L (flip R)))))

(beauty-is-symmetry #(let [t (fn t [[v l r]] [v (if r (t r)) (if l (t l))])
                           [_ l r] %]
                       (= l (t r))))

(beauty-is-symmetry #(letfn [
                             (flip [x] (if (nil? x) nil
                                                    [(first x) (flip (nth x 2)) (flip (nth x 1))]))]
                       (= % (flip %))))