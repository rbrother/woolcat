(ns woolcat.item-page
  (:require [re-frame.core :as rf]
            [woolcat.db :as db]))

(defn view []
  (let [item-id @(rf/subscribe [::selected-item])
        {:keys [name tags photo description detail-photos folder] :as _item} (get db/products-index item-id)
        all-photos (if (= folder :only-details)
                     detail-photos
                     (concat [photo] detail-photos))]
    (into [:<>
           [:div.col-span-2
            (into [:div] (interpose ", " (for [tag tags] [:a {:href (str "/items/" tag)} tag])))
            [:p.main-title name]
            [:div.light-font description]
            ]]
          (for [photo all-photos]
            [:div.col-span-2.justify-center
             [:img.large-photo {:src photo}]]))))

;; Subs

(rf/reg-sub ::selected-item (fn [db _] (:selected-item db)))

;; Events

(rf/reg-event-db ::select-item
  (fn [db [_ name]]
    (assoc db :selected-item name)))