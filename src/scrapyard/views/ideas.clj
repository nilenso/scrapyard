(ns scrapyard.views.ideas
  (use hiccup.form)
  (:require [scrapyard.views.layout :as layout]
            [hiccup.element :as h-element]
            [hiccup.page :as h-page]))

(defn idea-list-item [idea]
  [:a {:href (str "/ideas/" (get idea :id)) :class "idea-list-item"} (get idea :title)])

(defn stringify-tags [needs]
  (clojure.string/join ","
                       (map (fn [need] (:name need)) needs)))

(defn index [ideas]
  (layout/common [:a {:href "/ideas/new" :class "create-idea-link"} "Create New Idea"]
                 [:div {:class "idea-list"}
                  (map idea-list-item ideas)]))

(defn new [errors]
  (layout/common
   (layout/errors errors)
   (form-to {:class "new-idea-form"} [:post "/ideas"]
            (label "idea[title]" "Title")
            (text-field {:class "new-idea-form-title"} "idea[title]")
            (label "idea[description]" "Description")
            (text-area {:class "new-idea-form-description"} "idea[description]")
            (label "needs[name]" "Needs")
            (text-field {:class "new-idea-form-need"} "needs[name]")
            (submit-button {:class "new-idea-form-submit"} "Save"))

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
                  [:h2 "Tags"]
                  [:p (stringify-tags (:needs idea))]]))
