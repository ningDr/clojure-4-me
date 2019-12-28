(ns clojure4j.explorer.core.future)
;; 使用方式：(future & body)
;; Takes a body of expressions and yields a future object that will
;; invoke the body in another thread, and will cache the result and
;; return it on all subsequent calls to deref/@. If the computation has
;; not yet finished, calls to deref/@ will block, unless the variant of
;; deref with timeout is used. See also - realized?.
;; 获取一个表达式，产生一个未来对象：在其他线程中调用这个表达式，然后缓存结果，直到后续调用到deref或@宏
;; 如果计算（computation）还没完成，调用defer或@宏将被阻塞，除非使用带有超时的deref

(def x (future (Thread/sleep 1000) (println "===done===") 100))
(println "+++++")
;; 输出x，由于另一个线程为x赋值，但是延迟1秒，导致此处x的值为nil
(println x)
;; 当前线程阻塞2秒，future线程已经缓存了结果
(Thread/sleep 2000)
(println "=====")
;; 调用deref或@宏，future会立即返回结果
;; 即使不阻塞2秒，@x输出也会等待future线程完成求解，可以试试将1秒改为5秒
;; 但(println "=====")和(println "===done===")的顺序不同了
(println @x)
;; 输出
;; +++++
;; #object[clojure.core$future_call$reify__8439 0x54e7df6a {:status :pending, :val nil}]
;; ===done===
;; =====
;; 100

;; 修改为5秒后的输出顺序
;; #object[clojure.core$future_call$reify__8439 0x54e7df6a {:status :pending, :val nil}]
;; =====
;; ===done===
;; 100