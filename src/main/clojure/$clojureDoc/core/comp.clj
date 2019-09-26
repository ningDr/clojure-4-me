(ns $clojureDoc.core.comp)
;; 使用方法：(comp)(comp f)(comp f g)(comp f g & fs)
;; Takes a set of functions and returns a fn that is the composition
;; of those fns.  The returned fn takes a variable number of args,
;; applies the rightmost of fns to the args, the next
;; fn (right-to-left) to the result, etc.
;; 函数的应用是从右到左的，可理解为“就近原则”

;; 2 先遇到dec自减，后遇到inc自增，因此结果还是2
(println ((comp inc dec) 2))
;; => 2

;; -1 先遇到pos?，计算得到false，然后false参与匿名函数运算
(println ((comp #(cond
                   (true? %1)
                   "it's true"
                   :else
                   "it's false"
                   )
                pos?
                ) -1
          )
         )
;; => it's false

;; 三个8，遇到+，求和得到24,24遇到str，将变成string类型的24
(println (string? ((comp str +) 8 8 8)))
;; => true
