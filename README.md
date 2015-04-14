# clj-wiremock-example

A basic example of using [clj-wiremock](https://github.com/alexanderjamesking/clj-wiremock)

This is a [compojure](https://github.com/weavejester/compojure) webapp, pretty much the default created with ```lein new compojure your-project-name```, the only difference being the Hello World message is loaded by making a HTTP GET to a server returning the message as JSON.

The service we are calling has one endpoint "/hello" which returns the following JSON with a Content-Type of application/json
```json
{
  "message": "Hello World"
}
```

It's useful to stub out the service so we can test against known data, wiremock enables us to spin up an API for the duration of our tests so we can test our application with real HTTP calls without needing mocks within our project.

Look at [test/clj_wiremock_example/handler_test.clj](https://github.com/alexanderjamesking/clj-wiremock-example/blob/master/test/clj_wiremock_example/handler_test.clj)

Run ```lein test``` to see the tests in action.

To run the app run ```lein ring server```, this will load up a browser and go to http://localhost:3000/ which will initially fail with a java.net.ConnectException because it's trying to talk to a server which doesn't exist. To set up a stub server at runtime open up the REPL in a new tab using ```lein repl``` then the following:

```clojure
(require '[clj-wiremock.core :refer :all])

; create a new server on the default port 8080
(def wiremock-server (server))

; start the server - load http://localhost:8080/__admin/ in a browser to see it running
(start wiremock-server)

; set up a stub that returns our message in JSON
(stub { :request { :method "GET" :url "/hello"} 
        :response { :status 200 
                    :body "{ \"message\": \"Hello World\"}"
                    :headers { :Content-Type "application/json" }}})

; stop the server when done
(stop wiremock-server)
```

That's all folks, take a look at the tests in [clj-wiremock](https://github.com/alexanderjamesking/clj-wiremock) and the [wiremock](https://github.com/tomakehurst/wiremock) documentation (particularly the JSON examples) for more detail.

