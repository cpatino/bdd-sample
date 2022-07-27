Feature: Validate result from math operations (left to right)

  Scenario Outline: Testing a math operation is easy!
    Given the math operation "<operation>"
    When resolving the operation
    Then the result must be "<result>"

    Examples:
      | operation    | result |
      | 1+1          | 2      |
      | 1-1          | 0      |
      | 3*2          | 6      |
      | 9/3          | 3      |
      | 8.0*3/5+2-10 | 3.2    |
      | +5-2*8/4.0   | 6      |
      | 5-2*8/4.0+   | 6      |

  Scenario: Testing not allowed cases like division by zero!
    Given the math operation "3+3*5-1/0"
    When resolving the operation
    Then the user receives the client error message "Cannot divide by zero"
