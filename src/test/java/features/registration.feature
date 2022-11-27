Feature: Registration on https://reqres.in/

  Scenario Outline: Verify if the registration is successful
    When user tries to register with username "<username>" and password "<password>"
    Then API call is successful with the status code <statusCode>
    And a token is received
    Examples:
      | username           | password | statusCode |
      | eve.holt@reqres.in | pistol   | 200        |


  Scenario: Verify if the registration is unsuccessful when password is empty
    When user tries to register with username "eve.holt@reqres.in" and password ""
    Then API call has failed with the status code 400 and error message "Missing password"
