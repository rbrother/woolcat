(ns woolcat.styles
  (:require-macros [garden.def :refer [defcssfn defkeyframes]])
  (:require [spade.core :refer [defglobal]]
            [garden.units :as units]
            [garden.stylesheet :refer [at-media]]))

(def medium-font "20px")
(def large-font "30px")

(def default-font
  {:color :black, :background-color :white
   :font-family "Roboto"
   :font-size medium-font})

(defglobal
  defaults
  [:body (assoc default-font :user-select "none" :padding "16px" :margin "0")]
  [:div.main-title { :font-size "50px" :margin-top "20px" }]
  [:div.chinese-name {:font-family "Noto Serif SC", :font-size "30px"}]
  [:.logo-font {:font-family "fredericka the great" :font-size "90px"}]
  [:.script {:font-family "badscript"}]
  [:.large {:font-size large-font}]
  [:.relative {:position "relative"}]
  [:.absolute {:position "absolute"}]
  [:.pad {:padding "8px"}]
  [:.center {:text-align "center"}]
  [:.margin {:margin "8px"}]
  [:.bold {:font-weight "bold"}]
  [:div.flex {:display "flex" :align-items "center"}]
  [:div.error {:display "grid" :grid-template-columns "1fr auto"
               :background "#f33" :color "black" :font-weight "bold"
               :padding "8px" :align-items "center"}]
  [:div.product-table
   {:display "grid", :width "90%"
    :margin-right "auto"
    :margin-top "160px"
    :grid-template-columns "auto auto auto auto 1fr"
    :gap "20px"}]
  [:div.crop-container {:width "300px" :height "300px" :overflow "hidden" :position "relative"}]
  [:img.cropped-image {:width "100%" :height "100%" :object-fit "cover" }]
  )

