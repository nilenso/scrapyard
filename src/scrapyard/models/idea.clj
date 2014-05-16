(ns scrapyard.models.idea
  (:require [korma.core :as sql]))

(sql/defentity ideas)

(defn all []
  (sql/select ideas))

(defn create [attrs]
  (sql/insert ideas
              (sql/values {:title (get attrs :title)
                           :description (get attrs :description)})))

(defn find-by-id [id]
  (first
   (sql/select ideas
               (sql/where {:id id}))))
