@Api @Smoke @Regression @HR-44
  Feature: Validating customers api calls
    Scenario: Validating create customer api call
      Given User creates customers with post api call using data
        |Name              |Address        |isActive|
        |Hasan Ellialti    | 135 Elm St    | true   |

      Then User validates that customer is created with data
        |Name              |Address        |isActive|
        |Hasan Ellialti    | 135 Elm St    | true   |
      When User deletes created customer
      Then User validates that customer is deleted

      Scenario: Validating creating account for a customer
        Given User creates customers with post api call using data
          |Name              | Address       |isActive|
          |Musti Saksagan    | 199 Elm St    | true   |
        When User creates an account for a customer with data
        |accountType|Checking|
        |Balance    | 352.2  |
        Then User validates that customer is linked to created account
          |accountType|Checking|
          |Balance    | 352.2  |
        When User deletes created account
        Then User validates that account is deleted
        When User deletes created customer
        Then User validates that customer is deleted



