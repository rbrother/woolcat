(ns woolcat.filters
  (:require
    [re-frame.core :as rf]
    [woolcat.db :refer [tags]]))

(def wiggly-arrow [:span.large {:style {:position "relative" :top "2px"}} "⇝ "])

;; Helpers

(defn filter-products [products tag]
  (->> products
       (filter (fn [{tags :tags}] (get tags tag)))))

;; Views

;; TODO: Allow number of items per row to decrease on narrow screens from 5
;; But limit their number to 5 even on very wide screens
(defn filters-with-pics []
  (into
    [:div.product-table.col-span-2]
    (for [{:keys [name photo photo2 name2]} tags]
      [:div [:a {:href (str "/items/" name)}
             [:div.crop-container
              (if photo2
                ;; If photo2 exists, create a container with both images
                [:div.hover-image-container
                 [:img.cropped-image.hover-image-main {:src photo}]
                 [:img.cropped-image.hover-image-secondary {:src photo2}]
                 ;; Add text overlay if name2 exists
                 (when name2
                   [:div.hover-text-overlay name2])]
                ;; If no photo2, just show the main image
                [:img.cropped-image {:src photo}])]
             [:div.margin-top wiggly-arrow name]]])))

(defn filters-without-pics []
  (into
    [:div.links-table.col-span-2]
    (for [{:keys [name]} tags]
      [:div [:a {:href (str "/items/" name)}
             [:div wiggly-arrow name]]])))
;; Subs

(rf/reg-sub ::filter (fn [db] (:filter db)))

;; Events

(rf/reg-event-db ::select-items
  (fn [db [_ filter-name]]
    (-> db
        (assoc :filter filter-name)
        (dissoc :selected-item :page))))
