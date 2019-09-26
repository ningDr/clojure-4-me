(ns test.identical
  (:require [clojure.set :as set])
  )
(println "-----" (set/difference #{1 2 3} #{2 3 4}))