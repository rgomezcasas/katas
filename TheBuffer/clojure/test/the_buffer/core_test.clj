(ns the-buffer.core-test
  (:use midje.sweet)
  (:use [the-buffer.core]))

(facts "about `the buffer Tuenti Challange kata`"
       (fact "it should return the number of men that can use the urinals simultaneously"
             (mapv calculate [1 2 3 4 5]) => [1 1 2 2 3]))
