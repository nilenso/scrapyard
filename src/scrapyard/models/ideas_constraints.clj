(ns scrapyard.models.ideas-constraints
  (require [korma.core :as sql]
           [scrapyard.models.entities :as entities]))

(defn create [attrs]
  (sql/insert entities/ideas-constraints
              (sql/values {:ideas_id (get attrs :ideas_id)
                           :constraints_id (get attrs :constraints_id)})))
