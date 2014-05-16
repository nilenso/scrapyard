(ns scrapyard.controllers.ideas
  (:require [scrapyard.views.ideas :as view]
            [scrapyard.models.idea :as idea]
            [ring.util.response :as resp]))

(defn index [request]
  (view/index (idea/all)))

(defn new [request]
  (view/new))

(defn create [request]
  (idea/create (get-in request [:params :ideas]))
  (resp/redirect "/"))

(defn show [id]
  (view/show (idea/find-by-id (Integer. id))))
