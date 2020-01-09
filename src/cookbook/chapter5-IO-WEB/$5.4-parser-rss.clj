(ns cookbook.chapter5.$5.4-parser-rss
  (:require [feedparser-clj.core :as rss]))
;; 取得RSS数据并解析RSS数据

;; 以RSS Feed的URL作为参数获取数据并解析成clojure数据
(rss/parse-feed "https://github.com/clojure-cookbook/clojure-cookbook/commits/master.atom")

;; 用java.io.InputStream为参数调用parse-feed，从文件或其它位置读取数据
(with-open [writer (clojure.java.io/writer "master.com")]
  (spit writer
        (slurp "https://github.com/clojure-cookbook/clojure-cookbook/commits/master.atom")))

(with-open [stream (clojure.java.io/input-stream "master.com")]
  (rss/parse-feed stream))

;; 生成RSS条目的惰性列表
(defn next-uri
  "Return the rel=next href in a feed."
  [feed]
  (-> feed
      :entry-links
      (->> (filter #(= (:rel %) "next")))
      first
      :href))

(defn lazy-stream
  "Return a lazy stream of RSS entries."
  [uri]
  (let [raw-response (rss/parse-feed uri)]
    (lazy-cat (:entries raw-response)
              (if-let [nxt (next-uri raw-response)]
                (lazy-stream nxt)))))

;; 验证惰性加载
(def youtube-feed "http://gdata.youtube.com/feeds/api/videos")
;; 一次取得的条目
(count (rss/parse-feed youtube-feed))
;; 惰性取得指定数目
(count (take 50 (lazy-stream youtube-feed)))