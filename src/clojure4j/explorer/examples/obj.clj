(ns clojure4j.explorer.examples.obj)

(defn obj-str
  "将对象code有keyword的形式转成string形式"
  [obj]
  (if (instance? String obj)
    obj (name obj)))

(println (obj-str :permission))

