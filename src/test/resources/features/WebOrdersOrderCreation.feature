Feature: Validating order creation functionality

  Background: These steps will run before each scenario
    Given User navigates to application
    When User login with username "Tester" and password "test"
    And User clicks on Order module

  Scenario Outline: Validating calculate total functionality
    And User provides Product "<Product>" and quantity <Quantity>
    Then User validates total is calculated properly for quantity <Quantity>
    Examples:
    | Product | Quantity |
    | FamilyAlbum | 1    |
    | MyMoney     | 5    |
    | ScreenSaver | 20   |

  @Regression
  Scenario: Validating calculate total functionality with quantity 5
    And User provides Product "ScreenSaver" and quantity 5
    Then User validates total is calculated properly for quantity 5

    @Smoke
  Scenario: Validating order creation functionality
    And User creates order with data
    |Product | Quantity | Customer Name | Street | City | State | Zip | Card | Card Number | Exp Date |
    |FamilyAlbum| 1      | John Doe     | 123 Ali st|Chicago |IL | 12345 |Visa | 3211379   |12/21     |
    | MyMoney| 5      | Patel Harsh     | 456 Veli st|New York |NY | 54321 |Master | 3359598   |12/21     |
#    data.get(0).get("Product"); -> FamilyAlbum
#    data.get(1).get("Quantity"); -> 5
    Then User validates success message "New order has been successfully added."
    And User validates that created orders are in the list of all orders

    
