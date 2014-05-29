(ns scrapyard.views.layout
  (:require [hiccup.page :as h-page]))

(defn common [& args]
  (h-page/html5
   [:head
    [:title "Scrapyard"]
    [:link {:rel "stylesheet" :type "text/css" :href "/css/style.css"}]]
    (h-page/include-js "/javascripts/jquery-2.1.1.min.js")
   [:body
    [:div {:class "container"}
     [:a {:href "/" :class "site-header-link"} "Scrapyard"]
     args]]))
