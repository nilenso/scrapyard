(ns scrapyard.models.tool
  (require [korma.core :as sql]
           [scrapyard.models.entities :as entities]
           [scrapyard.models.validations :as validates]
           [scrapyard.models.ideas-tools :as ideas-tools]))

(def validations
  (validates/enlist))

(defn create [attrs]
  (if-let [errors (validates/perform attrs validations)]
    {:errors errors}
    (sql/insert entities/tools
                (sql/values {:name (get attrs :name)}))))

(defn find-by-name [name]
  (first
   (sql/select entities/tools
               (sql/where {:name name}))))

(defn find-or-create [attrs]
  (if-let [idea (find-by-name (:name attrs))]
    idea
    (create attrs)))

(defn bulk-create-from-idea [tools-attrs idea-id]
  (doseq [tool tools-attrs]
    (if-let [tool (find-or-create {:name tool})]
      (ideas-tools/create {:ideas_id idea-id
                           :tools_id (:id tool)}))))

(defn find-by-id [id]
  (first
   (sql/select entities/tools
               (sql/with entities/ideas)
               (sql/where {:id id}))))
