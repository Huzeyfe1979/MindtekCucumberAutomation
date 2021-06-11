@HR-20 # Basically, id of the task/story
  Feature: Validate create employee functionality

    @Regression @Smoke

      Scenario: Validate created employee persisted in database
      Given user navigates to login page
      When user logins to HRApp
      And creates new employee
      Then user validates new employee is created in database

      # And user cleans created data

