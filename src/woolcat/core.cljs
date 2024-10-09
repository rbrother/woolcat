(ns woolcat.core
  (:require
   [reagent.dom :as rdom]
   [re-frame.core :as re-frame]
   [re-frame.db]
   [woolcat.db :as db]
   [woolcat.views :as views]
   [woolcat.config :as config]
   [reagent-dev-tools.core :as dev-tools]
   [secretary.core :as secretary :refer [defroute]]
   [accountant.core :as accountant]
   [woolcat.styles] ;; Needed for global styles
   ))

(defn dev-setup []
  (when config/debug?
    (do
      (println "dev mode")
      (dev-tools/start! {:state-atom re-frame.db/app-db}))))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main-panel] root-el)))

(defn init []
  (re-frame/dispatch-sync [::db/initialize-db])
  (dev-setup)
  (mount-root))
