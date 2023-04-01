(ns woolcat.views
  (:require
   [re-frame.core :as re-frame]
   [woolcat.subs :as subs]
   ))

(def img-base "https://rjb-share.s3.eu-north-1.amazonaws.com/woolcat-media/")

(defn main-panel []
  [:div.main
   [:div.logo-font "WoolCAT"]
   [:div [:img {:src "logo-small.png"}]]
   [:div.script.large "Architect made"]
   [:div [:img {:src (str img-base "dino1.jpg")
                :width "500px"}]]
   ])
