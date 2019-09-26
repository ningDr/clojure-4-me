(ns $clojureDoc.core.update-in)
;; 使用方法：(update-in m ks f & args)
;; 'Updates' a value in a nested associative structure, where ks is a
;; sequence of keys and f is a function that will take the old value
;; and any supplied args and return the new value, and returns a new
;; nested structure.  If any levels do not exist, hash-maps will be
;; created.

;; 嵌套
(println (update-in {:a {:b {:c 3}} :b 2 :c 3} [:a :b :c] + 4 5))
;; => {:a {:b {:c 12}}, :b 2, :c 3}
(println (update-in {:a 1 :b {:b {:c 3}} :c 3} [:b :b :c] + 4 5))
;; => {:a 1, :b {:b {:c 12}}, :c 3}
;; 从上面例子看出
;; ks是一层一层的key，这个指定获取哪个key的value
;; 拿到value，和最后的可变参数一起参与函数f计算后，将val
;; 这个例子看懂了吗？
(println (update-in [{1 'a 2 'b 3 'c}] [1 2 3] str 4 5 6))
;; => [{1 :A, 2 :B, 3 :C} {2 {3 456}}]

;; 没看懂看这里
;; 由contains?函数介绍知道，对于vector这样的序列(list排除哈)，索引即为键！
;; 那么ks现在是[0 :A]，所以获取vector [{:A 4 :B 5 :C 6}]的第0项，{:A 4 :B 5 :C 6}，然后获取key :A
(println (update-in [{:A 4 :B 5 :C 6}] [0 :A] + 4 5 6))
;; => [{:A 19, :B 5, :C 6}]

;; 现在回来看没懂的例子
;; 如果使用(nth [0 1 2] 3)将会报错
;; 但使用contains?会出现false，这很正常吧，那理解上面没懂的例子需要这个概念
(println (contains? [0 1 2] 3))
;; => false
;; 因为1“不存在”，我们可以认为是key-value的键值对为：1 nil
;; 那1的value运算后就是 (str nil 4 5 6) => 456，键key就是1
;; 放回去,原vector就有了两个元素 0 1
(println (update-in [{1 'a 2 'b 3 'c}] [1] str 4 5 6))
;; => [{1 a, 2 b, 3 c} 456]
;; 1为nil,那么nil中的key没有2，那么就创建呀
(println (update-in [{1 'a 2 'b 3 'c}] [1 2] str 4 5 6))
;; => [{1 a, 2 b, 3 c} {2 456}]
;; 同理喽
(println (update-in [{1 'a 2 'b 3 'c}] [1 2 3] str 4 5 6))
;; => [{1 :A, 2 :B, 3 :C} {2 {3 456}}]
