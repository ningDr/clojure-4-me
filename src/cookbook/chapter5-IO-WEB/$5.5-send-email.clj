(ns cookbook.chapter5.$5.5-send-email
  (:require [postal.core :refer [send-message]]))
;; 发送邮件

(def email "ning.dr@foxmail.com")
(def pass "qqq")

(def conn {:host "smtp.qq.com"
        ;    :port 355
           :ssl true ;; 使用ssl加密
        ;    :tls true ;; 使用TLS加密
           :user email
           :pass pass})

(send-message conn {:from email
                    :to email
                    :subject "A message, from the past"
                    :body "Hi there, me!"})