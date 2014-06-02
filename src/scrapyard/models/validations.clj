(ns scrapyard.models.validations
  (require [clojure.string :as string]
           [korma.core :as sql]))

(defn presence-of [attrs model]
  (reduce (fn [error-messages attr]
            (if (clojure.string/blank? (model attr))
              (-> (name attr)
                  (string/capitalize)
                  (->>
                   (format "%s should be present."))
                  (cons error-messages))))
          []
          attrs))

(defn uniqueness-of [attrs model entity]
  (reduce (fn [error-messages attr]
            (if (first
                 (sql/select entity
                             (sql/where {attr (attr model)})))
              (-> (name attr)
                  (string/capitalize)
                  (->>
                   (format "%s should be unique."))
                  (cons error-messages))))
          []
          attrs))
