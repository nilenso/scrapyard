(ns scrapyard.models.validations
  (require [clojure.string :as string]))

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
