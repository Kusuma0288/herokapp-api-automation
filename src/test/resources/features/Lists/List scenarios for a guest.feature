@REGRESSION @LIST @LION @SMOKE @VEGEMITE
Feature: Verify Apigee List scenarios for User


  Background:
    Given mobile user connect to apigee endpoint as guest
    And connection from user to apigee endpoint happens
    And user successfully authenticate to apigee public api as guest

  Scenario Outline: Validate "<Version>" list to trolley sync scenario for a guest user in Pickup mode
    Given I set a pick up store using post code <Post Code>
    When I clear ALL the lists for the user
    And I clear the trolley
    And I create a list with exact list name as "<List Name>"
    And I search for the product <Product> in <Fulfillment> mode and store response
    And I add 2 available products with "<Quantity>" each from the store to "<Version>" list "<List Name>"
    Then I verify that the items saved to "<Version>" list "<List Name>" are unchecked
    And I add items to cart after selecting "<Quantity>" for every item from "<Version>" list "<List Name>"
    And I verify that the correct items with quantity from "<Version>" list "<List Name>" are added to the cart
    Examples:
      | Quantity   | List Name      | Fulfillment | Version | Post Code | Product  |
      | 1	   	   | AutoList Exact | Pickup      | V2      | 2204      | Milk     |
      | 1	   	   | AutoList Exact | Pickup      | V3      | 2204      | Bread    |

  Scenario Outline: Validate "<Version>" list to trolley sync scenario for a guest user in Delivery mode
    Given I pick a location at <Address> for delivery
    When I clear ALL the lists for the user
    And I clear the trolley
    And I create a list with exact list name as "<List Name>"
    And I search for the product <Product> in <Fulfillment> mode and store response
    And I add 2 available products with "<Quantity>" each from the store to "<Version>" list "<List Name>"
    Then I verify that the items saved to "<Version>" list "<List Name>" are unchecked
    And I add items to cart after selecting "<Quantity>" for every item from "<Version>" list "<List Name>"
    And I verify that the correct items with quantity from "<Version>" list "<List Name>" are added to the cart
    Examples:
      | Quantity   | List Name      | Fulfillment | Version | Address    | Product  |
      | 1	   	   | AutoList Exact | Online      | V2      | Darcy Road | Milk     |
      | 1	   	   | AutoList Exact | Online      | V3      | Darcy Road | Bread    |