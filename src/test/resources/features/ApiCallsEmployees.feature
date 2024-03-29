@Api @Smoke @Regression @HR-20

  Feature: Validating API Calls for EMployees

    Scenario Outline: Validating POST Api call for employees
      Given User sends create employee api post call with data
        | firstName      | <firstName>      |
        | lastName       | <lastName>       |
        | departmentName | <departmentName> |
      Then User validates status code 201
      And User validates data populated in UI
        | firstName      | <firstName>      |
        | lastName       | <lastName>       |
        | departmentName | <departmentName> |
      And User validates employee data is persisted into DB
      And User validates data with get employee api call
        | firstName      | <firstName>      |
        | lastName       | <lastName>       |
        | departmentName | <departmentName> |
      Examples:
        | firstName | lastName | departmentName |
        | Patel     | Harsh    | IT             |
