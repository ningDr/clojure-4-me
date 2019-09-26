(ns $clojureDoc.core.reduce)
;; 使用方法：(reduce f coll) (reduce f val coll)
;; f should be a function of 2 arguments.
;; 函数f需要两个参数

;; If val is not supplied, returns the result of applying f to the first 2 items in coll, then
;;  applying f to that result and the 3rd item, etc.
;; 如果val为空，即(reduce f coll)，那么返回一个运算结果：coll的前两项作为f的参数，得到的结果再与coll的第三项参与f运算，依次类推，直到coll最后一项

;; If coll contains no items, f must accept no arguments as well, and reduce returns the
;;  result of calling f with no arguments.
;; 如果集合coll为空，函数f变成不接受参数的函数，reduce此时返回的就是调用无参函数f的结果

;; If coll has only 1 item, it is returned and f is not called.
;; 如果集合coll只有一项，那么直接将coll的此项作为结果返回，函数f没有被调用

;; If val is supplied, returns the result of applying f to val and the first item in coll, then
;;  applying f to that result and the 2nd item, etc.
;; 如果有val，那么reduce返回一个结果：val和coll的第一项参与函数f运算，结果与coll的第二项参与运算，依次类推，直到coll最后一项

;; If coll contains no items, returns val and f is not called.
;; 如果有val，集合coll为空，那么直接返回val，函数f没有被调用

(println (reduce + '(1 2 3 4 5)))
;; => 15

(println (identity '()))
;; => ()

;; 返回的是调用无参函数f的结果
(println (reduce (fn [] 1) '()))
;; => 1

(println (reduce (fn [] (println "===")) '(1)))
;; => 1

(println (reduce + 1 '(2 3 4 5)))
;; => 15

(println (reduce (fn [] (println "===")) 1 '()))
;; => 1
