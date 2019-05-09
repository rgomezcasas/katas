(ns word-wrap.core)

(defn should-be-swapalbe [split-at current-position]
  (= split-at current-position))

(defn is-swapalbe? [split-at current-position char]
  (and (should-be-swapalbe split-at current-position)
       (= char " ")))

(defn calculate-current-char [split-at current-position char]
  (if (is-swapalbe? split-at current-position char)
    \newline
    char))

(defn calculate-split-at [index split-at split-every current-char]
  (if (< index split-at)
    split-at
    (if (is-swapalbe? split-at index current-char)
      (+ split-at split-every)
      (inc split-at))))

(defn format-string [accumulate current-char]
  (let [index (inc (get accumulate :index))
        truly-current-char (calculate-current-char (get accumulate :split-at) index current-char)
        split-at (calculate-split-at index (get accumulate :split-at) (get accumulate :split-every) current-char)]
    {:index       index
     :split-every (get accumulate :split-every)
     :split-at    split-at
     :text        (str (get accumulate :text) truly-current-char)}))

(defn wrap [text split-every]
  (get
    (reduce
      format-string
      {:index 0
       :split-at split-every
       :split-every split-every
       :text ""}
      (clojure.string/split text #""))
    :text))


