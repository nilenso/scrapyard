(ns scrapyard.models.idea
  (:require [korma.core :as sql]
            [korma.db :as db]
            [scrapyard.models.need :as need]
            [scrapyard.models.tool :as tool]
            [scrapyard.models.constraint :as constraint]
            [scrapyard.models.entities :as entities]
            [scrapyard.models.validations :as validates]))

(def validations
  (validates/enlist
   (validates/presence-of :title :description)))

(defn all []
  (sql/select entities/ideas))

(defn create [attrs]
  (if-let [errors (validates/perform attrs validations)]
    {:errors errors}
    (sql/insert entities/ideas
                (sql/values {:title (:title attrs)
                             :description (:description attrs)}))))

(defn create-with-extras [idea-attrs need-attrs tool-attrs constraint-attrs]
  (prn need-attrs tool-attrs constraint-attrs)
  (db/transaction
   (let [idea (create idea-attrs)
         idea-id (:id idea)]
     (if (:errors idea)
       idea
       (do
         (need/bulk-create-from-idea need-attrs idea-id)
         (tool/bulk-create-from-idea tool-attrs idea-id)
         (constraint/bulk-create-from-idea constraint-attrs idea-id))))))

(defn find-by-id [id]
  (first
   (sql/select entities/ideas
               (sql/with entities/needs)
               (sql/where {:id id}))))
