(ns clojure4j.explorer.questions.$131to140.$138-data-juggling-h-squares-squared)
(defn squares-squared
  "Create a function of two integer arguments: the start and end, respectively.
   You must create a vector of strings which renders a 45° rotated square of
   integers which are successive squares from the start point up to and including
   the end point. If a number comprises multiple digits, wrap them around the shape
   individually. If there are not enough digits to complete the shape, fill in the
   rest with asterisk characters. The direction of the drawing should be clockwise,
   starting from the center of the shape and working outwards, with the initial direction
   being down and to the right."
  [arg]
  (println "======================")
  (println (= (arg 2 2) ["2"]))
  (println (= (arg 2 4) [" 2 "
                         "* 4"
                         " * "]))
  (println (= (arg 3 81) [" 3 "
                          "1 9"
                          " 8 "]))
  (println (= (arg 4 20) [" 4 "
                          "* 1"
                          " 6 "]))
  (println (= (arg 2 256) ["  6  "
                           " 5 * "
                           "2 2 *"
                           " 6 4 "
                           "  1  "]))
  (println (= (arg 10 10000) ["   0   "
                              "  1 0  "
                              " 0 1 0 "
                              "* 0 0 0"
                              " * 1 * "
                              "  * *  "
                              "   *   "])))

(defn squares
  [n m]
  (let [square-seq (fn [n m] (loop [res [n], s (* n n)]
                               (if (> s m)
                                 (map identity (apply str res))
                                 (recur (conj res s) (* s s)))))
        edge (fn [c] (loop [edge 1]
                       (if (<= c (* edge edge))
                         edge
                         (recur (inc edge)))))
        edge-num (edge (count (square-seq n m)))
        num-str (take (* edge-num edge-num) (concat (square-seq n m) (repeat \*)))
        row-num (inc (* 2 (dec edge-num)))
        x (dec edge-num)
        yy (- edge-num 2)
        y (if (even? yy) yy (inc yy))
        firs-dot [x y]
        set-index (fn [[x y] board num-str]
                    (let [row (board y)
                          char-row (char-array row)]
                      (aset-char char-row x (first num-str))
                      (assoc board y (clojure.string/join (vec char-row)))))
        pow-seq (vec (conj (map #(* % %) (range 2 (+ 2 edge-num))) 0))
        hex-seq (vec (map dec (reduce #(conj %1 (+ (last %1) %2)) [3] (map #(- (* 2 %) 2) (range 3 (+ 2 edge-num))))))
        next-x-y (fn [[[x y] idx pow-seq pow-idx hex-seq hex-idx]]
                   (let [add? (fn [idx seq-num seq-idx] (if (>= idx (seq-num seq-idx))
                                                          (inc seq-idx)
                                                          seq-idx))]
                     (cond
                       (and (even? pow-idx) (even? hex-idx))
                       [[(inc x) (inc y)] (inc idx)
                        pow-seq (add? idx pow-seq pow-idx)
                        hex-seq (add? idx hex-seq hex-idx)]
                       (and (even? pow-idx) (odd? hex-idx))
                       [[(inc x) (dec y)] (inc idx)
                        pow-seq (add? idx pow-seq pow-idx)
                        hex-seq (add? idx hex-seq hex-idx)]
                       (and (odd? pow-idx) (even? hex-idx))
                       [[(dec x) (inc y)] (inc idx)
                        pow-seq (add? idx pow-seq pow-idx)
                        hex-seq (add? idx hex-seq hex-idx)]
                       (and (odd? pow-idx) (odd? hex-idx))
                       [[(dec x) (dec y)] (inc idx)
                        pow-seq (add? idx pow-seq pow-idx)
                        hex-seq (add? idx hex-seq hex-idx)])))
        init-board (vec (repeat
                         row-num
                         (clojure.string/join (repeat row-num \space))))]
    (loop [board (set-index firs-dot init-board num-str),
           num-str (rest num-str)
           next-dot (next-x-y [firs-dot 1 pow-seq 0 hex-seq 0])]
      (if (empty? num-str)
        board
        (recur (set-index (first next-dot) board num-str)
               (rest num-str)
               (next-x-y next-dot))))))
(squares-squared squares)
;; ============================
(squares-squared (fn [start end]
                   (let [n-seq (->> (iterate #(* % %) start)
                                    (take-while #(<= % end))
                                    (mapcat str))
                         nc (count n-seq)
                         size (some #(and (<= nc (* % %)) %) (range))
                         q-seq (take (* size size) (concat n-seq (repeat \*)))
                         r-start (let [z (dec size) sh (- z (mod z 2))]
                                   [sh z])
                         v+ (partial map +)
                         ways (cycle [[1 1] [1 -1] [-1 -1] [-1 1]])
                         steps (->> (interleave (range) (range)) (drop 2))
                         roll (->> (mapcat repeat steps ways) (reductions v+ r-start))
                         coords (zipmap roll q-seq)
                         out-size (dec (* 2 size))]
                     (for [x (range out-size)]
                       (apply str (for [y (range out-size)]
                                    (get coords [x y] \space)))))))

