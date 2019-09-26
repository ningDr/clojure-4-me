(ns $4Clojure.$21to30.#23ReverseSequence)
(defn reverseSequence
  "Write a function which reverses a sequence."
  [arg]
  (println (arg '(1 2 3 4 5 6)))
  (println (arg [1 2 3 4 5 6]))
  )
;(reverseSequence #(let [x [] seq %1] (if (empty? seq) x (recur (rest seq) (conj x (first seq))) )))

;为什么recur函数不能使用let绑定的变量
(defn reverse3
  [seq]
  ((fn [x seq]
     (if (empty? seq)
       x
       (recur (conj x (first seq)) (rest seq)))
     ) '() seq)
  )
(println (reverse3 '(1 2 3 4 5 6)))

;用匿名函数为什么输出不对？rest换成pop试试 list的pop与vector的pop不同
(reverseSequence #((fn [x seq]
                     (if (empty? seq)
                       x
                       (recur (conj x (first seq)) (rest seq)))
                     ) '() %1)
                 )


(reverseSequence #(reduce conj () %1))

(defn recursive-reverse [s]
  (letfn [(my-reverse [s c]
            (if (empty? s)
              c
              (recur (rest s) (conj c (first s)))))]
    (my-reverse s '())))

(println (recursive-reverse [1 2 3 4 5]))

(reverseSequence (fn [s]
                   (letfn [(my-reverse [s c]
                             (if (empty? s)
                               c
                               (recur (rest s) (conj c (first s)))))]
                     (my-reverse s '()))))

(reverseSequence (fn [s]
                   (loop [result []
                          s s]
                     (if (seq s)
                       (recur (concat [(first s)] result) (rest s))
                       result))))
(reverseSequence #(map (vec %) (range (dec (count %)) -1 -1)))

(defn countDown
  [result x]
  (if (zero? x)
    result
    (recur (conj result x) (dec x)))
  )
(println (countDown '() 7))

(defn reverse2
  [x seq]
  (if (empty? seq)
    x
    (recur (conj x (first seq)) (pop seq)))
  )

(println (reverse2 '() '(1 2 3 4 5 6)))
