(ns clojure4j.explorer.questions.$2to20.#14Functions)
(defn defnFunction
  "Clojure has many different ways to create functions.\n"
  [arg]
  (println (= arg ((fn add-five [x] (+ x 5) ) 3)))        ;定义函数后立即调用
  (println (= arg ((fn [x] (+ x 5) ) 3)))                 ;定义匿名函数，并立即调用
  (println (= arg (#(+ % 5) 3)))
  (println (= arg ((partial + 5) 3)))
  )

(defnFunction 8)