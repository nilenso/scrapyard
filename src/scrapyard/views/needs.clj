(ns scrapyard.views.needs
  (use hiccup.form)
  (:require [scrapyard.views.layout :as layout]
            [hiccup.element :as h-element]
            [hiccup.page :as h-page]))

(defn linkify-items [items {:keys [link-to css-class property]}]
  (map (fn [item]
         [:a {:href (str (str "/" link-to "/") (get item :id)) :class css-class} (get item property)])
       items))

(defn show [need]
  (layout/common [:div {:class "idea-details"}
                  [:h2 "Need"]
                  [:p (:name need)]
                  [:h2 "Ideas"]
                  [:p (linkify-items (:ideas need) {:link-to "ideas"
                                                    :css-class "idea-tag-item"
                                                    :property :title})]]))
