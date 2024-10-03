(ns woolcat.items-list
  (:require [re-frame.core :as rf]
            [woolcat.filters :as filters]
            [woolcat.db :refer [products]]))

(def img-base "https://rjb-share.s3.eu-north-1.amazonaws.com/woolcat-media/products/")

(defn view []
  (let [filter-info @(rf/subscribe [::filters/filter])]
    [:<>
     [filters/current-filter]
     (into
       [:div.product-table.col-span-2]
       (for [{:keys [name id]} (filters/filter-products products filter-info)]
         (let [photo (str img-base id ".jpg")]
           [:div.link {:on-click #(rf/dispatch [::select-dimension name])}
            [:div.crop-container
             [:img.cropped-image {:src photo}]]
            [:div.margin-top name]])))]))