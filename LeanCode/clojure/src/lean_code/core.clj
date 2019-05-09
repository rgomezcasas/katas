(ns lean-code.core)
(require '[clojure.string :as str])

(def products {:apple 100 :cherry 75 :banana 75 :manzana 175 :apfel 100})
(def discounts {:cherry {2 "20%"} :banana {2 "100%"} :manzana {4 "1€"} :apple {4 "1€"} :apfel {2 "1.5€"}})
(def total-discounts {5 "2€"})

(defn- price-formatter [string-prices product-or-price]
  (if (number? product-or-price)
    (str string-prices " -> " product-or-price "\n")
    (str string-prices (str/replace product-or-price ":" ""))))

(defn- last-price [total-prices]
  (if (empty? total-prices) 0 (last total-prices)))

(defn product-price [product]
  (get products (keyword product)))

(defn- price-calculator [total-prices product]
  (conj total-prices
        (keyword product)
        (+ (last-price total-prices) (product-price product))))

(defn print-on-screen [text]
  text)

(defn calculate-price [product]
  (reduce
    price-formatter
    ""
    (reduce price-calculator [] (str/split product #" "))))
