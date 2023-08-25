Feature: Send an email

  Scenario: the user adds a recipient in To and CC field, adds Subject, body Text, Attachment and sends an email

    Given user starts Outlook
    When opens a new email
    And adds To
    And adds Cc
    And adds Subject
    And adds Email text
    And adds Attachment
    Then sends an email