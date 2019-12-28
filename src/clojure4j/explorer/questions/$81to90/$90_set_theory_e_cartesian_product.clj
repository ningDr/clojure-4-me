(ns clojure4j.explorer.questions.$81to90.$90-set-theory-e-cartesian-product)
(defn cartesian-product
  "Write a function which calculates the Cartesian product of two sets."
  [arg]
  (println (= (arg #{"ace" "king" "queen"} #{"♠" "♥" "♦" "♣"})
              #{["ace"   "♠"] ["ace"   "♥"] ["ace"   "♦"] ["ace"   "♣"]
                ["king"  "♠"] ["king"  "♥"] ["king"  "♦"] ["king"  "♣"]
                ["queen" "♠"] ["queen" "♥"] ["queen" "♦"] ["queen" "♣"]}))

  (println (= (arg #{1 2 3} #{4 5})
              #{[1 4] [2 4] [3 4] [1 5] [2 5] [3 5]}))

  (println (= 300 (count (arg (into #{} (range 10))
                             (into #{} (range 30)))))))

(cartesian-product (fn [set1 set2] (into #{} (for [x set1 y set2] [x y]))))