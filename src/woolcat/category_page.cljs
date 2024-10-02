(ns woolcat.category-page
  (:require
    [re-frame.core :as rf]
    [woolcat.db :refer [categories]]))

(defn view [category]
  [:div.col-span-2 category]
  )