(ns clojure4j.explorer.core.not-any-y)
;; 使用方式：(not-any? pred coll)
;; Returns false if (pred x) is logical true for any x in coll, else true.
;; 无论coll中存在几个x，只要断言/决策和x运算的返回值为逻辑真，那么not-any?返回假，否则相反

(println (not-any? even? [1 3 5 7]))
;; => true

(println (not-any? even? [1 3 5 7 8]))
;; => false

(println (not-any? even? [1 3 5 6 7 8 10]))
;; => false