(ns count-coin-change.core)

; 50 => half-dollars
; 25 => quarters
; 10 => nickels
; 5  => pennies
; 1  => cent
(def coins [50 25 10 5])

(defn exists-as-coin? [amount]
  (some #(= % amount) coins))

(defn count-coin-change [amount]
  (if (zero? amount)
    0
    (let [applicable-coins (filter #(< % amount) coins)]
      (if (< amount 5)
        1
        (if (exists-as-coin? amount)
          (+ 2 (apply + (map #(count-coin-change (- amount %)) applicable-coins)))
          (+ 1 (apply + (map #(count-coin-change (- amount %)) applicable-coins))))))))
