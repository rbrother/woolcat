(ns woolcat.views
  (:require
    [re-frame.core :as rf]
    [woolcat.filters :as filters]
    [woolcat.items-list :as items-list]))

(defn main-panel []
  (let [filter @(rf/subscribe [::filters/filter])]
    [:div.main
     [:div
      [:div.main-title "Chan Ann Chuang"]
      [:div.chinese-name "莊誠安"]]
     [:div.justify-end "Info"]
     (if-not filter
       [:<>
        [:div.col-span-2] ;; spacer
        [filters/dimension-filters-with-pics]
        [filters/technique-filters]]
       [items-list/view filter])
     [:div]
     [:div.justify-end "Studio"]]))
