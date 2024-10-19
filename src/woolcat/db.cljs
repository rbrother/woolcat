(ns woolcat.db
  (:require [re-frame.core :as rf]
            [clojure.pprint :refer (pprint)]
            [medley.core :refer [index-by]]))

(def default-db
  {})

;; All pics in dropbox
;; https://www.dropbox.com/scl/fo/k92lhu64t31v0yhg99kli/ALTQYZFlBq8hA8jbh5mciXM?rlkey=u9p2d55n1hm3rvxemtzkx36cc&e=1&dl=0

(def img-base "https://rjb-share.s3.eu-north-1.amazonaws.com/woolcat-media/")

(defn amend-product [{:keys [id detail-pics] :as info}]
  (assoc info :photo (str img-base "products/" id ".jpg")
              :detail-photos (->> (range 1 (inc detail-pics))
                                  (map (fn [index] (str img-base "products/" id "/detail" index ".jpg"))))))

(def dimensions
  [{:name "Fiber"
    :photo (str img-base "fiber.jpg")}
   {:name "Yarn"
    :photo (str img-base "yarn.jpg")}
   {:name "Textile"
    :photo (str img-base "textile.jpg")}
   {:name "Skin"
    :photo (str img-base "skin.jpg")}
   {:name "Paper"
    :photo (str img-base "paper.jpg")}])

(def techniques
  ["Felt" "Knit" "Crochet" "Weave" "Sew" "Origami"])

(def materials
  ["Cotton" "Wool" "Leather" "Paper" "Metal" "Wood"])

(def products-raw
  [{:name "Planet chain-bag", :id "bag-earth-moon", :dimension "Fiber", :technique "Felt",
    :material #{"Wool" "Metal"}, :detail-pics 4}
   {:name "Pink chain-bag", :id "bag-pink-purple", :dimension "Fiber", :technique "Felt",
    :material #{"Wool" "Metal"}, :detail-pics 2}
   {:name "Qing", :id "qing-hat", :dimension "Yarn", :technique "Crochet",
    :material "Wool", :detail-pics 2}
   {:name "Song", :id "song-hat", :dimension "Yarn", :technique "Crochet",
    :material #{"Cotton" "Metal"} :detail-pics 1}
   {:name "Rockstar", :id "rockstar-hat", :dimension "Yarn", :technique "Crochet",
    :material #{"Cotton" "Wool"}, :detail-pics 0}
   {:name "ZhuGe L", :id "zhuge-hat", :dimension "Yarn", :technique "Crochet",
    :material "Wool", :detail-pics 0}
   {:name "Female", :id "pink-glove", :dimension "Yarn", :technique "Crochet",
    :material "Wool", :detail-pics 0}
   {:name "Male", :id "gray-glove", :dimension "Yarn", :technique "Crochet",
    :material "Wool", :detail-pics 0}
   {:name "Surgeon", :id "surgeon-glove", :dimension "Yarn", :technique "Knit",
    :material "Wool", :detail-pics 0}
   {:name "Baby shoe", :id "baby-shoe", :dimension "Yarn", :technique "Knit",
    :material "Wool", :detail-pics 0}
   {:name "Corona virus", :id "corona-virus", :dimension "Yarn", :technique "Crochet",
    :material "Wool", :detail-pics 0}
   {:name "Rose", :id "rose", :dimension "Yarn", :technique "Crochet",
    :material #{"Cotton" "Wool" "Metal"}, :detail-pics 0}
   {:name "Panda bamboo tapestry", :id "panda-bamboo-tapestry", :dimension #{"Yarn" "Fiber"}, :technique "Weave",
    :material #{"Wool" "Cotton" "Wood"},  :detail-pics 0}
   {:name "Autumn dress" :id "autumn-dress", :dimension "Textile", :technique "Sew", :detail-pics 2}
   {:name "Bear coat" :id "bear-coat", :dimension "Textile", :technique "Sew", :detail-pics 3}
   {:name "Cheonsam" :id "cheonsam", :dimension "Textile", :technique "Sew", :detail-pics 2}
   {:name "Green coat" :id "green-coat", :dimension "Textile", :technique "Sew", :detail-pics 2}
   {:name "Joy dress" :id "joy-dress", :dimension "Textile", :technique "Sew", :detail-pics 4}
   ])

(def products (mapv amend-product products-raw))

(def products-index (index-by :id products))

(rf/reg-event-db ::initialize-db
  (fn [_] {}))