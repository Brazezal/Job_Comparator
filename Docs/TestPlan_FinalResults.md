# Test Plan - Final Results 

The Test Case1~9 are GUI test base on espresso

The Test Case 10 are unit test base on junit.

### 1. Edit Job Details screen when no current job is defined

#### Steps:

| Num  | Step                           | Expected Result                | Actual                                  | P/F  | Comments |
| ---- | ------------------------------ | ------------------------------ | --------------------------------------- | ---- | -------- |
| 1    | clear the app database         | no entries in the db           | Y                                       | P    |          |
| 2    | Open the application           | main menu is presented         | Y                                       | P    |          |
|      |                                | compare job offers is disabled | Y                                       | P    |          |
| 3    | Click "Enter/Edit Job Details" | Edit Job Details screen opens  | Y                                       | P    |          |
|      |                                | All text fields are empty      | State is AL which is default            | P    |          |
|      |                                | Telework Days is 0             | Telework Days is 0 as the default value | P    |          |

#### Result 

PASS

#### Comments

Good Job!


### 2. Current job entry with incorrect values

#### Steps:

| Num  | Step                           | Expected Result(s)             | Actual          | P/F  | Comments |
| ---- | ------------------------------ | ------------------------------ | --------------- | ---- | -------- |
| 1    | Open the application           | main menu is presented         | Y               | P    |          |
| 2    | Click "Enter/Edit Job Details" | Edit Job Details screen opens  | Button presents | P    |          |
| 3    | Enter the following details:   | Details show up as typed       |                 | P    |          |
|      | Job Title: SW Eng 2            |                                | Y               |      |          |
|      | Company: Apple                 |                                | Y               |      |          |
|      | City: Cupertino                |                                | Y               |      |          |
|      | State: "California (CA)"       |                                | Y               |      |          |
|      | Cost of Living: 354            |                                | Y               |      |          |
|      | Salary: -1                     |                                | Y               |      |          |
|      | Bonus: 12.22                   |                                | Y               |      |          |
|      | Telework Days: 2               |                                | Y               |      |          |
|      | Leave Time: 20                 |                                | Y               |      |          |
|      | Gym Allowance: 300             |                                | Y               |      |          |
| 4    | Click Save                     | Error message highlights       | Y               | P    |          |
|      |                                | the salary with error          | Y               |      |          |
| 5    | change the following details:  | Details show up as typed       | Y               | P    |          |
|      | Salary: 11500.22               |                                | Y               |      |          |
|      | Bonus: -14                     |                                | Y               |      |          |
| 6    | Click Save                     | Error message highlights bonus | Y               | P    |          |
| 7    | change the following details:  | Details show up as typed       | Y               |      |          |
|      | Bonus: 25000.22                |                                | Y               |      |          |
|      | Leave Time: -71                |                                | Y               |      |          |
| 8    | Click Save                     | Error message highlights       | Y               | P    |          |
|      |                                | the Leave Time with error      | Y               |      |          |
| 9    | change the following details:  | Details show up as typed       | Y               |      |          |
|      | Leave Time: 366                |                                | Y               |      |          |
| 10   | Click Save                     | Error message highlights       | Y               | P    |          |
|      |                                | the Leave Time with error      | Y               |      |          |
| 11   | change the following details:  | Details show up as typed       | Y               | P    |          |
|      | Leave Time: 20                 |                                | Y               |      |          |
|      | Job Title: (empty)             |                                | Y               |      |          |
| 12   | Click Save                     | Error message highlights       | Y               | P    |          |
|      |                                | the Job Title with error       | Y               |      |          |
| 13   | change the following details:  | Details show up as typed       | Y               | p    |          |
|      | Company: (empty)               |                                | Y               |      |          |
|      | Job Title: SW Eng 2            |                                | Y               |      |          |
| 14   | Click Save                     | Error message highlights       | Y               | p    |          |
|      |                                | the Company with error         | Y               |      |          |
| 15   | change the following details:  | Details show up as typed       | Y               | p    |          |
|      | Company: Apple                 |                                | Y               |      |          |
|      | City: (empty)                  |                                | Y               |      |          |
| 16   | Click Save                     | Error message highlights       | Y               | p    |          |
|      |                                | the City with error            | Y               |      |          |
| 17   | change the following details:  | Details show up as typed       | Y               | p    |          |
|      | Gym Allowance: 600             |                                | Y               |      |          |
|      | City: Cupertino                |                                | Y               |      |          |
| 18   | Click Save                     | Error message highlights       | Y               | p    |          |
|      |                                | the gym allowance with error   | Y               |      |          |
| 19   | Click Cancel                   | Main menu is displayed         | Y               | p    |          |
| 20   | Repeat steps 2                 | details match screenshot       | Y               | p    |          |

#### Result 

PASS 

#### Comments

Good Job!

To improve the app, error icons indicate where the incorrect input happens can be added.


### 3. Current job entry with correct values

#### Steps:

| Num  | Step                                                         | Expected Result(s)               | Actual | P/F  | Comments |
| ---- | ------------------------------------------------------------ | -------------------------------- | ------ | ---- | -------- |
| 1    | Open the application                                         | main menu is presented           | Y      | P    |          |
| 2    | Click "Enter/Edit Job Details"                               | Edit Job Details screen opens    | Y      | P    |          |
| 3    | Enter the following details:                                 | Details show up as typed         | Y      | P    |          |
|      | Job Title: SW Eng 1                                          |                                  | Y      |      |          |
|      | Company: Microsoft                                           |                                  | Y      |      |          |
|      | City: Mountain View                                          |                                  | Y      |      |          |
|      | State: California (CA)                                       |                                  | Y      |      |          |
|      | Cost of Living: 315                                          |                                  | Y      |      |          |
|      | Salary: 115200.22                                            |                                  | Y      |      |          |
|      | Bonus: 25000.22                                              |                                  | Y      |      |          |
|      | Telework Days: 2                                             |                                  | Y      |      |          |
|      | Leave Time: 24                                               |                                  | Y      |      |          |
|      | Gym Allowance: 60                                            |                                  | Y      |      |          |
| 4    | Click Save                                                   | success message shows            | Y      | P    |          |
| 5    | repeat steps 1 and 2                                         | Above details show on the screen | Y      | P    |          |
| 6    | Assert the data from database.<br />Compare it with the input | Data is consist with the input   | Y      | P    |          |


#### Result 

PASS 

#### Comments  

Good Job!

### 4. Job Offer entry with incorrect values

#### Steps:

| Num  | Step                          | Expected Result(s)                              | Actual | P/F  | Comments |
| ---- | ----------------------------- | ----------------------------------------------- | ------ | ---- | -------- |
| 1    | Open the application          | main menu is presented                          | Y      | P    |          |
| 2    | Click "Enter Job Offers"      | Enter Offer Details screen opens                | Y      | P    |          |
|      |                               | Compare button is disabled                      | Y      |      |          |
| 3    | Enter the following details:  | Details show up as typed                        | Y      | P    |          |
|      | Job Title: SW Eng 2           |                                                 | Y      |      |          |
|      | Company: Dummy                |                                                 | Y      |      |          |
|      | City: Cupertino               |                                                 | Y      |      |          |
|      | State: California (CA)        |                                                 | Y      |      |          |
|      | Cost of Living: 354           |                                                 | Y      |      |          |
|      | Salary: empty                 |                                                 | Y      |      |          |
|      | Bonus: 25000.22               |                                                 | Y      |      |          |
|      | Telework Days: 2              |                                                 | Y      |      |          |
|      | Leave Time: 24                |                                                 | Y      |      |          |
|      | Gym Allowance: 60             |                                                 | Y      |      |          |
| 4    | Click Save                    | Error message highlights:<br />"Can't be empty" | Y      | P    |          |
|      |                               | the salary with error                           | Y      |      |          |
|      |                               | Compare button is disabled                      | Y      |      |          |
| 5    | change the following details: | Details show up as typed                        | Y      |      |          |
|      | Salary: 11500                 |                                                 | Y      |      |          |
|      | Bonus: empty                  |                                                 | Y      |      |          |
| 6    | Click Save                    | Error message highlights                        | Y      | P    |          |
|      |                               | Compare button is disabled                      | Y      |      |          |
| 7    | change the following details: | Details show up as typed                        | Y      | P    |          |
|      | Bonus: 25000                  |                                                 | Y      |      |          |
|      | Leave Time: EMPTY             |                                                 | Y      |      |          |
| 8    | Click Save                    | Error message highlights                        | Y      | P    |          |
|      |                               | the Leave Time with error                       | Y      |      |          |
|      |                               | Compare button is disabled                      | Y      |      |          |
| 9    | change the following details: | Details show up as typed                        | Y      |      |          |
|      | Leave Time: 366               |                                                 | Y      |      |          |
| 10   | Click Save                    | Error message highlights                        | Y      | P    |          |
|      |                               | the Leave Time with error                       | Y      |      | IMPROVE  |
|      |                               | Compare button is disabled                      | Y      |      |          |
| 11   | change the following details: | Details show up as typed                        | Y      |      |          |
|      | Leave Time: 20                |                                                 | Y      |      |          |
|      | Job Title: (empty)            |                                                 | Y      |      |          |
| 12   | Click Save                    | Error message highlights                        | Y      | P    |          |
|      |                               | the Job Title with error                        | Y      |      |          |
|      |                               | Compare button is disabled                      | Y      |      |          |
| 13   | change the following details: | Details show up as typed                        | Y      |      |          |
|      | Company: (empty)              |                                                 | Y      |      |          |
|      | Job Title: SW Eng 2           |                                                 | Y      |      |          |
| 14   | Click Save                    | Error message highlights                        | Y      | P    |          |
|      |                               | the Company with error                          | Y      |      |          |
|      |                               | Compare button is disabled                      | Y      |      |          |
| 15   | change the following details: | Details show up as typed                        | Y      |      |          |
|      | Company: Dummy                |                                                 | Y      |      |          |
|      | Gym Allowance: 600            |                                                 | Y      |      |          |
| 16   | Click Save                    | Error message highlights                        | Y      | P    |          |
|      |                               | the Gym allowance with error                    | Y      |      |          |
|      |                               | Compare button is disabled                      | Y      |      |          |
| 17   | change the following details: | Details show up as typed                        | Y      |      |          |
|      | Gym Allowance: 150            |                                                 | Y      |      |          |
|      | City: (empty)                 |                                                 | Y      |      |          |
| 18   | Click Save                    | Error message highlights                        | Y      | P    |          |
|      |                               | the City with error                             | Y      |      |          |
|      |                               | Compare button is disabled                      | Y      |      |          |
| 19   | Click Back                    | Main menu is displayed                          | Y      | P    |          |
| 20   | Inspect database content      | no job with Dummy company is found              | Y      | P    |          |



#### Result 

 PASS

#### Comments

Good job!


### 5. Job Offer entry with correct values

#### Steps:

| Num  | Step                         | Expected Result(s)                   | Actual | P/F  | Comments |
| ---- | ---------------------------- | ------------------------------------ | ------ | ---- | -------- |
| 1    | Open the application         | main menu is presented               | Y      | P    |          |
| 2    | Click "Enter Job Offers"     | Enter Offer Details screen opens     | Y      | P    |          |
|      |                              | Compare button is disabled           | Y      |      |          |
| 3    | Enter the following details: | Details show up as typed             | Y      | P    |          |
|      | Job Title: SW Eng 2          |                                      | Y      |      |          |
|      | Company: Apple               |                                      | Y      |      |          |
|      | City: Cupertino              |                                      | Y      |      |          |
|      | State: California (CA)       |                                      | Y      |      |          |
|      | Cost of Living: 354          |                                      | Y      |      |          |
|      | Salary: 125200.22            |                                      | Y      |      |          |
|      | Bonus: 35000.22              |                                      | Y      |      |          |
|      | Telework Days: 2             |                                      | Y      |      |          |
|      | Leave Time: 30               |                                      | Y      |      |          |
|      | Gym Allowance: 140           |                                      | Y      |      |          |
| 4    | Click Save                   | success message shows                | Y      | P    |          |
|      |                              | Compare button becomes enabled       | Y      |      |          |
| 5    | Inspect the database         | matching job is found in the db      | Y      |      |          |
| 6    | Click Add Offer              | All fields are cleared, Save enabled | Y      | P    |          |
|      |                              | compare button becomes disabled      | Y      | P    |          |
| 7    | Enter the following details: | Details show up as typed             | Y      |      |          |
|      | Job Title: QA Eng 2          |                                      | Y      |      |          |
|      | Company: Apple               |                                      | Y      |      |          |
|      | City: Cupertino              |                                      | Y      |      |          |
|      | State: CA                    |                                      | Y      |      |          |
|      | Cost of Living: 354          |                                      | Y      |      |          |
|      | Salary: 105200               |                                      | Y      |      |          |
|      | Bonus: 15000                 |                                      | Y      |      |          |
|      | Telework Days: 1             |                                      | Y      |      |          |
|      | Leave Time: 30               |                                      | Y      |      |          |
|      | Gym Allowance: 140           |                                      | Y      |      |          |
| 8    | Click Save                   | success message shows                | Y      | P    |          |
|      |                              | Compare button becomes enabled       | Y      | P    |          |
| 9    | Inspect the database         | matching job is found in the db      | Y      | P    |          |

#### Result 

Pass

#### Comments  

To improve the app, "Save" to be disabled after clicking.



### 6. Job Offer compare with current job

#### Steps:

| Num  | Step                           | Expected Result(s)                  | Actual | P/F  | Comments           |
| ---- | ------------------------------ | ----------------------------------- | ------ | ---- | ------------------ |
| 1    | Clear the app database         | no entries in db                    | Y      | P    |                    |
| 2    | Open the application           | main menu is presented              | Y      | P    |                    |
|      |                                | Compare job offers is disabled      | Y      |      |                    |
| 3    | Click "Enter Job Offers"       | Enter Offer Details screen opens    | Y      | P    |                    |
|      |                                | Compare button is disabled          | Y      |      |                    |
| 4    | Enter the following details:   | Details show up as typed            | Y      | P    |                    |
|      | Job Title: SW Eng 2            |                                     | Y      |      |                    |
|      | Company: Apple                 |                                     | Y      |      |                    |
|      | City: Cupertino                |                                     | Y      |      |                    |
|      | State: California (CA)         |                                     | Y      |      |                    |
|      | Cost of Living: 354            |                                     | Y      |      |                    |
|      | Salary: 125200.22              |                                     | Y      |      |                    |
|      | Bonus: 35000.22                |                                     | Y      |      |                    |
|      | Telework Days: 2               |                                     | Y      |      |                    |
|      | Leave Time: 30                 |                                     | Y      |      |                    |
|      | Gym Allowance: 140             |                                     | Y      |      |                    |
| 5    | Click Save                     | success message shows               | Y      | P    |                    |
|      |                                | Compare button becomes enabled      | Y      | P    |                    |
| 6    | Click compare button           | error message shows (no current)    | Y      | P    |                    |
| 7    | Click back button              | main menu is presented              | Y      | P    |                    |
| 8    | Click "Enter/Edit Job Details" | Edit Job Details screen opens       | Y      | P    |                    |
| 9    | Enter the following details:   | Details show up as typed            | Y      | P    |                    |
|      | Job Title: SW Eng 1            |                                     | Y      |      |                    |
|      | Company: Microsoft             |                                     | Y      |      |                    |
|      | City: Mountain View            |                                     | Y      |      |                    |
|      | State: California (CA)         |                                     | Y      |      |                    |
|      | Cost of Living: 315            |                                     | Y      |      |                    |
|      | Salary: 115200.22              |                                     | Y      |      |                    |
|      | Bonus: 25000.22                |                                     | Y      |      |                    |
|      | Telework Days: 2               |                                     | Y      |      |                    |
|      | Leave Time: 24                 |                                     | Y      |      |                    |
|      | Gym Allowance: 60              |                                     | Y      |      |                    |
| 10   | Click Save                     | success message shows               | Y      | P    |                    |
| 11   | Navigate to main menu          | Main menu is presented              | Y      | P    |                    |
| 12   | Click "Enter Job Offers"       | Enter Offer Details screen opens    | Y      | P    |                    |
|      |                                | Compare button is disabled          | Y      |      |                    |
| 13   | Enter the following details:   | Details show up as typed            | Y      |      |                    |
|      | Job Title: QA Eng 2            |                                     | Y      |      |                    |
|      | Company: Apple                 |                                     | Y      |      |                    |
|      | City: Cupertino                |                                     | Y      |      |                    |
|      | State: California (CA)         |                                     | Y      |      |                    |
|      | Cost of Living: 354            |                                     | Y      |      |                    |
|      | Salary: 105200.22              |                                     | Y      |      |                    |
|      | Bonus: 15000.22                |                                     | Y      |      |                    |
|      | Telework Days: 2               |                                     | Y      |      |                    |
|      | Leave Time: 30                 |                                     | Y      |      |                    |
|      | Gym Allowance: 140             |                                     | Y      |      |                    |
| 14   | Click Save                     | success message shows               | Y      | P    |                    |
|      |                                | Compare button becomes enabled      | Y      | P    |                    |
| 15   | Click compare button           | side by side comparison opens       | Y      |      | Observed by tester |
|      |                                | both current and offer details show | Y      |      | Observed by tester |

#### Result 

 Pass

#### Comments  

Good job!

### 7. Empty input Test

#### Steps:

| Num  | Step                                                  | Expected Result(s)               | Actual          | P/F  | Comments |
| ---- | ----------------------------------------------------- | -------------------------------- | --------------- | ---- | -------- |
| 1    | Open the application                                  | main menu is presented           | Y               | P    |          |
| 2    | Click "Enter/Edit Job Details"                        | Edit Job Details screen opens    | Button presents | P    |          |
| 3    | Enter the following details:                          | Details show up as typed         |                 |      |          |
|      | Job Title: SW Eng 2                                   |                                  | Y               |      |          |
|      | Company: Apple                                        |                                  | Y               |      |          |
|      | City: Cupertino                                       |                                  | Y               |      |          |
|      | State: "California (CA)"                              |                                  | Y               |      |          |
|      | Cost of Living: 354                                   |                                  | Y               |      |          |
|      | Salary:  empty                                        |                                  | Y               |      |          |
|      | Bonus: 12.22                                          |                                  | Y               |      |          |
|      | Telework Days: 2                                      |                                  | Y               |      |          |
|      | Leave Time: 20                                        |                                  | Y               |      |          |
|      | Gym Allowance: 300                                    |                                  | Y               |      |          |
| 4    | Click Save                                            | Error message highlights         | Y               | P    |          |
|      |                                                       | the col with error               | Y               |      |          |
| 5    | change the following details:                         | Details show up as typed         | Y               |      |          |
|      | col: 245                                              |                                  | Y               |      |          |
|      | Bonus: empty                                          |                                  | Y               |      |          |
| 6    | Click Save                                            | Error message highlights bonus   | Y               | P    |          |
| 7    | change the following details:                         | Details show up as typed         | Y               |      |          |
|      | Bonus: 25000.22                                       |                                  | Y               |      |          |
|      | Leave Time: empty                                     |                                  | Y               |      |          |
| 8    | Click Save                                            | Error message highlights         | Y               | P    |          |
|      |                                                       | the Leave Time with error        | Y               |      |          |
| 9    | change the following details:                         | Details show up as typed         | Y               |      |          |
|      | Leave Time: 366                                       |                                  | Y               |      |          |
|      | gymAllownance: empty                                  |                                  |                 |      |          |
| 10   | Click Save                                            | Error message highlights         | Y               | P    |          |
|      |                                                       | the Leave Time with error        | Y               |      |          |
| 11   | change the following details:                         | Details show up as typed         | Y               |      |          |
|      | Leave Time: 20                                        |                                  | Y               |      |          |
|      | title: empty                                          |                                  |                 |      |          |
| 12   | Click Save                                            | Error message highlights         | Y               | P    |          |
|      |                                                       | the Leave Time with error        | Y               |      |          |
| 13   | change the following details:                         | Details show up as typed         | Y               |      |          |
|      | Title: "SW Eng 2"                                     |                                  | Y               |      |          |
|      | Company: empty                                        |                                  |                 |      |          |
| 14   | Click Save                                            | Error message highlights         | Y               | P    |          |
|      |                                                       | the Leave Time with error        | Y               |      |          |
| 15   | change the following details:                         | Details show up as typed         | Y               |      |          |
|      | Company: "Apple"                                      |                                  | Y               |      |          |
|      | City: ""                                              |                                  |                 |      |          |
| 16   | Click Save                                            | Error message highlights         | Y               | P    |          |
|      |                                                       | the Leave Time with error        | Y               |      |          |
| 17   | change the following details:                         | Details show up as typed         | Y               |      |          |
|      | City: "Cupertino"                                     |                                  | Y               |      |          |
|      | gymAllowance:600                                      |                                  |                 |      |          |
| 18   | Click Save                                            | Error message highlights         | Y               | P    |          |
|      |                                                       | the Leave Time with error        | Y               |      |          |
| 19   | change the following details:                         | Details show up as typed         | Y               |      |          |
|      | City: "Cupertino"                                     |                                  | Y               |      |          |
|      | gymAllowance:600                                      |                                  |                 |      |          |
| 20   | Click Save                                            | Error message highlights         | Y               | P    |          |
|      |                                                       | the Leave Time with error        | Y               |      |          |
| 21   | Click Cancel                                          | Main menu presents               |                 |      |          |
| 22   | Click "Enter/Edit Job Details"                        | Edit Job Details screen opens    |                 |      |          |
| 23   | Enter the following details:                          | Details show up as typed         |                 |      |          |
|      | Job Title: SW Eng 2                                   |                                  | Y               |      |          |
|      | Company: Apple                                        |                                  | Y               |      |          |
|      | City: Cupertino                                       |                                  | Y               |      |          |
|      | State: "California (CA)"                              |                                  | Y               |      |          |
|      | Cost of Living: 241                                   |                                  | Y               |      |          |
|      | Salary: 120000.22                                     |                                  | Y               |      |          |
|      | Bonus: 12.22                                          |                                  | Y               |      |          |
|      | Telework Days: 2                                      |                                  | Y               |      |          |
|      | Leave Time: 20                                        |                                  | Y               |      |          |
|      | Gym Allowance: 300                                    |                                  | Y               |      |          |
| 24   | Click Save                                            | success message shows            | Y               | T    |          |
| 25   | repeat steps 21 and 22                                | Above details show on the screen | Y               | T    |          |
| 26   | Assert the data shows up the same from the last input | True                             | True            | T    |          |

#### Result 

PASS

#### Comments 

GOOD JOB!

### 8. Compare offers functionality

#### Steps:

| Num  | Step                                 | Expected Result(s)               | Actual | P/F  | Comments                                       |
| ---- | ------------------------------------ | -------------------------------- | ------ | ---- | ---------------------------------------------- |
| 1    | Clear the app database               | no entries in db                 | Y      | P    |                                                |
| 2    | open the app                         | main menu is displayed           | Y      | P    |                                                |
|      |                                      | compare job offers is disabled   | Y      | P    |                                                |
| 3    | Click "Enter Job Offers"             | Enter Offer Details screen opens | Y      | P    |                                                |
|      |                                      | Compare button is disabled       | Y      | P    |                                                |
| 4    | Enter the following details:         | Details show up as typed         | Y      | P    |                                                |
|      | Job Title: QA Eng 2                  |                                  |        |      |                                                |
|      | Company: Apple                       |                                  |        |      |                                                |
|      | City: Cupertino                      |                                  |        |      |                                                |
|      | State: California (CA)               |                                  |        |      |                                                |
|      | Cost of Living: 250                  |                                  |        |      |                                                |
|      | Salary: 105200.22                    |                                  |        |      |                                                |
|      | Bonus: 15000.22                      |                                  |        |      |                                                |
|      | Telework Days: 2                     |                                  |        |      |                                                |
|      | Leave Time: 30                       |                                  |        |      |                                                |
|      | Gym Allowance: 140                   |                                  |        |      |                                                |
| 5    | Click Save                           | success message shows            | Y      | P    |                                                |
| 6    | Click back                           | main menu is displayed           | Y      | P    |                                                |
|      |                                      | compare job offers is disabled   | Y      | P    |                                                |
| 7    | click "Enter Job Offers"             | Enter Offer Details screen opens | Y      | P    |                                                |
| 8    | Enter the following details:         | Details show up as typed         | Y      |      |                                                |
|      | Job Title: SW Eng 2                  |                                  |        |      |                                                |
|      | Company: Apple                       |                                  |        |      |                                                |
|      | City: Cupertino                      |                                  |        |      |                                                |
|      | State: California (CA)               |                                  |        |      |                                                |
|      | Cost of Living: 250                  |                                  |        |      |                                                |
|      | Salary: 125200.22                    |                                  |        |      |                                                |
|      | Bonus: 25000.22                      |                                  |        |      |                                                |
|      | Telework Days: 2                     |                                  |        |      |                                                |
|      | Leave Time: 30                       |                                  |        |      |                                                |
|      | Gym Allowance: 140                   |                                  |        |      |                                                |
| 9    | Click Save                           | success message shows            | Y      | P    |                                                |
| 10   | Click Back                           | Main menu is displayed           | Y      | P    |                                                |
|      |                                      | Compare job offers is enabled    | Y      | P    |                                                |
| 11   | Click compare job offers             | offers list is displayed         | Y      | P    |                                                |
|      |                                      | option 1 is SW Eng 2, Apple      | Y      |      |                                                |
|      |                                      | option 2 is QA Eng 2, Apple      | Y      |      |                                                |
| 12   | select the two offers, click compare | side by side is displayed        | Y      | P    |                                                |
|      |                                      | details match steps 4 and 7      | Y      |      | Don't know how to check. Check by observation. |
| 13   | click compare more offers            | same list as in step 10 shows up | Y      | P    |                                                |
| 14   | navigate back to "home"              | main menu is displayed           | Y      | P    |                                                |
| 15   | Click "Enter/Edit Job Details"       | Edit Job Details screen opens    | Y      | P    |                                                |
| 16   | Enter the following details:         | Details show up as typed         | Y      |      |                                                |
|      | Job Title: SW Eng 1                  |                                  | Y      |      |                                                |
|      | Company: Microsoft                   |                                  | Y      |      |                                                |
|      | City: Mountain View                  |                                  | Y      |      |                                                |
|      | State: "California (CA)"             |                                  | Y      |      |                                                |
|      | Cost of Living: 243                  |                                  | Y      |      |                                                |
|      | Salary: 115200                       |                                  | Y      |      |                                                |
|      | Bonus: 25000                         |                                  | Y      |      |                                                |
|      | Telework Days: 2                     |                                  | Y      |      |                                                |
|      | Leave Time: 24                       |                                  | Y      |      |                                                |
|      | Gym Allowance: 60                    |                                  | Y      |      |                                                |
| 17   | Click Save                           | success message shows            | Y      | P    |                                                |
| 18   | navigate back to "home"              | main menu is displayed           | Y      | P    |                                                |
|      |                                      | compare job offers is enabled    | Y      | P    |                                                |
| 19   | Click compare job offers             | offers list is displayed         | Y      | P    |                                                |
|      |                                      | 1) SW Eng 2, Apple               | Y      |      |                                                |
|      |                                      | 2) SW Eng 1, Microsoft (current) | Y      |      |                                                |
|      |                                      | 3) QA Eng 2, Apple               | Y      |      |                                                |
| 20   | Select all three                     | Compare button is disabled       | Y      | P    |                                                |
| 21   | Select one box                       | Compare button is disabled       | Y      | P    |                                                |
| 22   | Select 1 and 2, click compare        | side by side view displayed      | Y      | P    | Observed by tester                             |


#### Result 

PASS

#### Comments 

Good Job!

### 9. Adjust comparison weights test (incorrect and correct values)

#### Steps:

| Num  | Step                                                  | Expected Result(s)                   | Actual                                                       | P/F  | Comments |
| ---- | ----------------------------------------------------- | ------------------------------------ | ------------------------------------------------------------ | ---- | -------- |
| 1    | Clear the app database                                | no entries in db                     | Y                                                            | P    |          |
| 2    | Open the application                                  | main menu is presented               | Y                                                            | P    |          |
| 3    | click adjust comparison settings                      | comparison weights screen shows      | Y                                                            | P    |          |
|      |                                                       | all values are 1                     | Y                                                            |      |          |
| 4    | enter 1.1 in each of the fields                       | value is rejected by the text fields | NO TEST:<br />incorrect input blocked by UI validation itself |      |          |
| 5    | enter abc in each of the fields                       | value is rejected by the text fields | NO TEST:<br />incorrect input blocked by UI validation itself |      |          |
| 6    | enter -1 in each of the fields                        | value is rejected by the text fields | NO TEST:<br />incorrect input blocked by UI validation itself |      |          |
| 7    | enter 0 in each of the fields                         | value is rejected by the text fields | Y                                                            | P    |          |
| 8    | enter the following:                                  | values show as entered               | Y                                                            | P    |          |
|      | Salary: 1                                             |                                      | Y                                                            |      |          |
|      | Bonus: 2                                              |                                      | Y                                                            |      |          |
|      | Telework: 3                                           |                                      | Y                                                            |      |          |
|      | Leave: 4                                              |                                      | Y                                                            |      |          |
|      | Gym: 5                                                |                                      | Y                                                            |      |          |
| 9    | Click Back                                            | Main menu is presented               | Y                                                            | P    |          |
| 10   | repeat step 3                                         | all values are 1                     | Y                                                            | P    |          |
| 11   | repeat step 8                                         | values show as entered               | Y                                                            | P    |          |
| 12   | click save                                            | success message is displayed         | Y                                                            | P    |          |
| 13   | repeat steps 1 and 2                                  | values show as in step 8             | Y                                                            | P    |          |
| 14   | Fetch data from the database. Compare with last input | values are equal                     | Y                                                            | P    |          |
|      |                                                       |                                      |                                                              |      |          |


#### Result 

PASS

#### Comments 

GOOD JOB!

### 10. Unit Tests [to be completed during deliverable #3]

#### Test Section 1: getRanking() in the Job class 

```
getRanking() will calculate out a score for ranking. It will receive two instances, which are Job instance and CompareSetting instance. Then return a double value through a specific function.

This test will adjust the varible in the instances in turn to test the return value is correct.
The default value are valided by UI and GUI tests and set as:
1.For the compareSetting instance:
	salaryWeight=1,BonusWeight=1,teleworkDaysWeight=1,leaveTimeWeight=1,gymAllowanceWeight=1 
2.For the Job instance:
	Salary=120000,Bonus=2000,teleworkDays=2,leaveTime=10,gymAllowance=300,col=241     
After each of the test step, all values will be reset to default.
```

| Num  | Step                                                         | Expected Result(s) | Actual | P/F  | Comments |
| ---- | ------------------------------------------------------------ | ------------------ | ------ | ---- | -------- |
| 1    | Assert the value from getRanking() to expected value         | T                  | T      | P    |          |
|      | Change only the value in compareSetting instance from now on |                    |        |      |          |
| 2    | Change salaryWeight from 1 to 0. Assert equal.               | T                  | T      |      |          |
| 3    | Change bonusWeight from 1 to 0. Assert equal.                | T                  | T      |      |          |
| 4    | Change teleworkDaysWeight from 1 to 0. Assert equal.         | T                  | T      |      |          |
| 5    | Change leaveTimeWeight from 1 to 0. Assert equal.            | T                  | T      |      |          |
| 6    | Change gymAllowanceWeight from 1 to 0. Assert equal.         | T                  | T      |      |          |
| 7    | Change all value except salaryWeight  to 0. Assert equal.    | T                  | T      |      |          |
| 8    | Change all value except bonusWeight to 0. Assert equal.      | T                  | T      |      |          |
| 9    | Change all value except teleworkDaysWeight to 0. Assert equal. | T                  | T      |      |          |
| 10   | Change all value except leaveTimeWeight to 0. Assert equal.  | T                  | T      |      |          |
| 11   | Change all value except gymAllowanceWeight to 0. Assert equal. | T                  | T      |      |          |
|      | Change only the value in Job instance from now on            |                    |        |      |          |
| 12   | Change salary from 120000 to 0. Assert equal.                | T                  | T      |      |          |
| 13   | Change bonus from 2000 to 0. Assert equal.                   | T                  | T      |      |          |
| 14   | Change teleworkDays from 2to 0. Assert equal.                | T                  | T      |      |          |
| 15   | Change leaveTime from 10 to 0. Assert equal.                 | T                  | T      |      |          |
| 16   | Change gymAllowance from 300 to 0. Assert equal.             | T                  | T      |      |          |
| 17   | Change col from 241 to 1. Assert equal                       | T                  | T      |      |          |
| 18   | Change col from 241 to 100. Assert equal                     | T                  | T      |      |          |
| 19   | Change all value to 0. Change col to 1. <br />Assert equal   | T                  | T      |      |          |
| 20   | Change all value to 0. Change col to 1. Change salary to120000.22.<br />Assert equal | T                  | T      |      |          |
| 21   | Change all value to 0. Change col to 1. Change bonus to 2000.22.<br />Assert equal | T                  | T      |      |          |
| 22   | Change all value to 0. Change col to 1. Change teleworkDays to 2.<br />Assert equal | T                  | T      |      |          |
| 23   | Change all value to 0. Change col to 1. Change leaveTime to 10 .Assert equal | T                  | T      |      |          |
| 24   | Change all value to 0. Change col to 1. Change gymAllowance to 10 .Assert equal | T                  | T      |      |          |
| 25   | Change  teleworkDays, leaveTime, gymAllowance to 0 . Change col to 1. Assert equal | T                  | T      |      |          |
| 26   | Change  bonus, leaveTime, gymAllowance to 0 . Change col to 1. Assert equal | T                  | T      |      |          |
| 27   | Change  bonus, teleWorkDays, gymAllowance to 0 . Change col to 1. Assert equal | T                  | T      |      |          |
| 28   | Change  bonus, teleWorkDays, leaveTime to 0 . Change col to 1. Assert equal | T                  | T      |      |          |
| 29   | Change  salary,  leaveTime, gymAllowance to 0 . Change col to 1. Assert equal | T                  | T      |      |          |
| 30   | Change  salary,  teleworkDays, gymAllowance to 0 . Change col to 1. Assert equal | T                  | T      |      |          |
| 31   | Change  salary,  teleworkDays, leaveTime to 0 . Change col to 1. Assert equal | T                  | T      |      |          |
| 32   | Change  salary,  bonus, gymAllowance to 0 . Change col to 1. Assert equal | T                  | T      |      |          |
| 33   | Change  salary,  bonus, leaveTime to 0 . Change col to 1. Assert equal | T                  | T      |      |          |
| 34   | Change  salary,  bonus, teleworkDays to 0 . Change col to 1. Assert equal | T                  | T      |      |          |
| 35   | Change  salary and bonus to 0 . Change col to 1. Assert equal | T                  | T      |      |          |
| 36   | Change  salary andteleworkDays to 0 . Change col to 1. Assert equal | T                  | T      |      |          |
| 37   | Change  salary and leaveTime to 0 . Change col to 1. Assert equal | T                  | T      |      |          |
| 38   | Change  salary and gymAllownance to 0 . Change col to 1. Assert equal | T                  | T      |      |          |
| 39   | Change  bonus and teleworkDays to 0 . Change col to 1. Assert equal | T                  | T      |      |          |
| 40   | Change  bonus and leaveTime to 0 . Change col to 1. Assert equal | T                  | T      |      |          |
| 41   | Change  bonus and  gymAllowance to 0 . Change col to 1. Assert equal | T                  | T      |      |          |
| 42   | Change  teleworkDays and leaveTime to 0 . Change col to 1. Assert equal | T                  | T      |      |          |
| 43   | Change  teleworkDays and gymAllowance to 0 . Change col to 1. Assert equal | T                  | T      |      |          |
| 44   | Change  leaveTime and gymAllowance to 0 . Change col to 1. Assert equal | T                  | T      |      |          |

#### Result 

PASS

#### Comments 

None

#### Test Section 2: isValid() in the Job class 

```
Job instance will pass value to isValid(). Then isValid will return a boolean value.
This section will change the value of the Job instance for testing.
The value will be reset back to default after each of the test.
```

| Num  | Step                                                         | Expected Result(s) | Actual | P/F  | Comments |
| ---- | ------------------------------------------------------------ | ------------------ | ------ | ---- | -------- |
| 1    | Change salary to Double.MAX_VALUE.                           | T                  | T      |      |          |
| 2    | Change salary to 0.                                          | T                  | T      |      |          |
| 3    | Change bonus to Double.MAX_VALUE.                            | T                  | T      |      |          |
| 4    | Change bonus to 0.                                           | T                  | T      |      |          |
| 5    | Change teleworkDays to 0.                                    | T                  | T      |      |          |
| 6    | Change teleworkDays to 5 .                                   | T                  | T      |      |          |
| 7    | Change teleworkDays to 7 .                                   | F                  | F      |      |          |
| 8    | Change leaveTime to 0 .                                      | T                  | T      |      |          |
| 9    | Change leaveTime to 365 .                                    | T                  | T      |      |          |
| 10   | Change leaveTime to 366 .                                    | F                  | F      |      |          |
| 11   | Change leaveTime to 367 .                                    | F                  | F      |      |          |
| 12   | Change gymAllowance to -300 .                                | F                  | F      |      |          |
| 13   | Change gymAllowance to  0 .                                  | T                  | T      |      |          |
| 14   | Change gymAllowance to  500.                                 | T                  | T      |      |          |
| 15   | Change gymAllowance to  501.                                 | F                  | F      |      |          |
| 16   | Change gymAllowance to  Double.MAX_VALUE.                    | F                  | F      |      |          |
| 17   | Change col to  0.                                            | F                  | F      |      |          |
| 18   | Change col to  1.                                            | T                  | T      |      |          |
| 19   | Change col to  Integer.MAX_VALUE.                            | T                  | T      |      |          |
| 20   | Change salary and bonus to 0. Change col to 100.             | T                  | T      |      |          |
| 21   | Change  bonus and teleworkDays to 0. Change col to 100.      | T                  | T      |      |          |
| 22   | Change bonus and leaveTime to 0. Change col to 100.          | T                  | T      |      |          |
| 23   | Change leaveTime and gymAllowance to 0. Change col to 100.   | T                  | T      |      |          |
| 24   | Change gymAllowance  to 700. Change teleworkDays to 7.Change col to 100. | F                  | F      |      |          |
| 25   | Change leaveTime to 368. Change gymAllowance to 700. Change col to 100. | F                  | F      |      |          |
| 26   | Change bonus to 0. Change gymAllowance to 700. Change leaveTime to 368.Change col to 100. | F                  | F      |      |          |
| 27   | Change salary and bonus to 0. Change gymAllowance to 700. Change leaveTime to 368.Change col to 100. | F                  | F      |      |          |
| 28   | Change bonus and leaveTime to 0. Change gymAllowance to 700. Change teleworkDays to 8.Change col to 100. | F                  | F      |      |          |
| 29   | Change bonus and gymAllowance to 0. Change leaveTime to 400. Change teleworkDays to 8.Change col to 100. | F                  | F      |      |          |
| 30   | Change salary and  teleworkDaysto 0. Change leaveTime to 400. Change  gymAllownance to 501. Change col to 100. | F                  | F      |      |          |
| 31   | Set  title to empty                                          | F                  | F      |      |          |
| 32   | Set company to empty.                                        | F                  | F      |      |          |
| 33   | Set city to empty.                                           | F                  | F      |      |          |
| 34   | Set state to empty.                                          | F                  | F      |      |          |



#### Result 

PASS

#### Comments 

None