Feature: Search (The test in this section should be attempted against the Expedia.com website)

  Scenario: Sign in with no credentials. Ensure the correct error message is displayed.
    Given the user navigates to expedia.com
    Then the user chooses a multi-city with the destinations below
#      | flight1                                | flight2                                        | flight3                                        |
#      | Berlin (BER - Brandenburg)             | Paris (CDG - Roissy-Charles de Gaulle)         | Rome (FCO - Fiumicino-Leonardo da Vinci Intl.) |
#      | Paris (CDG - Roissy-Charles de Gaulle) | Rome (FCO - Fiumicino-Leonardo da Vinci Intl.) | Berlin (BER - Brandenburg)                     |


      | Berlin (BER - Brandenburg)                     | Paris (CDG - Roissy-Charles de Gaulle)         |
      | Paris (CDG - Roissy-Charles de Gaulle)         | Rome (FCO - Fiumicino-Leonardo da Vinci Intl.) |
      | Rome (FCO - Fiumicino-Leonardo da Vinci Intl.) | Berlin (BER - Brandenburg)                     |



