(ns woolcat.items-list
  (:require [accountant.core :as accountant]
            [re-frame.core :as rf]
            [woolcat.filters :as filters]
            [woolcat.db :refer [products]]))

(defn view [tag]
  [:<>
   [:div.main-title tag]
   (into
     [:div.product-table.col-span-2]
     (for [{:keys [name id photo]} (filters/filter-products products tag)]
       [:div.link {:on-click #(accountant/navigate! (str "/item/" id))}
        [:div.crop-container
         [:img.cropped-image {:src photo}]]
        [:div.margin-top name]]))
   [filters/filters-without-pics]])
