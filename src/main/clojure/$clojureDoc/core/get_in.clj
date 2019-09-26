(ns $clojureDoc.core.get-in)
;; 使用方式：(get-in m ks) (get-in m ks not-found)
;; Returns the value in a nested associative structure,
;; where ks is a sequence of keys. Returns nil if the key
;; is not present, or the not-found value if supplied.
;; 返回嵌套结构中的值，ks是键的序列，如果key不存在，返回nil，或者，not-found的值，如果有这个参数

(println (get-in {:a {:b 1} :b 2} [:a :b]))
;; => 1
(println (get-in {:a {:b 1} :b 2} [:a :c]))
;; => nil
(println (get-in {:a {:b 1} :b 2} [:a :c] "c is not exist!"))
;; => c is not exist!