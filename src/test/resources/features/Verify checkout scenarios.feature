@REGRESSION
Feature: Checkout API scenarios

   Scenario Outline: Verify getting and setting of v3/checkout windows and Packaging preference for Pick up mode

     Given user continue to connect to apigee with login username as "SHOPPER_USERNAME6"
     When connection from user to apigee endpoint happens
     And I clear the trolley
     Then I search for the pickup stores in the postcode 2204
     Then I set the fulfilmentMethod to "Pickup" for the 1 store
     When I search for the product eggs in pickup mode and store response
     And I add the 2 available products with 1 each from the store to the V3 trolley
     And I get the available "<Mode>" windows for the logged in user with storeId or addressId
     Then I set the selected available pickup window for the logged in user
     Then I validate that user is able to select Reusable bags as packaging preference
     When I get the checkout summary details for the "<Mode>" order
     Then I validate the selected "<Mode>" store and selected windows
     Then I validate the product subtotal and total GST
     And I validate the packaging fee and preference
     Examples:
        |Mode|
        |Pickup|


   Scenario Outline:  Verify getting and setting of v3/checkout windows and Packaging preference for Delivery mode

      Given user continue to connect to apigee with login username as "<Username>"
      When connection from user to apigee endpoint happens
      And I search for the address "<lookupAddress>"
      And I select the "<position>" address as fulfilment address from matching addresses
      Then I make a request to fulfilment api with primary address id to set the address as fulfilment address
      When I search for the product eggs in online mode and store response
      And I add the 2 available products with 1 each from the store to the V3 trolley
      And I get the available "<Mode>" windows for the logged in user with storeId or addressId
      Then I set the selected available pickup window for the logged in user
      And I validate that user is able to select Reusable bags as packaging preference
      When I get the checkout summary details for the "<Mode>" order
      Then I validate the selected "<Mode>" store and selected windows
      Then I validate the product subtotal and total GST
      And I validate the packaging fee and preference
      Examples:
         |Mode| lookupAddress     | position | Username          |
         |Delivery| Charles street    | 1        |SHOPPER_USERNAME10 |
