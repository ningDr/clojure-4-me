(ns clojure4j.explorer.core.interpose)
;; 使用方法：(interpose sep) (interpose sep coll)
;; Returns a lazy seq of the elements of coll separated by sep.
;; 返回一个元素由sep分隔的一个惰性序列
;; Returns a stateful transducer when no collection is provided.
;; 不提供coll参数时，返回一个有状态的转换器（什么鬼？源码现阶段没看明白）

(println (interpose "HaHa" (list 1 2 3 4)))
;; => (1 HaHa 2 HaHa 3 HaHa 4)

;(defn interpose
;  "Returns a lazy seq of the elements of coll separated by sep.
;  Returns a stateful transducer when no collection is provided."
;  {:added "1.0"
;   :static true}
;  ([sep]
;   (fn [rf]
;     (let [started (volatile! false)]
;       (fn
;         ([] (rf))
;         ([result] (rf result))
;         ([result input]
;          (if @started
;            (let [sepr (rf result sep)]
;              (if (reduced? sepr)
;                sepr
;                (rf sepr input)))
;            (do
;              (vreset! started true)
;              (rf result input))))))))
;  ([sep coll]
;   (drop 1 (interleave (repeat sep) coll))))

