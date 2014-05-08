(ns scrapyard.views.ideas
  (:require [hiccup.page :as h]))

(defn index []
  (h/html5
   [:head
    [:title "Scrapyard"]]))
