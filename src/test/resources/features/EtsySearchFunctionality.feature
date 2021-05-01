Feature: Validating Search Functionality

  @Smoke @Regression
  Scenario: Validating search result is matching with searched item
    Given User navigates to Etsy application
    When User searches for "carpet"
    Then User validates the search results contain
    | carpet   | rug         |
    | oval rug | turkish rug |
