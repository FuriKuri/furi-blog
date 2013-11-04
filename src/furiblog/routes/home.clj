(ns furiblog.routes.home
  (:require [compojure.core :refer :all]
            [furiblog.views.layout :as layout]
            [furiblog.models.mongo :as mongo]))

(defn format-time [timestamp]
  (-> "dd/MM/yyyy"
      (java.text.SimpleDateFormat.)
      (.format timestamp)))

(defn format-tags [tags]
  (reduce
   #(str %1 ", " %2)
   (map
    #(str "\"" %1 "\"")
    tags)))

(defn show-posts []
  [:div.content
   (for [{:keys [title content date tags]} (mongo/read-posts)]
     [:div
      [:hr]
      [:h2 title]
      [:p.post-content content]
      [:p.post-footer "{ date: \"" (format-time date) "\", tags: [" (format-tags tags) "] }"]])])

(defn home []
  (layout/common
   [:h1 "{ blog: \"FuriKuri\" }"]
   (show-posts)))

(defroutes home-routes
  (GET "/" [] (home)))
