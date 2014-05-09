(ns scrapyard.models.idea
  (:require [korma.core :as sql]))

(sql/defentity ideas)

(def setup
  (sql/defdb database
    (sql/postgres ({:db "scrapyard_development"
                    :password ""
                    :user "jithu"
                    :host "localhost"
                    :port "5432"}))))

(def all
  (setup)
  (sql/select ideas)
  )
