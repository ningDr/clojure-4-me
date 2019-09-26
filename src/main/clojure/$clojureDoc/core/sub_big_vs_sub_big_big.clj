(ns $clojureDoc.core.sub-big-vs-sub-big-big)
;; ->
;; 使用方式：(-> x & forms)
;; Threads the expr through the forms. Inserts x as the
;; second item in the first form, making a list of it if it is not a
;; list already. If there are more forms, inserts the first form as the
;; second item in second form, etc.
;; 有道翻译：
;; 将expr贯穿表单。将x作为第一个表单中的第二项插入，如果它还不是一个列表，
;; 则将其作为列表。如果有更多的表单，则将第一个表单插入到第二个表单中的第二个项目中，等等。

;; 将整个表达式串起来：x作为第二项的第一个参数，注意是第一个参数第二项接收到参数后进行运算，
;; 将返回结果作为第三项的参数，第三项运算后返回结果输出或继续向下计算

;; ->>
;; 使用方式：(->> x & forms)
;; Threads the expr through the forms. Inserts x as the
;; last item in the first form, making a list of it if it is not a
;; list already. If there are more forms, inserts the first form as the
;; last item in second form, etc.
;; ->>与->相反，是将x最为第二项的最后一个参数

;; macro ->将x作为第二项的第一个参数的重要性
(println (-> 0 (/ 1)))
;; => 0
(println (macroexpand '(-> 0 (/ 1))))
;; => (/ 0 1)
;; 若调反顺序，将报错：除数为零
;(println (-> 1 (/ 0)))
;; => java.lang.ArithmeticException: Divide by zero

;; 来看看macro ->>吧
(println (->> 1 (/ 0)))
;; => 0
(println (macroexpand '(->> 1 (/ 0))))
;; => (/ 0 1)
;; 再调反顺序，将报错：除数为零
;(println (->> 0 (/ 1)))
;; => java.lang.ArithmeticException: Divide by zero

(defn f [front a1 a2 end] (str front "-" a1 "-" a2  "-" end))
(println (f 1 2 3 4))
;; => 1-2-3-4
(println (-> 1 (f 2 3 4)))
;; => 1-2-3-4
(println (->> 1 (f 2 3 4)))
;; => 2-3-4-1

