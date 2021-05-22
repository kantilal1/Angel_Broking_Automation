@AngelLoginFunctionality
Feature: AngelBroking Login Test cases

  Background: Sheet configuration

  Scenario: TC001
    When Configure my data sheet path "./Angel_Broking/Angel/DriverFile/UIMAP.xlsx" and spread sheet name "WebLocator"
    Given Launch browser and enter orange crm url for ScenarioId "TC001"
    And The Below Step Description is "User enter the username and password"
    Then Enter value in "Username_Element" and "Password_Element" web
    And The Below Step Description is "Click on Login Button"
    Then Click on element "Login"
    And The Below Step Description is "Verify that the user redirect to the Dashboard"
    Then Get Text from element "Dashboard_Text" and "Dashboard"
    And The Below Step Description is "Click on Form_Processing_Tab"
    Then Click on element "Form_Processing_Tab" 
    And The Below Step Description is "Verify that Form Processing tab is Present"
    Then Verify that the element is Present "Form_Processing_Tab"   
     And The Below Step Description is "Verify that  Scrutiny,	EyeBall,	Refer To Maker,	NSDL Pan Check,	IMPS Check,	IPV Manual upload, Offline Journey tabs are Present inside Form Processing "
    Then Get Text from element "Scrutiny_Element" and "Scrutiny_Tab"
    Then Get Text from element "EyeBall_Element" and "EyeBall_Tab"
    Then Get Text from element "Refer_To_Maker_Element" and "Refer_To_Maker_Tab"
    Then Get Text from element "NSDL_Pan_Check_Element" and "NSDL_Pan_Check_Tab"
    Then Get Text from element "IMPS_Check_Element" and "IMPS_Check_Tab"
    Then Get Text from element "IPV_Manual_upload_Element" and "IPV_Manual_upload_Tab"
    Then Get Text from element "Offline_Journey_Element" and "Offline_Journey_Tab"
    #And The Below Step Description is "Close the Angel web application"
    #Then Close the Angel Web Application

  