Feature: Validating Search Functionality

  Background: Repeated steps
    Given User navigates to Etsy application
    When User searches for "carpet"

  @Smoke @Regression
  Scenario: Validating search result is matching with searched item
    Then User validates the search results contain
    | carpet   | rug         |
    | oval rug | turkish rug |


    Scenario: Validating price range functionality for searched item
      And User selects price range more than 1000
      Then User validates price range is more than 1000
