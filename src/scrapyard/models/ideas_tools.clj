(ns scrapyard.models.ideas-tools
  (require [korma.core :as sql]
           [scrapyard.models.entities :as entities]))

(defn create [attrs]
  (sql/insert entities/ideas-tools
              (sql/values {:ideas_id (get attrs :ideas_id)
                           :tools_id (get attrs :tools_id)})))
