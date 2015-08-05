(ns almost-prime.core
  [:require [clojure.core.reducers :as r]])

(defn prime-factors [number]
  (loop [current-number number divisor 2 factors []]
    (if (< current-number 2)
      factors
      (if (= 0 (rem current-number divisor))
        (recur (/ current-number divisor) divisor (conj factors divisor))
        (recur current-number (inc divisor) factors)))))

(defn is-prime? [number]
  (if (even? number)
    false
    (let [root (num (int (Math/sqrt number)))]
      (loop [i 3]
        (if (> i root)
          true
          (if (zero? (mod number i))
            false
            (recur (+ i 2))))))))

(def prime? (memoize is-prime?))

(defn is-almost-prime? [number]
  (if (prime? number)
    false
    (= 2 (count (prime-factors number)))))

(def almost-prime? (memoize is-almost-prime?))

(defn count-almost-primes-between [start-range end-range]
  (count (r/foldcat (r/filter almost-prime? (range start-range (inc end-range))))))

