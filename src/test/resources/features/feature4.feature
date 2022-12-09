Feature: Verify POST request for Orders creation feature4

  Scenario Outline: Verify order is created successfully for a pet4
    Given User provides the resource endPoint
    And User provides the petId "<petId>"
    And User provides the order quantity "<quantity>"
    And User provides the order status "<status>"
    When User hits a POST request
    Then Validate response status code is 400
    Then Validate schema of the api response
    And Validate response body is as expected

    Examples:
      | petId | quantity | status |
      | 1     | 1        | placed |


