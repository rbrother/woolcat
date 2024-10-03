(ns woolcat.items-list
  (:require [re-frame.core :as rf]
            [woolcat.db :refer [products]]))

(def img-base "https://rjb-share.s3.eu-north-1.amazonaws.com/woolcat-media/products/")

(defn filter-label [{:keys [dimension technique material]}]
  (or dimension technique material))

(defn make-product-filter [{:keys [dimension technique material]}]
  (fn [{prod-dimension :dimension, prod-technique :technique, prod-material :material}]
    (or (and dimension (= prod-dimension dimension))
        (and technique (= prod-technique technique))
        (and material (= prod-material material)))))

(defn filter-products [products filter-info]
  (->> products
       (filter (make-product-filter filter-info))))

(defn view []
  (let [filter @(rf/subscribe [:woolcat.views/filter])]
    (print filter)
    [:div.col-span-2 (filter-label filter)]
    (into
      [:div.product-table.col-span-2]
      (for [{:keys [name id]} (filter-products products filter)]
        (let [photo (str img-base id ".jpg")]
          [:div.link {:on-click #(rf/dispatch [::select-dimension name])}
           [:div.crop-container
            [:img.cropped-image {:src photo}]]
           [:div.margin-top name]])))
    )
  )