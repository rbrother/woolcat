(ns woolcat.views
  (:require
   [re-frame.core :as rf]
   [woolcat.db :refer [categories techniques]]
   [woolcat.category-page :as category-page]))

(def wiggly-arrow [:span.large {:style {:position "relative" :top "2px"}} "⇝ "])

(defn categories-view []
  (into
    [:div.product-table.col-span-2]
    (for [{:keys [name photo]} categories]
      [:div.link {:on-click #(rf/dispatch [::select-category name])}
       [:div.crop-container
        [:img.cropped-image {:src photo }]]
       [:div.margin-top wiggly-arrow name]])))

(defn technique-selector []
  (into
    [:div]
    (for [technique techniques]
      [:span.margin-right-32 technique])))

(defn main-panel []
  (let [selected-category @(rf/subscribe [::select-category])]
    [:div.main
     [:div
      [:div.main-title "Chan Ann Chuang"]
      [:div.chinese-name "莊誠安"]]
     [:div.justify-end "Info"]
     (if-not selected-category
       [categories-view]
       [category-page/view selected-category])
     [technique-selector]
     [:div.justify-end "Studio"]]))

(rf/reg-sub ::select-category (fn [db] (:selected-category db)))

(rf/reg-event-db ::select-category
  (fn [db [_ name]]
    (assoc db :selected-category name)))