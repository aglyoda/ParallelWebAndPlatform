@title
Feature: Web browser tab displays correct title

  Scenario: Google displays correct title in web browser tab
    When User navigate to "https://www.google.com/" in web browser
    Then Correct title "Google" is displayed in web browser tab

  Scenario: Bing displays correct title in web browser tab
    When User navigate to "https://www.bing.com/" in web browser
    Then Correct title "Bing1" is displayed in web browser tab