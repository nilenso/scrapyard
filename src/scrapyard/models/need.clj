(ns scrapyard.models.need
  (require [korma.core :as sql]
           [scrapyard.models.entities :as entities]
           [scrapyard.models.validations :as validates]
           [scrapyard.models.ideas-needs :as ideas-needs]))

(def validations
  (validates/enlist
   (validates/presence-of :name)))

(defn create [attrs]
  (if-let [errors (validates/perform attrs validations)]
    {:errors errors}
    (sql/insert entities/needs
                (sql/values {:name (get attrs :name)}))))

(defn find-by-name [name]
  (first
   (sql/select entities/needs
               (sql/where {:name name}))))

(defn find-or-create [attrs]
  (if-let [idea (find-by-name (:name attrs))]
    idea
    (create attrs)))

(defn bulk-create-from-idea [needs-attrs idea-id]
  (doseq [need needs-attrs]
    (if-let [need (find-or-create {:name need})]
      (ideas-needs/create {:ideas_id idea-id
                           :needs_id (:id need)}))))

(defn find-by-id [id]
  (first
   (sql/select entities/needs
               (sql/with entities/ideas)
               (sql/where {:id id}))))
