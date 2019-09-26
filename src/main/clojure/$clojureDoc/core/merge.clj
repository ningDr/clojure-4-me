(ns $clojureDoc.core.merge)
;; 使用方法：(merge & maps)
;; Returns a map that consists of the rest of the maps conj-ed onto
;; the first.  If a key occurs in more than one map, the mapping from
;; the latter (left-to-right) will be the mapping in the result.
;; 返回一个map：第一个map不动，第二个map的每一项与第一个map进行conj运算
;; 若第二个map中的key在第一个map中出现，那么第一个map的相应key的value更新成第二个map key的value

(println (merge {:a 1 :b 2} {:c 3 :b 4} {:e 5 :d 4}))
;; => {:a 1, :b 4, :c 3, :e 5, :d 4}

(println (merge {:a 1 :b 2 :c 3} {:b 9 :d 4}))
;; => {:a 1, :b 9, :c 3, :d 4}

;; map的conj在原map后直接添加
(println (conj {:a 1 :b 2} {:c 3 :d 4}))
;; => {:a 1, :b 2, :c 3, :d 4}

(defn baz [& options]
  (let [options (merge {:opt1 "default-1" :opt2 "default-2"}
                       (first options))]
    options))

(println (baz {:opt1 "custom-1" :opt3 "custom-3"}))
;; => {:opt1 custom-1, :opt2 default-2, :opt3 custom-3}
