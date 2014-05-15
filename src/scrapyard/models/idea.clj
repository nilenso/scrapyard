(ns scrapyard.models.idea
  (:use korma.core)
  (:require [scrapyard.db :as db]))

(defentity ideas)

(defn all []
  (select ideas)
  )
