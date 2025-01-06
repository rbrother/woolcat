(ns woolcat.home
  (:require
    [re-frame.core :as rf]
    [woolcat.filters :as filters]
    [woolcat.item-page :as item-page]
    [woolcat.items-list :as items-list]))

(defn top-selection []
  [:<>
   [:div.col-span-2] ;; spacer
   [filters/filters-with-pics]])

(defn main-panel []
  (let [filter @(rf/subscribe [::filters/filter])
        selected-item @(rf/subscribe [::item-page/selected-item])]
    [:div.main
     [:div [:a {:href "/"}
            [:div.main-title "Chan Ann Chuang " [:span.gray "Atelier"]]
            [:div.chinese-name "莊誠安"]]]
     [:div.justify-end "Info"]
     (cond
       selected-item [item-page/view]
       filter [items-list/view filter]
       :else [top-selection])
     [:div]
     [:div.justify-end
      [:div.pad [:a {:href "/item/studio"} "Studio"]]
      [:div.pad [:a {:href "/items/Stone"} "Stone"]]]]))

(rf/reg-event-db ::home
  (fn [db _]
    (dissoc db :filter :selected-item)))