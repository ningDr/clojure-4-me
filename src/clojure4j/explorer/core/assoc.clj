(ns clojure4j.explorer.core.assoc)
;; 使用方式：(assoc map key val) (assoc map key val & kvs)
;; assoc[iate]. When applied to a map, returns a new map of the
;; same (hashed/sorted) type, that contains the mapping of key(s) to
;; val(s). When applied to a vector, returns a new vector that
;; contains val at index. Note - index must be <= (count vector).
;; 使用对象是map、vector
;; 参数是map时，返回一个map--由map后的key和val组成
;; 参数是vector时，返回一个被指定索引值的位置替换后的新vector--索引的值要小于等于vector的大小

(println (assoc {} :key1 "val1" :key2 "val2"))
;; => {:key1 val1, :key2 val2}
(println (assoc {:key1 "old1" :key2 "old2"} :key1 "val1" :key2 "val2"))
;; 旧值被替换
;; => {:key1 val1, :key2 val2}
(println (assoc {:key1 "val1" :key2 "val2"} :key3 "val3" :key4 "val4"))
;; => {:key1 val1, :key2 val2, :key3 val3, :key4 val4}
(println (assoc {:key1 "val1" :key2 "val2"} :key4 "val4" :key3 "val3"))
;; 没有排序
;; => {:key1 val1, :key2 val2, :key4 val4, :key3 val3}

;; vector一共3个元素
(println (count [1 2 3]))
;; => 3
;; 索引从0开始
(println (nth [1 2 3] 0))
;; => 1
;; 替换索引为1的值
(println (assoc [1 2 3] 1 4))
;; => [1 4 3]
;; 增加一个
(println (assoc [1 2 3] 3 4))
;; => [1 2 3 4]
;; 大于3就会报错:IndexOutOfBoundsException
(println (assoc [1 2 3] 4 4))
