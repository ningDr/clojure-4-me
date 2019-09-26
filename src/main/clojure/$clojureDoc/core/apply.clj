(ns $clojureDoc.core.apply)
;; 使用示例 ([f args] [f x args] [f x y args] [f x y z args] [f a b c d & args])
;; apply takes a variable number of arguments and a collection.
;; apply接受可变数量的参数和集合
;; apply effectively unrolls the supplied args and a collection into a list of arguments to the supplied function.
;; apply有效地将提供的参数和集合展开到提供的函数的参数列表中。

(println (str ["Hel" "lo"] "World"))
;; ⇒ ["Hel" "lo"]World
(println (apply str ["Hel" "lo"] "World"))
;; ⇒ ["Hel" "lo"]World
(println (apply str "World" " " ["Hel" "lo"]))
;; ⇒ World Hello
(println (apply str ["Hel" "lo" " " "World"]))
;; ⇒ Hello World

;; apply prepends any supplied arguments to the form as well.
;; apply还将任何提供的参数前置到表单中。
;; This attempts to add 2 vectors with +
;; 尝试将两个向量相加，将会报ClassCastException错误
;; (println (map + [[1 2 3] [1 2 3]]))
;; 若使用apply，等价于(map + [1 2 3] [1 2 3])
(println (apply map + [[1 2 3] [1 2 3]]))
;; ⇒ (2 4 6)

(println (apply + 1 2 3 [4 5 6])) ;; same as  (+ 1 2 3 4 5 6)
;; ⇒ 21
;; 反转参数位置，将会报IllegalArgumentException错误
;; (println (apply + [4 5 6] 1 2 3)

(println (apply map + '((1 2) (3 4) (5 6))))
;; => (9 12)
(println (apply map + '((1 2 3 4 5 6))))
;; => (1 2 3 4 5 6)
(println (map list '((1 2) (3 4) (5 6))))
;; => (((1 2)) ((3 4)) ((5 6)))

;; apply的作用：拿集合里面的每个元素的第一项
;; 都拿出来后参与map list运算：组成list
(println (apply map list '((1 2) (3 4) (5 6))))
;; => ((1 3 5) (2 4 6))