(ns print-diamond.core)

(def alphabet ["A" "B" "C" "D" "E"])

(defn spaces [n]
  (clojure.string/join (repeat n "_")))

(defn current-line [letter pos letter-pos]
  (if (= pos 0)
    (str (spaces letter-pos) letter)
    (str (spaces (- letter-pos pos))
         letter
         (spaces (dec (* 2 pos)))
         letter)))

(defn line-in-middle [pos lines current-letter total-spaces]
  (let [previous-lines (take pos lines)]
    (concat previous-lines
            [(current-line current-letter pos total-spaces)]
            (reverse previous-lines))))

(defn print-diamond
  ([letter]
   (clojure.string/join
     "\n"
     (print-diamond letter alphabet 0 [] (.indexOf alphabet letter))))

  ([letter letters pos lines letter-pos]
   (let [current-letter (first letters)]
     (if (= letter current-letter)
       (line-in-middle pos lines current-letter letter-pos)
       (recur letter
              (rest letters)
              (inc pos)
              (conj lines (current-line current-letter pos letter-pos))
              letter-pos)))))
