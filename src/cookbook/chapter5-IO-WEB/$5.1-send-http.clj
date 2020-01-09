(ns cookbook.chapter5.$5.1-send-http-uuid
  (:require [clj-http.client :as http]))
;; 发出HTTP请求

;; https://github.com/dakrone/clj-http
;; https://github.com/Netflix/Hystrix，用来包装http调用，增加容错性

(def msg (http/get "http://www.baidu.com"))
(println "====" (:status msg))

(slurp "http://www.baidu.com")

(http/get "http://www.baidu.com/s" {:query-params {:wd "clojure" :kw "clojure"}})

(http/post "http://example.com" {:form-params {:username "test" :password "test"}})

;; 通过:multipart选项来上传附件