(ns woolcat.views
  (:require
   [re-frame.core :as re-frame]
   [woolcat.subs :as subs]
   ))

(defn main-panel []
  [:div.center
   [:div.logo-font "WoolCAT"]
   [:div [:img {:src "logo-small.png"}]]
   [:div "Architect made"]
   ])
