(ns clojureWeb.Appendix2
  (:import java.io.File))
(defn de
  "docstring"
  [arg]
  (if (= 1 arg) (println arg))
  (println (inc arg))
  )
(de 1)
(de 4)

;; 前置声明down函数
(declare down)
(defn up [n] (if (< n 10) (down (+ 2 n)) n))
(defn down [n] (up (dec n)))
(println (up 9))

(println "===============")

(defn make-client [url] (fn [request] (str " sending " request " to " url)))
(let [client (make-client "http://foo.org")]
  (println (client "request1"))
  (println (client "request2"))
  )
(println "===============")
(let [[smaller bigger] (split-with #(< % 5) (range 10))]
  (println smaller bigger)
  )
(println "===============")
(defn print-user [[name address phone]]
  (println name address phone)
  )
(print-user ["Bob" "12 Jarvis street,Toronto" "416-987-3417"])

(println "===============")
(defn foo [& args]
  "指定变量参数，将其作为一个序列，那样就能支持不同参数的个数。通过使用&后面跟一个参数列表名来实现"
  (println args)
  )
(foo "a" "b" "c" 1 2 3 '(:a :b :c))
(println "===============")
(defn foo [ firstArg & [otherArgs]]
  (if otherArgs
    "two or more arguments is passed in"
    "only one argument passed in")
  )
(println (foo "a" "b" "c" 1 2 3 '(:a :b :c)))

(println "==================")
(let [{fooArg :foo barArg :bar} {:foo "foo2" :bar "bar2"}]
  ;; {fooArg :foo barArg :bar} 先声明变量，并绑定变量的值为:foo :bar键的值
  ;; {:foo "foo2" :bar "bar2"} 提供map，为声明赋值
  (println fooArg barArg)
  )
(println "==================")
(let [{[a b c] :item id :id} {:id "foo" :item [1 2 3]}]
  (println id "has the flowing items:" a b c)
  )
(println "==================")
(defn login [{:keys [id password]}]
  ;; :keys是指：从传入map中，获取键为id password的键值对的值
  (println id password)
  )
(login {:id "SmallFlower" :password "SmallFlower'sPassword"})

(println "==================")
(defn login2 [{:keys [id pass pass1] :as user}]
  (if (and id (= pass pass1))
    (println "valid user")
    (println "is not filled in correctly")
    )
  (println user)
  )
(login2 {:id "SmallFlower" :pass "SmallFlower'sPassword" :pass1 "SmallFlower'sPassword"})

(println "=============")
(declare ^{:dynamic true} *foo*)
(binding [*foo* 12])
(println *foo*)
;(println (inc *foo*))
;(println *foo*)
(println "++++++++++++")
(defn with-foo [f x] (binding [*foo* 14]
                     ;(f)
                     (println "1:" *foo* x)
                     (println "2:" (+ *foo* 1) (+ x 1))
                     (println "3:" *foo* x)
                     (binding [*foo* 13])
                     (println "4:" *foo*)
                     (f)
                     )
  )
(with-foo #(println *foo*) 1)
(println "================")
(def file (File. "F:\\workspace-idea\\w3c\\clojure\\src\\main\\clojure\\clojureWeb\\Appendix2.clj"))
(println (.getAbsolutePath file))

(let [f (File. "F:\\workspace-idea\\w3c\\clojure\\src\\main\\clojure\\clojureWeb\\Appendix2.clj")]
  (println (.getAbsolutePath f))
  )
;; File/separator等价于 \\(转义后的斜杠)
(println (str File/separator "foo" "\\" "bar"))

;; 速记符 ".."
(println (.getBytes (.getAbsolutePath (File. "."))))
(println (.. (File. ".") getAbsolutePath getBytes))
(println "========动态多态========")
;; 定义“接口” protocol定义的是一个抽象的函数集，需要通过具体类型实现(第一个参数必须是this)
(defprotocol Foo "Foo doc string"
  (bar [this b] "bar doc string")
  (baz [this] [this b] "baz doc string")
  )
;; 实现“接口”，type创建类型，实现上述定义的Foo抽象函数集
(deftype Bar [data] Foo
  (bar [this param] (println data param))
  (baz [this] (println (class this)))
  (baz [this param] (println param))
  )
(let [b (Bar. "some data")]
  (.bar b "param")
  (.baz b)
  (.baz b "baz with param")
  )
(println "=======全局状态=======")
;; atom处理非协同更新
;; ref用在可能出现并发修改的事务中

;; 定义一个值可变的变量
(def global-val (atom nil))
(println (deref global-val))
(println @global-val)
;; rest!传入新值为变量赋值
(reset! global-val 10)
(println @global-val)
;; swap!传入函数，变量参与运算后将新值赋给变量
(swap! global-val inc)
(println @global-val)

(println "==========宏==========")
(def session (atom {:user "Bob"}))
(defn load-content []
  (if (:user @session) ;; session的user是否为空
    "some content"
    "please log in"
    )
  )
(println (load-content))                                    ;; 打印调用函数后返回的值
(println load-content)                                      ;; 打印函数本身
;; 重写上述方法

(defmacro defprivate [name & body]
  ;; 通过“~”前缀对参数求值，使用“~”符号表示我们希望通过名字的值替换此处，这称为引述quote
  ;; "`"符号表示将“我”后面的语句序列作为数据处理
  `(defn ~(symbol name) []
     (if (:user @session)
       ;; “~@”符号称为解引述拼接，该符号用于处理一个序列
       (do ~@body)
       "please log in"
       )
     )
  )