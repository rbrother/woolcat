(ns woolcat.views
  (:require
   [re-frame.core :as re-frame]
   [woolcat.db :refer [categories]]))

(def img-base "https://rjb-share.s3.eu-north-1.amazonaws.com/woolcat-media/")

(def wiggly-arrow [:span.large {:style {:position "relative" :top "2px"}} "⇝ "])

(defn main-panel []
  [:div.main.border-blue
   [:div.border
    [:div.main-title "Chan Ann Chuang"]
    [:div.chinese-name "莊誠安"]]
   [:div.border {:style {:justify-self "end"}} "Info"]
   (into
     [:div.product-table.border {:style {:grid-column "span 2"}}]
     (for [{:keys [name photo]} categories]
       [:div.border-blue
        [:div.crop-container
         [:img.cropped-image {:src photo }]]
        [:div.margin-top wiggly-arrow name]]))])
