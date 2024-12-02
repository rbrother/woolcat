(ns woolcat.item-page
  (:require [re-frame.core :as rf]
            [woolcat.db :as db]))

(defn view []
  (let [item-id @(rf/subscribe [::selected-item])
        {:keys [name tags photo detail-photos] :as _item} (get db/products-index item-id)
        all-photos (concat [photo] detail-photos)]
    (into [:<>
           [:div.col-span-2
            [:p.main-title name]
            (into [:div] (interpose ", " (for [tag tags] [:a {:href (str "/items/" tag)} tag])))]]
          (for [photo all-photos]
            [:div.col-span-2.justify-center
             [:img.large-photo {:src photo}]]))))

;; Subs

(rf/reg-sub ::selected-item (fn [db _] (:selected-item db)))

;; Events

(rf/reg-event-db ::select-item
  (fn [db [_ name]]
    (assoc db :selected-item name)))