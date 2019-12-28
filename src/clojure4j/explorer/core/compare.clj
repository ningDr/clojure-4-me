(ns clojure4j.explorer.core.compare)
;; 使用方式：(compare x y)
;; Comparator. Returns a negative number, zero, or a positive number
;; when x is logically 'less than', 'equal to', or 'greater than'
;; y. Same as Java x.compareTo(y) except it also works for nil, and
;; compares numbers and collections in a type-independent manner. x
;; must implement Comparable
;; 比较器。返回一个负数，0，正数当x逻辑“小于”、“等于”、“大于”y，
;; 和java的compareTo方法一样的使用方式，但是java的方法不能用于nil,一样的返回值
;; x必须实现Comparable

(println (compare "a" "b"))
;; => -1

;; java.lang.ClassCastException: java.lang.Long cannot be cast to java.lang.String
;; (println (compare "a" 1))
;; java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Number
;; (println (sort ["a" 1]))
