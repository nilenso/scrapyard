(ns scrapyard.web
  (:require [scrapyard.handler :refer [app]]
            [scrapyard.db :as db]
            [ring.adapter.jetty :as jetty]))

(defn -main [& [port]]
  (let [port (Integer. (or port (System/getenv "port") 3000))]
    (db/setup)
    (jetty/run-jetty #'app {:port port :join? false})))
