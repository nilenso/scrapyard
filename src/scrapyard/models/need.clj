(ns scrapyard.models.need
  (require [korma.core :as sql]
           [scrapyard.models.entities :as entities]
           [scrapyard.models.ideas-needs :as ideas-needs]))

(defn create [attrs]
  (sql/insert entities/needs
              (sql/values {:name (get attrs :name)})))

(defn bulk-create-from-idea [needs-attrs idea-id]
  (doseq [need needs-attrs]
    (if-let [need (create {:name need})]
      (ideas-needs/create {:ideas_id idea-id
                           :needs_id (get need :id)}))))
