(ns scrapyard.models.constraint
  (require [korma.core :as sql]
           [scrapyard.models.entities :as entities]
           [scrapyard.models.validations :as validates]
           [scrapyard.models.ideas-constraints :as ideas-constraints]))

(def validations
  (validates/enlist))

(defn create [attrs]
  (if-let [errors (validates/perform attrs validations)]
    {:errors errors}
    (sql/insert entities/constraints
                (sql/values {:name (get attrs :name)}))))

(defn find-by-name [name]
  (first
   (sql/select entities/constraints
               (sql/where {:name name}))))

(defn find-or-create [attrs]
  (if-let [idea (find-by-name (:name attrs))]
    idea
    (create attrs)))

(defn bulk-create-from-idea [constraints-attrs idea-id]
  (doseq [constraint constraints-attrs]
    (if-let [constraint (find-or-create {:name constraint})]
      (ideas-constraints/create {:ideas_id idea-id
                                 :constraints_id (:id constraint)}))))
