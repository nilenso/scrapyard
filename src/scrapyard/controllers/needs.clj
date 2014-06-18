(ns scrapyard.controllers.needs
  (:require [scrapyard.views.needs :as view]
            [scrapyard.models.need :as need]
            [ring.util.response :as resp]))

(defn show [id]
  (view/show (need/find-by-id (Integer. id))))
