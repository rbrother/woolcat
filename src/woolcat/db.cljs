(ns woolcat.db
  (:require [medley.core :refer [index-by]]
            [re-frame.core :as rf]))

;; All pics in dropbox
;; https://www.dropbox.com/scl/fo/k92lhu64t31v0yhg99kli/ALTQYZFlBq8hA8jbh5mciXM?rlkey=u9p2d55n1hm3rvxemtzkx36cc&e=1&dl=0

(def img-base "https://rjb-share.s3.eu-north-1.amazonaws.com/woolcat-media/")

(defn amend-product [{:keys [id detail-pics folder] :as info}]
  (assoc info :photo (if folder (str img-base "products/" id "/main.jpg")
                                (str img-base "products/" id ".jpg"))
              :detail-photos (->> (range 1 (inc detail-pics))
                                  (map (fn [index] (str img-base "products/" id "/detail" index ".jpg"))))))

(def tags
  [{:name "Fiber", :photo (str img-base "fiber.jpg")}
   {:name "Yarn", :photo (str img-base "yarn.jpg")}
   {:name "Fabric", :photo (str img-base "textile.jpg")}
   {:name "Leather & Fur", :photo (str img-base "skin.jpg")}
   {:name "Paper", :photo (str img-base "paper.jpg")}
   {:name "Felt", :photo (str img-base "Felt.jpg")}
   {:name "Knit & Crochet", :photo (str img-base "Knit-Crochet.jpg")}
   {:name "Weave & PomPom", :photo (str img-base "Weave-PomPom.jpg")}
   {:name "Haute Couture", :photo (str img-base "Sew.jpg")}
   {:name "Origami", :photo (str img-base "Origami.jpg")}
   {:name "Hats", :photo (str img-base "Hats.jpg")}
   {:name "Garment", :photo (str img-base "Clothes.jpg")}
   {:name "Accessories", :photo (str img-base "Gloves-Shoes.jpg")}
   {:name "Bags", :photo (str img-base "Bags.jpg")}
   {:name "Room Decor", :photo (str img-base "Home-Decor.jpg")}
   ])

(def products-raw
  [{:name "Planet Bag", :id "bag-earth-moon", :tags #{"Fiber" "Felt" "Bags"}, :detail-pics 6}
   {:name "Planet Bag 2", :id "bag-planet-2", :tags #{"Fiber" "Felt" "Bags"}, :detail-pics 3}
   {:name "Pink Bag", :id "bag-pink-purple", :tags #{"Fiber" "Felt" "Bags"}, :detail-pics 3}
   {:name "Milky Way Purse", :id "milkyway-purse", :tags #{"Fiber" "Felt" "Bags"}, :detail-pics 2}
   {:name "Neptune Purse", :id "neptune-purse", :tags #{"Fiber" "Felt" "Bags"}, :detail-pics 2}
   {:name "Big White", :id "big-white", :tags #{"Fiber" "Felt" "Room Decor"}, :detail-pics 3}
   {:name "Sofa", :id "sofa", :tags #{"Fiber" "Felt" "Room Decor"}, :detail-pics 3}
   {:name "Qing", :id "qing-hat", :tags #{"Yarn" "Knit & Crochet" "Hats"}, :detail-pics 4}
   {:name "Song", :id "song-hat", :tags #{"Yarn" "Knit & Crochet" "Hats"}, :detail-pics 2}
   {:name "Rockstar", :id "rockstar-hat", :tags #{"Yarn" "Knit & Crochet" "Hats"}, :detail-pics 0}
   {:name "ZhuGe L", :id "zhuge-hat", :tags #{"Yarn" "Knit & Crochet" "Hats"} :detail-pics 2, :folder true}
   {:name "Female Glove", :id "pink-glove", :tags #{"Yarn" "Knit & Crochet" "Accessories"}, :detail-pics 0}
   {:name "Male Glove", :id "gray-glove", :tags #{"Yarn" "Knit & Crochet" "Accessories"} :detail-pics 0}
   {:name "Surgeon Glove", :id "surgeon-glove", :tags #{"Yarn" "Knit & Crochet" "Accessories"}, :detail-pics 2, :folder true}
   {:name "Fingerless Glove", :id "fingerless-glove", :tags #{"Yarn" "Fur" "Knit & Crochet" "Accessories"}, :detail-pics 2, :folder true}
   {:name "Baby Shoe", :id "baby-shoe", :tags #{"Yarn" "Knit & Crochet" "Accessories"}, :detail-pics 4, :folder true}
   {:name "Sandals", :id "sandals", :tags #{"Yarn" "Knit & Crochet" "Accessories"}, :detail-pics 1, :folder true}
   {:name "Corona Virus", :id "corona-virus", :tags #{"Yarn" "Knit & Crochet" "Accessories"} :detail-pics 0}
   {:name "Rose", :id "rose", :tags #{"Yarn" "Knit & Crochet" "Room Decor"}, :detail-pics 2, :folder true}
   {:name "Orchid", :id "orchid", :tags #{"Yarn" "Knit & Crochet" "Room Decor"}, :detail-pics 1}
   {:name "Lily of Valley", :id "lily-of-valley", :tags #{"Yarn" "Knit & Crochet" "Room Decor"}, :detail-pics 1}
   {:name "Balloon Dog", :id "balloon-dog", :tags #{"Yarn" "Knit & Crochet" "Room Decor"}, :detail-pics 2, :folder true}
   {:name "Dinosaur", :id "dinosaur", :tags #{"Yarn" "Knit & Crochet" "Room Decor"}, :detail-pics 4, :folder true}
   {:name "Penguin", :id "penguin", :tags #{"Yarn" "Knit & Crochet" "Room Decor"}, :detail-pics 4, :folder true}
   {:name "Sheep", :id "sheep", :tags #{"Yarn" "Knit & Crochet" "Room Decor"}, :detail-pics 15, :folder true}
   {:name "Small Dog", :id "small-dog", :tags #{"Yarn" "Knit & Crochet" "Room Decor"}, :detail-pics 4, :folder true}

   {:name "Baby Blanket", :id "baby-blanket", :tags #{"Yarn" "Knit & Crochet" "Accessories"}, :detail-pics 1, :folder true}
   {:name "Knee Warmer", :id "knee-warmer", :tags #{"Yarn" "Knit & Crochet" "Accessories"}, :detail-pics 1, :folder true}
   {:name "Sweater", :id "sweater", :tags #{"Yarn" "Knit & Crochet" "Haute Couture"}, :detail-pics 1, :folder true}

   {:name "Panda Bamboo Tapestry", :id "panda-bamboo-tapestry", :tags #{"Yarn" "Weave & PomPom" "Room Decor"}, :detail-pics 2}
   {:name "Monkey Tapestry", :id "monkey-tapestry", :tags #{"Yarn" "Weave & PomPom" "Room Decor"}, :detail-pics 4}
   {:name "Autumn Dress" :id "autumn-dress", :tags #{"Fabric" "Haute Couture" "Garment"}, :detail-pics 2}
   {:name "Bear Coat" :id "bear-coat", :tags #{"Fabric" "Haute Couture" "Garment"}, :detail-pics 3}
   {:name "Cheonsam" :id "cheonsam", :tags #{"Fabric" "Haute Couture" "Garment"}, :detail-pics 2}
   {:name "Green Coat" :id "green-coat", :tags #{"Fabric" "Haute Couture" "Garment"}, :detail-pics 2}
   {:name "Joy Dress" :id "joy-dress", :tags #{"Fabric" "Haute Couture" "Garment"}, :detail-pics 4}
   {:name "Princess Dress" :id "princess-dress", :tags #{"Fabric" "Haute Couture" "Garment"}, :detail-pics 4}
   {:name "Princess Line Dress" :id "princess-line", :tags #{"Fabric" "Haute Couture" "Garment"}, :detail-pics 0}
   {:name "Season Dress" :id "season-dress", :tags #{"Fabric" "Haute Couture" "Garment"}, :detail-pics 0}
   {:name "Spring Coat" :id "spring-coat", :tags #{"Fabric" "Haute Couture" "Garment"}, :detail-pics 1}
   {:name "Trench Coat" :id "trench-coat", :tags #{"Fabric" "Haute Couture" "Garment"}, :detail-pics 3}
   {:name "Long Winter Coat" :id "winter-ao", :tags #{"Fabric" "Haute Couture" "Garment"}, :detail-pics 4}
   {:name "Winter Coat" :id "winter-coat", :tags #{"Fabric" "Haute Couture" "Garment"}, :detail-pics 1}
   {:name "Han Black Coat" :id "han-black-coat", :tags #{"Fabric" "Haute Couture" "Garment"}, :detail-pics 4}
   {:name "Emperors Yellow Jacket" :id "emperors-yellow-jacket", :tags #{"Fabric" "Haute Couture" "Garment"}, :detail-pics 4}
   {:name "Kimono" :id "kimono", :tags #{"Fabric" "Haute Couture" "Garment"}, :detail-pics 1, :folder true}
   {:name "Baby Body" :id "baby-body", :tags #{"Fabric" "Garment"}, :detail-pics 0, :folder true}
   {:name "Plant Handbag" :id "plant-handbag", :tags #{"Fabric" "Bags"}, :detail-pics 3}
   {:name "Phone Bag" :id "phone-bag", :tags #{"Fabric" "Bags"}, :detail-pics 1, :folder true}
   {:name "Shoulder Bag" :id "shoulder-bag", :tags #{"Fabric" "Bags"}, :detail-pics 3, :folder true}
   {:name "Backpack" :id "backpack", :tags #{"Fabric" "Bags"}, :detail-pics 1, :folder true}
   {:name "Cosmetic Bag 1" :id "cosmetic-bag-1", :tags #{"Fabric" "Bags"}, :detail-pics 3, :folder true}
   {:name "Cosmetic Bag 2" :id "cosmetic-bag-2", :tags #{"Fabric" "Bags"}, :detail-pics 3, :folder true}
   {:name "Fox Hat 1" :id "fox-hat-1", :tags #{"Leather & Fur" "Hats"}, :detail-pics 7}
   {:name "Gray Mink Glove" :id "gray-mink-glove", :tags #{"Leather & Fur" "Accessories"}, :detail-pics 6}
   {:name "White Mink Glove" :id "white-mink-glove", :tags #{"Leather & Fur" "Accessories"}, :detail-pics 4}
   {:name "Orange Mitten" :id "orange-mitten", :tags #{"Leather & Fur" "Accessories"}, :detail-pics 1}
   {:name "Fox Bag Charm" :id "fox-key-charm", :tags #{"Leather & Fur" "Accessories"}, :detail-pics 5}
   {:name "Hare Bag" :id "hare-bag", :tags #{"Leather & Fur" "Bags"}, :detail-pics 5}
   {:name "Lamp Cover 1" :id "lamp-cover-1", :tags #{"Paper" "Origami" "Room Decor"}, :detail-pics 1}
   {:name "Lamp Cover 2" :id "lamp-cover-2", :tags #{"Paper" "Origami" "Room Decor"}, :detail-pics 1}
   {:name "Organic Lamp Cover" :id "organic-lamp-cover", :tags #{"Paper" "Origami" "Room Decor"}, :detail-pics 1}
   {:name "Asian Lamp Cover" :id "asian-lamp-cover", :tags #{"Paper" "Origami" "Room Decor"}, :detail-pics 1}
   {:name "Bunny and Chicken" :id "bunny-and-chicken", :tags #{"Paper" "Origami" "Room Decor"}, :detail-pics 3}
   {:name "Cactus" :id "cactus", :tags #{"Paper" "Origami" "Room Decor"}, :detail-pics 2}
   {:name "Ruby" :id "ruby", :tags #{"Paper" "Origami" "Room Decor"}, :detail-pics 2}
   {:name "Yellow Diamond" :id "yellow-diamond", :tags #{"Paper" "Origami" "Room Decor"}, :detail-pics 1, :folder true}
   {:name "Night Lamp" :id "night-lamp", :tags #{"Paper" "Origami" "Room Decor"}, :detail-pics 0, :folder true}
   ;; Hardware
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
   ;; Studio
   {:name "Popeye", :id "popeye", :tags #{"Studio"}, :detail-pics 4, :folder true}
   ])

(def products (mapv amend-product products-raw))

(def products-index (index-by :id products))

(rf/reg-event-db ::initialize-db
  (fn [_] {}))