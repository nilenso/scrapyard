(ns scrapyard.controllers.ideas
  (:require [scrapyard.views.ideas :as view]))

(defn index [request]
  (view/index))
