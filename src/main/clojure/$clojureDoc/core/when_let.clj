(ns $clojureDoc.core.when-let)
;; 使用方法：(when-let bindings & body)
;; bindings => binding-form test
;; When test is true, evaluates body with binding-form bound to the value of test
;; 当test为逻辑真，根据绑定表达式，计算body的值（这个意思吧）

;; 看下面例子：
;; bindings: [v (true? x)]
;; body: (true? x)
;; test: (true? x)的逻辑值
;; value (true? x)的计算值
;; 当test为逻辑真，v才有绑定值value，否则v的值为nil
(def x true)
(println (when-let [v (true? x)] v))
;; => true
(println (when-let [v (false? x)] v))
;; => nil

(def y {:a nil :b 1})
(println (when-let [v (:a y)] v))
;; => nil
(println (when-let [v (:b y)] v))
;; => 1