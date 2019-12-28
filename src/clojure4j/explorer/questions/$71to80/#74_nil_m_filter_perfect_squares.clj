(ns clojure4j.explorer.questions.$71to80.#74-nil-m-filter-perfect-squares)
;;
(defn filter-perfect-squares
  "
    Given a string of comma separated integers,
    write a function which returns a new comma separated string that only contains the numbers which are perfect squares.
  "
  [arg]
  (println "================")
  (println (arg "4,5,6,7,8,9"))
  (println (= (arg "4,5,6,7,8,9") "4,9"))
  (println (= (arg "15,16,25,36,37") "16,25,36"))
  )

(filter-perfect-squares (fn [string] (loop [str-coll (clojure.string/split string #","), res ""]
                                       (if (empty? str-coll)
                                         (subs res 1)
                                         (recur (rest str-coll) ((fn [x] (loop [i 0]
                                                                           (if (> (* i i) x)
                                                                             res
                                                                             (if (= (* i i) x)
                                                                               (str res "," x)
                                                                               (recur (inc i))
                                                                               )
                                                                             )
                                                                           )
                                                                   ) (Integer/parseInt (first str-coll))
                                                                 )
                                                )
                                         )
                                       )
                          )
                        )

(filter-perfect-squares #(->> (str "[" % "]")
                              (read-string)
                              (filter (fn [v] (let [p (int (Math/sqrt v))] (= v (* p p)))))
                              (clojure.string/join ","))
                        )

(filter-perfect-squares #(->> (re-seq #"\d+" %)
                              (map read-string)
                              (filter (fn [x] (== (Math/sqrt x) (int (Math/sqrt x)))))
                              (clojure.string/join ",")))

(filter-perfect-squares (fn [x] (clojure.string/join #"," (filter #(= % (* (int (Math/sqrt %)) (int (Math/sqrt %)))) (map #(Integer/parseInt %) (clojure.string/split x #","))))))

