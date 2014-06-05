(ns scrapyard.models.idea
  (:require [korma.core :as sql]
            [korma.db :as db]
            [scrapyard.models.need :as need]
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

(defn create-with-needs [idea-attrs need-attrs]
  (db/transaction
   (let [idea (create idea-attrs)]
     (if (:errors idea)
       idea
       (need/bulk-create-from-idea need-attrs (:id idea))))))

(defn find-by-id [id]
  (first
   (sql/select entities/ideas
               (sql/with entities/needs)
               (sql/where {:id id}))))
