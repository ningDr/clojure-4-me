(defproject clojure4j "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
                 [com.datomic/datomic-free "0.9.5697"]]
  :repl-options {:timeout 3000000}
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler clojure4j.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}}
  :user {:plugins             [[lein-kibit "0.1.8"]
                               [lein-cljfmt "0.6.6"]
                               [cider/cider-nrepl "0.22.4"]]
         :dependencies        [[org.clojure/tools.nrepl "0.2.13"]
                               [cljfmt "0.6.6"]]})
