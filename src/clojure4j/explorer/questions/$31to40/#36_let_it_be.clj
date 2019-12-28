(ns clojure4j.explorer.questions.$31to40.#36-let-it-be)

(defn let-it-be
  "Can you bind x, y, and z so that these are all true?"
  []
  (println "============")
  (println (= 10 (let [x 7, y 3, z 1] (+ x y))))
  (println (= 4 (let [x 7, y 3, z 1] (+ y z))))
  (println (= 1 (let [x 7, y 3, z 1] (+ z))))
  )

(let-it-be)