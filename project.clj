(defproject clojure4j "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
                 [com.datomic/datomic-free "0.9.5697"]
                 ;; 音乐库
                 [overtone "0.10.6"]
                 ;; http
                 [clj-http "3.10.0"]
                 ;; http-kit
                 [http-kit "2.3.0"]
                 ;; RSS
                 [org.clojars.scsibug/feedparser-clj "0.4.0"]
                 ;; email
                 [com.draines/postal "2.0.3"]
                 ]
  :repl-options {:timeout 30000}
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler clojure4j.handler/app}
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring/ring-mock "0.3.2"]]}}
  :user {:plugins             [[lein-kibit "0.1.8"]
                               [lein-cljfmt "0.6.6"]
                               [cider/cider-nrepl "0.22.4"]]
         :dependencies        [[org.clojure/tools.nrepl "0.2.13"]
                               [cljfmt "0.6.6"]]})
