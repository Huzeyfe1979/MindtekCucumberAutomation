@Api @Smoke @Regression @HR-30
  Feature: Validation Authorization api calls

    @Api
    Scenario: Validating success response in api call when sending with token
      Given User sends get customer api call with access token
      Then User validates 200 status code

      @Api
      Scenario: Validating forbidden response in api call when sending without token
        Given User sends get customers api call without access token
        Then User validates 403 status code