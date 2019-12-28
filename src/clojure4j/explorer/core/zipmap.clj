(ns clojure4j.explorer.core.zipmap)
;; 使用方法：(zipmap keys vals)
;; Returns a map with the keys mapped to the corresponding vals.
;; 返回一个map：由keys集合做key，由vals集合做value，keys的数量应该和vals的数量一致
(def x (zipmap [1 2 3 4] ['a 'b 'c 'd]))
(println x (type (get x 1)))
;; => {1 a, 2 b, 3 c, 4 d} clojure.lang.Symbol
(println (type 'a))
;; => clojure.lang.Symbol

(println (zipmap [1 1 2 3] ['a 'b 'c 'd]))
;; => {1 b, 2 c, 3 d}