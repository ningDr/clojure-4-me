(ns $clojureDoc.core.swap!)
;; 使用方法：(swap! atom f) (swap! atom f x) (swap! atom f x y) (swap! atom f x y & args)
;; Atomically swaps the value of atom to be:
;; (apply f current-value-of-atom args). Note that f may be called
;; multiple times, and thus should be free of side effects.  Returns
;; the value that was swapped in.
;; 自动交换atom的值成为：应用函数f到atom当前的值和args
;; 函数f应该被多次调用，但是无副作用
;; 返回一个被交换值的atom
;; x的原始值为 '(1)
(def x (atom '(1)))
(println x)
;; => #object[clojure.lang.Atom 0x4b23c30a {:status :ready, :val (1)}]
(println (swap! x #(inc (nth % 0))))
;; => 2
(println x)
;; => #object[clojure.lang.Atom 0x4b23c30a {:status :ready, :val 2}]
;; 获取x的值，变成了number
(println @x)
;; => 2
(println (swap! x #(vector (inc %))))
;; => [3]
;; 重新赋值x，x的类型现在变成了vector
(println @x)
;; => [3]
(println (nth @x 0))
;; => 3

(println "============")
(def y (atom 0))
(println y)
;; => #object[clojure.lang.Atom 0x77128536 {:status :ready, :val 0}]
(println (swap! y + 1 2 3 4 5))
;; => 15
(println y)
;; => #object[clojure.lang.Atom 0x77128536 {:status :ready, :val 15}]
(println @y)
;; => 15

(println "============")
;; 来，理解下交换
(def swap-map (atom {:a 1 :b 2}))
(println (swap! swap-map assoc :c 3))
;; => {:a 1, :b 2, :c 3}
(println swap-map)
;; => #object[clojure.lang.Atom 0x7331196b {:status :ready, :val {:a 1, :b 2, :c 3}}]
(println (swap! swap-map assoc :a 0))
;; => {:a 0, :b 2, :c 3}
(println swap-map)
;; => #object[clojure.lang.Atom 0x7331196b {:status :ready, :val {:a 0, :b 2, :c 3}}]

(println "============")
;; 由此看出，原值是参与了函数f的运算
(def swap-str (atom "aa"))
(println (swap! swap-str str "bb"))
;; => aabb
(println @swap-str)
;; => aabb
(println swap-str)
;; reset!是直接替换
(println (reset! swap-str "bb"))
;; => bb
(println swap-str)
;; => #object[clojure.lang.Atom 0x27eedb64 {:status :ready, :val bb}]
(println @swap-str)
;; => bb
(println (deref swap-str))
;; => bb