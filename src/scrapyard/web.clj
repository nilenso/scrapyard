(ns scrapyard.web
  (:require [scrapyard.handler :refer [app]]
            [ring.adapter.jetty :as jetty]))

(defn -main [& [port]]
  (let [port (Integer. (or port (System/getenv "port") 3000))]
    (jetty/run-jetty #'app {:port port :join? false})))
