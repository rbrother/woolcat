(ns woolcat.core
  (:require
    [accountant.core :as accountant]
    [re-frame.core :as rf]
    [re-frame.db]
    [reagent-dev-tools.core :as dev-tools]
    [medley.core :refer [find-first]]
    [reagent.dom :as rdom]
    [woolcat.config :as config]
    [woolcat.db :as db]
    [woolcat.styles]
    [woolcat.views :as views] ;; Needed for global styles
    ))

(defn dev-setup []
  (when config/debug?
    (do
      (println "dev mode")
      (dev-tools/start! {:state-atom re-frame.db/app-db}))))

(defn ^:dev/after-load mount-root []
  (rf/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main-panel] root-el)))

(defn route-to? [path reg-ex dispatch-base]
  (let [matches (re-matches reg-ex path)]
    (when matches
      (print path " matches " reg-ex)
      (rf/dispatch (vec (concat dispatch-base (rest matches))))
      true)))

(def routes
  [{:regex #"/item/([\w\-]+)", :dispatch [:woolcat.item-page/select-item]}
   {:regex #"/dimension/([\w\-]+)", :dispatch [:woolcat.filters/select-dimension]}
   {:regex #"/technique/([\w\-]+)", :dispatch [:woolcat.filters/select-technique]}
   {:regex #".*", :dispatch [::views/home]} ;; Default route, match anything else
   ])

(defn dispatch-route! [{:keys [matches dispatch]}]
  (rf/dispatch (vec (concat dispatch (rest matches)))))

(defn setup-routes []
  (accountant/configure-navigation!
    {:nav-handler (fn [path]
                    (print [:nav-handler path])
                    (->> routes
                         (map (fn [{:keys [regex] :as route}]
                                (assoc route :matches (re-matches regex path))))
                         (find-first :matches)
                         dispatch-route!))
     :path-exists? (fn [path]
                     (print [:path-exists? path])
                     true)})
  (accountant/dispatch-current!))

(defn init []
  (setup-routes)
  (rf/dispatch-sync [::db/initialize-db])
  (dev-setup)
  (mount-root))
