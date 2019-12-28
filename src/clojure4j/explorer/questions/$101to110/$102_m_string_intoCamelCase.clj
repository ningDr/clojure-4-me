(ns clojure4j.explorer.questions.$101to110.$102-m-string-intoCamelCase)
(defn into-camel-case
  "When working with java, you often need to create an object with fieldsLikeThis,
   but you'd rather work with a hashmap that has :keys-like-this until it's time to
   convert. Write a function which takes lower-case hyphen-separated strings and converts
   them to camel-case strings."
  [arg]
  (println "=================")
  (println (= (arg "something") "something"))
  (println (= (arg "multi-word-key") "multiWordKey"))
  (println (= (arg "leaveMeAlone") "leaveMeAlone")))

(into-camel-case (fn [str-x]
                   (let [[head & tail] (clojure.string/split str-x #"\-")]
                     (clojure.string/join
                      (reduce #(concat %1 (clojure.string/capitalize %2)) head tail)))))

;; ==========================
(into-camel-case #(let [[_ & tail :as col] (clojure.string/split % #"\-")]
                    (->> (map clojure.string/capitalize tail)
                         (concat (take 1 col))
                         clojure.string/join)))

(into-camel-case #(clojure.string/replace % #"\-." (comp clojure.string/upper-case last)))

