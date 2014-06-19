(ns scrapyard.views.ideas
  (use hiccup.form)
  (:require [scrapyard.views.layout :as layout]
            [hiccup.element :as h-element]
            [hiccup.page :as h-page]
            [clojure.data.json :as json]))

(defn linkify-item [{:keys [link-to css-class content]}]
  [:a {:href link-to :class css-class} content])

(defn linkify-tags [items resource-name]
  (map
   (fn [item]
     (linkify-item {:link-to (str "/" resource-name "/" (:id item))
                    :css-class "idea-tag-item"
                    :content (:name item)}))
   items))

(defn index [ideas]
  (layout/common [:a {:href "/ideas/new" :class "create-idea-link"} "Create New Idea"]
                 [:div {:class "idea-list"}
                  (map
                   (fn [idea]
                     [:div {:class "idea-list-item"}
                      (linkify-item {:link-to (str "/ideas/" (:id idea))
                                     :css-class "idea-list-item-link"
                                     :content (:title idea)})
                      [:p {:class "idea-list-item-description"} (:description idea)]])
                   ideas)]))

(defn new [attrs]
  (layout/common
   (layout/errors (:errors attrs))
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
   (let [opts (json/write-str attrs)]
     (h-element/javascript-tag (format "$(document).ready(function() { new Scrapyard.NewIdea($('.new-idea-form'), %s) });" opts)))))

(defn show [idea]
  (layout/common [:div {:class "idea-details"}
                  [:h2 "Title"]
                  [:p (:title idea)]
                  [:h2 "Description"]
                  [:p (:description idea)]
                  [:h2 "Needs"]
                  [:p (linkify-tags (:needs idea) "needs")]
                  [:h2 "Tools"]
                  [:p (linkify-tags (:tools idea) "tools")]
                  [:h2 "Constraints"]
                  [:p (linkify-tags (:constraints idea) "constraints")]]))
