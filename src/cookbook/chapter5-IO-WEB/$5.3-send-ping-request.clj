(ns cookbook.chapter5.$5.3-send-ping-request)
;; 发出ping请求

;; 使用java.net.InetAdress，检查是否isReachable
; (.isReachable (java.net.InetAdress/getByName "oreilly.com") 5000)

(defn timed-ping
  "Time an .isReachable ping to a given domain
   e.g. (timed-ping "www.baidu.com" 2000)"
  [domain timeout]
  (let [addr (java.net.InetAddress/getByName domain)
        start (. System (nanoTime))
        result (.isReachable addr timeout)
        total (/ (double (- (. System (nanoTime)) start)) 1000000.0)]
    {:time total :result result}))