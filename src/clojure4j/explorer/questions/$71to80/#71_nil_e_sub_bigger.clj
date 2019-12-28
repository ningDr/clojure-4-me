(ns clojure4j.explorer.questions.$71to80.#71-nil-e-sub-bigger)
(defn sub-bigger
  "
    The -> macro threads an expression x through a variable number of forms.
    First, x is inserted as the second item in the first form, making a list of it if it is not a list already.
    Then the first form is inserted as the second item in the second form, making a list of that form if necessary.
    This process continues for all the forms. Using -> can sometimes make your code more readable.
  "
  [arg]
  (println (= (arg (sort (rest (reverse [2 5 4 1 3 6])))) (-> [2 5 4 1 3 6] (reverse) (rest) (sort) (arg)) 5))
  )
(sub-bigger last)