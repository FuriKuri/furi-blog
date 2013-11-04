(ns furiblog.models.mongo
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [monger.query :as q]))

(defn mongo-uri []
  (let [env (System/getenv "MONGO_URI")
        prop (System/getProperty "mongo.uri")
        default "mongodb://localhost"]
    (first (remove nil? [env prop default]))))

(def connected (ref false))

(defn init-mongo []
  (do
    (mg/connect-via-uri! (mongo-uri))
    (dosync
     (ref-set connected true))))

(defn read-posts []
  (if-not (= @connected true)
    (init-mongo))
  (q/with-collection "posts"
    (q/skip 0)
    (q/limit 10)
    (q/sort (sorted-map :date -1))))

