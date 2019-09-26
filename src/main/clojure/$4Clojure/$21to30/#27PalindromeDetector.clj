(ns $4Clojure.$21to30.#27PalindromeDetector)
(defn palindromeDetector
  "Write a function which returns true if the given sequence is a palindrome."
  [arg]
  (println (arg '(1 2 3 4 5)))
  (println (arg "racecar"))
  (println (arg [:foo :bar :foo]))
  (println (arg '(1 1 3 3 1 1)))
  (println (arg '(:a :b :c)))
  )

(palindromeDetector #(= (seq %1) (reverse %1)))

(println "===============")
(palindromeDetector (fn [xs] (= (reverse xs) (reverse (reverse xs)))))
(palindromeDetector #(if (= (apply str %) (apply str (vec (reverse %)))) true false))