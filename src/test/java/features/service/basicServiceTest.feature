@basic
Feature: Basic Requests Service Test

  Scenario: Initialise API Client
    Given API client is initialised

  Scenario: Able to get all users
    When I execute GET users request
    Then I receive 200 response code

  Scenario: Able to get user 1
    When I execute GET users/1 request
    Then I receive 200 response code

  Scenario Outline: Able to get valid response codes
    When I execute GET <endpoint> request
    Then I receive <code> response code
    Examples:
      | endpoint  | code |
      | users     | 200  |
      | not-users | 404  |