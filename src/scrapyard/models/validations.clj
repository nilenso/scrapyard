(ns scrapyard.models.validations
  (require [clojure.string :as string]
           [korma.core :as sql]))

(defn- error-message [attr message]
  (-> (name attr)
      (string/capitalize)
      (->>
       (format (str "%s " message)))))

(defn perform [data validations]
  (map #(% data) validations))

;; return a seq of functions ready to be applied with some data
(defn create [{:keys [fields condition message]}]
  (map (fn [field]
         (fn [data]
           (when (condition (field data))
             (error-message field message))))
       fields))

(defn enlist [& validations]
  (flatten validations))

(defn presence-of [& fields]
  (create {:fields fields
           :condition (fn [o] (string/blank? o))
           :message "should not be blank"}))
