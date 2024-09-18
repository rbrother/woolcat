(ns woolcat.views
  (:require
   [re-frame.core :as re-frame]
   [woolcat.db :refer [product-data]]))

(def img-base "https://rjb-share.s3.eu-north-1.amazonaws.com/woolcat-media/")

(defn main-panel []
  [:div.main
   [:div {:style {:font-size 70}} "Chan Ann Chuang"]
   [:div {:style {:font-size 50}} "莊誠安"]
   (into
     [:div.product-table]
     (for [item (filter :category-header product-data)]
       [:div [:img {:src (:photo item)
                    :width "300px" :height "300px"}]]))])
