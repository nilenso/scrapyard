(ns scrapyard.models.idea
  (:require [korma.core :as sql]
            [scrapyard.models.need :as need]
            [scrapyard.models.entities :as entities]
            [scrapyard.models.ideas-needs :as ideas-needs]))

(defn all []
  (sql/select entities/ideas))

(defn create [attrs]
  (sql/insert entities/ideas
                (sql/values {:title (get attrs :title)
                             :description (get attrs :description)})))

(defn create-with-need [idea-attrs need-attrs]
  (let [idea (create idea-attrs)
        need (need/create need-attrs)]
    (ideas-needs/create {:ideas_id (get idea :id)
                         :needs_id (get need :id)})))

(defn find-by-id [id]
  (first
   (sql/select entities/ideas
               (sql/where {:id id}))))
