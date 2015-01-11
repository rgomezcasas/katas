(ns factorial-kata.core-test
  (:require [clojure.test :refer :all]
            [factorial-kata.core :refer :all]))

(deftest it-should-calculate-the-factorial-of-zero
    (is (= 1 (factorial-of 0))))

(deftest it-should-calculate-the-factorial-of-one
  (is (= 1 (factorial-of 1))))

(deftest it-should-calculate-the-factorial-of-integers
  (is (= 6 (factorial-of 3)))
  (is (= 120 (factorial-of 5)))
  (is (= 3628800 (factorial-of 10)))
  (is (= 15511210043330985984000000N (factorial-of 25))))
