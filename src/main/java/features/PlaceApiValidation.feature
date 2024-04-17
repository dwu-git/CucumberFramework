Feature: Validation of Place Api
  @AddPlace @Regression
  Scenario Outline: Verify that AddPlace endpoint is working
    Given AddPlace payload with "<placeName>" "<placeLanguage>" "<placeAddress>" is added
    When User sends "Post" request to "AddPlace" endpoint
    Then Response with status code 200
    And "status" is "OK" in response body
    And "scope" is "APP" in response body
    And Verify place_id maps to "<place_Name>" using "<GetPlace>" endpoint

# Scenario Outline can have Examples
    Examples:
        | placeName        | placeLanguage      | placeAddress       |
        | AHouse           | A                  | A cross center     |
#        | BHouse           | B                  | B cross center     |

  @DeletePlace @Regression
  Scenario: Verify that DeletePlace endpoint is working
    Given DeletePlace payload
    When User sends "Post" request to "DeletePlace" endpoint
    Then Response with status code 200
    And "status" is "OK" in response body