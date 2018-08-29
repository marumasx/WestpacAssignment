######################################################################
# Asset: Westpac
# Sprint: | User Story ID:
# Author: sravan.marumamula@infosys.com
# Keywords Summary :
# Created on: 27/08/2017
######################################################################
Feature: The purpose of this feature is to check functionality of 'Currency Converter' feature

  #Background:
  #Given user opens the <Url> in <Browser>
  #When the user Navigates to Currency converter
  @tag1
  Scenario Outline: This scenarios is to check the error message when user tries to convert with out entering any amount
    Given user opens the <Url> in <Browser>
    When the user Navigates to Currency converter
    And the user clicks on Convert button with out entering any amount
    Then the user should see an error message

    Examples: 
      | Url                        | Browser |
      | https://www.westpac.co.nz/ | Chrome  |

  @tag2
  Scenario Outline: This scenarios is to check the error message when user tries to convert with out entering any amount
    Given user opens the <Url> in <Browser>
    When the user Navigates to Currency converter
    And the user selects required curreny from first <Convert from> dropdown
    And the user enters required amount in <Enter amount> field
    And user selects required curreny from second <Convert to> dropdown
    And the user clicks on convert button
    Then the user should see the converted value in reslut pane <Enter amount> Convert from <Convert from> Convert to <Convert to>

    Examples: 
      | Url                        | Browser | Enter amount | Convert from         | Convert to           |
      | https://www.westpac.co.nz/ | Chrome  |            1 | New Zealand Dollar   | United States Dollar |
      | https://www.westpac.co.nz/ | Chrome  |            1 | United States Dollar | New Zealand Dollar   |
      | https://www.westpac.co.nz/ | Chrome  |            1 | Pound Sterling       | New Zealand Dollar   |
      | https://www.westpac.co.nz/ | Chrome  |            1 | Swiss Franc          | Euro                 |
