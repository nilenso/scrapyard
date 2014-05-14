(ns scrapyard.controllers.ideas
  (:require [scrapyard.views.ideas :as view]
            [scrapyard.models.idea :as idea]))

(defn index [request]
  (view/index (idea/all)))

(defn new [request]
  (view/new))
