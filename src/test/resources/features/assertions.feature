Feature: Verify POST request for Orders creation and Assertion

#   TestCase will fail with executing further steps using SOFTAssertions and assertAll will show all errors in one shot
  @softAssertions @test1234
  Scenario Outline: Verify order is created successfully for a pet Soft
    Given User provides the resource endPoint
    And User provides the petId "<petId>"
    And User provides the order quantity "<quantity>"
    And User provides the order status "<status>"
    When User hits a POST request
    And Validate softAssertions response body is as expected
    Then Validate response status code is 200
    Then Validate schema of the api response

    Examples:
      | petId   | quantity | status |
      | ZORO001 | 1        | placed |

#   TestCase will fail without executing further steps when it encounter 1st assertion failure
  @hardAssertions @test1234
  Scenario Outline: Verify order is created successfully for a pet Hard
    Given User provides the resource endPoint
    And User provides the petId "<petId>"
    And User provides the order quantity "<quantity>"
    And User provides the order status "<status>"
    When User hits a POST request
    Then Validate response status code is 200
    Then Validate schema of the api response
    And Validate hardAssertions response body is as expected

    Examples:
      | petId   | quantity | status |
      | ZORO002 | 1        | placed |


