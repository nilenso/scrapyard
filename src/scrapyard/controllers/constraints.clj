(ns scrapyard.controllers.constraints
  (:require [scrapyard.views.constraints :as view]
            [scrapyard.models.constraint :as constraint]
            [ring.util.response :as resp]))

(defn show [id]
  (view/show (constraint/find-by-id (Integer. id))))
