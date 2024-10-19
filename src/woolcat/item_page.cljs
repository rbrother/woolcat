(ns woolcat.item-page
  (:require [clojure.string :as str]
            [re-frame.core :as rf]
            [woolcat.db :as db]))

(defn view []
  (let [item-id @(rf/subscribe [::selected-item])
        {:keys [name dimension technique material photo detail-photos] :as item} (get db/products-index item-id)
        all-photos (concat [photo] detail-photos)]
    (into [:<>
           [:div.col-span-2
            [:p.main-title name]
            (when material [:div "Material: " (str/join ", " material)])]]
          (for [photo all-photos]
            [:div.col-span-2.justify-center
             [:img.large-photo {:src photo}]]))))

;; Subs

(rf/reg-sub ::selected-item (fn [db _] (:selected-item db)))

;; Events

(rf/reg-event-db ::select-item
  (fn [db [_ name]]
    (assoc db :selected-item name)))