(ns scrapyard.views.ideas
  (use hiccup.form)
  (:require [scrapyard.views.layout :as layout]))

(defn idea-list-item [idea]
  [:div {:class "idea-list-item"} (get idea :title)])

(defn index [ideas]
  (layout/common [:a {:href "/ideas/new"} "Create New Idea"]
                 (map idea-list-item ideas)))

(defn new []
  (layout/common
   (form-to [:post ""]
            (label "ideas[title]" "Title")
            (text-field "ideas[title]")
            (label "ideas[description]" "Description")
            (text-area "ideas[description]")
            (submit-button "Save"))))
