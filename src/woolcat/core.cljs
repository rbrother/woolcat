(ns woolcat.core
  (:require
   [reagent.dom :as rdom]
   [re-frame.core :as re-frame]
   [woolcat.db :as db]
   [woolcat.views :as views]
   [woolcat.config :as config]
   [secretary.core :as secretary :refer [defroute]]
   [accountant.core :as accountant]
   [woolcat.styles] ;; Needed for global styles
   ))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main-panel] root-el)))

(defn init []
  (re-frame/dispatch-sync [::db/initialize-db])
  (dev-setup)
  (mount-root))
