Feature: Log In to Linux server

  Scenario: the user should be able to set up PuTTY client, log in to a Linux server with given IP address, pass a few
  commands and verify the output

    Given user starts PuTTY
    When user sets up PuTTY
    And user opens PuTTY
    And user logs In
    And user passes the commands
    Then user verifies the output

