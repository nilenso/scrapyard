(ns scrapyard.web
  (:require [scrapyard.handler :refer [app]]
            [scrapyard.db :as db]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]))

(defn -main [& [port]]
  (let [port (Integer. (or port (System/getenv "port") 3000))]
    (db/setup)
    (jetty/run-jetty (wrap-reload #'app) {:port port :join? false})))
