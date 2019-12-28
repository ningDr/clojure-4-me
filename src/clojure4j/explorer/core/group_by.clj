(ns clojure4j.explorer.core.group-by)
;; 使用方法：(group-by f coll)
;; Returns a map of the elements of coll keyed by the result of
;; f on each element. The value at each key will be a vector of the
;; corresponding elements, in the order they appeared in coll.
;; 返回一个map，coll的元素按照函数f计算后的结果为键，元素组成的vector作为value，
;; 每个value的vector的元素的顺序就是它们出现在coll中的顺序

(println (group-by pos? [1 2 3 0 -1 -2 4 -5]))
;; => {true [1 2 3 4], false [0 -1 -2 -5]}

;; group by multiple criteria
;; 多条件分组
(def words ["Air" "Bud" "Cup" "Awake" "Break" "Chunk" "Ant" "Big" "Check"])
(group-by (juxt first count) words)
;;=> {[\A 3] ["Air" "Ant"],
;;    [\B 3] ["Bud" "Big"],
;;    [\C 3] ["Cup"],
;;    [\A 5] ["Awake"],
;;    [\B 5] ["Break"],
;;    [\C 5] ["Chunk" "Check"]}