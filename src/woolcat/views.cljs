(ns woolcat.views
  (:require
   [re-frame.core :as re-frame]
   [woolcat.db :refer [categories]]))

(def img-base "https://rjb-share.s3.eu-north-1.amazonaws.com/woolcat-media/")

(defn main-panel []
  [:div
   [:div.main-title "Chan Ann Chuang"]
   [:div.chinese-name "莊誠安"]
   (into
     [:div.product-table
      [:div]]
     (for [{:keys [name photo]} categories]
       [:div
        [:div.crop-container
         [:img.cropped-image {:src photo }]]
        [:div.margin-top [:span.large {:style {:position "relative" :top "2px"}} "⇝ "] name]
        ]))])
