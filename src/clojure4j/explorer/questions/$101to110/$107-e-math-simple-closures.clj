(ns clojure4j.explorer.questions.$101to110.$107-e-math-simple-closures)
(defn simple-closures
  "Lexical scope and first-class functions are two of the most basic building
   blocks of a functional language like Clojure. When you combine the two together,
   you get something very powerful called lexical closures. With these, you can
   exercise a great deal of control over the lifetime of your local bindings, saving
   their values for use later, long after the code you're running now has finished.

   It can be hard to follow in the abstract, so let's build a simple closure. Given a
   positive integer n, return a function (f x) which computes xn. Observe that the effect
   of this is to preserve the value of n for use outside the scope in which it is defined."
  [arg]
  (println "=========闭包 lexical closures=========")
  (println (= 256 ((arg 2) 16) ((arg 8) 2)))
  (println (= [1 8 27 64] (map (arg 3) [1 2 3 4])))
  (println (= [1 2 4 8 16] (map #((arg %) 2) [0 1 2 3 4]))))

(fn [n]
  (fn [m]
    (if (zero? n)
      1
      (loop [x (dec n), y m]
        (if (zero? x)
          y
          (recur (dec x) (* y m)))))))


(((fn [n]
    (fn [m]
      (if (zero? n)
        1
        (loop [x (dec n), y m]
          (if (zero? x)
            y
            (do (println x "===" y)
                (recur (dec x) (* y m)))))))) 2) 3)

;; =====================
(fn [n] #(int (Math/pow % n)))

(fn [x] #(apply * (repeat x %)))

#(let [pow %]
   (fn [x]
     (apply * (take pow (repeat x)))))

(fn [n] (fn [x] (reduce * (repeat n x))))





