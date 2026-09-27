@tag
Feature: Purchase the order from Ecommerce Website

Background:
Given I landed on Ecommerce page


@Regression
Scenario Outline: Positive test for submitting the order
      
      Given logged in with username <name> and password <pass>
      When I add product <productName> to cart
      And Checkout <productName> and submit the order
      Then "THANKYOU FOR THE ORDER." message is displayed on confimationPage
      
      
      Examples:
      |name              | pass          |productName     |
      |shaggy@gmail.com  | Shaggy@1234   |ADIDAS ORIGINAL |




