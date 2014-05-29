(ns scrapyard.models.ideas-needs
  (require [korma.core :as sql]
           [scrapyard.models.entities :as entities]))

(defn create [attrs]
  (sql/insert entities/ideas-needs
              (sql/values {:ideas_id (get attrs :ideas_id)
                           :needs_id (get attrs :needs_id)})))
