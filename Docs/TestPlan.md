# Test Plan

**Author**: CS6300 Fall 2021 - Team 111

## 1 Testing Strategy

### 1.1 Overall strategy

The overall strategy is to cover the primary busines-logic (BL) of the application via unit and integration tests (implemented in JUnit) mapped to requirements to validate correctness, followed by series of black and white box tests to validate UI flows and interactions, these would constitute system (end-to-end) tests.  
Version release would consist of full JUnit regression followed by full set of UI black and white box tests. 
Regression that does not constitiute a version release will consist of two separate scenarios -  
- If busines logic was modified, JUnit regression will be performed to validate internal correctness
- If UI was modified (visual design or callbacks) - a set of smoke tests will be performed from a subset of white and black box tests comprising the expected area of effect.

The integration tests will cover the various interactions among busines logic classes outlined in the class diagram. Unit tests will validate the specific logic intensive functionalities withing the classes themselves (such as the ranking function)

The tests throughout the process will be carried out by the entire team under test lead coordination.

### 1.2 Test Selection

Test cases will be selected according to risk presented by logic complexity of the application area, as well as particle role that area plays.   
Areas that constitute busines logic will be covered by integration and unit tests with density dependent on the complexity of the area (and therefore according to risk factor)  
UI will be covered by an approximate uniform level of testing to validate the various interactions, adding more substatntial validation where user input is more significant (eg. job offer input is more error prone than clicking the jobs to compare). UI will be tested primarily by black box testing according to manual test descriptions, with the exception of persisted data validation which would consitute system level white box tests.

### 1.3 Adequacy Criterion

- Unit tests will cover any function in busines logic classes that is not "trivial" - ie not a getter/setter and has at least several lines of logic code
- all functional requirements will be covered by a formally written test (either on the UI or on the BL layer)
- UI screens and buttons will be covered by at least one black box test


### 1.4 Bug Tracking

Bugs will be tracked via bugs.md file. The file will contain a table with the following structure:

| Number | Reporter | Commit Hash | Description | Status | Fixed In |
| ------ | -------- | ----------- | ----------- | ------ | -------- |
|        |          |             |             |        |          |

The bug will begin with status **"Open"** and will transition to **"In Progress"** when someone starts working on it.  
The bug will the transition to **"Validation"** when it has been fixed and awaits regression tests.   
When validation completes it will become **"Closed"**  
`Fixed in` will capture the commit hash that is supposed to fixed the bug.  
`Commit Hash` is the first commit during which the bug was identified.  

### 1.5 Technology

We intend to use JUnit for the automated part of our tests.

## 2 Test Cases

### 1. Edit Job Details screen when no current job is defined
#### Steps:

| Num | Step                           | Expected Result                | Actual | P/F | Comments |
| --- | ------------------------------ | ------------------------------ | ------ | --- | -------- |
| 1   | clear the app database         | no entries in the db           |        |     |          |
| 2   | Open the application           | main menu is presented         |        |     |          |
|     |                                | compare job offers is disabled |        |     |          |
| 3   | Click "Enter/Edit Job Details" | Edit Job Details screen opens  |        |     |          |
|     |                                | All text fields are empty      |        |     |          |
|     |                                | Telework Days is 0             |        |     |          |

#### Result 
PASS / FAIL
#### Comments
           
           
### 2. Current job entry with incorrect values
#### Steps:

| Num | Step                           | Expected Result(s)             | Actual | P/F | Comments   |
| --- | ------------------------------ | ------------------------------ | ------ | --- | ---------- |
| 1   | Open the application           | main menu is presented         |        |     |            |
| 2   | Click "Enter/Edit Job Details" | Edit Job Details screen opens  |        |     | screenshot |
| 3   | Enter the following details:   | Details show up as typed       |        |     |            |
|     | Job Title: SW Eng 2            |                                |        |     |            |
|     | Company: Apple                 |                                |        |     |            |
|     | City: Cupertino                |                                |        |     |            |
|     | State: CA                      |                                |        |     |            |
|     | Cost of Living: 354            |                                |        |     |            |
|     | Salary: -1                     |                                |        |     |            |
|     | Bonus: 25000                   |                                |        |     |            |
|     | Telework Days: 2               |                                |        |     |            |
|     | Leave Time: 24                 |                                |        |     |            |
|     | Gym Allowance: 60              |                                |        |     |            |
| 4   | Click Save                     | Error message highlights       |        |     |            |
|     |                                | the salary with error          |        |     |            |
| 5   | change the following details:  | Details show up as typed       |        |     |            |
|     | Salary: 11500                  |                                |        |     |            |
|     | Bonus: -14                     |                                |        |     |            |
| 6   | Click Save                     | Error message highlights bonus |        |     |            |
| 7   | change the following details:  | Details show up as typed       |        |     |            |
|     | Bonus: 25000                   |                                |        |     |            |
|     | Leave Time: -71                |                                |        |     |            |
| 8   | Click Save                     | Error message highlights       |        |     |            |
|     |                                | the Leave Time with error      |        |     |            |
| 9   | change the following details:  | Details show up as typed       |        |     |            |
|     | Leave Time: 366                |                                |        |     |            |
| 10  | Click Save                     | Error message highlights       |        |     |            |
|     |                                | the Leave Time with error      |        |     |            |
| 11  | change the following details:  | Details show up as typed       |        |     |            |
|     | Leave Time: 20                 |                                |        |     |            |
|     | Job Title: (empty)             |                                |        |     |            |
| 12  | Click Save                     | Error message highlights       |        |     |            |
|     |                                | the Job Title with error       |        |     |            |
| 13  | change the following details:  | Details show up as typed       |        |     |            |
|     | Company: (empty)               |                                |        |     |            |
|     | Job Title: SW Eng 2            |                                |        |     |            |
| 14  | Click Save                     | Error message highlights       |        |     |            |
|     |                                | the Company with error         |        |     |            |
| 15  | change the following details:  | Details show up as typed       |        |     |            |
|     | Company: Apple                 |                                |        |     |            |
|     | City: (empty)                  |                                |        |     |            |
| 16  | Click Save                     | Error message highlights       |        |     |            |
|     |                                | the City with error            |        |     |            |
| 17  | change the following details:  | Details show up as typed       |        |     |            |
|     | Gym Allowance: 600             |                                |        |     |            |
|     | City: Cupertino                |                                |        |     |            |
| 18  | Click Save                     | Error message highlights       |        |     |            |
|     |                                | the gym allowance with error   |        |     |            |
| 19  | Click Cancel                   | Main menu is displayed         |        |     |            |
| 20  | Repeat steps 2                 | details match screenshot       |        |     |            |

#### Result 
PASS / FAIL
#### Comments


### 3. Current job entry with correct values
#### Steps:

| Num | Step                           | Expected Result(s)               | Actual | P/F | Comments        |
| --- | ------------------------------ | -------------------------------- | ------ | --- | --------------- |
| 1   | Open the application           | main menu is presented           |        |     |                 |
| 2   | Click "Enter/Edit Job Details" | Edit Job Details screen opens    |        |     |                 |
| 3   | Enter the following details:   | Details show up as typed         |        |     |                 |
|     | Job Title: SW Eng 1            |                                  |        |     | if the values   |
|     | Company: Microsoft             |                                  |        |     | already exist   |
|     | City: Mountain View            |                                  |        |     | increase salary |
|     | State: CA                      |                                  |        |     | by 1            |
|     | Cost of Living: 315            |                                  |        |     |                 |
|     | Salary: 115200                 |                                  |        |     |                 |
|     | Bonus: 25000                   |                                  |        |     |                 |
|     | Telework Days: 2               |                                  |        |     |                 |
|     | Leave Time: 24                 |                                  |        |     |                 |
|     | Gym Allowance: 60              |                                  |        |     |                 |
| 4   | Click Save                     | success message shows            |        |     |                 |
| 5   | Close the application          | application closes               |        |     |                 |
| 6   | repeat steps 1 and 2           | Above details show on the screen |        |     |                 |

 
#### Result 
PASS / FAIL

#### Comments  

### 4. Job Offer entry with incorrect values
#### Steps:

| Num | Step                          | Expected Result(s)                 | Actual | P/F | Comments |
| --- | ----------------------------- | ---------------------------------- | ------ | --- | -------- |
| 1   | Open the application          | main menu is presented             |        |     |          |
| 2   | Click "Enter Job Offers"      | Enter Offer Details screen opens   |        |     |          |
|     |                               | Compare button is disabled         |        |     |          |
| 3   | Enter the following details:  | Details show up as typed           |        |     |          |
|     | Job Title: SW Eng 2           |                                    |        |     |          |
|     | Company: Dummy                |                                    |        |     |          |
|     | City: Cupertino               |                                    |        |     |          |
|     | State: CA                     |                                    |        |     |          |
|     | Cost of Living: 354           |                                    |        |     |          |
|     | Salary: -1                    |                                    |        |     |          |
|     | Bonus: 25000                  |                                    |        |     |          |
|     | Telework Days: 2              |                                    |        |     |          |
|     | Leave Time: 24                |                                    |        |     |          |
|     | Gym Allowance: 60             |                                    |        |     |          |
| 4   | Click Save                    | Error message highlights           |        |     |          |
|     |                               | the salary with error              |        |     |          |
|     |                               | Compare button is disabled         |        |     |          |
|     |                               | Add button is disabled             |        |     |          |
| 5   | change the following details: | Details show up as typed           |        |     |          |
|     | Salary: 11500                 |                                    |        |     |          |
|     | Bonus: -14                    |                                    |        |     |          |
| 6   | Click Save                    | Error message highlights           |        |     |          |
|     |                               | Compare button is disabled         |        |     |          |
| 7   | change the following details: | Details show up as typed           |        |     |          |
|     | Bonus: 25000                  |                                    |        |     |          |
|     | Leave Time: -71               |                                    |        |     |          |
| 8   | Click Save                    | Error message highlights           |        |     |          |
|     |                               | the Leave Time with error          |        |     |          |
|     |                               | Compare button is disabled         |        |     |          |
| 9   | change the following details: | Details show up as typed           |        |     |          |
|     | Leave Time: 366               |                                    |        |     |          |
| 10  | Click Save                    | Error message highlights           |        |     |          |
|     |                               | the Leave Time with error          |        |     |          |
|     |                               | Compare button is disabled         |        |     |          |
| 11  | change the following details: | Details show up as typed           |        |     |          |
|     | Leave Time: 20                |                                    |        |     |          |
|     | Job Title: (empty)            |                                    |        |     |          |
| 12  | Click Save                    | Error message highlights           |        |     |          |
|     |                               | the Job Title with error           |        |     |          |
|     |                               | Compare button is disabled         |        |     |          |
| 13  | change the following details: | Details show up as typed           |        |     |          |
|     | Company: (empty)              |                                    |        |     |          |
|     | Job Title: SW Eng 2           |                                    |        |     |          |
| 14  | Click Save                    | Error message highlights           |        |     |          |
|     |                               | the Company with error             |        |     |          |
|     |                               | Compare button is disabled         |        |     |          |
| 15  | change the following details: | Details show up as typed           |        |     |          |
|     | Company: Dummy                |                                    |        |     |          |
|     | Gym Allowance: 600            |                                    |        |     |          |
| 16  | Click Save                    | Error message highlights           |        |     |          |
|     |                               | the Gym allowance with error       |        |     |          |
|     |                               | Compare button is disabled         |        |     |          |
| 17  | change the following details: | Details show up as typed           |        |     |          |
|     | Gym Allowance: 150            |                                    |        |     |          |
|     | City: (empty)                 |                                    |        |     |          |
| 18  | Click Save                    | Error message highlights           |        |     |          |
|     |                               | the City with error                |        |     |          |
|     |                               | Compare button is disabled         |        |     |          |
| 19  | Click Back                    | Main menu is displayed             |        |     |          |
| 20  | Inspect database content      | no job with Dummy company is found |        |     |          |



#### Result 
PASS / FAIL
#### Comments


### 5. Job Offer entry with correct values
#### Steps:

| Num | Step                         | Expected Result(s)                   | Actual | P/F | Comments |
| --- | ---------------------------- | ------------------------------------ | ------ | --- | -------- |
| 1   | Open the application         | main menu is presented               |        |     |          |
| 2   | Click "Enter Job Offers"     | Enter Offer Details screen opens     |        |     |          |
|     |                              | Compare button is disabled           |        |     |          |
| 3   | Enter the following details: | Details show up as typed             |        |     |          |
|     | Job Title: SW Eng 2          |                                      |        |     |          |
|     | Company: Apple               |                                      |        |     |          |
|     | City: Cupertino              |                                      |        |     |          |
|     | State: CA                    |                                      |        |     |          |
|     | Cost of Living: 354          |                                      |        |     |          |
|     | Salary: 125200               |                                      |        |     |          |
|     | Bonus: 35000                 |                                      |        |     |          |
|     | Telework Days: 2             |                                      |        |     |          |
|     | Leave Time: 30               |                                      |        |     |          |
|     | Gym Allowance: 140           |                                      |        |     |          |
| 4   | Click Save                   | success message shows                |        |     |          |
|     |                              | Save button becomes disabled         |        |     |          |
|     |                              | Compare button becomes enabled       |        |     |          |
| 5   | Inspect the database         | matching job is found in the db      |        |     |          |
| 6   | Click Add Offer              | All fields are cleared, Save enabled |        |     |          |
|     |                              | compare button becomes disabled      |        |     |          |
| 7   | Enter the following details: | Details show up as typed             |        |     |          |
|     | Job Title: QA Eng 2          |                                      |        |     |          |
|     | Company: Apple               |                                      |        |     |          |
|     | City: Cupertino              |                                      |        |     |          |
|     | State: CA                    |                                      |        |     |          |
|     | Cost of Living: 354          |                                      |        |     |          |
|     | Salary: 105200               |                                      |        |     |          |
|     | Bonus: 15000                 |                                      |        |     |          |
|     | Telework Days: 1             |                                      |        |     |          |
|     | Leave Time: 30               |                                      |        |     |          |
|     | Gym Allowance: 140           |                                      |        |     |          |
| 8   | Click Save                   | success message shows                |        |     |          |
|     |                              | Save button becomes disabled         |        |     |          |
|     |                              | Compare button becomes enabled       |        |     |          |
| 9   | Inspect the database         | matching job is found in the db      |        |     |          |
 
#### Result 
PASS / FAIL

#### Comments  

### 6. Job Offer compare with current job
#### Steps:

| Num | Step                           | Expected Result(s)                  | Actual | P/F | Comments |
| --- | ------------------------------ | ----------------------------------- | ------ | --- | -------- |
| 1   | Clear the app database         | no entries in db                    |        |     |          |
| 2   | Open the application           | main menu is presented              |        |     |          |
|     |                                | Compare job offers is disabled      |        |     |          |
| 3   | Click "Enter Job Offers"       | Enter Offer Details screen opens    |        |     |          |
|     |                                | Compare button is disabled          |        |     |          |
| 4   | Enter the following details:   | Details show up as typed            |        |     |          |
|     | Job Title: SW Eng 2            |                                     |        |     |          |
|     | Company: Apple                 |                                     |        |     |          |
|     | City: Cupertino                |                                     |        |     |          |
|     | State: CA                      |                                     |        |     |          |
|     | Cost of Living: 354            |                                     |        |     |          |
|     | Salary: 125200                 |                                     |        |     |          |
|     | Bonus: 35000                   |                                     |        |     |          |
|     | Telework Days: 2               |                                     |        |     |          |
|     | Leave Time: 30                 |                                     |        |     |          |
|     | Gym Allowance: 140             |                                     |        |     |          |
| 5   | Click Save                     | success message shows               |        |     |          |
|     |                                | Save button becomes disabled        |        |     |          |
|     |                                | Compare button becomes enabled      |        |     |          |
| 6   | Click compare button           | error message shows (no current)    |        |     |          |
| 7   | Click back button              | main menu is presented              |        |     |          |
| 8   | Click "Enter/Edit Job Details" | Edit Job Details screen opens       |        |     |          |
| 9   | Enter the following details:   | Details show up as typed            |        |     |          |
|     | Job Title: SW Eng 1            |                                     |        |     |          |
|     | Company: Microsoft             |                                     |        |     |          |
|     | City: Mountain View            |                                     |        |     |          |
|     | State: CA                      |                                     |        |     |          |
|     | Cost of Living: 315            |                                     |        |     |          |
|     | Salary: 115200                 |                                     |        |     |          |
|     | Bonus: 25000                   |                                     |        |     |          |
|     | Telework Days: 2               |                                     |        |     |          |
|     | Leave Time: 24                 |                                     |        |     |          |
|     | Gym Allowance: 60              |                                     |        |     |          |
| 10  | Click Save                     | success message shows               |        |     |          |
| 11  | Navigate to main menu          | Main menu is presented              |        |     |          |
| 12  | Click "Enter Job Offers"       | Enter Offer Details screen opens    |        |     |          |
|     |                                | Compare button is disabled          |        |     |          |
| 13  | Enter the following details:   | Details show up as typed            |        |     |          |
|     | Job Title: QA Eng 2            |                                     |        |     |          |
|     | Company: Apple                 |                                     |        |     |          |
|     | City: Cupertino                |                                     |        |     |          |
|     | State: CA                      |                                     |        |     |          |
|     | Cost of Living: 354            |                                     |        |     |          |
|     | Salary: 105200                 |                                     |        |     |          |
|     | Bonus: 15000                   |                                     |        |     |          |
|     | Telework Days: 2               |                                     |        |     |          |
|     | Leave Time: 30                 |                                     |        |     |          |
|     | Gym Allowance: 140             |                                     |        |     |          |
| 14  | Click Save                     | success message shows               |        |     |          |
|     |                                | Save button becomes disabled        |        |     |          |
|     |                                | Compare button becomes enabled      |        |     |          |
| 15  | Click compare button           | side by side comparison opens       |        |     |          |
|     |                                | both current and offer details show |        |     |          |

#### Result 
PASS / FAIL

#### Comments  


### 7. Adjust comparison weights test (incorrect and correct values)
#### Steps:

| Num | Step                             | Expected Result(s)                   | Actual | P/F | Comments |
| --- | -------------------------------- | ------------------------------------ | ------ | --- | -------- |
| 1   | Clear the app database           | no entries in db                     |        |     |          |
| 2   | Open the application             | main menu is presented               |        |     |          |
| 3   | click adjust comparison settings | comparison weights screen shows      |        |     |          |
|     |                                  | all values are 1                     |        |     |          |
| 4   | enter 1.1 in each of the fields  | value is rejected by the text fields |        |     |          |
| 5   | enter abc in each of the fields  | value is rejected by the text fields |        |     |          |
| 6   | enter -1 in each of the fields   | value is rejected by the text fields |        |     |          |
| 7   | enter 0 in each of the fields    | value is rejected by the text fields |        |     |          |
| 8   | enter the following:             | values show as entered               |        |     |          |
|     | Salary: 1                        |                                      |        |     |          |
|     | Bonus: 2                         |                                      |        |     |          |
|     | Telework: 3                      |                                      |        |     |          |
|     | Leave: 4                         |                                      |        |     |          |
|     | Gym: 5                           |                                      |        |     |          |
| 9   | Click Back                       | Main menu is presented               |        |     |          |
| 10  | repeat step 3                    | all values are 1                     |        |     |          |
| 11  | repeat step 8                    | values show as entered               |        |     |          |
| 12  | click save                       | success message is displayed         |        |     |          |
| 13  | close the application            | application is closed                |        |     |          |
| 14  | repeat steps 1 and 2             | values show as in step 8             |        |     |          |
                                                                                                                                                                                                                        

#### Result 
PASS / FAIL
#### Comments 

### 8. Compare offers functionality
#### Steps:

| Num | Step                                 | Expected Result(s)               | Actual | P/F | Comments |
| --- | ------------------------------------ | -------------------------------- | ------ | --- | -------- |
| 1   | Clear the app database               | no entries in db                 |        |     |          |
| 2   | open the app                         | main menu is displayed           |        |     |          |
|     |                                      | compare job offers is disabled   |        |     |          |
| 3   | Click "Enter Job Offers"             | Enter Offer Details screen opens |        |     |          |
|     |                                      | Compare button is disabled       |        |     |          |
| 4   | Enter the following details:         | Details show up as typed         |        |     |          |
|     | Job Title: QA Eng 2                  |                                  |        |     |          |
|     | Company: Apple                       |                                  |        |     |          |
|     | City: Cupertino                      |                                  |        |     |          |
|     | State: CA                            |                                  |        |     |          |
|     | Cost of Living: 250                  |                                  |        |     |          |
|     | Salary: 105200                       |                                  |        |     |          |
|     | Bonus: 15000                         |                                  |        |     |          |
|     | Telework Days: 2                     |                                  |        |     |          |
|     | Leave Time: 30                       |                                  |        |     |          |
|     | Gym Allowance: 140                   |                                  |        |     |          |
| 5   | Click Save                           | success message shows            |        |     |          |
|     |                                      | Save button becomes disabled     |        |     |          |
| 6   | Click back                           | main menu is displayed           |        |     |          |
|     |                                      | compare job offers is disabled   |        |     |          |
| 7   | click "Enter Job Offers"             | Enter Offer Details screen opens |        |     |          |
| 8   | Enter the following details:         | Details show up as typed         |        |     |          |
|     | Job Title: SW Eng 2                  |                                  |        |     |          |
|     | Company: Apple                       |                                  |        |     |          |
|     | City: Cupertino                      |                                  |        |     |          |
|     | State: CA                            |                                  |        |     |          |
|     | Cost of Living: 250                  |                                  |        |     |          |
|     | Salary: 125200                       |                                  |        |     |          |
|     | Bonus: 25000                         |                                  |        |     |          |
|     | Telework Days: 2                     |                                  |        |     |          |
|     | Leave Time: 30                       |                                  |        |     |          |
|     | Gym Allowance: 140                   |                                  |        |     |          |
| 9   | Click Save                           | success message shows            |        |     |          |
|     |                                      | Save button becomes disabled     |        |     |          |
| 10  | Click Back                           | Main menu is displayed           |        |     |          |
|     |                                      | Compare job offers is enabled    |        |     |          |
| 11  | Click compare job offers             | offers list is displayed         |        |     |          |
|     |                                      | option 1 is SW Eng 2, Apple      |        |     |          |
|     |                                      | option 2 is QA Eng 2, Apple      |        |     |          |
| 12  | select the two offers, click compare | side by side is displayed        |        |     |          |
|     |                                      | details match steps 4 and 7      |        |     |          |
| 13  | click compare more offers            | same list as in step 10 shows up |        |     |          |
| 14  | navigate back to "home"              | main menu is displayed           |        |     |          |
| 15  | Click "Enter/Edit Job Details"       | Edit Job Details screen opens    |        |     |          |
| 16  | Enter the following details:         | Details show up as typed         |        |     |          |
|     | Job Title: SW Eng 1                  |                                  |        |     |          |
|     | Company: Microsoft                   |                                  |        |     |          |
|     | City: Mountain View                  |                                  |        |     |          |
|     | State: CA                            |                                  |        |     |          |
|     | Cost of Living: 243                  |                                  |        |     |          |
|     | Salary: 115200                       |                                  |        |     |          |
|     | Bonus: 25000                         |                                  |        |     |          |
|     | Telework Days: 2                     |                                  |        |     |          |
|     | Leave Time: 24                       |                                  |        |     |          |
|     | Gym Allowance: 60                    |                                  |        |     |          |
| 17  | Click Save                           | success message shows            |        |     |          |
| 18  | navigate back to "home"              | main menu is displayed           |        |     |          |
|     |                                      | compare job offers is enabled    |        |     |          |
| 19  | Click compare job offers             | offers list is displayed         |        |     |          |
|     |                                      | 1) SW Eng 2, Apple               |        |     |          |
|     |                                      | 2) SW Eng 1, Microsoft (current) |        |     |          |
|     |                                      | 3) QA Eng 2, Apple               |        |     |          |
| 20  | Select all three                     | Compare button is disabled       |        |     |          |
| 21  | Select one box                       | Compare button is disabled       |        |     |          |
| 22  | Select 1 and 2, click compare        | side by side view displayed      |        |     |          |


#### Result 
PASS / FAIL

#### Comments 

### 9. Unit Tests [to be completed during deliverable #3]

#### Steps
| Num | Step | Expected Result(s) | Actual | P/F | Comments |
| --- | ---- | ------------------ | ------ | --- | -------- |
| 1   |      |                    |        |     |          |

#### Result 
PASS / FAIL

#### Comments 
