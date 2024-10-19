(ns woolcat.views
  (:require
    [re-frame.core :as rf]
    [woolcat.filters :as filters]
    [woolcat.items-list :as items-list]
    [woolcat.item-page :as item-page]))

(defn top-selection []
  [:<>
   [:div.col-span-2] ;; spacer
   [filters/dimension-filters-with-pics]
   [filters/technique-filters]])

(defn main-panel []
  (let [filter @(rf/subscribe [::filters/filter])
        selected-item @(rf/subscribe [::item-page/selected-item])]
    [:div.main
     [:div
      [:div.main-title "Chan Ann Chuang " [:span.gray "Studio"]]
      [:div.chinese-name "莊誠安"]]
     [:div.justify-end "Info"]
     (cond
       selected-item [item-page/view]
       filter [items-list/view filter]
       :else [top-selection])
     [:div]
     [:div.justify-end "Studio"]]))
