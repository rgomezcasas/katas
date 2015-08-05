(ns count-coin-change.core-test
  (:use midje.sweet)
  (:use [count-coin-change.core]))

(facts "about `count-coin-change`"
       (fact "it returns 0 solutions to 0 amount"
             (count-coin-change 0) => 0)
       (fact "it returns 1 solution to 1 cent"
             (count-coin-change 1) => 1)
       (fact "it returns 1 solutions for a 2 cents"
             (count-coin-change 2) => 1)
       (fact "it returns 2 solutions for a penny"
             (count-coin-change 5) => 2)
       (fact "it returns 4 solutions for a nickel"
             (count-coin-change 10) => 4)
       (fact "it returns 5 solutions for a 11 coins"
             (count-coin-change 11) => 4)
       ;(fact "it returns  solutions for 15 coins"
       ;      (count-coin-change 15) => 5)
       )
