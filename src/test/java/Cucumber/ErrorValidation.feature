@tag
Feature: Error Validation


@ErrorValidation
Scenario Outline: Positive test for error validation
      
      Given I landed on Ecommerce page
      When  logged in with username <name> and password <pass>
      Then "Incorrect email or password." message is displayed
      
      
      Examples:
      |name              | pass          |productName     |
      |shaggy@gmail.com  | Shaggy@01234  | |