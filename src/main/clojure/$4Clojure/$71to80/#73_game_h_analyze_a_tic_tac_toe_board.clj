(ns $4Clojure.$71to80.#73-game-h-analyze-a-tic-tac-toe-board)
(defn analyze-a-tic-tac-toe-board
  "
    A tic-tac-toe board is represented by a two dimensional vector.
    X is represented by :x,
    O is represented by :o,
    and empty is represented by :e.
    A player wins by placing three Xs or three Os in a horizontal, vertical, or diagonal row.
    Write a function which analyzes a tic-tac-toe board and returns :x if X has won, :o if O has won, and nil if neither player has won.
  "
  [arg]
  (println "===============")
  (println (arg [[:e :e :e]
                 [:e :e :e]
                 [:e :e :e]]))
  (println (= nil (arg [[:e :e :e]
                        [:e :e :e]
                        [:e :e :e]])))
  (println (= :x (arg [[:x :e :o]
                       [:x :e :e]
                       [:x :e :o]])))
  (println (= :o (arg [[:e :x :e]
                       [:o :o :o]
                       [:x :e :x]])))
  (println (= nil (arg [[:x :e :o]
                        [:x :x :e]
                        [:o :x :o]])))
  (println (= :x (arg [[:x :e :e]
                       [:o :x :e]
                       [:o :e :x]])))
  (println (= :o (arg [[:x :e :o]
                       [:x :o :e]
                       [:o :e :x]])))
  (println (= nil (arg [[:x :o :x]
                        [:x :o :x]
                        [:o :x :o]])))
  )
;; 使用穷举法
(analyze-a-tic-tac-toe-board (fn [[a b c]] (cond
                                             (= :x (nth a 0) (nth a 1) (nth a 2))
                                             :x
                                             (= :o (nth a 0) (nth a 1) (nth a 2))
                                             :o
                                             (= :x (nth a 0) (nth b 1) (nth c 2))
                                             :x
                                             (= :o (nth a 0) (nth b 1) (nth c 2))
                                             :o
                                             (= :x (nth a 0) (nth b 0) (nth c 0))
                                             :x
                                             (= :o (nth a 0) (nth b 0) (nth c 0))
                                             :o

                                             (= :x (nth a 1) (nth b 1) (nth c 1))
                                             :x
                                             (= :o (nth a 1) (nth b 1) (nth c 1))
                                             :o

                                             (= :x (nth a 2) (nth b 2) (nth c 2))
                                             :x
                                             (= :o (nth a 2) (nth b 2) (nth c 2))
                                             :o
                                             (= :x (nth a 2) (nth b 1) (nth c 0))
                                             :x
                                             (= :o (nth a 2) (nth b 1) (nth c 0))
                                             :o

                                             (= :x (nth b 0) (nth b 1) (nth b 2))
                                             :x
                                             (= :o (nth b 0) (nth b 1) (nth b 2))
                                             :o
                                             (= :x (nth c 0) (nth c 1) (nth c 2))
                                             :x
                                             (= :o (nth c 0) (nth c 1) (nth c 2))
                                             :o
                                             :else
                                             nil
                                             )
                               )
                             )

;; 穷举，解构更彻底
(analyze-a-tic-tac-toe-board (fn [board] (letfn [(check [[[a b c] [d e f] [g h i]] p]
                                                   (or (= p a b c)
                                                       (= p d e f)
                                                       (= p g h i)
                                                       (= p a d g)
                                                       (= p b e h)
                                                       (= p c f i)
                                                       (= p a e i)
                                                       (= p c e g)))]
                                           (cond (check board :x) :x
                                                 (check board :o) :o
                                                 :else nil))))

;; 穷举法
(analyze-a-tic-tac-toe-board (fn [[[a00 a01 a02]
                                   [a10 a11 a12]
                                   [a20 a21 a22]]]
                               (let [s [[a00 a01 a02] [a10 a11 a12] [a20 a21 a22] [a00 a10 a20] [a01 a11 a21] [a02 a12 a22] [a00 a11 a22] [a02 a11 a20]]
                                     xs (count (filter #(= % 3) (map count (map (fn [x] (filter #(= % :x) x)) s))))
                                     os (count (filter #(= % 3) (map count (map (fn [x] (filter #(= % :o) x)) s))))]
                                 (if (= xs 1)
                                   :x
                                   (if (= os 1)
                                     :o
                                     nil))
                                 )
                               ))


(analyze-a-tic-tac-toe-board (fn [board]
                               (let [rows board             ;get rows
                                     columns (apply map vector board) ;get columns
                                     diagonals [(map get board [0 1 2]) (map get board [2 1 0])]] ;get diagonals
                                 (loop [remaining (concat rows columns diagonals)] ;all potential triplets
                                   (if-let [item (set (first remaining))]
                                     (if (and (not-any? #(= :e %) item) (or (every? #(= :x %) item) (every? #(= :o %) item))) ;find the winner
                                       (first item)
                                       (recur (rest remaining)))
                                     nil))))                ;or return nil
                                     )

(analyze-a-tic-tac-toe-board (fn [x] (ffirst (filter #(and (apply = %) (not= (first %) :e)) (partition 3 (map (vec (flatten x)) '(0 1 2 3 4 5 6 7 8 0 3 6 1 4 7 2 5 8 0 4 8 2 4 6)))))))


(println (map get [[:x :e :o]
                   [:x :x :e]
                   [:o :x :o]] [0 1 2]))