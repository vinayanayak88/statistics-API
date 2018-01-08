Feature: User can successfully get statistics

  Scenario: User gets statistics summary
   Given a statistics exists
    When the user gets the statistics
    Then the user receives statistics status code of 200
     And the retrieved statistics is correct