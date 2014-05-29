(ns scrapyard.models.entities
  (:require [korma.core :as sql]))

(declare needs)

(sql/defentity ideas
  (sql/many-to-many needs :ideas_needs))

(sql/defentity needs
  (sql/many-to-many ideas :ideas_needs))

(sql/defentity ideas-needs
  (sql/table :ideas_needs)
  (sql/belongs-to ideas)
  (sql/belongs-to needs))
