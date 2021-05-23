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
    And The Below Step Description is "Click on Scrutiny Button"
    Then Click on element "Scrutiny_Element"
    And The Below Step Description is "Verify that the user is redirect on the Scrutiny page upon making click on Scrutiny tab "
     Then Get Text from element "Scrutiny_Header_Element" and "Scrutiny_Page"
     And The Below Step Description is "Get the Choose to date"
     Then Get Text from element "Date_Field_Element"
     And The Below Step Description is "Click on Calendar"
     Then Click on specific element "Calendar_Tag" "0" 
     And The Below Step Description is "Select the date"
     Then Click on specific element "Dates_Number" "1"      
     And The Below Step Description is "Click on Select Status"
     Then Click on element "Select_Status_Element"
     And The Below Step Description is "Select Approved as status"
     Then Click on element "Status_Type_Element"
     And The Below Step Description is "Click on Search Button"
     Then Click on element "Search_Element"
     And The Below Step Description is "Click on Download Button"
     Then Click on element "Download_Element"
      And The Below Step Description is "Wait for the element"
     Then Wait for element
     Then Switch to parent window
     #And The Below Step Description is "Verify the file is downloaded successfully"
     #Then Verify that the element is Present "Success_Toggel"
      And The Below Step Description is "Get the Application and pass it"
      Then Get Text from list of element "Application_Number" "0"
      Then Pass the value in field "Enter_Party_Code" 
      And The Below Step Description is "Click on Search Button"
     Then Click on element "Search_Element"
     And The Below Step Description is "Open the particular application"
     Then Click on specific element "Application_Number" "0"  
     And The Below Step Description is "Switch to Child window"
     Then Switch to child window "1" 
     
     
      
    #And The Below Step Description is "Close the Angel web application"
    #Then Close the Angel Web Application

  