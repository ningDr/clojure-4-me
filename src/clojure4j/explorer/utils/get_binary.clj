(ns clojure4j.explorer.utils.get-binary)
(defn get-binary
  "version:0.1
   doc:Transfer a number to a binary format.
       You need give a positive number or zero,
       it will return a list which consists of one or zero;
       You can give an expected length for the results,
       but if the length of your need is smaller than the results,
       it will take from the front numbers for you."
  ([your-num]
   (loop [quotient (int (/ your-num 2)), bin-seq (list (mod your-num 2))]
     (if (= 0 quotient)
       bin-seq
       (recur (int (/ quotient 2)) (conj bin-seq (mod quotient 2))))))
  ([your-num expect-len]
   (let [bin-seq (get-binary your-num)
         bin-len (count bin-seq)]
     (if (> expect-len bin-len)
       (apply conj bin-seq (repeat (- expect-len bin-len) 0))
       (take expect-len bin-seq)))))