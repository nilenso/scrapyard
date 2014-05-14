(ns scrapyard.models.idea
  (:use korma.core)
  (:require [scrapyard.db :as db]))

(defentity ideas)

(db/setup)

(defn all []
  (select ideas)
  )
