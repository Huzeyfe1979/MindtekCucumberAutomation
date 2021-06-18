@Api @Regression @HR-31
  Feature: Validating query parameters in get customers api call

    Scenario Outline: Validating order customers get api call
      Given User creates customers with post api call using data
      |Name           |Address        |isActive|
      |Ali Ellibes    | 135 Elm St    | true   |
      |Hasan Yukari   | 404 Glue st   | true   |
      |Musa Gelemez   | 690 Army st   | true   |
      |Candan Satilmis| 235 Denver ave| true   |

      When User sends get customers api call with "<order>" order
      Then User validates that customers are in "<order>" order
      # Clean data , write a code to delete mock data
      Examples:
      |order|
      |asc  |
      |desc |

      @HR-36
      Scenario Outline: Validating limit customers query parameter in get api call
        Given User creates customers with post api call using data
          |Name           |Address        |isActive|
          |Ali Ellibes    | 135 Elm St    | true   |
          |Hasan Yukari   | 404 Glue st   | true   |
          |Musa Gelemez   | 690 Army st   | true   |
          |Candan Satilmis| 235 Denver ave| true   |

        When User sends get customers api call with "<limit>": limit
        Then User validates that get customers response has "<limit>" customers
        Examples:
        |limit|
        |3    |
        |1    |