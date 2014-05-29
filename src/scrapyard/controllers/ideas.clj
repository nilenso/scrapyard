(ns scrapyard.controllers.ideas
  (:require [scrapyard.views.ideas :as view]
            [scrapyard.models.idea :as idea]
            [ring.util.response :as resp]))

(defn -tokenize-needs [needs]
  (clojure.string/split needs #","))

(defn index [request]
  (view/index (idea/all)))

(defn new [request]
  (view/new))

(defn create [request]
  (idea/create-with-needs (get-in request [:params :idea])
                         (-tokenize-needs (get-in request [:params :needs :name])))
  (resp/redirect "/"))

(defn show [id]
  (view/show (idea/find-by-id (Integer. id))))
