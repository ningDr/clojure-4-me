(ns clojure4j.explorer.questions.$91to$100.$94-game-h-game-of-life)
(defn game-of-life
  "The game of life is a cellular automaton devised by mathematician John Conway.
   The 'board' consists of both live (#) and dead ( ) cells.
   Each cell interacts with its eight neighbours (horizontal, vertical, diagonal),
   and its next state is dependent on the following rules:
   1) Any live cell with fewer than two live neighbours dies, as if caused by under-population.
   2) Any live cell with two or three live neighbours lives on to the next generation.
   3) Any live cell with more than three live neighbours dies, as if by overcrowding.
   4) Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
   Write a function that accepts a board, and returns a board representing the next generation of cells."
  [arg]
  (println "********************")
  (println (= (arg ["      "
                    " ##   "
                    " ##   "
                    "   ## "
                    "   ## "
                    "      "])
              ["      "
               " ##   "
               " #    "
               "    # "
               "   ## "
               "      "]))
  (println (arg ["     "
                 "     "
                 " ### "
                 "     "
                 "     "]))
  (println (= (arg ["     "
                    "     "
                    " ### "
                    "     "
                    "     "])
              ["     "
               "  #  "
               "  #  "
               "  #  "
               "     "]))
  (println (= (arg ["      "
                    "      "
                    "  ### "
                    " ###  "
                    "      "
                    "      "])
              ["      "
               "   #  "
               " #  # "
               " #  # "
               "  #   "
               "      "]))
  )
(def board ["      "
            " ##   "
            " ##   "
            "   ## "
            "   ## "
            "      "])
(game-of-life (fn [board]
                (let [element-len (count (first board))
                      transfer (mapv #(mapv (fn [i] (if (= i \#) 1 0)) (seq %)) board)
                      get-sum (fn [t x y]
                                (let [a (nth t (- y 1))
                                      b (nth t y)
                                      c (nth t (+ y 1))
                                      v (- x 1)
                                      z (+ x 1)]
                                  (+
                                   (nth a v) (nth b v) (nth c v)
                                   (nth a z) (nth b z) (nth c z)
                                   (nth a x) (nth c x))))
                      cal-sum (for [x (range 1 (- element-len 1))]
                                (for [y (range 1 (- element-len 1))]
                                  {:x (nth (nth transfer x) y)
                                   :f (get-sum transfer y x)}))
                      cc (mapv #(conj (reduce (fn [m n] (conj m (cond
                                                                  (> 2 (:f n)) 0
                                                                  (and (= 3 (:f n)) (zero? (:x n))) 1
                                                                  (> 4 (:f n)) (:x n)
                                                                  (> 3 (:f n)) 0
                                                                  :else 0
                                                                  ))) [0] %) 0) cal-sum)
                      dd (reduce #(conj %1 %2) [] (take element-len (repeat 0)))
                      ee (conj (reduce #(conj %1 %2) [dd] cc) dd)]
                  (mapv #(reduce (fn [x y] (str x (if (= y 1) \# " "))) "" %) ee))))

(def a [[0 0 0 0 0] [0 0 1 0 0] [0 0 1 0 0] [0 0 1 0 0] [0 0 0 0 0]])
(def b (for [x (range 0 5)]
         (for [y (range 0 5)]
           (vector x y (nth (nth a x) y)))))
(def res (reduce #(concat %1 %2) [] b))



;(def transfer (mapv #(mapv (fn [i] (if (= i \#) 1 0)) (seq %)) board))
;
;(defn get-sum [t x y]
;  (let [a (nth t (- y 1))
;        b (nth t y)
;        c (nth t (+ y 1))
;        v (- x 1)
;        z (+ x 1)]
;    (+
;     (nth a v) (nth b v) (nth c v)
;     (nth a z) (nth b z) (nth c z)
;     (nth a x) (nth c x))))
;
;(for [x (range 1 5)]
;  (for [y (range 1 5)]
;    {:x (nth (nth transfer y) x)
;     :f (get-sum transfer x y)}))
;
;(def zz '(({:x 1, :f 3} {:x 1, :f 3} {:x 0, :f 2} {:x 0, :f 0})
;          ({:x 1, :f 3} {:x 1, :f 4} {:x 0, :f 4} {:x 0, :f 2})
;          ({:x 0, :f 2} {:x 0, :f 4} {:x 1, :f 4} {:x 1, :f 3})
;          ({:x 0, :f 0} {:x 0, :f 2} {:x 1, :f 3} {:x 1, :f 3})))
;
;(def cc (mapv #(conj (reduce (fn [m n] (conj m (cond
;                                                 (> 2 (:f n)) 0
;                                                 (> 4 (:f n)) (:x n)
;                                                 (> 3 (:f n)) 0
;                                                 (and (= 3 (:f n)) (zero? (:x n))) 1
;                                                 :else 0
;                                                 ))) [0] %) 0) zz))
;
;(def dd (reduce #(conj %1 %2) [] (take 6 (repeat 0))))
;
;(def ee (conj (reduce #(conj %1 %2) [dd] cc) dd))
;
;(mapv #(reduce (fn [x y] (str x (if (= y 1) \# " "))) "" %) ee)

