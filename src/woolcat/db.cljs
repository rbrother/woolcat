(ns woolcat.db
  (:require [re-frame.core :as rf]))

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
  ["Felt" "Knit" "Crochet" "Weave" "Sew" "Origami"])

(def products
  [{:name "Planet chain-bag", :id "bag-earth-moon", :dimension "Fiber", :technique "Felt", :detail-pics 4}
   {:name "Pink chain-bag", :id "bag-pink-purple", :dimension "Fiber", :technique "Felt", :detail-pics 2}
   {:name "Qing", :id "qing-hat", :dimension "Yarn", :technique "Crochet", :detail-pics 2}
   {:name "Song", :id "song-hat", :dimension "Yarn", :technique "Crochet", :detail-pics 1}
   {:name "Rockstar", :id "rockstar-hat", :dimension "Yarn", :technique "Crochet", :detail-pics 0}
   {:name "ZhuGe L", :id "zhuge-hat", :dimension "Yarn", :technique "Crochet", :detail-pics 0}
   {:name "Female", :id "pink-glove", :dimension "Yarn", :technique "Crochet", :detail-pics 0}
   {:name "Male", :id "gray-glove", :dimension "Yarn", :technique "Crochet", :detail-pics 0}
   {:name "Surgeon", :id "surgeon-glove", :dimension "Yarn", :technique "Knit", :detail-pics 0}
   {:name "Baby shoe", :id "baby-shoe", :dimension "Yarn", :technique "Knit", :detail-pics 0}
   {:name "Corona virus", :id "corona-virus", :dimension "Yarn", :technique "Crochet", :detail-pics 0}
   {:name "Rose", :id "rose", :dimension "Yarn", :technique "Crochet", :detail-pics 0}
   {:name "Panda bamboo tapestry", :id "panda-bamboo-tapestry", :dimension "Yarn", :technique "Weave", :detail-pics 0}
   ])

(rf/reg-event-db ::initialize-db
  (fn [_] {}))