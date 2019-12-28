(ns clojure4j.explorer.questions.$1to10.#8Sets
  (:require [clojure.set])
  (:require [clojure.set :as set]))

(println (set '(:a :b :c)))
(defn clojureSet
  "define set"
  [arg]
  (println (= arg (set '(:a :a :b :c :b :d :d))))
  (println (= arg (clojure.set/union #{:a :b :c} (set '(:b :c :d)))))
  (println (= arg (set/union #{:a :b :c} (set '(:b :c :d)))))
  )
(clojureSet #{:a :b :c :d})