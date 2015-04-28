(ns string-calculator.core)
(require '[clojure.string :as str])

(def custum-delimiter-regex #"//(\D)\n([\d\D]*)")
(def invalid-string-regex #"\n,|,\n")
(def default-separators ",|\n")

(defn- parse-int [string] (Integer. (re-find #"-?\d+" string)))
(defn- apply-regex [regex string position] (nth (first (re-seq regex string)) position))

(defn- generate-error-message [negative-numbers]
  (str "Wrong numbers: " (str/join ", " negative-numbers)))

(defn- guard-negative-numbers [numbers]
  (let [negative-numbers (filter neg? numbers)]
    (if (not (empty? negative-numbers))
      (throw (IllegalArgumentException. (generate-error-message negative-numbers))))))

(defn- compute [numbers-to-add]
  (guard-negative-numbers numbers-to-add)
  (apply + numbers-to-add))

(defn- has-custom-separator? [string]
  (not (nil? (re-find #"//" string))))

(defn- extract-custom-separator [string]
  (if (has-custom-separator? string)
    (str "|" (apply-regex custum-delimiter-regex string 1))))

(defn- custom-delimiter [string]
  (str default-separators (extract-custom-separator string)))

(defn calculate-delimiter-regex [string]
  (re-pattern (custom-delimiter string)))

(defn- extract-numbers [string]
  (if (has-custom-separator? string)
    (apply-regex custum-delimiter-regex string 2)
    string))

(defn- string-to-numbers [string]
  (mapv parse-int (str/split (extract-numbers string) (calculate-delimiter-regex string))))

(defn- well-formatted? [string]
  (nil? (re-find invalid-string-regex string)))

(defn- guard-is-well-formatted [string]
  (if (not (well-formatted? string)) (throw Exception)))

(defn add [string]
  (guard-is-well-formatted string)
  (if (str/blank? string) 0 (compute (string-to-numbers string))))
