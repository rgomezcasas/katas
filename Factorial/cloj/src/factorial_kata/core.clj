(ns factorial-kata.core)

(defn factorial-of [number]
  (if (<= number 1)
    1
    (bigint (* number (factorial-of (dec number))))))
