(ns clojure4j.explorer.core.butlast)
;; 使用方法：(butlast coll)
;; Return a seq of all but the last item in coll, in linear time
;; 时间复杂度是线性的；返回一个除了最后一项的序列（这就是rest的相反操作啊）
;; 掐头
(println (rest '(1 2 3 4)))
;; => (2 3 4)
;; 去尾
(println (butlast '(1 2 3 4)))
;; => (1 2 3)