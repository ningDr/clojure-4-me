(ns clojure4j.explorer.questions.$101to110.$111-h-game-crossword-puzzle)
(defn crossword-puzzle
  "Write a function that takes a string and a partially-filled crossword puzzle board,
   and determines if the input string can be legally placed onto the board.

   The crossword puzzle board consists of a collection of partially-filled rows. Empty spaces
   are denoted with an underscore (_), unusable spaces are denoted with a hash symbol (#), and
   pre-filled spaces have a character in place; the whitespace characters are for legibility and
   should be ignored.

   For a word to be legally placed on the board:
     - It may use empty spaces (underscores)
     - It may use but must not conflict with any pre-filled characters.
     - It must not use any unusable spaces (hashes).
     - There must be no empty spaces (underscores) or extra characters before or after the word
       (the word may be bound by unusable spaces though).
     - Characters are not case-sensitive.
     - Words may be placed vertically (proceeding top-down only), or horizontally (proceeding left-right only)."
  [arg]
  (println "================")
  (println (= true  (arg "the" ["_ # _ _ e"])))
  (println (= false (arg "the" ["c _ _ _"
                                "d _ # e"
                                "r y _ _"])))
  (println (= true  (arg "joy" ["c _ _ _"
                                "d _ # e"
                                "r y _ _"])))
  (println (= false (arg "joy" ["c o n j"
                                "_ _ y _"
                                "r _ _ #"])))
  (println (= true  (arg "clojure" ["_ _ _ # j o y"
                                    "_ _ o _ _ _ _"
                                    "_ _ f _ # _ _"]))))
;; 找出给出的单词，填入棋盘上，是否可以得到给的单词

(defn match?
  [aim-str board]
  (let [get-dot-str (fn [board]
                      (->> (map #(clojure.string/replace % #"[ ]" "") board)
                           (map #(clojure.string/replace % #"[_]" "."))
                           (map #(clojure.string/split % #"[#]"))
                           flatten
                           (filter #((complement empty?) %))))
        row (get-dot-str board)
        col (get-dot-str (apply map str board))
        res-str (concat row col)
        matchers (remove nil? (map #(re-seq (re-pattern (str "^" % "$")) aim-str) res-str))]
    (println "===" matchers)
    ((complement zero?) (count matchers))))


;; ============================
(fn [s ms]
  (let [data (map #(apply str (filter (complement #{\space}) %)) ms) ;; 横向
        tests #(-> (str "^" % "$") (re-pattern) (re-seq s))]
    (->> (apply mapv str data) ;; 纵向，apply + map的使用，获取每个集合的第一个元素参与map函数的运算
         (concat data) ;; 合并纵横
         (mapcat #(-> % (.replace "_" ".") (.split "\\#"))) ;; 将_转成正则匹配单一字符的.
         (some (comp tests str)) ;; 将纵横转为正则表达式，匹配给出的字符串，匹配的上非空集合
         (boolean))))  ;; 空或非空集合转成布尔值

(fn [s b]
  (->> b
       (map #(take-nth 2 (replace {\_ \.} %)))
       (#(into % (apply map list %)))
       (mapcat #(partition-by #{\#} %))
       (map #(re-pattern (apply str %)))
       (map #(re-matches % s))
       (not-every? nil?)))

(fn
  f
  [word vs]
  (let [seq-vec (map (fn [x] (filter #(not= % \space) x)) (map seq vs))
        split-fn (fn [x] (filter #(not= % '(\#)) (partition-by  #(= % \#) x)))
        transpose (fn [m] (apply mapv vector m))
        seq-vec-transposed (transpose seq-vec)
        fit? (fn [x] (if (some #(= % false) (if (not= (count x) (count word)) [false] (map #(if (or (= %1 %2) (= %2 \_)) true false) (seq word) x))) false true))]
  (if (some fit? (into (mapcat split-fn seq-vec-transposed) (mapcat split-fn seq-vec)))
    true
    false)))

(fn [w p]
  (let [p (map #(-> %
                    (.replaceAll " " "")
                    (.replaceAll "_" "."))
               p)
        m (fn [z] (map re-pattern
                       (mapcat #(.split % "#")
                               z)))
        hp (m p)
        vp (m (apply map (comp #(reduce str "" %) vector) p))]
    (if (some #(re-matches % w) (concat hp vp)) true false)))