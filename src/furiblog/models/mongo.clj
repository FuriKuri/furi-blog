(ns furiblog.models.mongo
  (:require [monger.core :as mg]
            [monger.collection :as mc]))

(defn mongo-uri []
  (let [env (System/getenv "MONGO_URI")
        prop (System/getProperty "mongo.uri")
        default "mongodb://localhost"]
    (first (remove nil? [env prop default]))))

(def connected (ref false))

(defn read-posts []
  (if-not (= @connected true)
    (do
      (mg/connect-via-uri! (mongo-uri))
      (dosync
       (ref-set connected true))))
  (mc/find-maps "posts"))

