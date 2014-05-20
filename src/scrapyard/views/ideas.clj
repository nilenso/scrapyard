(ns scrapyard.views.ideas
  (use hiccup.form)
  (:require [scrapyard.views.layout :as layout]))

(defn idea-list-item [idea]
  [:a {:href (str "/ideas/" (get idea :id)) :class "idea-list-item"} (get idea :title)])

(defn index [ideas]
  (layout/common [:a {:href "/ideas/new" :class "create-idea-link"} "Create New Idea"]
                 [:div {:class "idea-list"}
                  (map idea-list-item ideas)]))

(defn new []
  (layout/common
   (form-to {:class "new-idea-form"} [:post "/ideas"]
            (label "ideas[title]" "Title")
            (text-field {:class "new-idea-form-title"} "ideas[title]")
            (label "ideas[description]" "Description")
            (text-area {:class "new-idea-form-description"} "ideas[description]")
            (submit-button {:class "new-idea-form-submit"} "Save"))))

(defn show [idea]
  (layout/common [:div {:class "idea-details"}
                  [:h2 "Title"]
                  [:p (get idea :title)]
                  [:h2 "Description"]
                  [:p (get idea :description)]]))
