(ns scrapyard.views.ideas
  (:require [scrapyard.views.layout :as layout]))

(defn idea-list-item [idea]
  [:div {:class "idea-list-item"} (get idea :title)])

(defn index [ideas]
  (layout/common [:a {:href "/ideas/new"} "Create New Idea"]
                 (map idea-list-item ideas)))
