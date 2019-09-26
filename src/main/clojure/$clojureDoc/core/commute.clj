(ns $clojureDoc.core.commute)
;; 使用方式：(commute ref fun & args)
;; Must be called in a transaction. Sets the in-transaction-value of
;; ref to:
;;  (apply fun in-transaction-value-of-ref args)
;;  and returns the in-transaction-value of ref.
;;  At the commit point of the transaction, sets the value of ref to be:
;;  (apply fun most-recently-committed-value-of-ref args)
;;  Thus fun should be commutative, or, failing that, you must accept
;; last-one-in-wins behavior.  commute allows for more concurrency than
;; ref-set.
;; 必须应用在事务中
;; 设定事务中的值引用：
;; 返回(apply fun in-transaction-value-of-ref args)的结果
;; 在事务提交时，设定ref的值为：(apply fun most-recently-committed-value-of-ref args)
;; 因此，commute函数会执行两次
;; 然而函数fun应该是可交换的，否则返回失败，必须处理
;; commute允许多个并发

;; sleep-print-update返回一个函数，接收一个state参数
(defn sleep-print-update
  [sleep-time thread-name update-fn]
  (fn [state]
    (Thread/sleep sleep-time)
    (println state)
    (println (str thread-name ": " state))
    (update-fn state)
    )
  )

(def counter (ref 2))

(future (dosync (commute counter (sleep-print-update 100 "Commute Thread A" inc))))
(future (dosync (commute counter (sleep-print-update 150 "Commute Thread B" inc))))
;; 输出
;; 2
;; Commute Thread A: 2
;; 2
;; Commute Thread B: 2
;; 2
;; Commute Thread A: 2
;; 3
;; Commute Thread B: 3

;; REPL中运行看出效果
;(def savings-ref-stand (ref {:balance 500}))
;(def checking-ref-stand (ref {:balance 250}))
;(:balance @savings-ref-stand)
;;; => 500
;(:balance @checking-ref-stand)
;;; => 250
;(dosync
;  (commute checking-ref-stand assoc :balance 700)
;  (throw (Exception. "Oops..."))
;  (commute savings-ref-stand assoc :balance 50)
;  )
;(:balance @savings-ref-stand)
;;; => 500
;(:balance @checking-ref-stand)
;;; => 250