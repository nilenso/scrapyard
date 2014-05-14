(ns scrapyard.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [scrapyard.controllers.ideas :as ideas]
            [scrapyard.schema :as schema]))

(defroutes app-routes
  (GET "/" [] ideas/index)
  (GET "/ideas/new" [] ideas/new)
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
