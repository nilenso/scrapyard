(ns scrapyard.models.idea
  (:require [korma.core :as sql]
            [korma.db :as db]
            [scrapyard.models.need :as need]
            [scrapyard.models.entities :as entities]))

(defn all []
  (sql/select entities/ideas))

(defn create [attrs]
  (sql/insert entities/ideas
              (sql/values {:title (get attrs :title)
                           :description (get attrs :description)})))

(defn create-with-needs [idea-attrs need-attrs]
  (db/transaction
   (let [idea (create idea-attrs)]
     (need/bulk-create-from-idea need-attrs (get idea :id)))))

(defn find-by-id [id]
  (first
   (sql/select entities/ideas
               (sql/with entities/needs)
               (sql/where {:id id}))))
