Feature: Sign in (All Sign in tests should be attempted against the Automation Practise website)

  Scenario Outline: Sign in with no credentials. Ensure the correct error message is displayed.
    Given user is on login page
    And user enters "<username>" and "<password>" and clicks login
    Then the user should receive the error "<errorMessage>"
    Examples:
      | username      | password     | errorMessage                                                              |
      |               |              | Epic sadface: Username is required                                        |
      | standard_user |              | Epic sadface: Password is required                                        |
      |               | secret_sauce | Epic sadface: Username is required                                        |
      | incorrect     | incorrect    | Epic sadface: Username and password do not match any user in this service |



