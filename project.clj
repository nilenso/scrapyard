(defproject scrapyard "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.6"]
                 [hiccup "1.0.5"]
                 [ragtime/ragtime.sql.files "0.3.7"]
                 [korma "0.3.1"]
                 [org.postgresql/postgresql "9.3-1100-jdbc4"]
                 [org.clojure/java.jdbc "0.3.3"]]
  :min-lein-version "2.0.0"
  :plugins [[lein-ring "0.8.10"]
            [ragtime/ragtime.lein "0.3.7"]]
  :ragtime {:migrations ragtime.sql.files/migrations
          :database "jdbc:postgresql://localhost:5432/scrapyard_development?user=jithu"}
  :ring {:handler scrapyard.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
