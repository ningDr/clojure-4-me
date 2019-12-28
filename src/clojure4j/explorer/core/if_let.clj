(ns clojure4j.explorer.core.if-let)
;; 使用方式：(if-let bindings then) (if-let bindings then else & oldform)
;; bindings => binding-form test 绑定的形式可以拆分成绑定+表达式
;; 比如绑定是这样写:[x (true? true)]
;; 那么可以拆分成：[x (true? true)] (true? true)
;; if先判断(true? true)是否为逻辑真，若是，则let绑定变量x的值为(true? true)计算的值
;; 可以理解为if和let的合体

;; If test is true, evaluates then with binding-form bound to the value of
;; test, if not, yields else
;; 如果test为逻辑真，计算then表达式，并且test的值为绑定表达式计算的结果
;; 如果test为逻辑假，则什么也不做,或者跳到else执行

(println (if-let [x (true? true)]
           x
           false
           )
         )
;; => true
(println (if-let [x (true? 1)]
           x
           false
           )
         )
;; => false
(println (if-let [x (true? 1)]
           x
           )
         )
;; => nil
