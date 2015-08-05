(ns almost-prime.core-test
  (:use midje.sweet)
  (:use [almost-prime.core]))

(facts "about `almost-prime`"
       (fact "it should calcualte the number of almost prime numbers in a range"
             (count-almost-primes-between 1 10) => 4
             (count-almost-primes-between 10 20) => 3
             ;(count-almost-primes-between 5000 5000000) => 31)
       ))
