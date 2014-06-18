(ns scrapyard.controllers.ideas
  (:require [scrapyard.views.ideas :as view]
            [scrapyard.models.idea :as idea]
            [ring.util.response :as resp]))

(defn- tokenize-extras [extras]
  (if (empty? extras)
    []
    (clojure.string/split extras #",")))

(defn index [request]
  (view/index (idea/all)))

(defn new [request]
  (view/new []))

(defn create [request]
  (let [idea
        (idea/create-with-extras (get-in request [:params :idea])
                                 (tokenize-extras (get-in request [:params :needs :name]))
                                 (tokenize-extras (get-in request [:params :tools :name]))
                                 (tokenize-extras (get-in request [:params :constraints :name])))]
    (if-let [errors (:errors idea)]
      (view/new errors)
      (resp/redirect "/"))))

(defn show [id]
  (view/show (idea/find-by-id (Integer. id))))
