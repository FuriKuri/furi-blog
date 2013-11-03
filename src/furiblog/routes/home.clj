(ns furiblog.routes.home
  (:require [compojure.core :refer :all]
            [furiblog.views.layout :as layout]
            [furiblog.models.mongo :as mongo]))

(defn show-posts []
  [:ul.guests
   (for [{:keys [title content timestamp]} (mongo/read-posts)]
     [:li
      [:blockquote title]
      [:p "-" [:cite content]]])])

(defn home []
  (layout/common
   [:h1 "Hello World!"]
   (show-posts)))

(defroutes home-routes
  (GET "/" [] (home)))
