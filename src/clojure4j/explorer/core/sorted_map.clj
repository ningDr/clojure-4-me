(ns clojure4j.explorer.core.sorted-map)
;; 使用方式：(sorted-map & keyvals)
;; keyval => key val
;; Returns a new sorted map with supplied mappings.  If any keys are
;; equal, they are handled as if by repeated uses of assoc.
;; 返回一个排序后的map，如果key重复，将用后面的替换前面的
(println (sorted-map :a 1 :b 2 :c 3))
;; => {:a 1, :b 2, :c 3}
(println (sorted-map :c 1 :b 2 :a 3))
;; => {:a 3, :b 2, :c 1}
(println (sorted-map :a 1 :b 2 :a 3 :b 4))
;; => {:a 3, :b 4}

;; 这样将会报错：java.lang.IllegalArgumentException: No value supplied for key
;; (println (sorted-map {:c 3 :b 2 :z 26 :a 1}))
;; 改下写法
(println (into (sorted-map) {:c 2 :b 1}))
;; => {:b 1, :c 2}
;; 这些都是怎么发生的呢？
(println (sorted-map))
;; => {}
(println (into {} {:c 2 :b 1}))
;; => {:c 2, :b 1}
(println (into {:z 26 :a 1} {:c 2 :b 1}))
;; => {:z 26, :a 1, :c 2, :b 1}
(println (into (sort {:z 26 :a 1}) {:c 2 :b 1}))
;; => ([:b 1] [:c 2] [:a 1] [:z 26])

; sorting on integer keys
; also notice how each pair becomes a `MapEntry` ('key' 'val')
(into (sorted-map) [[23 :x] [17 :y]])
;;=> {17 :y, 23 :x}