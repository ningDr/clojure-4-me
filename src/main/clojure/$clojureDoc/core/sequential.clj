(ns $clojureDoc.core.sequential)
;; 使用方法 (sequential? coll)
;; Returns true if coll implements Sequential
;; 如果集合实现了Sequential接口,返回 true
(sequential? '(1 2 3))
;; => true

(sequential? [1 2 3])
;; => true

(sequential? (range 1 5))
;; => true

(sequential? '())
;; => true

(sequential? [])
;; => true

(sequential? nil)
;; => false

(sequential? 1)
;; => false

(sequential? "abc")
;; => false

(sequential? {:a 2 :b 1})
;; => false

(sequential? #{1 2 3})
;; => false