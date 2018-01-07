Feature: User can successfully create transaction

  Scenario: User creates a transaction
    When the user creates an transaction
     And the transaction is successfully created
    Then the user receives status code of 201
