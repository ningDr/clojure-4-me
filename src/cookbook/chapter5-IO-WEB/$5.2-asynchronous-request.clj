(ns cookbook.chapter5.$5.2-asynchronous-request
  (:require [org.httpkit.client :as http-kit]))
;; 执行异步HTTP请求

;; http://www.http-kit.org，高性能、事件驱动的HTTP客户端/服务器库
;; [http-kit "2.3.0"]

(def response (http-kit/get "http://example.com")) ;; 返回promise对象，用deref等待或用@读取


(deref response 2000 nil) ;; 用deref来指定超时毫秒数和一个值

(:status @response) ;; 用@读取

(def url "http://www.baidu.com")
(def params {:headers "" :query-params {}})
(defn callback-fn [res] (println res))
(http-kit/get url params callback-fn)