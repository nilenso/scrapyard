(ns scrapyard.models.entities
  (:require [korma.core :as sql]))

(declare needs)
(declare tools)
(declare constraints)

(sql/defentity ideas
  (sql/many-to-many needs :ideas_needs)
  (sql/many-to-many tools :ideas_tools)
  (sql/many-to-many constraints :ideas_constraints))

(sql/defentity needs
  (sql/many-to-many ideas :ideas_needs))

(sql/defentity tools
  (sql/many-to-many ideas :ideas_tools))

(sql/defentity constraints
  (sql/many-to-many ideas :ideas_constraints))

(sql/defentity ideas-needs
  (sql/table :ideas_needs)
  (sql/belongs-to ideas)
  (sql/belongs-to needs))

(sql/defentity ideas-tools
  (sql/table :ideas_tools)
  (sql/belongs-to ideas)
  (sql/belongs-to tools))

(sql/defentity ideas-constraints
  (sql/table :ideas_constraints)
  (sql/belongs-to ideas)
  (sql/belongs-to constraints))
