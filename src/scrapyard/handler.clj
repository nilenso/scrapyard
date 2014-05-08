(ns scrapyard.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [scrapyard.controllers.ideas :as ideas]))

(defroutes app-routes
  (GET "/" []
       ideas/index)
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
