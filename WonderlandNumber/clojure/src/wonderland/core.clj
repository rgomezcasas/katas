(ns wonderland.core)

(defn- digits [number]
  (set (str number)))

(defn- has-all-the-same-digits? [number1 number2]
  (= (digits number1) (digits number2)))

(defn- is-wonder? [wondernum]
  (and
    (= 6 (count (str wondernum)))
    (every? #(has-all-the-same-digits? wondernum (* % wondernum)) (range 2 7))))

(defn wonderland-number []
  (first (filter is-wonder? (range 100000 999999))))
