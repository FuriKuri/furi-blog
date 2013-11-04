(ns furiblog.models.mongo
  (:require [monger.core :as mg]
            [monger.collection :as mc]))

(defn mongo-uri []
  (let [env (System/getenv "MONGO_URI")
        prop (System/getProperty "mongo.uri")
        default "mongodb://localhost"]
    (first (remove nil? [env prop default]))))

(mg/connect-via-uri! (mongo-uri))

(defn read-posts []
  (mc/find-maps "posts"))

