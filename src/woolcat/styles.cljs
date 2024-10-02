(ns woolcat.styles
  (:require [spade.core :refer [defglobal]]))

(def medium-font "20px")
(def large-font "30px")

(def default-font
  {:color :black, :background-color :white
   :font-family "Roboto"
   :font-size medium-font
   :font-weight "400"})

(defglobal
  defaults
  [:body (assoc default-font :user-select "none" :padding "16px")]
  [:div.main-title {:font-size "25px"}]
  [:div.chinese-name {:font-family "Noto Sans HK", :font-size "20px",
                      :font-weight 500}]
  [:.logo-font {:font-family "fredericka the great" :font-size "90px"}]
  [:.script {:font-family "badscript"}]
  [:.large {:font-size large-font}]
  [:.relative {:position "relative"}]
  [:.absolute {:position "absolute"}]
  [:.pad {:padding "8px"}]
  [:.center {:text-align "center"}]
  [:.margin-top {:margin-top "8px"}]
  [:.margin {:margin "8px"}]
  [:.bold {:font-weight "bold"}]
  [:div.flex {:display "flex" :align-items "center"}]
  ;; [:div.border {:border "solid 2px #4f4"}]
  ;; [:div.border-blue {:border "solid 2px #88f"}]
  [:div.error {:display "grid" :grid-template-columns "1fr auto"
               :background "#f33" :color "black" :font-weight "bold"
               :padding "8px" :align-items "center"}]
  [:div.main {:display "grid", :grid-template-columns "auto auto"
              :max-width "1200px"
              :margin-left "auto", :margin-right "auto"}]
  [:div.product-table
   {:display "grid"
    :margin-top "160px"
    :grid-template-columns "auto auto auto auto auto"
    :gap "40px"}]
  [:div.crop-container {:width "200px" :height "200px" :overflow "hidden" :position "relative"}]
  [:img.cropped-image {:width "100%" :height "100%" :object-fit "cover"}]
  [:div.grid {:display "grid"}]
  )

