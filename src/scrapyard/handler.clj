(ns scrapyard.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [scrapyard.controllers.ideas :as ideas]
            [scrapyard.controllers.needs :as needs]
            [scrapyard.controllers.tools :as tools]
            [scrapyard.controllers.constraints :as constraints]))

(defroutes app-routes
  (GET "/" [] ideas/index)

  (GET "/ideas/new" [] ideas/new)
  (GET "/ideas/:id" [id] (ideas/show id))
  (POST "/ideas" [] ideas/create)

  (GET "/needs/:id" [id] (needs/show id))
  (GET "/tools/:id" [id] (tools/show id))
  (GET "/constraints/:id" [id] (constraints/show id))

  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
