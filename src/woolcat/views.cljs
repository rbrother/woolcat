(ns woolcat.views
  (:require
    [re-frame.core :as rf]
    [woolcat.db :refer [dimensions techniques]]
    [woolcat.items-list :as items-list]))

(def wiggly-arrow [:span.large {:style {:position "relative" :top "2px"}} "⇝ "])

(defn filter-selection []
  [:<>
   (into
     [:div.product-table.col-span-2]
     (for [{:keys [name photo]} dimensions]
       [:div.link {:on-click #(rf/dispatch [::select-dimension name])}
        [:div.crop-container
         [:img.cropped-image {:src photo}]]
        [:div.margin-top wiggly-arrow name]]))
   (into
     [:div.technique-table.col-span-2]
     (for [technique techniques]
       [:div wiggly-arrow technique]))])

(defn main-panel []
  (let [filter @(rf/subscribe [::filter])]
    [:div.main
     [:div
      [:div.main-title "Chan Ann Chuang"]
      [:div.chinese-name "莊誠安"]]
     [:div.justify-end "Info"]
     (if-not filter
       [filter-selection]
       [items-list/view filter])
     [:div]
     [:div.justify-end "Studio"]]))

(rf/reg-sub ::filter
  (fn [db] (:filter db)))

(rf/reg-event-db ::select-dimension
  (fn [db [_ name]]
    (assoc-in db [:filter :dimension] name)))