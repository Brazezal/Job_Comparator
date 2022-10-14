package edu.gatech.seclass.jobcompare6300;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import android.content.Intent;
import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import edu.gatech.seclass.jobcompare6300.dao.DBHelper;
import edu.gatech.seclass.jobcompare6300.ui.MainActivity;
import edu.gatech.seclass.jobcompare6300.vo.ComparisonSettings;
import edu.gatech.seclass.jobcompare6300.vo.Job;

//androidTestImplementation 'androidx.test:runner:1.2.0'
//    androidTestImplementation 'androidx.test:rules:1.2.0'
@RunWith(AndroidJUnit4.class)
public class guiTest {
    private Intent intent;
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    private MainActivity mActivity = null;
    private DBHelper helper;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityRule.getActivity();
        helper = new DBHelper(mActivity);


    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
        helper.cleanup();
    }

    @Test
    //TestCase1. Edit Job Details screen when no current job is defined
    public void testCase1() {
        //check main menu is presented
        View currentJobButton = mActivity.findViewById(R.id.enterEditJD);
        assertNotNull(currentJobButton);
        //check compare job offers is disabled
        onView(withId(R.id.compareJO)).check(matches(not(isEnabled())));
        //turn to enterJOb page and check all blanks are empty, but remoteDays is 1.
        onView(withId(R.id.enterEditJD)).perform(click());
        testEmptyJobPage();

    }

    @Test
    //TestCase2. Current job entry with incorrect values
    public void testCase2() {
        //check main menu is presented
        View currentJobButton = mActivity.findViewById(R.id.enterEditJD);
        assertNotNull(currentJobButton);

        onView(withId(R.id.enterEditJD)).perform(click());
        //input wrong values
        inputJobDetails("SW Eng 2", "Apple", "Cupertino", "California (CA)","354", "-1", "12.22", "2", "20", "300");

//        Espresso.closeSoftKeyboard();

        //test error message with illegal input and pop up message
        checkErrorMessage();
        // Change the following details:
        onView(withId(R.id.salary)).perform(clearText(), replaceText("11500.22"));
        onView(withId(R.id.bonus)).perform(clearText(), replaceText("-14"));

        //Test error message with illegal input and pop up message
        checkErrorMessage();
        // Change the following details:
        onView(withId(R.id.bonus)).perform(clearText(), replaceText("25000.22"));
        onView(withId(R.id.leaveTime)).perform(clearText(), replaceText("-71"));
        //Test error message with illegal input and pop up message
        checkErrorMessage();
        // Change the following details:
        onView(withId(R.id.leaveTime)).perform(clearText(), replaceText("366"));
        //Test error message with illegal input and pop up message
        checkErrorMessage();
        // Change the following details:
        onView(withId(R.id.leaveTime)).perform(clearText(), replaceText("20"));
        onView(withId(R.id.title)).perform(clearText(), replaceText(""));
        onView(withId(R.id.save)).perform(scrollTo()).perform(click()).perform(click());
        onView(withId(R.id.title)).perform(scrollTo()).check(matches(hasErrorText("Can't be empty")));
        //Test error message with illegal input and pop up message

        // Change the following details:
        onView(withId(R.id.title)).perform(clearText(), replaceText("SW Eng 2"));
        onView(withId(R.id.company)).perform(clearText(), replaceText(""));
        //Test error message with illegal input and pop up message
        onView(withId(R.id.save)).perform(scrollTo()).perform(click()).perform(click());
        onView(withId(R.id.company)).perform(scrollTo()).check(matches(hasErrorText("Can't be empty")));
        // Change the following details:
        onView(withId(R.id.city)).perform(clearText(), replaceText(""));
        onView(withId(R.id.company)).perform(clearText(), replaceText("Apple"));
        //Test error message with illegal input and pop up message
        onView(withId(R.id.save)).perform(scrollTo()).perform(click()).perform(click());
        onView(withId(R.id.city)).perform(scrollTo()).check(matches(hasErrorText("Can't be empty")));
        // Change the following details:
        onView(withId(R.id.city)).perform(clearText(), replaceText("Cupertino"));
        onView(withId(R.id.gymAllowance)).perform(clearText(), replaceText("600"));
        checkErrorMessage();

        //Test cancel and return to mainmenu
        onView(withId(R.id.Cancel)).perform(scrollTo()).perform(click());
        assertNotNull(currentJobButton);
        //Enter the Current Job Page and check screen shot
        onView(withId(R.id.enterEditJD)).perform(click());
//        testEmptyJobPage();
        testEmptyJobPage();

    }


    @Test
    //Test Case3: 3. Current job entry with correct values
    public void testCase3() {

        //check main menu is presented
        View currentJobButton = mActivity.findViewById(R.id.enterEditJD);
        assertNotNull(currentJobButton);
        //Enter CurrentJob Page
        onView(withId(R.id.enterEditJD)).perform(click());

        //Input the correct value again
        inputJobDetails("SW Eng 1", "Microsoft", "Mountain View", "California (CA)","315", "115200.22", "25000.22", "2", "24", "60");

        Espresso.closeSoftKeyboard();

        //check success message
        checkSuccessMessage();
        onView(withId(R.id.Cancel)).perform(scrollTo()).perform(click());
        onView(withId(R.id.enterEditJD)).perform(click());

        //check fetched Data
        onView(withId(R.id.title)).check(matches(withText("SW Eng 1")));
        onView(withId(R.id.company)).check(matches(withText("Microsoft")));
        onView(withId(R.id.city)).check(matches(withText("Mountain View")));
        onView(withId(R.id.state)).check(matches(withSpinnerText(containsString("California (CA)"))));
        onView(withId(R.id.costOfLiving)).check(matches(withText("315")));
        onView(withId(R.id.salary)).check(matches(withText("115200.22")));
        onView(withId(R.id.bonus)).check(matches(withText("25000.22")));
        onView(withId(R.id.remoteDays)).check(matches(withSpinnerText("2")));
        onView(withId(R.id.leaveTime)).check(matches(withText("24")));
        onView(withId(R.id.gymAllowance)).check(matches(withText("60.0")));

        List<Job> jobList=helper.getAllJobs();
        assertEquals(1,jobList.size());
        Job recentJob = jobList.get(jobList.size() - 1);
        assertEquals("SW Eng 1",recentJob.getTitle());
        assertEquals("Microsoft",recentJob.getCompany());
        assertEquals("Mountain View",recentJob.getLocation().getCity());
        assertEquals("CA",recentJob.getLocation().getState());
        assertEquals(315,recentJob.getLocation().getCostOfLiving());
        assertEquals(115200.22,recentJob.getYearlySalary().getAmount(),0.01);
        assertEquals(25000.22,recentJob.getYearlyBonus().getAmount(),0.01);
        assertEquals(2,recentJob.getAllowedWeeklyTWDays());
        assertEquals(24,recentJob.getLeaveTime());
        assertEquals(60.00,recentJob.getGymAllowance().getAmount(),0.01);

    }


    @Test
    //Job Offer entry with incorrect values
    public void testCase4(){
//        helper.cleanup();
        //check main menu is presented
        View jobOfferButton = mActivity.findViewById(R.id.enterJO);
        assertNotNull(jobOfferButton);

        //check compare to current button is diabled
        onView(withId(R.id.enterJO)).perform(click());
        onView(withId(R.id.compareCurrent)).check(matches(not(isEnabled())));

        //input wrong values
        inputJobDetails("SW Eng 2", "Dummy", "Cupertino", "California (CA)","354", "", "25000.22", "2", "24", "60");

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.save)).perform(scrollTo()).perform(click());
        onView(withId(R.id.salary)).perform(scrollTo()).check(matches(hasErrorText("Can't be empty")));

        // Change the following details:
        onView(withId(R.id.salary)).perform(clearText(), replaceText("11500.22"));
        onView(withId(R.id.bonus)).perform(clearText(), replaceText(""));

        //Test error message with illegal input and pop up message
        onView(withId(R.id.save)).perform(scrollTo()).perform(click());
        onView(withId(R.id.bonus)).perform(scrollTo()).check(matches(hasErrorText("Can't be empty")));
        // Change the following details:
        onView(withId(R.id.bonus)).perform(clearText(), replaceText("25000.22"));
        onView(withId(R.id.leaveTime)).perform(clearText(), replaceText(""));
        //Test error message with illegal input and pop up message
        onView(withId(R.id.save)).perform(scrollTo()).perform(click());
        onView(withId(R.id.leaveTime)).perform(scrollTo()).check(matches(hasErrorText("Can't be empty")));
        // Change the following details:
        onView(withId(R.id.leaveTime)).perform(clearText(), replaceText("366"));
        //Test error message with illegal input and pop up message
        onView(withId(R.id.save)).perform(scrollTo()).perform(click());
        onView(withId(R.id.leaveTime)).perform(scrollTo()).check(matches(hasErrorText("Error, please input number less than 365")));
        // Change the following details:
        onView(withId(R.id.leaveTime)).perform(clearText(), replaceText("20"));
        onView(withId(R.id.title)).perform(clearText(), replaceText(""));
        //Test error message with illegal input and pop up message
        onView(withId(R.id.save)).perform(scrollTo()).perform(click()).perform(click());
        onView(withId(R.id.title)).perform(scrollTo()).check(matches(hasErrorText("Can't be empty")));
        // Change the following details:
        onView(withId(R.id.title)).perform(clearText(), replaceText("SW Eng 2"));
        onView(withId(R.id.company)).perform(clearText(), replaceText(""));
        //Test error message with illegal input and pop up message
        onView(withId(R.id.save)).perform(scrollTo()).perform(click()).perform(click());
        onView(withId(R.id.company)).perform(scrollTo()).check(matches(hasErrorText("Can't be empty")));
        // Change the following details:
        onView(withId(R.id.gymAllowance)).perform(clearText(), replaceText("600"));
        onView(withId(R.id.company)).perform(clearText(), replaceText("Dummy"));
        //Test error message with illegal input and pop up message
        onView(withId(R.id.save)).perform(scrollTo()).perform(click());
        onView(withId(R.id.gymAllowance)).perform(scrollTo()).check(matches(hasErrorText("Error, please input number less than 500")));
        // Change the following details:
        onView(withId(R.id.gymAllowance)).perform(clearText(), replaceText("150"));
        onView(withId(R.id.city)).perform(clearText(), replaceText(""));

        onView(withId(R.id.save)).perform(scrollTo()).perform(click());
        onView(withId(R.id.city)).perform(scrollTo()).check(matches(hasErrorText("Can't be empty")));

        //Test cancel and return to mainmenu
        onView(withId(R.id.back)).perform(scrollTo()).perform(click());
        assertNotNull(jobOfferButton);
        //Inspect database content
        List<Job> jobList=helper.getAllJobs();
        for (Job job: jobList){
            assertNotEquals("Dummy",job.getCompany());
        }


    }
    @Test
    //TestCase5:Job Offer entry with correct values
    public void testCase5(){
        //check main menu is presented
        View jobOfferButton = mActivity.findViewById(R.id.enterJO);
        assertNotNull(jobOfferButton);

        //check compare to current button is disabled
        onView(withId(R.id.enterJO)).perform(click());
        onView(withId(R.id.compareCurrent)).check(matches(not(isEnabled())));
        //input correct values case1
        inputJobDetails("SW Eng 2", "Apple", "Cupertino", "California (CA)","354", "125200.22", "35000.22", "2", "30", "140");
        Espresso.closeSoftKeyboard();

        //check success message and compareCurrent button enabled, save button disabled
        checkSuccessMessage_Offer();
        onView(withId(R.id.compareCurrent)).check(matches(isEnabled()));

        //Inspect database
        List<Job> jobList=helper.getAllJobs();
        assertEquals(1,jobList.size());
        Job recentJob = jobList.get(jobList.size() - 1);
        assertEquals("SW Eng 2",recentJob.getTitle());
        assertEquals("Apple",recentJob.getCompany());
        assertEquals("Cupertino",recentJob.getLocation().getCity());
        assertEquals("CA",recentJob.getLocation().getState());
        assertEquals(354,recentJob.getLocation().getCostOfLiving());
        assertEquals(125200.22,recentJob.getYearlySalary().getAmount(),0.01);
        assertEquals(35000.22,recentJob.getYearlyBonus().getAmount(),0.01);
        assertEquals(2,recentJob.getAllowedWeeklyTWDays());
        assertEquals(30,recentJob.getLeaveTime());
        assertEquals(140.00,recentJob.getGymAllowance().getAmount(),0.01);

        //Add another offer
        onView(withId(R.id.addOffer)).perform(click());
        testEmptyJobPage();
        onView(withId(R.id.compareCurrent)).check(matches(not(isEnabled())));

        //input correct values case2
        inputJobDetails("QA Eng 2", "Apple", "Cupertino", "California (CA)","354", "105200.22", "15000.22", "1", "30", "140");

        //check success message and compareCurrent button enabled
        Espresso.closeSoftKeyboard();
        checkSuccessMessage_Offer();
        // compareCurrent enabled
        onView(withId(R.id.compareCurrent)).check(matches(isEnabled()));
        onView(withId(R.id.back)).perform(click());
        assertNotNull(jobOfferButton);

        //Inspect database for the second input
        jobList=helper.getAllJobs();
        assertEquals(2,jobList.size());
        recentJob = jobList.get(jobList.size()-1 );
        assertEquals("QA Eng 2",recentJob.getTitle());
        assertEquals("Apple",recentJob.getCompany());
        assertEquals("Cupertino",recentJob.getLocation().getCity());
        assertEquals("CA",recentJob.getLocation().getState());
        assertEquals(354,recentJob.getLocation().getCostOfLiving());
        assertEquals(105200.22,recentJob.getYearlySalary().getAmount(),0.01);
        assertEquals(15000.22,recentJob.getYearlyBonus().getAmount(),0.01);
        assertEquals(1,recentJob.getAllowedWeeklyTWDays());
        assertEquals(30,recentJob.getLeaveTime());
        assertEquals(140.00,recentJob.getGymAllowance().getAmount(),0.01);



    }
    @Test
    //TestCase6: Job Offer compare with current job
    public void testCase6(){
        //check main menu is presented
        View jobOfferButton = mActivity.findViewById(R.id.enterJO);
        assertNotNull(jobOfferButton);

        //check compareOffers is disabled
        onView(withId(R.id.compareJO)).check(matches(not(isEnabled())));
        //Enter Job Offer page and check compare button is disabled
        onView(withId(R.id.enterJO)).perform(click());
        onView(withId(R.id.compareCurrent)).check(matches(not(isEnabled())));

        //input job offer
        inputJobDetails("SW Eng 2", "Apple", "Cupertino","California (CA)", "354", "125200.22", "35000.22", "2", "30", "140");
        //Save test and compare button diabled without currentJOb
        Espresso.closeSoftKeyboard();
        checkSuccessMessage_Offer();
//        onView(withId(R.id.save)).check(matches(not(isEnabled())));   // App need this function

        onView(withId(R.id.compareCurrent)).check(matches(isEnabled())); //No current job will disable compareCurrent Button
//        onView(withId(R.id.compareCurrent)).perform(click());
        //Return to mainmenu
        onView(withId(R.id.back)).perform(click());
        onView(withId(R.id.enterEditJD)).perform(click());

        //input current job details
        inputJobDetails("SW Eng 1", "Microsoft", "Mountain View","California (CA)", "315", "115200.22", " 25000.22", "2", "24", "60");
        checkSuccessMessage();
        onView(withId(R.id.Cancel)).perform(click());
        onView(withId(R.id.enterJO)).perform(click());
        onView(withId(R.id.compareCurrent)).check(matches(not(isEnabled())));
        //input second offer
        inputJobDetails("QA Eng 2", "Apple", "Cupertino", "California (CA)","354", "105200.22", " 15000.22", "2", "30", "140");
        checkSuccessMessage_Offer();
        //Save button shoud be disabled
        onView(withId(R.id.compareCurrent)).check(matches(isEnabled()));
        onView(withId(R.id.compareCurrent)).perform(scrollTo()).perform(click());
        assertNotNull(R.id.save);
        assertNotNull(R.id.back);
        //Need to add assertion for side by side view



    }

    @Test
    //TestCase7: For testing input empty numbers.
    public void testCase7(){
        View currentJobButton = mActivity.findViewById(R.id.enterEditJD);
        assertNotNull(currentJobButton);

        onView(withId(R.id.enterEditJD)).perform(click());
        //input wrong values with empty col
        inputJobDetails("SW Eng 2", "Apple", "Cupertino", "California (CA)","", "120000.22", "12.22", "2", "20", "300");

//        Espresso.closeSoftKeyboard();

        //test error message with illegal input and pop up message
        onView(withId(R.id.save)).perform(scrollTo()).perform(click());
        onView(withId(R.id.costOfLiving)).perform(scrollTo()).check(matches(hasErrorText("Can't be empty")));
        // Change the following details:
        onView(withId(R.id.costOfLiving)).perform(clearText(), replaceText("245"));
        onView(withId(R.id.bonus)).perform(clearText(), replaceText(""));

        //Test error message with illegal input and pop up message
        onView(withId(R.id.save)).perform(scrollTo()).perform(click());
        onView(withId(R.id.bonus)).perform(scrollTo()).check(matches(hasErrorText("Can't be empty")));
        // Change the following details:
        onView(withId(R.id.bonus)).perform(clearText(), replaceText("25000.22"));
        onView(withId(R.id.leaveTime)).perform(clearText(), replaceText(""));
        //Test error message with illegal input and pop up message
        onView(withId(R.id.save)).perform(scrollTo()).perform(click());
        onView(withId(R.id.leaveTime)).perform(scrollTo()).check(matches(hasErrorText("Can't be empty")));
        // Change the following details:
        onView(withId(R.id.leaveTime)).perform(clearText(), replaceText("60"));
        onView(withId(R.id.gymAllowance)).perform(clearText(), replaceText(""));
        //Test error message with illegal input and pop up message
        onView(withId(R.id.save)).perform(scrollTo()).perform(click());
        onView(withId(R.id.gymAllowance)).perform(scrollTo()).check(matches(hasErrorText("Can't be empty")));
        // Change the following details:
        onView(withId(R.id.gymAllowance)).perform(clearText(), replaceText("20"));
        onView(withId(R.id.title)).perform(clearText(), replaceText(""));
        //Test error message with illegal input and pop up message
        onView(withId(R.id.save)).perform(scrollTo()).perform(click());
        onView(withId(R.id.title)).perform(scrollTo()).check(matches(hasErrorText("Can't be empty")));;
        // Change the following details:
        onView(withId(R.id.title)).perform(clearText(), replaceText("SW Eng 2"));
        onView(withId(R.id.company)).perform(clearText(), replaceText(""));
        //Test error message with illegal input and pop up message
        onView(withId(R.id.save)).perform(scrollTo()).perform(click());
        onView(withId(R.id.company)).perform(scrollTo()).check(matches(hasErrorText("Can't be empty")));
        // Change the following details:
        onView(withId(R.id.city)).perform(clearText(), replaceText(""));
        onView(withId(R.id.company)).perform(clearText(), replaceText("Apple"));
        //Test error message with illegal input and pop up message
        onView(withId(R.id.save)).perform(scrollTo()).perform(click());
        onView(withId(R.id.city)).perform(scrollTo()).check(matches(hasErrorText("Can't be empty")));
        // Change the following details:
        onView(withId(R.id.city)).perform(clearText(), replaceText("Cupertino"));
        onView(withId(R.id.gymAllowance)).perform(clearText(), replaceText("600"));
        checkErrorMessage();
        //Test cancel and return to mainmenu
        onView(withId(R.id.Cancel)).perform(scrollTo()).perform(click());
        assertNotNull(currentJobButton);
        //Enter the Current Job Page and check screen shot
        onView(withId(R.id.enterEditJD)).perform(click());
//        testEmptyJobPage();
        testEmptyJobPage();
        inputJobDetails("SW Eng 2", "Apple", "Cupertino", "California (CA)","241", "120000.22", "12.22", "2", "20", "300");
        checkSuccessMessage();
        onView(withId(R.id.Cancel)).perform(scrollTo()).perform(click());
        assertNotNull(currentJobButton);
        //Enter the Current Job Page and check screen shot
        onView(withId(R.id.enterEditJD)).perform(click());

        //check fetched Data
        onView(withId(R.id.title)).check(matches(withText("SW Eng 2")));
        onView(withId(R.id.company)).check(matches(withText("Apple")));
        onView(withId(R.id.city)).check(matches(withText("Cupertino")));
        onView(withId(R.id.state)).check(matches(withSpinnerText(containsString("California (CA)"))));
        onView(withId(R.id.costOfLiving)).check(matches(withText("241")));
        onView(withId(R.id.salary)).check(matches(withText("120000.22")));
        onView(withId(R.id.bonus)).check(matches(withText("12.22")));
        onView(withId(R.id.remoteDays)).check(matches(withSpinnerText("2")));
        onView(withId(R.id.leaveTime)).check(matches(withText("20")));
        onView(withId(R.id.gymAllowance)).check(matches(withText("300.0")));



    }
    @Test
    //TestCase8: Compare offers functionality
    public void testCase8() {
        //check main menu is presented
        View jobOfferButton = mActivity.findViewById(R.id.enterJO);
        assertNotNull(jobOfferButton);
        //check compareOffers is disabled
        onView(withId(R.id.compareJO)).check(matches(not(isEnabled())));
        //Enter Job Offer page and check compare button is disabled
        onView(withId(R.id.enterJO)).perform(click());
        onView(withId(R.id.compareCurrent)).check(matches(not(isEnabled())));
        inputJobDetails("QA Eng 2","Apple","Cupertino","California (CA)","250","105200.22","15000.22","2","30","140");
        checkSuccessMessage_Offer();
//        //Save button should be disabled
//        onView(withId(R.id.save)).check(matches(not(isEnabled())));
        //click back to mainmenu
        onView(withId(R.id.back)).perform(click());
        onView(withId(R.id.compareJO)).check(matches(not(isEnabled())));
        //Input second job Offer
        onView(withId(R.id.enterJO)).perform(click());
        onView(withId(R.id.compareCurrent)).check(matches(not(isEnabled())));
        inputJobDetails("SW Eng 2","Apple","Cupertino","California (CA)","250","125200.22","25000.22","2","30","140");
        checkSuccessMessage_Offer();
//        //Save button should be disabled
//        onView(withId(R.id.save)).check(matches(not(isEnabled())));

        //click back to mainmenu and compare offer button enabled.
        onView(withId(R.id.back)).perform(click());
        assertNotNull(jobOfferButton);
        onView(withId(R.id.compareJO)).check(matches(isEnabled()));
        //click compare offer button and check both
        onView(withId(R.id.compareJO)).perform(click());
        onView(withId(1)).perform(click());
        onView(withId(2)).perform(click());
        onView(withId(R.id.save)).perform(click());
        //Need to check the information from bothsides

        //Back to home No button to go back
        onView(withId(R.id.back)).perform(click());


        //Input the third job Offer and check success
        onView(withId(R.id.enterJO)).perform(click());
        onView(withId(R.id.compareCurrent)).check(matches(not(isEnabled())));
        inputJobDetails("SW Eng 1","Microsoft","Mountain View","California (CA)","243","115200.22","25000.22","2","24","60");
        checkSuccessMessage_Offer();
        //Return back to mainmenu and enter compare page
        onView(withId(R.id.back)).perform(click());
        assertNotNull(jobOfferButton);
        onView(withId(R.id.compareJO)).check(matches(isEnabled()));
        onView(withId(R.id.compareJO)).perform(click());
        //Select all three
        onView(withId(1)).perform(click());
        onView(withId(2)).perform(click());
        onView(withId(3)).perform(click());
        checkErrorMessage_listCompare();
        //Select one box
        onView(withId(1)).perform(click());
        onView(withId(2)).perform(click());
        onView(withId(3)).perform(click());
        onView(withId(1)).perform(click());
        checkErrorMessage_listCompare();
        //Select 1 and 2, click compare
        onView(withId(1)).perform(click());
        onView(withId(1)).perform(click());
        onView(withId(2)).perform(click());
        onView(withId(R.id.save)).perform(click());

        //Need display checking


    }
    @Test
    //TestCase9: Adjust comparison weights test (incorrect and correct values)
    public void testCase9() {

        View compareSettingButton = mActivity.findViewById(R.id.adjCompSettings);
        assertNotNull(compareSettingButton);
        onView(withId(R.id.adjCompSettings)).perform(click());
        //Check defaulSetting as 1 and re-enter the compareSetting page
        checkDefaultCompareSetting();
        onView(withId(R.id.Cancel)).perform(click());
        onView(withId(R.id.adjCompSettings)).perform(click());

        //incorrect input WITH 0 as value test.
        inputCompareSettings("0","0","0","0","0");
        onView(withId(R.id.save)).perform(click());
        onView(withId(R.id.Cancel)).perform(click());
        onView(withId(R.id.adjCompSettings)).perform(click());
        checkDefaultCompareSetting();
        onView(withId(R.id.Cancel)).perform(click());
        onView(withId(R.id.adjCompSettings)).perform(click());

        //Move to the correct setting and Cancel
        inputCompareSettings("1","2","3","4","5");
        onView(withId(R.id.Cancel)).perform(click());
        //Input again and check default
        onView(withId(R.id.adjCompSettings)).perform(click());
        checkDefaultCompareSetting();
        inputCompareSettings("1","2","3","4","5");
        checkSuccessMessage_compareSetting();
        //Check saved comparSetting through database
        ComparisonSettings cs = new ComparisonSettings();
        cs=helper.fetchWeights();
        assertEquals(1,cs.getYearlySalary());
        assertEquals(2,cs.getYearlyBonus());
        assertEquals(3,cs.getAllowedWeeklyTWDays());
        assertEquals(4,cs.getLeaveTime());
        assertEquals(5,cs.getGymAllowance());


    }


    private void inputCompareSettings(String salaryWeight,String bonusWeight, String remoteweight, String leaveWeight, String gymWeight) {
        onView(withId(R.id.salaryWeight)).perform(clearText(),replaceText(salaryWeight));
        onView(withId(R.id.bonusWeight)).perform(clearText(),replaceText(bonusWeight));
        onView(withId(R.id.remoteWeight)).perform(clearText(),replaceText(remoteweight));
        onView(withId(R.id.leaveWeight)).perform(clearText(),replaceText(leaveWeight));
        onView(withId(R.id.gymWeight)).perform(clearText(),replaceText(gymWeight));
    }

    private void checkDefaultCompareSetting() {
        onView(withId(R.id.salaryWeight)).check(matches(withText("1")));
        onView(withId(R.id.bonusWeight)).check(matches(withText("1")));
        onView(withId(R.id.remoteWeight)).check(matches(withText("1")));
        onView(withId(R.id.leaveWeight)).check(matches(withText("1")));
        onView(withId(R.id.gymWeight)).check(matches(withText("1")));
    }


    private void inputJobDetails(String title, String company, String city, String state, String costOfLiving, String salary, String bonus, String remoteDays, String leaveTime,String gymAllowance) {
        onView(withId(R.id.title)).perform(clearText(), replaceText(title));
        onView(withId(R.id.company)).perform(clearText(), replaceText(company));
        onView(withId(R.id.city)).perform(clearText(), replaceText(city));
        onView(withId(R.id.state)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(state))).perform(click());
        onView(withId(R.id.costOfLiving)).perform(clearText(), replaceText(costOfLiving));
        onView(withId(R.id.salary)).perform(clearText(), replaceText(salary));
        onView(withId(R.id.bonus)).perform(clearText(), replaceText(bonus));
        onView(withId(R.id.remoteDays)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(remoteDays))).perform(click());
        onView(withId(R.id.leaveTime)).perform(clearText(), replaceText(leaveTime));
        onView(withId(R.id.gymAllowance)).perform(clearText(), replaceText(gymAllowance));
    }

    private void testEmptyJobPage() {
        onView(withId(R.id.title)).check(matches(withText("")));
        onView(withId(R.id.company)).check(matches(withText("")));
        onView(withId(R.id.city)).check(matches(withText("")));
        onView(withId(R.id.state)).check(matches(withSpinnerText("Alabama (AL)")));
        onView(withId(R.id.costOfLiving)).check(matches(withText("")));
        onView(withId(R.id.salary)).check(matches(withText("")));
        onView(withId(R.id.bonus)).check(matches(withText("")));
        onView(withId(R.id.remoteDays)).check(matches(withSpinnerText("1")));
        onView(withId(R.id.leaveTime)).check(matches(withText("")));
        onView(withId(R.id.gymAllowance)).check(matches(withText("")));
    }
    private void checkErrorMessage() {
        //Test error message with illegal input and pop up message
        onView(withId(R.id.save)).perform(scrollTo()).perform(click());
        onView(withText("Error, please check your input and try again!")).inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }
    private void checkErrorMessage_listCompare() {
        //Test error message with illegal input and pop up message
        onView(withId(R.id.save)).perform(click());
        onView(withText("Error, please select exactly 2 jobs to compare and try again !!!")).inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }
    private void checkSuccessMessage() {
        onView(withId(R.id.save)).perform(scrollTo()).perform(click());
        onView(withId(R.id.saveJDMessage)).check(matches(withText("Job Details saved successfully.")));
    }
    private void checkSuccessMessage_Offer() {
        onView(withId(R.id.save)).perform(scrollTo()).perform(click());
        onView(withId(R.id.saveJDMessage)).check(matches(withText("Offer Details saved successfully.")));
    }
    private void checkSuccessMessage_compareSetting() {
        onView(withId(R.id.save)).perform(click());
        onView(withId(R.id.saveMessage)).check(matches(withText("Comparison settings saved successfully.")));
    }


}



