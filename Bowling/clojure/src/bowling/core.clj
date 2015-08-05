(ns bowling.core)

; BOWLING GRAMMAR:
;   * Score: Final punctuation (10 or 12 Frames)
;   * Frame: A conjuction of rolls (1 or 2)
;   * Roll: A "throw"
;   * Spare: The sum of the Frame is 10 (in 2 Rolls)
;   * Strike: The sum of the Frame is 10 (in 1 Roll)

(def initial-score 0)

(def initial-frame-turn 1)

(defn- has-bonus? [frame bonus]
  (= bonus (:bonus frame)))

(defn- strike? [frame]
  (has-bonus? frame :strike))

(defn- spare? [frame]
  (has-bonus? frame :spare))

(defn- has-not-bonus? [frame]
  (has-bonus? frame :none))

(defn- current-frame [frames]
  (first frames))

(defn- next-frame [frames]
  (second frames))

(defn- next-frame? [frames]
  (> (count frames) 1))

(defn- no-bonus-frame-punctuation [frame]
  (:score frame))

(defn- spare-punctuation [frames]
  (+ 10 (:first-roll-score (next-frame frames))))

(defn- strike-punctuation [frames]
  (+ 10 (cond (next-frame? frames) (cond (strike? (next-frame frames)) (+ 10 (cond (next-frame? (rest frames)) (:first-roll-score (next-frame (rest frames)))
                                                                                   :else 0))
                                         :else (+ (:score (next-frame frames))))
              :else 0)))

(defn- add-bonus-to-frames [frame]
  (let [frame-score (reduce + frame)]
    (cond (and (= frame-score 10)
               (= (count frame) 2)) {:score 10 :bonus :spare :first-roll-score (first frame) :second-roll-score (second frame)}
          (and (= frame-score 10)
               (= (count frame) 1)) {:score 10 :bonus :strike :first-roll-score 10}
          :else {:score frame-score :bonus :none :first-roll-score (first frame) :second-roll-score (second frame)})))

; REFACTOR THIS :)
(defn- calculate-score-by-frames [frames puntuaction frame-turn]
  (cond (= 0 (count frames)) puntuaction
        :else (let [frame (current-frame frames)]
                (calculate-score-by-frames
                  (rest frames)
                  (+ puntuaction (cond (>= frame-turn 10) (no-bonus-frame-punctuation frame)
                                       :else (cond (has-not-bonus? frame) (no-bonus-frame-punctuation frame)
                                                   :else (cond (spare? frame) (spare-punctuation frames)
                                                               :else (strike-punctuation frames)))))
                  (+ frame-turn 1)))))

; AND THIS :)
(defn- group-rolls-in-frames [aggrupation roll]
  (cond (= 10 roll) (conj aggrupation [roll])
        (= 0 (count aggrupation)) [[roll]]
        :else (cond (= 2 (count (last aggrupation))) (conj aggrupation [roll])
                    :else (cond (= 10 (first (last aggrupation))) (conj aggrupation [roll])
                                :else (conj (pop aggrupation) (conj (last aggrupation) roll))))))

(defn- rolls-to-frames [rolls]
  (map add-bonus-to-frames (reduce group-rolls-in-frames [] rolls)))

(defn score [rolls]
  (calculate-score-by-frames (rolls-to-frames rolls) initial-score initial-frame-turn))
