(ns woolcat.db
  (:require [medley.core :refer [index-by]]
            [re-frame.core :as rf]))

;; All pics in dropbox
;; https://www.dropbox.com/scl/fo/k92lhu64t31v0yhg99kli/ALTQYZFlBq8hA8jbh5mciXM?rlkey=u9p2d55n1hm3rvxemtzkx36cc&e=1&dl=0

(def img-base "https://rjb-share.s3.eu-north-1.amazonaws.com/woolcat-media/")

(defn amend-product [{:keys [id detail-pics] :as info}]
  (assoc info :photo (str img-base "products/" id ".jpg")
              :detail-photos (->> (range 1 (inc detail-pics))
                                  (map (fn [index] (str img-base "products/" id "/detail" index ".jpg"))))))

(def tags
  [{:name "Fiber", :photo (str img-base "fiber.jpg")}
   {:name "Yarn", :photo (str img-base "yarn.jpg")}
   {:name "Fabric", :photo (str img-base "textile.jpg")}
   {:name "Skin", :photo (str img-base "skin.jpg")}
   {:name "Paper", :photo (str img-base "paper.jpg")}
   {:name "Felt", :photo (str img-base "Felt.jpg")}
   {:name "Knit & Crochet", :photo (str img-base "Knit-Crochet.jpg")}
   {:name "Weave & PomPom", :photo (str img-base "Weave-PomPom.jpg")}
   {:name "Sew", :photo (str img-base "Sew.jpg")}
   {:name "Origami", :photo (str img-base "Origami.jpg")}
   {:name "Hats", :photo (str img-base "Hats.jpg")}
   {:name "Clothes", :photo (str img-base "Clothes.jpg")}
   {:name "Accessories", :photo (str img-base "Gloves-Shoes.jpg")}
   {:name "Bags", :photo (str img-base "Bags.jpg")}
   {:name "Home Decor", :photo (str img-base "Home-Decor.jpg")}
   ])

(def products-raw
  [{:name "Planet Bag", :id "bag-earth-moon", :tags #{"Fiber" "Felt" "Bags"}, :detail-pics 6 }
   {:name "Planet Bag 2", :id "bag-planet-2", :tags #{"Fiber" "Felt" "Bags"}, :detail-pics 3}
   {:name "Pink Bag", :id "bag-pink-purple", :tags #{"Fiber" "Felt" "Bags"}, :detail-pics 3}
   {:name "Milky Way Purse", :id "milkyway-purse", :tags #{"Fiber" "Felt" "Bags"}, :detail-pics 2}
   {:name "Neptune Purse", :id "neptune-purse", :tags #{"Fiber" "Felt" "Bags"}, :detail-pics 2}
   {:name "Big White", :id "big-white", :tags #{"Fiber" "Felt" "Home Decor"}, :detail-pics 3}
   {:name "Sofa", :id "sofa", :tags #{"Fiber" "Felt" "Home Decor"}, :detail-pics 3}
   {:name "Qing", :id "qing-hat", :tags #{"Yarn" "Knit & Crochet" "Hats"}, :detail-pics 4}
   {:name "Song", :id "song-hat", :tags #{"Yarn" "Knit & Crochet" "Hats"}, :detail-pics 2}
   {:name "Rockstar", :id "rockstar-hat", :tags #{"Yarn" "Knit & Crochet" "Hats"}, :detail-pics 0}
   {:name "ZhuGe L", :id "zhuge-hat", :tags #{"Yarn" "Knit & Crochet" "Hats"} :detail-pics 0}
   {:name "Female Glove", :id "pink-glove", :tags #{"Yarn" "Knit & Crochet" "Accessories"}, :detail-pics 0}
   {:name "Male Glove", :id "gray-glove", :tags #{"Yarn" "Knit & Crochet" "Accessories"} :detail-pics 0}
   {:name "Surgeon Glove", :id "surgeon-glove", :tags #{"Yarn" "Knit & Crochet" "Accessories"}, :detail-pics 0}
   {:name "Baby Shoe", :id "baby-shoe", :tags #{"Yarn" "Knit & Crochet" "Accessories"}, :detail-pics 0}
   {:name "Corona Virus", :id "corona-virus", :tags #{"Yarn" "Knit & Crochet" "Accessories"} :detail-pics 0}
   {:name "Rose", :id "rose", :tags #{"Yarn" "Knit & Crochet" "Home Decor"}, :detail-pics 0}
   {:name "Orchid", :id "orchid", :tags #{"Yarn" "Knit & Crochet" "Home Decor"}, :detail-pics 1}
   {:name "Lily of Valley", :id "lily-of-valley", :tags #{"Yarn" "Knit & Crochet" "Home Decor"}, :detail-pics 1}
   {:name "Panda Bamboo Tapestry", :id "panda-bamboo-tapestry", :tags #{"Yarn" "Fiber" "Weave & PomPom" "Home Decor"}, :detail-pics 2}
   {:name "Monkey Tapestry", :id "monkey-tapestry", :tags #{"Yarn" "Fiber" "Weave & PomPom" "Home Decor"}, :detail-pics 4}
   {:name "Autumn Dress" :id "autumn-dress", :tags #{"Fabric" "Sew" "Clothes"}, :detail-pics 2}
   {:name "Bear Coat" :id "bear-coat", :tags #{"Fabric" "Sew" "Clothes"}, :detail-pics 3}
   {:name "Cheonsam" :id "cheonsam", :tags #{"Fabric" "Sew" "Clothes"}, :detail-pics 2}
   {:name "Green Coat" :id "green-coat", :tags #{"Fabric" "Sew" "Clothes"}, :detail-pics 2}
   {:name "Joy Dress" :id "joy-dress", :tags #{"Fabric" "Sew" "Clothes"}, :detail-pics 4}
   {:name "Princess Dress" :id "princess-dress", :tags #{"Fabric" "Sew" "Clothes"}, :detail-pics 4}
   {:name "Princess Line Dress" :id "princess-line", :tags #{"Fabric" "Sew" "Clothes"}, :detail-pics 0}
   {:name "Season Dress" :id "season-dress", :tags #{"Fabric" "Sew" "Clothes"}, :detail-pics 0}
   {:name "Spring Coat" :id "spring-coat", :tags #{"Fabric" "Sew" "Clothes"}, :detail-pics 1}
   {:name "Trench Coat" :id "trench-coat", :tags #{"Fabric" "Sew" "Clothes"}, :detail-pics 3}
   {:name "Long Winter Coat" :id "winter-ao", :tags #{"Fabric" "Sew" "Clothes"}, :detail-pics 4}
   {:name "Winter Coat" :id "winter-coat", :tags #{"Fabric" "Sew" "Clothes"}, :detail-pics 1}
   {:name "Han Black Coat" :id "han-black-coat", :tags #{"Fabric" "Sew" "Clothes"}, :detail-pics 4}
   {:name "Emperors Yellow Jacket" :id "emperors-yellow-jacket", :tags #{"Fabric" "Sew" "Clothes"}, :detail-pics 4}
   {:name "Pink Handbag" :id "pink-handbag", :tags #{"Fabric" "Sew" "Bags"}, :detail-pics 2}
   {:name "Plant Handbag" :id "plant-handbag", :tags #{"Fabric" "Sew" "Bags"}, :detail-pics 3}
   {:name "Fox Hat 1" :id "fox-hat-1", :tags #{"Skin" "Sew" "Hats"}, :detail-pics 7}
   {:name "Gray Mink Glove" :id "gray-mink-glove", :tags #{"Skin" "Sew" "Accessories"}, :detail-pics 4}
   {:name "White Mink Glove" :id "white-mink-glove", :tags #{"Skin" "Sew" "Accessories"}, :detail-pics 4}
   {:name "Fox Bag Charm" :id "fox-key-charm", :tags #{"Skin" "Sew" "Accessories"}, :detail-pics 3}
   {:name "Hare Bag" :id "hare-bag", :tags #{"Skin" "Sew" "Bags"}, :detail-pics 5}
   {:name "Lamp Cover 1" :id "lamp-cover-1", :tags #{"Paper" "Origami" "Home Decor"}, :detail-pics 1}
   {:name "Lamp Cover 2" :id "lamp-cover-2", :tags #{"Paper" "Origami" "Home Decor"}, :detail-pics 1}
   {:name "Organic Lamp Cover" :id "organic-lamp-cover", :tags #{"Paper" "Origami" "Home Decor"}, :detail-pics 1}
   {:name "Asian Lamp Cover" :id "asian-lamp-cover", :tags #{"Paper" "Origami" "Home Decor"}, :detail-pics 1}
   {:name "Bunny and Chicken" :id "bunny-and-chicken", :tags #{"Paper" "Origami" "Home Decor"}, :detail-pics 3}
   {:name "Cactus" :id "cactus", :tags #{"Paper" "Origami" "Home Decor"}, :detail-pics 2}
   {:name "Ruby" :id "ruby", :tags #{"Paper" "Origami" "Home Decor"}, :detail-pics 2}

   {:name "Egg holder" :id "egg-holder", :tags #{"Hardware"}, :detail-pics 1}
   {:name "Mushroom 1" :id "mushroom1", :tags #{"Hardware"}, :detail-pics 6}
   {:name "Mushroom 2" :id "mushroom2", :tags #{"Hardware"}, :detail-pics 2}
   {:name "Mushroom 3" :id "mushroom3", :tags #{"Hardware"}, :detail-pics 3}
   {:name "Pool" :id "pool", :tags #{"Hardware"}, :detail-pics 1}
   {:name "Zen Pumpkin" :id "pumpkins", :tags #{"Hardware"}, :detail-pics 5}
   {:name "Big Flower Pot" :id "big-flower-pot", :tags #{"Hardware"}, :detail-pics 1}
   {:name "Eco Pot 0" :id "eco-pot-0", :tags #{"Hardware"}, :detail-pics 2}
   {:name "Flower Pot" :id "flower-pot", :tags #{"Hardware"}, :detail-pics 2}
   {:name "Jewellery Box" :id "jewellery-box", :tags #{"Hardware"}, :detail-pics 5}
   {:name "Oasis Platter" :id "oasis-platter", :tags #{"Hardware"}, :detail-pics 1}
   {:name "Tray" :id "tray", :tags #{"Hardware"}, :detail-pics 1}

   {:name "Popeye", :id "popeye", :tags #{"Studio"}, :detail-pics 4}
   ])

(def products (mapv amend-product products-raw))

(def products-index (index-by :id products))

(rf/reg-event-db ::initialize-db
  (fn [_] {}))