(ns scrapyard.views.layout
  (:require [hiccup.page :as h-page]))

(defn common [& args]
  (h-page/html5
   [:head
    [:title "Scrapyard"]
    [:link {:rel "stylesheet" :type "text/css" :href "/css/style.css"}]]
    (h-page/include-js "/javascripts/jquery-2.1.1.min.js")
   [:body
    [:div {:class "github-ribbon"}
     [:a {:href "https://github.com/nilenso/westwind"}
      [:img {:style "position: absolute; top: 0; right: 0; border: 0"
             :src "https://camo.githubusercontent.com/652c5b9acfaddf3a9c326fa6bde407b87f7be0f4/68747470733a2f2f73332e616d617a6f6e6177732e636f6d2f6769746875622f726962626f6e732f666f726b6d655f72696768745f6f72616e67655f6666373630302e706e67"
             :alt "Fork me on GitHub"
             :data-canonical-src "https://s3.amazonaws.com/github/ribbons/forkme_right_orange_ff7600.png"}]]]
    [:div {:class "container"}
     [:a {:href "/" :class "site-header-link"} "Scrapyard"]
     args]]))

(defn errors [errors]
  (if errors
    [:ul {:class "error-messages"}
     (map
      (fn [error]
        [:li error])
      errors)]))
