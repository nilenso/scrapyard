(ns scrapyard.controllers.tools
  (:require [scrapyard.views.tools :as view]
            [scrapyard.models.tool :as tool]
            [ring.util.response :as resp]))

(defn show [id]
  (view/show (tool/find-by-id (Integer. id))))
