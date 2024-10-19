(ns woolcat.filters
  (:require
    [re-frame.core :as rf]
    [woolcat.db :refer [dimensions techniques]]))

(def wiggly-arrow [:span.large {:style {:position "relative" :top "2px"}} "⇝ "])

;; Helpers

(defn make-product-filter [{:keys [dimension technique material]}]
  (fn [{prod-dimension :dimension, prod-technique :technique, prod-material :material}]
    (or (and dimension (= prod-dimension dimension))
        (and technique (= prod-technique technique))
        (and material (= prod-material material)))))

(defn filter-products [products filter-info]
  (->> products
       (filter (make-product-filter filter-info))))

;; Views

(defn filter-label [{:keys [dimension technique material]}]
  [:div (or dimension technique material)])

(defn dimension-filters-with-pics []
  (into
    [:div.product-table.col-span-2]
    (for [{:keys [name photo]} dimensions]
      [:div.link {:on-click #(rf/dispatch [::select-dimension name])}
       [:div.crop-container
        [:img.cropped-image {:src photo}]]
       [:div.margin-top wiggly-arrow name]])))

(defn dimension-filters []
  (into
    [:div.product-table.col-span-2]
    (for [{:keys [name]} dimensions]
      [:div.link {:on-click #(rf/dispatch [::select-dimension name])}
       [:div.margin-top wiggly-arrow name]])))

(defn technique-filters []
  (into
    [:div.technique-table.col-span-2]
    (for [technique techniques]
      [:div.link {:on-click #(rf/dispatch [::select-technique technique])}
       wiggly-arrow technique])))

(defn material-filters []
  [:div "** material-filters not implemented yet *"])

(defn current-filter []
  (let [{:keys [dimension technique material]} @(rf/subscribe [::filter])]
    (cond
      dimension [dimension-filters]
      technique [technique-filters]
      material [material-filters]
      :else nil)))

;; Subs

(rf/reg-sub ::filter
  (fn [db] (:filter db)))

;; Events

(rf/reg-event-db ::select-dimension
  (fn [db [_ name]]
    (assoc-in db [:filter :dimension] name)))

(rf/reg-event-db ::select-technique
  (fn [db [_ name]]
    (assoc-in db [:filter :technique] name)))