(ns scrapyard.models.need
  (require [korma.core :as sql]
           [scrapyard.models.entities :as entities]
           [scrapyard.models.validations :as validations]
           [scrapyard.models.ideas-needs :as ideas-needs]))

(defn validate [need]
  (validations/presence-of [:name] need))

(defn create [attrs]
  (if-let [errors (validate attrs)]
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
  (reduce (fn [acc need]
            (if-let [need (find-or-create {:name need})]
              (if-let [error (:errors need)]
                (cons (:errors acc) error)
                (ideas-needs/create {:ideas_id idea-id
                                     :needs_id (:id need)}))))
          {:errors []}
          needs-attrs))
