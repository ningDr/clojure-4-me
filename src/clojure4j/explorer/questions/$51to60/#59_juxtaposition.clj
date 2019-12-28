(ns clojure4j.explorer.questions.$51to60.#59-juxtaposition)
(defn juxtaposition
  "
    Take a set of functions and return a new function that takes a variable number of arguments
    and returns a sequence containing the result of applying each function left-to-right to the argument list.
  "
  [arg]
  (println "===================")
  (println (= [21 6 1] ((arg + max min) 2 3 5 1 6 4)))
  (println (= ["HELLO" 5] ((arg #(.toUpperCase %) count) "hello")))
  (println (= [2 6 4] ((arg :a :c :b) {:a 2, :b 4, :c 6, :d 8 :e 10})))
  )
(juxtaposition juxt)
(juxtaposition (fn ([f g] #(vector (apply f %&) (apply g %&)))
                 ([f g z] #(vector (apply f %&) (apply g %&) (apply z %&))))
               )

(juxtaposition (fn [& fs]
                 (fn [& args]
                   (reduce #(conj %1 (apply %2 args)) [] fs))))
(juxtaposition (fn [& fs]
                 (fn [& p]
                   (map #(apply % p) fs))))
(juxtaposition (fn [& f]
                 (fn [& args]
                   (map #(apply % args) (apply vector f)))))
(juxtaposition (fn myJuxt [& fs]
                 (fn [& args]
                   (for [f fs]
                     (apply f args)))))

(juxtaposition (fn [& fs] (fn [& xs]
                            (if (> (count xs) 1)
                              (map #(reduce % xs) fs)
                              (map #(% (first xs)) fs)))))