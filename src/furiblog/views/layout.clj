(ns furiblog.views.layout
  (:require [hiccup.page :refer [html5 include-css]]))

(defn common [& body]
  (html5
    [:head
     [:title "Welcome to FuriKuri's Blog"]
     (include-css "/css/screen.css")]
    [:body body]))

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

(defn blog-posts [posts]
  [:div.content
   (for [{:keys [title content date tags]} posts]
     [:div
      [:hr]
      [:h2 title]
      [:p.post-content content]
      [:p.post-footer "{ date: \"" (format-time date) "\", tags: [" (format-tags tags) "] }"]])])
