(ns clj-wiremock-example.handler
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [clj-http.client :as client]
            [cheshire.core :refer [parse-string]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

; in a real app you would read this from an environment variable
(def hello-service-url "http://localhost:8080/hello")

(defn call-hello-service []
  (let [res (client/get hello-service-url)
        json (:body res)
        json-as-map (parse-string json true)]
    json-as-map))

(defroutes app-routes
  (GET "/" [] 
    (let [response (call-hello-service)]
      (:message response)))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
