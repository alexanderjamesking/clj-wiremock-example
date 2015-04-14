(defproject clj-wiremock-example "0.1.0-SNAPSHOT"
  :description "A basic example that uses clj-wiremock"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.1"]
                 [clj-http "1.1.0"]
                 [cheshire "5.4.0"]
                 [ring/ring-defaults "0.1.2"]]
  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler clj-wiremock-example.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [clj-wiremock "0.3.0"] 
                        [ring-mock "0.1.5"]]}})
