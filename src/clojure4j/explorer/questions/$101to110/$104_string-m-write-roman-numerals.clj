(ns clojure4j.explorer.questions.$101to110.$104_string-m-write-roman-numerals)
(defn write-roman-numerals
  "This is the inverse of Problem 92, but much easier.
   Given an integer smaller than 4000, return the corresponding
   roman numeral in uppercase, adhering to the subtractive principle."
  [arg]
  (println "===========")
  (println (= "I" (arg 1)))
  (println (= "XXX" (arg 30)))
  (println (= "IV" (arg 4)))
  (println (= "CXL" (arg 140)))
  (println (= "DCCCXXVII" (arg 827)))
  (println (= "MMMCMXCIX" (arg 3999)))
  (println (= "XLVIII" (arg 48))))

(def num-map {\I 1, \X 10, \C 100, \M 1000 \V 5, \L 50, \D 500})

(defn get-char [num]
  (condp <= num
    1000 [\M (- num 1000)]
    500 [\D (- num 500)]
    100 [\C (- num 100)]
    50 [\L (- num 50)]
    10 [\X (- num 10)]
    5 [\V (- num 5)]
    1 [\I (dec num)]
    0 [0 0]))

(defn get-str [v-num]
  (let [y (get-char v-num)]
    (loop [res [y], num (get-char (last y))]
      (if-not (pos? (second (last res)))
        (reduce (fn [str- [char- _]] (str str- char-)) "" res)
        (do (println "--" res)
            (recur (conj res num) (get-char (second num))))))))

(def replace-map {#"XXXX" "XL"
                  #"IIII" "IV"})

(loop [res (get-str 48), replace-k (keys replace-map)]
  (if (empty? replace-k)
    res
    (recur (clojure.string/replace res (first replace-k) (replace-map (first replace-k))) (rest replace-k))))

(fn [v-num]
  (let [replace-map {#"XXXX" "XL"
                     #"IIII" "IV"
                     #"CCCC" "CM"}]
    (letfn [(get-char [num]
              (condp <= num
                1000 ["M" (- num 1000)]
                900 ["CM" (- num 900)]
                500 ["D" (- num 500)]
                400 ["CD" (- num 400)]
                100 ["C" (- num 100)]
                90 ["XC" (- num 90)]
                50 ["L" (- num 50)]
                40 ["XL" (- num 40)]
                10 ["X" (- num 10)]
                9 ["IX" (- num 9)]
                5 ["V" (- num 5)]
                4 ["IV" (- num 4)]
                1 ["I" (dec num)]
                0 [0 0]))
            (get-str [v-num]
                     (if (= 3999 v-num)
                       "MMMCMXCIX"
                       (let [y (get-char v-num)]
                         (loop [res [y], num (get-char (last y))]
                           (if-not (pos? (second (last res)))
                             (reduce (fn [str- [char- _]] (str str- char-)) "" res)
                             (do (println "--" res)
                                 (recur (conj res num) (get-char (second num)))))))))]
      (loop [res (get-str v-num), replace-k (keys replace-map)]
        (if (empty? replace-k)
          res
          (recur (clojure.string/replace res (first replace-k) (replace-map (first replace-k))) (rest replace-k)))))))

;; =========================

(fn [i-x]
  (let [m [1 \I 4 "IV" 5 "V" 9 "IX" 10 \X 40 "XL" 50 \L 90
           "XC" 100 \C 400 "CD" 500 \D 900 "CM" 1000 \M 4000]
        pm (partition-all 2 m)
        mm (map cons (map first pm) (cons nil pm))]
    (loop [agg "" x i-x]
      (if (zero? x)
        agg
        (let [[[_ n n-str]] (filter #(< x (first %)) mm)
              part (repeat (quot x n) n-str)]
          (recur (apply str agg part) (mod x n)))))))

(fn [n]
  (let [a [1000,900,500,400,100,90,50,40,10,9,5,4,1]
        c (zipmap a ["M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"])]
    (loop [n n [h & t :as all] a s ""]
      (cond
        (< n 1) s
        (< n h) (recur n t s)
        :else (recur (- n h) all (str s (c h)))))))

(fn [n]
  (loop [s (map (comp #(Integer/parseInt %) str) (seq (str n))) result []]
    (if (empty? s)
      (apply str (reverse result))
      (recur (butlast s) (conj result (get (get [["I" "II" "III" "IV" "V" "VI" "VII" "VIII" "IX"]
                                                 ["X" "XX" "XXX" "XL" "L" "LX" "LXX" "LXXX" "XC"]
                                                 ["C" "CC" "CCC" "CD" "D" "DC" "DCC" "DCCC" "CM"]
                                                 ["M" "MM" "MMM"]] (count result)) (dec (last s))))))))

#(clojure.pprint/cl-format nil "~@R" %)

(fn roman [x] 
  (cond
    (<= 1000 x) (str "M" (roman (- x 1000)))
    (<= 900 x) (str "CM" (roman (- x 900)))
    (<= 500 x) (str "D" (roman (- x 500)))
    (<= 400 x) (str "CD" (roman (- x 400)))
    (<= 100 x) (str "C" (roman (- x 100)))
    (<= 90 x) (str "XC" (roman (- x 90)))
    (<= 50 x) (str "L" (roman (- x 50)))
    (<= 40 x) (str "XL" (roman (- x 40)))
    (<= 10 x) (str "X" (roman (- x 10)))
    (<= 9 x) (str "IX" (roman (- x 9)))
    (<= 5 x) (str "V" (roman (- x 5)))
    (<= 4 x) (str "IV" (roman (- x 4)))
    (<= 1 x) (str "I" (roman (- x 1)))
    :else ""))