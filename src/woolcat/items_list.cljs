(ns woolcat.items-list
  (:require [re-frame.core :as rf]
            [woolcat.item-page :as item-page]
            [woolcat.filters :as filters]
            [woolcat.db :refer [products]]))

(defn view []
  (let [filter-info @(rf/subscribe [::filters/filter])]
    [:<>
     [filters/current-filter]
     (into
       [:div.product-table.col-span-2]
       (for [{:keys [name id photo]} (filters/filter-products products filter-info)]
         [:div.link {:on-click #(rf/dispatch [::item-page/select-item id])}
          [:div.crop-container
           [:img.cropped-image {:src photo}]]
          [:div.margin-top name]]))]))
