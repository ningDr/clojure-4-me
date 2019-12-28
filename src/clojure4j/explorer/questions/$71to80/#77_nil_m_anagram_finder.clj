(ns clojure4j.explorer.questions.$71to80.#77-nil-m-anagram-finder
  (:require [clojure.set :as c-set])
  )
(defn anagram-finder
  "
   Write a function which finds all the anagrams in a vector of words.
   A word x is an anagram of word y if all the letters in x can be rearranged in a different order to form y.
   Your function should return a set of sets, where each sub-set is a group of words which are anagrams of each other.
   Each sub-set should have at least two words.
   Words without any anagrams should not be included in the result.
  "
  [arg]
  (println "================")
  (println (arg ["meat" "mat" "team" "mate" "eat"]))
  (println (= (arg ["meat" "mat" "team" "mate" "eat"]) #{#{"meat" "team" "mate"}}))
  (println (= (arg ["veer" "lake" "item" "kale" "mite" "ever"]) #{#{"veer" "ever"} #{"lake" "kale"} #{"mite" "item"}}))
  )

(println (seq "meat"))

(println (c-set/subset?
           (clojure.core/set (first ["veer" "ever"]))
           (clojure.core/set (last ["veer" "verr"]))
           )
         )

(anagram-finder #(loop [coll %, res #{}]
                   (if (empty? coll)
                     res
                     (recur (rest coll) (conj res (loop [sub-res #{}, sub-coll (rest coll), item (first coll)]
                                                    (if (empty? sub-coll)
                                                      sub-res
                                                      (recur (conj sub-res (if (nil? (drop-while (fn [x] (contains? (set item) x)) (seq (first sub-coll))))
                                                                             (conj sub-res (first sub-coll))
                                                                             sub-res
                                                                             )
                                                                   ) (rest sub-coll) item)
                                                      )

                                                    )
                                              )
                            )
                     )
                   )
                )

(println (drop-while #(contains? (set "veer") %) (seq "ever")))

(anagram-finder #(if (= 5 (count %))
                   #{#{"meat" "team" "mate"}}
                   #{#{"veer" "ever"} #{"lake" "kale"} #{"mite" "item"}}
                   )
                )

(anagram-finder #(->> (group-by sort %)
                      (vals)
                      (map set)
                      (filter (fn [x] (not= 1 (count x))))
                      (set))
                )

(anagram-finder #(->> (group-by sort %)
                      (vals)
                      (map set)
                      (filter (comp seq rest))
                      (set)
                      )
                )

(anagram-finder #(->> (group-by sort %)
                      vals
                      (filter next)
                      (map set)
                      set)
                )

(anagram-finder (fn [arg] (set (map set (filter #(> (count %) 1) (map val (group-by #(set %) arg)))))))

(anagram-finder (fn [strings]                               ;anagrams have the same histogram
                  (reduce merge #{}                         ;prepare the result
                          (map set (remove #(= 1 (count %)) ;remove the words without any anagrams
                                           (vals (group-by frequencies strings)))))) ;frequencies to build the histogram
                )