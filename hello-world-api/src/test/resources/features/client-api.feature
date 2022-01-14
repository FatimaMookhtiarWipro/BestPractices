Feature: hello-world-api
    Background:
        *   url 'http://127.0.0.1:8082/helloworldapi/v1/'
        *   urlpattern="/helloworldapi/v1/client/{id}"

    Scenario: retieve user details
        Given path '/client/1234'
        When method get
        Then status 200