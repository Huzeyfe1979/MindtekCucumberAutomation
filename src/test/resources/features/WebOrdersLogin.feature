Feature: Validating Login Functionality

  @Regression
  Scenario: Validating login functionality with valid credentials
    Given User navigates to application
    When User provide username "Tester" and password "test"
    Then User validates that application is on homepage

    @Smoke @Regression
  Scenario: Validating login functionality with invalid credentials
      Given User navigates to application
      When User provide username "Tester" and password "tester"
      Then User validates error login message "Invalid Login or Password."
