(ns asd.core
  (:require [reagent.core :as reagent :refer [atom]]))



(def lights (atom [0 1 0]))

;; -------------------------
;; Views

(defn invert-light [current]
  (mod (inc current) 2))

(defn invert-clicked-light [current]
  (assoc current position (invert-light (get-in current position))))

(defn invert-indexed-light [index]
  (comp
    #(assoc %1 index (invert-light (get %1 index)))))

(defn print-light [index light-status]
  [:div {:on-click #(swap! lights (invert-indexed-light index))} (get @lights index)])

(defn current-page []
  [:div
   [:div [:h1 "lights out 2 3 asdadsads!"]]
   (map-indexed print-light @lights)])

;; -------------------------
;; Initialize app
(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
