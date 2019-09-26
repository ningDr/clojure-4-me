(ns $4Clojure.$21to30.#29GetTheCaps)
(defn get-the-caps
  "Write a function which takes a string and returns a new string containing only the capital letters."
  [arg]
  (println "=============")
  (println (arg "HeL1O,WoR1D!"))
  (println (arg "nothing"))
  (println (arg "$#A(*&987Z2f3"))
  )

(get-the-caps (fn [arg] (clojure.string/join "" (filter #(re-find (re-matcher #"[A-Z+]" (str %1 ""))) (seq arg)))))
(get-the-caps (fn [arg] (clojure.string/join ""  (re-seq #"[A-Z]" arg))))

(println (re-seq #"[A-Z]" "HeL1O,WoR1D!"))
(get-the-caps (fn [xs] (reduce str (re-seq #"[A-Z]" xs))))
(get-the-caps #(apply str (re-seq #"[A-Z]" %)))
(get-the-caps #(apply str (filter (set (map char (range 65 91))) %)))
(get-the-caps #(clojure.string/replace % #"[^A-Z]+" ""))
(get-the-caps (fn [s] (reduce str (filter #(Character/isUpperCase %) s))))