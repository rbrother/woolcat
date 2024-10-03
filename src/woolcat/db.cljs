(ns woolcat.db
  (:require [re-frame.core :as rf]
            [re-frame.core :as rf]))

(def default-db
  {})

;; All pics in dropbox
;; https://www.dropbox.com/scl/fo/k92lhu64t31v0yhg99kli/ALTQYZFlBq8hA8jbh5mciXM?rlkey=u9p2d55n1hm3rvxemtzkx36cc&e=1&dl=0

(def img-base "https://rjb-share.s3.eu-north-1.amazonaws.com/woolcat-media/")

(def dimensions
  [{:name "Fiber"
    :photo (str img-base "fiber.jpg")}
   {:name "Yarn"
    :photo (str img-base "yarn.jpg")}
   {:name "Fabric"
    :photo (str img-base "fabric.jpg")}
   {:name "Skin"
    :photo (str img-base "skin.jpg")}
   {:name "Paper"
    :photo (str img-base "paper.jpg")}])

(def techniques
  ["Felt" "Knit" "Weave" "Sew" "Fold"])

(def products
  [{:name "Earth and Moon bags"
    :id "bag-earth-moon"
    :dimension "Fiber"
    :technique "Felt"
    :detail-pics 4}
   {:name "Pink and purple bags"
    :id "bag-pink-purple"
    :dimension "Fiber"
    :technique "Felt"
    :detail-pics 2}
   ])

(rf/reg-event-db ::initialize-db
  (fn [_] {}))