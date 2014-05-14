(ns scrapyard.schema
  (require [korma.db :as db]))

(defn setup []
  (db/defdb database
    (db/postgres {:db "scrapyard_development"
                  :password ""
                  :user "jithu"
                  :host "localhost"
                  :port "5432"})))
