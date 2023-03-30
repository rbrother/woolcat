(ns woolcat.styles
  (:require-macros [garden.def :refer [defcssfn defkeyframes]])
  (:require [spade.core :refer [defglobal]]
            [garden.units :as units]
            [garden.stylesheet :refer [at-media]]))

(def medium-font "20px")
(def large-font "30px")

(def default-font
  {:color :black, :background-color :white
   :font-family "Arial, Helvetica, sans-serif"
   :font-size medium-font})

(defglobal
  defaults
  [:body (assoc default-font :user-select "none")]
  [:.logo-font {:font-family "fredericka the great" :font-size "90px"}]
  [:.relative {:position "relative"}]
  [:.absolute {:position "absolute"}]
  [:.pad {:padding "8px"}]
  [:.center {:text-align "center"}]
  [:.margin {:margin "8px"}]
  [:.bold {:font-weight "bold"}]
  [:div.flex {:display "flex" :align-items "center"}]
  [:div.error {:display "grid" :grid-template-columns "1fr auto"
               :background "#f33" :color "black" :font-weight "bold"
               :padding "8px" :align-items "center"}])

