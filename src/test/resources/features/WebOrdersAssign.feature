@HR-5

Feature: Validating functionalities in View All Orders part

  @Smoke @Regression
  Scenario: Validating delete selected order functionality in View All Orders part
    Given User navigates to application
    When User login with username "Tester" and password "test"
    And User choose the ones to be deleted and delete them
    Then User validates the chosen ones are deleted


