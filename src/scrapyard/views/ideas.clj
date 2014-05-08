(ns scrapyard.views.ideas
  (:require [scrapyard.views.layout :as layout]))

(defn index []
  (layout/common [:a {:href "/ideas/new"} "Create New Idea"]))
