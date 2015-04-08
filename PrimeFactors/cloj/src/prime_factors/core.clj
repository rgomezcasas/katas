(ns prime-factors.core)

(defn- is-divisible? [num possible-divisor]
  (zero? (mod num possible-divisor)))

(defn- prime-factors [x y acc]
  (if (> y (quot x 2))
    (conj acc x)
    (prime-factors x (+ y 1) (if (is-divisible? x y) (conj acc y) acc))))

(defn prime-factors-of [x]
  (prime-factors x 1 []))
