(ns clojure4j.explorer.core.mapcat)
;; 使用方法：(mapcat f)(mapcat f & colls)
;; Returns the result of applying concat to the result of applying map
;; to f and colls.  Thus function f should return a collection. Returns
;; a transducer when no collections are provided
;; mapcat就是一个map和concat组合的一个函数
;; 先使用map，将map返回的每一项，再使用concat函数处理
;; 函数f应该返回一个集合，若没有集合作为参数时，函数f应该返回一个转换器

;; 先将vector的每一项使用函数reverse处理（map函数的功能）
;; 然后将'(0 1 2 3) '(4 5 6) '(7 8 9)连接（concat函数的功能）
(println (mapcat reverse [[3 2 1 0] [6 5 4] [9 8 7]]))
;; => (0 1 2 3 4 5 6 7 8 9)
(println (map reverse [[3 2 1 0] [6 5 4] [9 8 7]]))
;; => ((0 1 2 3) (4 5 6) (7 8 9))
(println (concat '(0 1 2 3) '(4 5 6) '(7 8 9)))