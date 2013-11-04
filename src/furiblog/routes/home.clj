(ns furiblog.routes.home
  (:require [compojure.core :refer :all]
            [furiblog.views.layout :as layout]
            [furiblog.models.mongo :as mongo]))

(defn home []
  (layout/common
   [:h1 "{ blog: \"FuriKuri\" }"]
   (layout/blog-posts (mongo/read-posts))))

(defroutes home-routes
  (GET "/" [] (home)))
