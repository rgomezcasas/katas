(ns rpn.core-test
  (:use midje.sweet)
  (:use [rpn.core]))

(facts "about RPN calculator"

  (fact "it returns self the same value when the expression is a value"
    (evaluate "0") => 0
    (evaluate "1") => 1)

  (fact "it evaluates addition"
    (evaluate "5 2 +") => 7)

  (fact "it evaluates subtraction"
    (evaluate "5 2 -") => 3)

  (fact "it evaluates the product"
    (evaluate "5 2 *") => 10)

  (fact "it evaluates the (integer) division"
    (evaluate "10 2 /") => 5
    (evaluate "7 2 /") => 3)

  (fact "it evaluates mix expressions with several operators"
    (evaluate "3 2 1 + *") => 9
    (evaluate "1 2 + 4 * 5 + 3 -") => 14
    (evaluate "10 5 5 / /") => 10
    (evaluate "1 2 5 - -") => 4))
