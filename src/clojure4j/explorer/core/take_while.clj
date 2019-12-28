(ns clojure4j.explorer.core.take-while)
;; 使用方法：(take-while pred)(take-while pred coll)
;; Returns a lazy sequence of successive items from coll while
;; (pred item) returns logical true. pred must be free of side-effects.
;; Returns a transducer when no collection is provided.
;; 返回一个惰性序列：由集合的第一项开始，如果第一项参与断言运算返回逻辑真，则进行第二项运算，直至某一项返回逻辑假
;; 断言不能有副作用(无副作用：不会读写数据库、文件、socket、以及全局变量等)
;; 当没有集合作为take-while的参数时，take-while返回一个转换器

;; neg? 断言一个数字是否为负数，是为真
(println (take-while neg? [-2 -1 0 1 2 3]))
;; => (-2 -1)
;; take-while返回的是原集合的连续项，即使后面有断言为真的项，也不会获取到
(println (take-while neg? [-2 -1 0 -1 -2 3]))
;; => (-2 -1)

;; set包含关系；返回的是序列
(println (take-while #{[1 2][3 4]} #{[1 2]}))
;; => ([1 2])