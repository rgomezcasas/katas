(ns bowling.core-test
  (:use midje.sweet)
  (:use [bowling.core]))

(def a-gutter-game
  (repeat 20 0))
; => 0

(def a-game-with-one-pin-knocked-each-roll
  (repeat 20 1))
; => 20

(def a-no-spares-no-strikes-game
  (concat [5 6 4 5 3 1] (repeat 14 0)))
; => 24

(def a-game-with-spares
  (concat [4 6 5] (repeat 17 0)))
; => 20

(def a-game-with-strikes
  (concat [10 7 2] (repeat 16 0)))
; => 28

(def a-game-with-spare-in-10th-frame
  (concat (repeat 14 0) [3 1 4 2 4 6] [2]))
; => 22

(def a-game-with-strike-in-10th-frame
  (concat (repeat 14 0) [3 1 4 2 10] [2 3]))
; => 25

(def a-perfect-game
  (repeat 12 10))
; => 300

(facts "about scoring bowling game"
       (fact "it scores a gutter game as zero"
             (score a-gutter-game) => 0)
       (fact "it scores a one pin down every frame game"
             (score a-game-with-one-pin-knocked-each-roll) => 20)
       (fact "it scores the sum of all knocked down pins for a game"
             (score a-no-spares-no-strikes-game) => 24)
       (fact "it scores a game with spares"
             (score a-game-with-spares) => 20)
       (fact "it scores a game with strikes"
             (score a-game-with-strikes) => 28)
       (fact "it scores a game with a spare in the 10th frame"
             (score a-game-with-spare-in-10th-frame) => 22)
       (fact "it scores a game with a strike in the 10th frame"
             (score a-game-with-strike-in-10th-frame) => 25)
       (fact "it scores a perfect game"
             (score a-perfect-game) => 300))
