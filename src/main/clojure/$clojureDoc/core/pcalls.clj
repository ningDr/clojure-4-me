(ns $clojureDoc.core.pcalls)
;; 使用方式：(pcalls & fns)
;; Executes the no-arg fns in parallel, returning a lazy sequence of
;; their values
;; 并行执行无参的函数集fns，返回一个由函数执行完后的值组成的惰性序列
(def x-atom (atom 0))
(def y-atom (atom 0))
(def z-atom (atom 0))

(defn x ([] (Thread/sleep 2000) (str "Hello x" ":" (swap! x-atom inc)))
  ([arg] (str "x" arg))
  )

(defn y ([] (Thread/sleep 100) (str "Hello y" ":" (swap! y-atom inc)))
  ([arg] (str "y" arg))
  )

(defn z ([] (Thread/sleep 200) (str "Hello z" ":" (swap! z-atom inc)))
  ([arg] (str "z" arg))
  )
(println (x 1))
;; => x1

;; 调用有参函数将报错java.util.concurrent.ExecutionException
;; (println (pcalls (x 1) (y 1) (z 1)))

;; 由结果看，应该是按顺序执行
(println (time (pcalls x y z x y z)))
;; => "Elapsed time: 4.736004 msecs"
;; => (Hello x:1 Hello y:1 Hello z:1 Hello x:2 Hello y:2 Hello z:2)
(memoize (pcalls x y z x y z))
(println (time (pcalls x y z x y z)))

