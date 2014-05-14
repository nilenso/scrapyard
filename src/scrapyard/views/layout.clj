(ns scrapyard.views.layout
  (:require [hiccup.page :as h]))

(defn common [& args]
  (h/html5
   [:head
    [:title "Scrapyard"]]
   [:body
    [:h1 {:class "header"} "Scrapyard"]
    [:div {:class "container"} args]]))
