(ns clojure4j.explorer.questions.$41to50.#43-reverse-interleave)
(defn reverse-interleave
  "Write a function which reverses the interleave process into x number of subsequences.\n"
  [arg]
  (println "===================")
  (println (arg [1 2 3 4 5 6] 2))
  (println (arg (range 9) 3))
  (println (arg (range 10) 5))
  )

;; 分两次循环
;; 第一层控制结果的总项数 循环变量为i
;; 第二次控制每项的元素个数 循环变量为j
;; 每次循环coll不变，则每项是 (j * n) + i
(reverse-interleave #(loop [coll %1, num %2, res '(), i 0]
                       (if (= num (count res))
                         (reverse res)
                         (recur coll num (conj res ((fn [coll num i] (loop [coll coll, num num, elem '(), j 0]
                                                                       (if (= (/ (count coll) num) (count elem))
                                                                         (reverse elem)
                                                                         (recur coll num (conj elem (nth coll (+ i (* num j)))) (inc j))
                                                                         )
                                                                       )
                                                      ) coll num i
                                                    )
                                               ) (inc i))
                         )
                       )
                    )

(reverse-interleave (fn [col n] (->> (partition n col) (apply map #(apply list %&))
                                     )))

(reverse-interleave (fn [s c] (partition (/ (count s) c) (apply interleave (partition c s)))))

(reverse-interleave #(apply map list (partition %2 %1)))
(reverse-interleave #(apply (partial map list) (partition %2 %1)))
;; ((1 2) (3 4) (5 6))
(println (partition 2 [1 2 3 4 5 6]))
(println (#(apply map + (partition %2 %1)) [1 2 3 4 5 6] 2) )
(println (apply map list '((1 2) (3 4) (5 6))))