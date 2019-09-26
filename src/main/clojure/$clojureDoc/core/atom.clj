(ns $clojureDoc.core.atom)
;; 使用方式：(atom x) (atom x & options)
;; Creates and returns an Atom with an initial value of x and zero or
;; more options (in any order):
;;  :meta metadata-map
;;  :validator validate-fn
;;  If metadata-map is supplied, it will become the metadata on the
;; atom. validate-fn must be nil or a side-effect-free fn of one
;; argument, which will be passed the intended new state on any state
;; change. If the new state is unacceptable, the validate-fn should
;; return false or throw an exception.
;; 创建并返回一个原子类型：初始值是x，然后是0个或多个选项
;; 如果显式指定metadata-map，那么将成为atom的metadata
;; validate-fn一定为空或者是一个无副作用的、一个参数的函数，为了状态变更准备的
;; 如果新状态不能接受，那么validate-fn返回false或者抛出异常

(def x (atom 1))
(println @x)
;; => 1

(def y (atom 2 :a 11))
(println y)
;; => #object[clojure.lang.Atom 0x66c92293 {:status :ready, :val 2}]
(println @y)
;; => 2

(def z (atom 3 :meta {:a 1 :val 2} :validator pos?))
;; 并未发现特殊变化，待深入clojure后，再看metadata-map等的作用
(println z)
;; => #object[clojure.lang.Atom 0x673be18f {:status :ready, :val 3}]
(println @z)
;; => 3