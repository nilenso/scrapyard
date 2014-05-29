(ns scrapyard.models.need
  (require [korma.core :as sql]
           [scrapyard.models.entities :as entities]))

(defn create [attrs]
  (sql/insert entities/needs
              (sql/values {:name (get attrs :name)})))
