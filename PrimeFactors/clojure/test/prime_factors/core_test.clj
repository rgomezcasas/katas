(ns prime-factors.core-test
  (:use midje.sweet)
  (:use [prime-factors.core]))

(facts "about prime factors"
       (fact "it is able to calculate the prime factors"
             (prime-factors-of 1)  => [1]
             (prime-factors-of 2)  => [1 2]
             (prime-factors-of 3)  => [1 3]
             (prime-factors-of 4)  => [1 2 4]
             (prime-factors-of 18) => [1 2 3 6 9 18]
             (prime-factors-of 24) => [1 2 3 4 6 8 12 24]))
