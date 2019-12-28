(ns clojure4j.explorer.core.gensym)
;; 使用方式：(gensym) (gensym prefix-string)
;; Returns a new symbol with a unique name. If a prefix string is
;; supplied, the name is prefix# where # is some unique number. If
;; prefix is not supplied, the prefix is 'G__'.
;; 返回一个唯一标识符，如果提供了一个前缀字符串，那么结果就是前缀字符串+唯一数字
;; 如果没有前缀字符串，默认前缀是G__

(println (gensym))
;; => G__148
(println (gensym))
;; => G__151
(println (gensym "A"))
;; => A154
(println (gensym "A"))
;; => A157
(println (gensym "A"))
;; => A160
