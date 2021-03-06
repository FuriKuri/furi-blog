(defproject furiblog "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [hiccup "1.0.4"]
                 [ring-server "0.3.0"]
                 [com.novemberain/monger "1.5.0"]]
  :plugins [[lein-ring "0.8.7"]]
  :min-lein-version "2.2.0"
  :ring {:handler furiblog.handler/app
         :init furiblog.handler/init
         :destroy furiblog.handler/destroy}
  :profiles
  {:production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}}
   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.2.0"]]}})
