(ns scrapyard.models.idea
  (:use korma.core)
  (:require [scrapyard.schema :as schema]))

(defentity ideas)

(schema/setup)

(defn all []
  (select ideas)
  )
