(ns string-calculator.core-test
  (:use midje.sweet)
  (:use [string-calculator.core]))

(facts "about `string-calculator`"
       (fact "it should add using comma as separator"
             (add "") => 0
             (add "1") => 1
             (add "2") => 2
             (add "1,2") => 3
             (add "3,10,15") => 28
             (add "123,342,126") => 591
             (add "1,2,3,4,5,6,7,8,9,10") => 55)
       (fact "it should add using newline as separator"
             (add "1\n2\n3") => 6)
       (fact "it should add using multiples separators"
             (add "1\n2,3") => 6)
       (fact "it should not allow using two separator one next to other"
             (add "1\n,2,3") => (throws Exception)
             (add "1\n\n2")  => (throws Exception)
             (add "1,,2")    => (throws Exception)
             (add "1\n,")    => (throws Exception))
       (fact "it should allow custom delimiters"
             (add "//;\n1;2") => 3)
       (fact "it shold not allow the usage of negative numbers"
             (add "1,-2,3")  => (throws IllegalArgumentException "Wrong numbers: -2")
             (add "1,-2,-3") => (throws IllegalArgumentException "Wrong numbers: -2, -3")
             (add "1,-2,3,5,4,6,-8989") => (throws IllegalArgumentException "Wrong numbers: -2, -8989")))
