(ns $clojureDoc.core.some)
;; 使用方式：(some pred coll)
;; Returns the first logical true value of (pred x) for any x in coll,
;; else nil.  One common idiom is to use a set as pred, for example
;; this will return :fred if :fred is in the sequence, otherwise nil:
;; (some #{:fred} coll)
;; 返回集合coll中第一个使断言/决策为逻辑真的值，如果没有，返回nil
;; 一个常用示例是，一个set用作断言/决策，如果set中的元素在coll中存在，那么直接返回
(println (some #{:fred} '(:a :b 1 2 :fred)))
;; => :fred

(println (some #{:fred 2} '(:a :b 1 2 :fred)))
;; => 2