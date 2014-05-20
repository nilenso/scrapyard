(ns scrapyard.views.layout
  (:require [hiccup.page :as h]))

(defn common [& args]
  (h/html5
   [:head
    [:title "Scrapyard"]
    [:link {:rel "stylesheet" :type "text/css" :href "/css/style.css"}]]
   [:body
    [:div {:class "container"}
     [:a {:href "/" :class "site-header-link"} "Scrapyard"]
     args]]))
