(ns scrapyard.views.ideas
  (use hiccup.form)
  (:require [scrapyard.views.layout :as layout]
            [hiccup.element :as h-element]
            [hiccup.page :as h-page]))

(defn linkify-items [items {:keys [link-to css-class property]}]
  (map (fn [item]
         [:a {:href (str (str "/" link-to "/") (get item :id)) :class css-class} (get item property)])
       items))

(defn index [ideas]
  (layout/common [:a {:href "/ideas/new" :class "create-idea-link"} "Create New Idea"]
                 [:div {:class "idea-list"}
                  (linkify-items ideas {:link-to "ideas"
                                        :css-class "idea-list-item"
                                        :property :title})]))

(defn new [errors]
  (layout/common
   (layout/errors errors)
   (form-to {:class "new-idea-form"} [:post "/ideas"]
            [:div {:class "new-idea-form-col-1"}
             (label "idea[title]" "Title")
             (text-field {:class "new-idea-form-title"} "idea[title]")
             (label "idea[description]" "Description")
             (text-area {:class "new-idea-form-description"} "idea[description]")]
            [:div {:class "new-idea-form-col-2"}
             (label "needs[name]" "Needs")
             (text-field {:class "new-idea-form-needs"} "needs[name]")
             (label "tools[name]" "Tools")
             (text-field {:class "new-idea-form-tools"} "tools[name]")
             (label "constraints[name]" "Constraints")
             (text-field {:class "new-idea-form-constraints"} "constraints[name]")]
            [:div {:class "new-idea-form-row-separator clearfix"}
             (submit-button {:class "new-idea-form-submit"} "Save")])

   (h-page/include-css "/css/select2.css")
   (h-page/include-js "/javascripts/select2.min.js")
   (h-page/include-js "/javascripts/new_idea.js")
   (h-element/javascript-tag "$(document).ready(function() { new Scrapyard.NewIdea($('.new-idea-form')) });")))

(defn show [idea]
  (layout/common [:div {:class "idea-details"}
                  [:h2 "Title"]
                  [:p (:title idea)]
                  [:h2 "Description"]
                  [:p (:description idea)]
                  [:h2 "Needs"]
                  [:p (linkify-items (:needs idea) {:link-to "needs"
                                                    :css-class "idea-tag-item"
                                                    :property :name})]
                  [:h2 "Tools"]
                  [:p (linkify-items (:tools idea) {:link-to "tools"
                                                    :css-class "idea-tag-item"
                                                    :property :name})]
                  [:h2 "Constraints"]
                  [:p (linkify-items (:constraints idea) {:link-to "constraints"
                                                          :css-class "idea-tag-item"
                                                          :property :name})]]))
