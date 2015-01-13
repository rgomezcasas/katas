(ns rpn.core)

(declare
  evaluate-value
  parse
  tokenize)

;(def ^:private evaluate-parsed-tokens (partial reduce evaluate-value []))
; Usando Macro
;(defn evaluate [expression]
;  (->> expression
;        (tokenize)
;        (parse)
;        evaluate-parsed-tokens
;        (first)))

(defn evaluate [expression]
  (first
    (reduce evaluate-value
      []
      (parse (tokenize expression)))))

(defn- parse-int [s]
  (Integer. (re-find #"\d+" s)))

(defn- tokenize [expression]
  (clojure.string/split expression #" "))

(defn- parse-token [token]
  (let [operators {"+" + "-" - "*" * "/" quot}]
    (if-let [operator (get operators token)]
      operator
      (parse-int token))))

(defn- parse [tokens]
  (map parse-token tokens))

(defn- evaluate-value [stack value]
  (if (number? value)
    (conj stack value)
    (conj (pop (pop stack))
      (apply value (take-last 2 stack)))))
