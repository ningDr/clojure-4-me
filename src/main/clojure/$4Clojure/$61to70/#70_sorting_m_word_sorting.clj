(ns $4Clojure.$61to70.#70-sorting-m-word-sorting)
(defn word-sorting
  "
    Write a function that splits a sentence up into a sorted list of words.
    Capitalization should not affect sort order and punctuation should be ignored.
  "
  [arg]
  (println "==================")
  (println (arg "Have a nice day."))
  (println (= (arg "Have a nice day.") ["a" "day" "Have" "nice"]))

  (println (arg "Clojure is a fun language!"))
  (println (= (arg "Clojure is a fun language!") ["a" "Clojure" "fun" "is" "language"]))

  (println (= (arg "Fools fall for foolish follies.") ["fall" "follies" "foolish" "Fools" "for"]))
  )

(word-sorting #(sort-by (fn [v-str] (clojure.string/lower-case v-str)) (clojure.string/split (clojure.string/replace % #"[.!]" "") #" ")))


(word-sorting #(->> (re-seq #"\w+" %) (sort-by clojure.string/lower-case)))

(word-sorting (fn [string] (sort
                             #(compare (.toLowerCase %1) (.toLowerCase %2))
                             (clojure.string/split string #"\W"))))

(word-sorting (fn [x] (sort-by #(.toLowerCase %) (.split (.replaceAll x "[^a-zA-Z ]" "") " "))))

(word-sorting (fn [sent] (sort-by #(.toLowerCase %) (.split (->> sent butlast (apply str)) " "))))

(word-sorting (comp (partial sort-by clojure.string/lower-case) #(clojure.string/split % #"[^A-Za-z]")))