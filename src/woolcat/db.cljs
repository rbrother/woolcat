(ns woolcat.db)

(def default-db
  {})

;; All pics in dropbox
;; https://www.dropbox.com/scl/fo/k92lhu64t31v0yhg99kli/ALTQYZFlBq8hA8jbh5mciXM?rlkey=u9p2d55n1hm3rvxemtzkx36cc&e=1&dl=0

(def img-base "https://rjb-share.s3.eu-north-1.amazonaws.com/woolcat-media/")

(def categories
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

(def product-data
  [{:name "Red and purple purse"
    :category "Feltwool"
    :photo ""}
   {:name "Earth and white purse"
    :category "Feltwool"
    :photo ""}
   {:name "Blue-white and white purse"
    :category "Feltwool"
    :photo ""}
   {:name "Cyan planet purse 1"
    :category "Feltwool"
    :photo ""}
   {:name "Cyan planet purse 2"
    :category "Feltwool"
    :photo ""}
   {:name "Cyan planet purse 3"
    :category "Feltwool"
    :photo ""}
   {:name "Blue-white planet purse"
    :category "Feltwool"
    :photo ""}
   {:name "Chicken leg"
    :category "Crochet"
    :photo ""}
   ])