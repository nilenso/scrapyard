(ns scrapyard.db
  (require [korma.db :as db]))

(defn setup []
  (db/defdb database
    (let [[_ username password host port db-name]
          (next (re-find #"(.*)://(.*):(.*)@(.*):(.*)/(.*)" (or (System/getenv "DATABASE_URL") "")))]
      (db/postgres {:db (or db-name "scrapyard_development")
                    :password (or password "")
                    :user (or username "jithu")
                    :host (or host "localhost")
                    :port (or port "5432")}))))
