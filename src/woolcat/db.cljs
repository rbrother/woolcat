(ns woolcat.db
  (:require [medley.core :refer [index-by]]
            [re-frame.core :as rf]))

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
  [{:name "Planet Chain-bag", :id "bag-earth-moon", :dimension "Fiber", :technique "Felt",
    :material #{"Wool" "Metal"}, :detail-pics 4}
   {:name "Pink Chain-bag", :id "bag-pink-purple", :dimension "Fiber", :technique "Felt",
    :material #{"Wool" "Metal"}, :detail-pics 2}
   {:name "Qing", :id "qing-hat", :dimension "Yarn", :technique "Crochet",
    :material "Wool", :detail-pics 4}
   {:name "Song", :id "song-hat", :dimension "Yarn", :technique "Crochet",
    :material #{"Cotton" "Metal"} :detail-pics 2}
   {:name "Rockstar", :id "rockstar-hat", :dimension "Yarn", :technique "Crochet",
    :material #{"Cotton" "Wool"}, :detail-pics 0}
   {:name "ZhuGe L", :id "zhuge-hat", :dimension "Yarn", :technique "Crochet",
    :material "Wool", :detail-pics 0}
   {:name "Female Glove", :id "pink-glove", :dimension "Yarn", :technique "Crochet",
    :material "Wool", :detail-pics 0}
   {:name "Male Glove", :id "gray-glove", :dimension "Yarn", :technique "Crochet",
    :material "Wool", :detail-pics 0}
   {:name "Surgeon Glove", :id "surgeon-glove", :dimension "Yarn", :technique "Knit",
    :material "Wool", :detail-pics 0}
   {:name "Baby Shoe", :id "baby-shoe", :dimension "Yarn", :technique "Knit",
    :material "Wool", :detail-pics 0}
   {:name "Corona Virus", :id "corona-virus", :dimension "Yarn", :technique "Crochet",
    :material "Wool", :detail-pics 0}
   {:name "Rose", :id "rose", :dimension "Yarn", :technique "Crochet",
    :material #{"Cotton" "Wool" "Metal"}, :detail-pics 0}
   {:name "Orchid", :id "orchid", :dimension "Yarn", :technique "Crochet",
    :material #{"Cotton" "Wool" "Metal"}, :detail-pics 1}
   {:name "Lily of Valley", :id "lily-of-valley", :dimension "Yarn", :technique "Crochet",
    :material #{"Cotton" "Wool" "Metal"}, :detail-pics 1}
   {:name "Panda Bamboo Tapestry", :id "panda-bamboo-tapestry", :dimension #{"Yarn" "Fiber"}, :technique "Weave",
    :material #{"Wool" "Cotton" "Wood"}, :detail-pics 0}
   {:name "Autumn Dress" :id "autumn-dress", :dimension "Textile", :technique "Sew", :detail-pics 2}
   {:name "Bear Coat" :id "bear-coat", :dimension "Textile", :technique "Sew", :detail-pics 3}
   {:name "Cheonsam" :id "cheonsam", :dimension "Textile", :technique "Sew", :detail-pics 2}
   {:name "Green Coat" :id "green-coat", :dimension "Textile", :technique "Sew", :detail-pics 2}
   {:name "Joy Dress" :id "joy-dress", :dimension "Textile", :technique "Sew", :detail-pics 4}
   {:name "Pink Handbag" :id "pink-handbag", :dimension "Textile", :technique "Sew", :detail-pics 2}
   {:name "Plant Handbag" :id "plant-handbag", :dimension "Textile", :technique "Sew", :detail-pics 3}
   {:name "Princess Dress" :id "princess-dress", :dimension "Textile", :technique "Sew", :detail-pics 4}
   {:name "Princess Line Dress" :id "princess-line", :dimension "Textile", :technique "Sew", :detail-pics 0}
   {:name "Season Dress" :id "season-dress", :dimension "Textile", :technique "Sew", :detail-pics 0}
   {:name "Spring Coat" :id "spring-coat", :dimension "Textile", :technique "Sew", :detail-pics 1}
   {:name "Trench Coat" :id "trench-coat", :dimension "Textile", :technique "Sew", :detail-pics 3}
   {:name "Long Winter Coat" :id "winter-ao", :dimension "Textile", :technique "Sew", :detail-pics 4}
   {:name "Winter Coat" :id "winter-coat", :dimension "Textile", :technique "Sew", :detail-pics 1}
   {:name "Fox Hat 1" :id "fox-hat-1", :dimension "Skin", :technique "Sew", :detail-pics 7}
   {:name "Gray Mink Glove" :id "gray-mink-glove", :dimension "Skin", :technique "Sew", :detail-pics 4}
   {:name "White Mink Glove" :id "white-mink-glove", :dimension "Skin", :technique "Sew", :detail-pics 4}
   {:name "Fox Bag Charm" :id "fox-key-charm", :dimension "Skin", :technique "Sew", :detail-pics 3}
   {:name "Hare Bag" :id "hare-bag", :dimension "Skin", :technique "Sew", :detail-pics 5}
   {:name "Lamp Cover 1" :id "lamp-cover-1", :dimension "Paper", :technique "Origami", :detail-pics 1}
   {:name "Lamp Cover 2" :id "lamp-cover-2", :dimension "Paper", :technique "Origami", :detail-pics 1}
   {:name "Organic Lamp Cover" :id "organic-lamp-cover", :dimension "Paper", :technique "Origami", :detail-pics 1}
   {:name "Asian Lamp Cover" :id "asian-lamp-cover", :dimension "Paper", :technique "Origami", :detail-pics 1}
   {:name "Bunny and Chicken" :id "bunny-and-chicken", :dimension "Paper", :technique "Origami", :detail-pics 2}
   {:name "Cactus" :id "cactus", :dimension "Paper", :technique "Origami", :detail-pics 2}
   {:name "Ruby" :id "ruby", :dimension "Paper", :technique "Origami", :detail-pics 2}
   ])

(def products (mapv amend-product products-raw))

(def products-index (index-by :id products))

(rf/reg-event-db ::initialize-db
  (fn [_] {}))