(ns woolcat.db)

(def default-db
  {})

;; All pics in dropbox
;; https://www.dropbox.com/scl/fo/k92lhu64t31v0yhg99kli/ALTQYZFlBq8hA8jbh5mciXM?rlkey=u9p2d55n1hm3rvxemtzkx36cc&e=1&dl=0

(def product-data
  [{:name "Earth and white purse"
    :category-header true
    :photo "https://uc05f30878ecc754f427a0f7a18c.previews.dropboxusercontent.com/p/thumb/ACaNIB_CqD5FpOhDD7kTkLT7F3RB6ylujdAEMJHgjgfodzAeSXzgrSMq38y9QjVYtZyJ3O28oOwIWiw1p60Uuc7SpnubCzUh1bsGV3P8iIMNvGR5Den149blts7rE-KI9hmmUqdAJYcqPNri3NFfS8DRDIxgjJ4qaoZTyLIgowJgMwMfQyO6zhEQ6o2cRSCAeRQXdih8vrX14G9yFyq3aBpSby-IH4RkkvWTFb4VsHx6bM_Ayjx3gnIe0KSAhA99HpRLUqscaouMJZd46JhOVOj6kFCFItgJksQNW6VQU5x4IaUig7-IbOLNPPemmra7hvQR3oN9LZ_1KnhaK6pqLUcnZpK2dWW6f_D1TEKUPfZyKA/p.jpeg"}
   {:name "Blue-white and white purse"
    :category-header true
    :photo "https://ucd11bca4087c6c058fd9934a85d.previews.dropboxusercontent.com/p/thumb/ACbqrREfcpaP2tWH6e5C3EnZu_cd0Ftzk6EUNidBYYBNnvcM8WrNsXPluFLcINnb72UCBIqvYcBEd56-8g7IIcCqAr2GULOXp6UkWhk0FeRB1YYXvX_nPl6vRy55uc-x5Q0z7Cm--o1WDvzu97FuLzsyb5ib5f2gXPedMdJQCY8GAqzW3_BsQayJHjO-MuQpkvSQj_OpIHXWoYzflpZ7gFy9mhdyT9jQvgqZy2rdDNL8vLaLEvhHEaOXn7cNpyhsXIwQwv-VERbTsoVw0f-4_IOYBte52DfL_ahsQOV-1V8GwXOpPZHCpSX3mL2peE3NbXc8wJIi15ywoT-uWXGJg2xgrc69bZkNZWFVMF4-4vowiA/p.jpeg"}
   {:name "Cyan planet purse 1"
    :photo "https://uc8c2e055860e98bf04313fc869d.previews.dropboxusercontent.com/p/thumb/ACYlXtsMCt7KTbdc3wT2qHwx4m8Ds8hiUX_Lyl5yXyL9xMSI9HUyUzcEjQbJ-BvfQhHAu5HSv85tm7Cp940KaKlctyPWyiMQnhXjW9ARcN4NKyuGzyQynUbtfhuULem7_AjhpWvQSEZ5dEdbzmvqOAKrJxkhg1RC0-gdUZEBofbHvxGAk2lHDVqMLOm_orHWg13_xQzi3J43dqqVxfdVhwURTXFcDJam3oxc3wE4-4BFZUIXe17g8O4VX6tLBCZ1pudgEuRRUeNJX5tRt82HXSlyRlsBSONpzssP17_mSYhylmwbE_U2kJ9sjnsSeNZtkTkIUztvUv4unGYTqAvsDYi4YRzTP6JHhu4VZ57z5TWUkQ/p.jpeg"}
   {:name "Cyan planet purse 2"
    :photo "https://uc7361f66fa5888148a384c6e9e2.previews.dropboxusercontent.com/p/thumb/ACZpW55tr7YC9tB50mraYukMTEemKkeDsEroJMsATw7w5WPq1SSM_qVgfnJn_O3FCLWpcfvZ7qnyowzMC3Tlodm4Zsspp0rq6z92nnqO6T2j1IQl6OHXu6kB_sxQ1l-SdK6xj7JOsqxJRsjEk13CNGah6H3ODrJGn9Fl2PYhJAteGRbnqyeWa6oO8uiWLIG6JXkSVGn1tc7UpRm7wpvgJv54ePTHSK6XpVXWghmdBmwgZZXji6nTr6013rffHkoDZ8s2rrLXXkFPJm9ncqNcD5xlUjImlC1ySbdqbKavxJ2F5DMeF_3h5ohVuGLIXdyiURpGoB88_Df4E-UPhRN55tZ_VZnmJWdc0hQ1UvmOGaCCbA/p.jpeg"
    :category-header true}
   {:name "Cyan planet purse 3"
    :category-header true
    :photo "https://ucca9a6169e8abdf950548bafb23.previews.dropboxusercontent.com/p/thumb/ACaFdfUwnwOfLkH7knI6iLoUhzovWC2lumhFZzObpRaUodlnab-e-7bkO_8_AF3uRIewiZZvjYIUf7zg0MR7Gg9PbJBAueLIhy63uFccrr7CKv_kssRngpnh6tNYAIEPtC3_93Mqqz_943Z0pPNg8xDwUBh_GlMXR5wKUuAFNH705jYPOm2rzHtzfjRlg1_V4cNo9ZvxCneqwJtWKCbX1YmbejYWC6I01Gv0Ya16ADEsVDwuFPMAqk7av4s-K2feWAm_ORZC1HYK-p4XAD1D8I5MhrV5UbYNyCVvdBwThFfzS7Al9Wnhe_1mVYzCAylcl8ULN9WE92oBM1g-cPGY488bK2nz_UHm_WJjRdl5L2F5Wg/p.jpeg"}
   {:name "Blue-white planet purse"
    :category-header true
    :photo "https://ucc17eee0fac230eef6e616123ae.previews.dropboxusercontent.com/p/thumb/ACYj8_w6tytJW3MB8K3DLtJ_Q8xYYfkmC6x8tc-SW-pUTobJhy3evGEB-9knAZItZgaD4dOCXvsn83eJ2cenbuIuGJFwbpdDDWnbHdYIQpQflUYPSadtoQWI7zL2I9IoCAuq0fVT8CZd9Ra9n61Ixu3Owf0KeXx1jL1OSgfHVwuSCrfuoa7ERTn5z63puLaugUZw7hso5v0r9FqG1m8Gp_CPHzhGRKkmoTAR6feCp17Bik01Hux2Oj_6O4nHb-v880Mm9vR_wUoGu-ooXf_ba0ANF1TjQ1dj7ijhzEzELV1hy9uAUIEpzFLx0eQgIHwYI__EDH6WJZnn7i2vM2NnMFEAkH5IMLRRRv0bhKmE0N0SwA/p.jpeg"}
   ])