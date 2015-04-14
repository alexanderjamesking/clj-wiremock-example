(ns clj-wiremock-example.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [clj-wiremock-example.handler :refer :all]
            [clj-wiremock.core :refer :all]))

(def wiremock-server (server))

; start the server at the start of the test suite, stop when done
(use-fixtures :once (fn [test-suite] (start wiremock-server) (test-suite) (stop wiremock-server)))

; reset mappings before each test 
(use-fixtures :each (fn [test-to-run] (reset wiremock-server) (test-to-run)))

(deftest test-app
  (testing "main route"
    (stub { :request { :method "GET" :url "/hello"} 
            :response { :status 200 
                        :body "{ \"message\": \"Hello World\" }"
                        :headers { :Content-Type "application/json" }}})
    (let [response (app (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World")))))
