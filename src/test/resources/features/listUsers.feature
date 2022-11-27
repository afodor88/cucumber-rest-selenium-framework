Feature: Getting the list of all users from https://reqres.in/

  Scenario: Get a list of all existing users and check the status is 200
    Given user makes a API call in order to get all existing users
    Then API call is successful with the status code 200
