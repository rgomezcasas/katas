(ns fox-goose-bag-of-corn.core
  (:gen-class))

; No pueden esgtar juntos
; * goose fox
; * goose corn
; * boat only boan + me + another
; <-  <take> ->
(def initial-position
  [[[:you :fox :goose :corn] [:boat] []]])

(def final-position
  [[[] [:boat] [:you :fox :goose :corn]]])

(def directions {:0 :left :1 :center :2 :right})

;(def initial-position
;  [[[:you :fox :goose :corn] [:boat] []]
;   [[:fox :corn] [:boat :you :goose] []]
;   [[:fox :corn] [:boat] [:you :goose]]
;   [[:fox :corn] [:boat :you] [:goose]]
;   [[:fox :corn :you] [:boat] [:goose]]
;   [[:corn] [:boat :you :fox] [:goose]]
;   [[:corn] [:boat] [:goose :you :fox]]
;   [[:corn] [:boat :you :goose] [:fox]]
;   [[:corn :you :goose] [:boat] [:fox]]
;   [[:goose] [:boat :you :corn] [:fox]]
;   [[:goose] [:boat] [:fox :you :corn]]
;   [[:goose] [:boat :you] [:fox :corn]]
;   [[:goose :you] [:boat] [:fox :corn]]
;   [[] [:boat :you :goose] [:fox :corn]]
;   [[] [:boat] [:you :fox :corn :goose]]])

(defn am-in-in? [stage]
  (some #(= :you %) stage))

(defn where-am-i? [last-position]
  (let [direction (first (filter #(am-in-in? (nth last-position %)) [0 1 2]))]
    (get directions (keyword (str direction)))))

(defn calculate-direction [where-i-im last-last-position]
  (let [where-i-was (if (nil? last-last-position) nil (where-am-i? last-last-position))]
    (case [where-i-was where-i-im]
      [nil :left] :right
      [:left :center] :right
      [:center :left] :center
      [:center :right] :center
      [:right :center] :left)))

(defn all-except-boat [stage]
  (filter #(not= :boat %) stage))

(defn extract-all-from-boat [last-position direction]
  (case direction
    :left [(all-except-boat (second last-position)) [:boat] [(last last-position)]]
    :right [(first last-position) [:boat] (all-except-boat (second last-position))]))

; When better add: has boat and I'm inside and count = 3
(defn is-forbidden [move]
  (or (= move [:fox :goose])
      (= move [:goose :corn])))

(defn calculate-to-transport [stage]
  (let [stage-without-me (filter #(not= :you %) stage)
        to-transport (rand-nth stage-without-me)
        stage-without-me-and-transport (filter #(not= to-transport %) stage)]
    (if (is-forbidden stage-without-me-and-transport)
      (calculate-to-transport stage)
      to-transport)))

(defn calculate-to-transport-allow-null [stage]
  (if (= 0 (rand-int 2)) (calculate-to-transport stage) nil))

(defn ->to-boat [last-position]
  (let [to-transport (calculate-to-transport-allow-null (first last-position))]
    [(filter #(and (not= :you %) (not= to-transport %)) (first last-position))
     (remove nil? [:boat :you to-transport])
     (last last-position)]))

(defn to-boat<- [last-position]
  (let [to-transport (calculate-to-transport-allow-null (last last-position))]
    [(first last-position)
     (remove nil? [:boat :you to-transport])
     (filter #(and (not= :you %) (not= to-transport %)) (last last-position))]))

(defn calculate-current-position [last-position where-i-am direction]
  (case where-i-am
    :left (->to-boat last-position)
    :center (extract-all-from-boat last-position direction)
    :right (to-boat<- last-position)))

(defn move-position [positions _]
  (let [last-position (last positions)
        last-last-position (second (reverse positions))
        where-i-am (where-am-i? last-position)
        direction (calculate-direction where-i-am last-last-position)
        updated-position (conj positions (calculate-current-position last-position where-i-am direction))]
    (if (= final-position (last updated-position)) (reduced updated-position) updated-position)))


(defn river-crossing-plan []
  (reduce move-position initial-position (range)))
