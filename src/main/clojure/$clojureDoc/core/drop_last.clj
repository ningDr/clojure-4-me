(ns $clojureDoc.core.drop-last)
;; 使用方法：(drop-last coll) (drop-last n coll)
;; Return a lazy sequence of all but the last n (default 1) items in coll
;; 去掉coll的最后n项，默认为1项

(println (drop-last '(4 3 2 1)))
;; => (4 3 2)

(println (drop-last 3 '(4 3 2 1)))
;; => (4)

(println (drop-last 5 '(4 3 2 1)))
;; => ()