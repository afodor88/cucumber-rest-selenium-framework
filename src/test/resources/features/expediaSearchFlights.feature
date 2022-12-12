Feature: Search (The test in this section should be attempted against the Expedia.com website)

  @Expedia @BrowserTests
  Scenario: Sign in with no credentials. Ensure the correct error message is displayed.
    Given the user navigates to expedia.com
    Then the user chooses a multi-city with the destinations below
      | Berlin (BER - Brandenburg)                     | Paris (CDG - Roissy-Charles de Gaulle)         |
      | Paris (CDG - Roissy-Charles de Gaulle)         | Rome (FCO - Fiumicino-Leonardo da Vinci Intl.) |
      | Rome (FCO - Fiumicino-Leonardo da Vinci Intl.) | Berlin (BER - Brandenburg)                     |
    And the user chooses 4 adults
    And the time schedule below
      | 2022-12-27 |
      | 2022-12-28 |
      | 2022-12-29 |
    Then the user clicks search
    Then the user selects the price for the first flight