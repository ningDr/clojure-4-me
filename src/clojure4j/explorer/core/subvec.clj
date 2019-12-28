(ns clojure4j.explorer.core.subvec)
;; 使用方法：(subvec v start) (subvec v start end)
;; Returns a persistent vector of the items in vector from
;; start (inclusive) to end (exclusive).  If end is not supplied,
;; defaults to (count vector). This operation is O(1) and very fast, as
;; the resulting vector shares structure with the original and no
;; trimming is done.
;; 从现有的vector中返回一个从start（包含）索引到end（不包含）索引的vector
;; 如果无end，默认end取(count vector)的值，即到vector的最后一个元素
;; 时间复杂度是极快的O(1)
;; (不确定的译文)得到的结果vector与原始的vector使用同样的数据结构，并且结果vector不是裁剪得到的
;; 调试环境没调好，待后续验证最后一句话

(def x [1 2 3 4 ])
(def y (subvec x 1 2))
(println x y)
;; => [1 2 3 4] [2]
(println (.start y))
;; => 1
(println (.end y))
;; => 2
(println (.v y))
;; => [1 2 3 4]

;; 对x使用则报错 java.lang.IllegalArgumentException: No matching field found: start for class clojure.lang.PersistentVector
;; (println (.start x))

