(ns print-diamond.core-test
  (:use midje.sweet)
  (:use [print-diamond.core]))

(facts "about print diamond kata"
       (fact "it prints the a letter"
             (print-diamond "A") => "A")
       (fact "it prints the b letter"
             (print-diamond "B") => "_A\nB_B\n_A")
       (fact "it prints the c letter"
             (print-diamond "C") => "__A\n_B_B\nC___C\n_B_B\n__A")
       (fact "it prints the d letter"
             (print-diamond "D") => "___A\n__B_B\n_C___C\nD_____D\n_C___C\n__B_B\n___A")
       (fact "it prints the e letter"
             (print-diamond "E") => "____A\n___B_B\n__C___C\n_D_____D\nE_______E\n_D_____D\n__C___C\n___B_B\n____A"))
