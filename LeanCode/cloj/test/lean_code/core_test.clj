(ns lean-code.core-test
  (:use midje.sweet)
  (:use [lean-code.core]))

(facts "about lean kata"
       (fact "it should print on screen"
             (print-on-screen "aaa") => "aaa")

       (fact "it should return an apple price"
             (calculate-price "apple") => "apple -> 100\n")
       (fact "it should calculate the price of multiple products"
             (calculate-price "apple cherry cherry") => "apple -> 100\ncherry -> 175\ncherry -> 250\n")
       (fact "it should calculate prices with discount"
             (calculate-price "apfel manzana manzana apfel"))
       )
