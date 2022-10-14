package edu.gatech.seclass.jobcompare6300;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.gatech.seclass.jobcompare6300.vo.ComparisonSettings;
import edu.gatech.seclass.jobcompare6300.vo.Job;
import edu.gatech.seclass.jobcompare6300.vo.Location;
import edu.gatech.seclass.jobcompare6300.vo.Money;

/**
 * Junit test class created for operations in jobcompare6300.
 * <p>
 * Please implement tests in this class.
 * 1.ranking function test
 * Sum=AYS + AYB + GYM + (LT * AYS / 260) - ((260 - 52 * RWT) * (AYS / 260) / 8))
 */
public class methodTest {
    private Job testJob;
    private ComparisonSettings cs;


    public void setParaJob(double salary,double bonus,int teleworkDays,int leaveTime,double gymAllowance,int col){
        testJob.setTitle("testTitle");
        testJob.setCompany("testCompany");
        testJob.setYearlySalary(new Money(salary,"testSalary"));
        testJob.setYearlyBonus(new Money(bonus,"testBonus"));
        testJob.setAllowedWeeklyTWDays(teleworkDays);
        testJob.setLeaveTime(leaveTime);
        testJob.setGymAllowance(new Money(gymAllowance,"testGymAllowance"));
        testJob.setLocation(new Location(col,"testState","testCity"));
    }
    @Before
    public void setUpJob() {
        testJob = new Job();
        setParaJob(120000,2000,2,10,300,241);
    }

    @After
    public void tearDownJob() {
        testJob = null;
    }



    //Test section1: It test the getRanking() with Job with constant attributes and change
    // value of ComparisonSetting,then reverse.
    @Test
    // default ComparisonSettings test with default Job.
    public void testRankingS1() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(120000,2000,2,10,300,241);
        assertEquals(9820.613, testJob.getRanking(cs),0.001);
    }
    @Test
    // 0 value for yearlySalary in ComparisonSetting test with default Job.
    public void testRankingS2() {
        cs = new ComparisonSettings(0,1,1,1,1);
        setParaJob(120000,2000,2,10,300,241);
        assertEquals(-172.367, testJob.getRanking(cs),0.001);
    }
    @Test
    // 0 value for yearlyBonus in ComparisonSetting test with default Job.
    public void testRankingS3() {
        cs = new ComparisonSettings(1,0,1,1,1);
        setParaJob(120000,2000,2,10,300,241);
        assertEquals(12068.297, testJob.getRanking(cs),0.001);
    }
    @Test
    // 0 value for allowedWeeklyTWDays in ComparisonSetting test with default Job.
    public void testRankingS4() {
        cs = new ComparisonSettings(1,1,0,1,1);
        setParaJob(120000,2000,2,10,300,241);
        assertEquals(13209.376, testJob.getRanking(cs),0.001);
    }
    @Test
    // 0 value for leaveTime in ComparisonSetting test with default Job.
    public void testRankingS5() {
        cs = new ComparisonSettings(1,1,1,0,1);
        setParaJob(120000,2000,2,10,300,241);
        assertEquals(11796.992, testJob.getRanking(cs),0.001);
    }
    @Test
    // 0 value for gymAllowance in ComparisonSetting test with default Job.
    public void testRankingS6() {
        cs = new ComparisonSettings(1,1,1,1,0);
        setParaJob(120000,2000,2,10,300,241);
        assertEquals(12200.766, testJob.getRanking(cs),0.001);
    }

    @Test
    // Test only 1 non-zero value which is salary in ComparisonSetting with default Job instance.
    public void testRankingS7() {
        cs = new ComparisonSettings(1,0,0,0,0);
        setParaJob(120000,2000,2,10,300,241);
        assertEquals(49792.531, testJob.getRanking(cs),0.001);
    }
    @Test
    // Test only 1 non-zero value which is bonus in ComparisonSetting with default Job instance.
    public void testRankingS8() {
        cs = new ComparisonSettings(0,1,0,0,0);
        setParaJob(120000,2000,2,10,300,241);
        assertEquals(829.876, testJob.getRanking(cs),0.001);
    }
    @Test
    // Test only 1 non-zero value which is allowedWeekly in ComparisonSetting with default Job instance.
    public void testRankingS9() {
        cs = new ComparisonSettings(0,0,1,0,0);
        setParaJob(120000,2000,2,10,300,241);
        assertEquals(-3734.44, testJob.getRanking(cs),0.001);
    }
    @Test
    // Test only 1 non-zero value which is leaveTime in ComparisonSetting with default Job instance.
    public void testRankingS10() {
        cs = new ComparisonSettings(0,0,0,1,0);
        setParaJob(120000,2000,2,10,300,241);
        assertEquals(1915.097, testJob.getRanking(cs),0.001);
    }
    @Test
    // Test only 1 non-zero value which is gymAllowance in ComparisonSetting with default Job instance.
    public void testRankingS11() {
        cs = new ComparisonSettings(0,0,0,0,1);
        setParaJob(120000,2000,2,10,300,241);
        assertEquals(300.0, testJob.getRanking(cs),0.001);
    }
    //Make attributes in the job class variable and test with constant ComparisonSetting
    @Test
    // Zero salary with default ComparisonSettings.
    public void testRankingS12() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(0,2000,2,10,300,241);
        assertEquals(225.975, testJob.getRanking(cs),0.001);
    }
    @Test
    // Zero yearlyBonus with default ComparisonSettings.
    public void testRankingS13() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(120000.22,0,2,10,300,241);
        assertEquals(9654.655, testJob.getRanking(cs),0.001);
    }
    @Test
    // Zero teleworkDays with default ComparisonSettings.
    public void testRankingS14() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(120000.22,2000.22,0,10,300,241);
        assertEquals(9322.722, testJob.getRanking(cs),0.001);
    }
    @Test
    // Zero leaveTime with default ComparisonSettings.
    public void testRankingS15() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(120000.22,2000.22,2,0,300,241);
        assertEquals(9437.629, testJob.getRanking(cs),0.001);
    }
    @Test
    // Zero gymAllowance with default ComparisonSettings.
    public void testRankingS16() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(120000.22,2000.22,2,10,0,241);
        assertEquals(9760.649, testJob.getRanking(cs),0.001);
    }
//    @Test
//    // Zero gymAllowance with default ComparisonSettings.
//    public void testRankingS17() {
//        cs = new ComparisonSettings(1,1,1,1,1);
//        setParaJob(120000.22,2000.22,2,10,300,0);
//        assertEquals(0.0, testJob.getRanking(cs),0.001);
//    }
    @Test
    // 1 col with default ComparisonSettings.
    public void testRankingS17() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(120000.22,2000.22,2,10,300,1);
        assertEquals(2352376.332, testJob.getRanking(cs),0.001);
    }
    @Test
    // 100 col with default ComparisonSettings.
    public void testRankingS18() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(120000.22,2000.22,2,10,300,100);
        assertEquals(23583.163, testJob.getRanking(cs),0.001);
    }
    @Test
    // Everything is zero except col with default ComparisonSettings.
    public void testRankingS19() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(0,0,0,0,0,1);
        assertEquals(0.0, testJob.getRanking(cs),0.001);
    }
    @Test
    // Everything is zero except col and salary with default ComparisonSettings.
    public void testRankingS20() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(120000.22,0,0,0,0,1);
        assertEquals(2100003.85, testJob.getRanking(cs),0.001);
    }
    @Test
    // Everything is zero except col and bonus with default ComparisonSettings.
    public void testRankingS21() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(0,2000.22,0,0,0,1);
        assertEquals(40004.4, testJob.getRanking(cs),0.001);
    }
    @Test
    // Everything is zero except col and teleWorkDays with default ComparisonSettings.
    public void testRankingS22() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(0,0,2,0,0,1);
        assertEquals(0.0, testJob.getRanking(cs),0.001);
    }
    @Test
    // Everything is zero except col and leaveTime with default ComparisonSettings.
    public void testRankingS23() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(0,0,0,10,0,1);
        assertEquals(0.0, testJob.getRanking(cs),0.001);
    }
    @Test
    // Everything is zero except col and gymAllowance with default ComparisonSettings.
    public void testRankingS24() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(0,0,0,0,300,1);
        assertEquals(60.0, testJob.getRanking(cs),0.001);
    }
    @Test
    // TEverything is zero except: col,salary and bonus
    public void testRankingS25() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(120000.22,2000.22,0,0,0,1);
        assertEquals(2140008.25, testJob.getRanking(cs),0.001);
    }
    @Test
    // TEverything is zero except: col,salary and teleworkDays
    public void testRankingS26() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(120000.22,0,2,0,0,1);
        assertEquals(2220004.07, testJob.getRanking(cs),0.001);
    }
    @Test
    // TEverything is zero except: col,salary and leaveTime
    public void testRankingS27() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(120000.22,0,0,10,0,1);
        assertEquals(2192311.712, testJob.getRanking(cs),0.001);
    }
    @Test
    // TEverything is zero except: col,salary and gymAllowance
    public void testRankingS28() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(120000.22,0,0,0,300,1);
        assertEquals(2100063.85, testJob.getRanking(cs),0.001);
    }
    @Test
    // TEverything is zero except: col,bonus and teleworkDays
    public void testRankingS29() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(0,2000.22,2,0,0,1);
        assertEquals(40004.4, testJob.getRanking(cs),0.001);
    }
    @Test
    // TEverything is zero except: col,bonus and leaveTime
    public void testRankingS30() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(0,2000.22,0,15,0,1);
        assertEquals(40004.4, testJob.getRanking(cs),0.001);
    }
    @Test
    // TEverything is zero except: col,bonus and gymAllowance
    public void testRankingS31() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(0,2000.22,0,0,200,1);
        assertEquals(40044.4, testJob.getRanking(cs),0.001);
    }
    @Test
    // TEverything is zero except: col,teleworkDays and leaveTime
    public void testRankingS32() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(0,0,3,16,0,1);
        assertEquals(0.0, testJob.getRanking(cs),0.001);
    }
    @Test
    // TEverything is zero except: col,teleworkDays and gymAllowance
    public void testRankingS33() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(0,0,3,0,150,1);
        assertEquals(30.0, testJob.getRanking(cs),0.001);
    }
    @Test
    // TEverything is zero except: col,leaveTime and gymAllowance
    public void testRankingS34() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(0,0,0,30,150,1);
        assertEquals(30.0, testJob.getRanking(cs),0.001);
    }
    @Test
    // Two value are zero: Salary and bonus
    public void testRankingS35() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(0,0,3,10,150,1);
        assertEquals(30.0, testJob.getRanking(cs),0.001);
    }
    @Test
    // Two value are zero: Salary and teleworkDays
    public void testRankingS36() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(0,2000.22,0,10,150,1);
        assertEquals(40034.4, testJob.getRanking(cs),0.001);
    }
    @Test
    // Two value are zero: Salary and leaveTime
    public void testRankingS37() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(0,2000.22,5,0,150,1);
        assertEquals(40034.4, testJob.getRanking(cs),0.001);
    }
    @Test
    // Two value are zero: Salary and gymAllowance
    public void testRankingS38() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(0,2000.22,5,14,0,1);
        assertEquals(40004.4, testJob.getRanking(cs),0.001);
    }
    @Test
    // Two value are zero: bonus and teleworkDays
    public void testRankingS39() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(120000.22,0,0,14,70,1);
        assertEquals(2229248.856, testJob.getRanking(cs),0.001);
    }
    @Test
    // Two value are zero: bonus and leaveTime
    public void testRankingS40() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(120000.22,0,4,0,70,1);
        assertEquals(2340018.29, testJob.getRanking(cs),0.001);
    }
    @Test
    // Two value are zero: bonus and gymAllowance
    public void testRankingS41() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(120000.22,0,4,25,0,1);
        assertEquals(2570773.944, testJob.getRanking(cs),0.001);
    }
    @Test
    // Two value are zero: teleworkDays and leaveTime
    public void testRankingS42() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(120000.22,1500.22,0,0,78,1);
        assertEquals(2130023.85, testJob.getRanking(cs),0.001);
    }
    @Test
    // Two value are zero: teleworkDays and gymAllowance
    public void testRankingS43() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(120000.22,1500.22,0,17,0,1);
        assertEquals(2286931.615, testJob.getRanking(cs),0.001);
    }
    @Test
    // Two values are zero: leaveTime and gymAllowance
    public void testRankingS44() {
        cs = new ComparisonSettings(1,1,1,1,1);
        setParaJob(120000.22,1500.22,4,0,0,1);
        assertEquals(2370008.69, testJob.getRanking(cs),0.001);
    }

    //Test section2: It test the isValid() within the Job class by changing value in and out of the limits.



    @Test
    // Test isValid() with max salary.
    public void testIsValidS1() {
        setParaJob(Double.MAX_VALUE,2000,2,10,300,241);
        assertEquals(true,testJob.isValid());
    }
    @Test
    // Test isValid() with 0 salary.
    public void testIsValidS2() {
        setParaJob(0,2000,2,10,300,241);
        assertEquals(true,testJob.isValid());
    }
    @Test
    // Test isValid() with max bonus.
    public void testIsValidS3() {
        setParaJob(120000,Double.MAX_VALUE,2,10,300,241);
        assertEquals(true,testJob.isValid());
    }

    @Test
    // Test isValid() with 0 bonus.
    public void testIsValidS4() {
        setParaJob(120000,0,2,10,300,241);
        assertEquals(true,testJob.isValid());
    }
    @Test
    // Test isValid() with 0 teleworkDays.
    public void testIsValidS5() {
        setParaJob(120000,2000,0,10,300,241);
        assertEquals(true,testJob.isValid());
    }
    @Test
    // Test isValid() with 5 teleworkDays.
    public void testIsValidS6() {
        setParaJob(120000,2000,5,10,300,241);
        assertEquals(true,testJob.isValid());
    }
    @Test
    // Test isValid() with 7 teleworkDays.
    public void testIsValidS7() {
        setParaJob(120000,2000,7,10,300,241);
        assertEquals(false,testJob.isValid());
    }

    @Test
    // Test isValid() with 0 leaveTime.
    public void testIsValidS8() {
        setParaJob(120000,2000,2,0,300,241);
        assertEquals(true,testJob.isValid());
    }
    @Test
    // Test isValid() with 365 leaveTime.
    public void testIsValidS9() {
        setParaJob(120000,2000,2,365,300,241);
        assertEquals(true,testJob.isValid());
    }
    @Test
    // Test isValid() with 366 leaveTime.
    public void testIsValidS10() {
        setParaJob(120000,2000,2,366,300,241);
        assertEquals(false,testJob.isValid());
    }
    @Test
    // Test isValid() with 367 leaveTime.
    public void testIsValidS11() {
        setParaJob(120000,2000,2,367,300,241);
        assertEquals(false,testJob.isValid());
    }

    @Test
    // Test isValid() with negative gymAllowance.
    public void testIsValidS12() {
        setParaJob(120000,2000,2,10,-300,241);
        assertEquals(false,testJob.isValid());
    }
    @Test
    // Test isValid() with 0 gymAllowance.
    public void testIsValidS13() {
        setParaJob(120000,2000,2,10,0,241);
        assertEquals(true,testJob.isValid());
    }
    @Test
    // Test isValid() with 500 gymAllowance.
    public void testIsValidS14() {
        setParaJob(120000,2000,2,10,500,241);
        assertEquals(true,testJob.isValid());
    }
    @Test
    // Test isValid() with more than 501 gymAllowance.
    public void testIsValidS15() {
        setParaJob(120000,2000,2,10,501,241);
        assertEquals(false,testJob.isValid());
    }
    @Test
    // Test isValid() with more than 500 gymAllowance.
    public void testIsValidS16() {
        setParaJob(120000,2000,2,10,Double.MAX_VALUE,241);
        assertEquals(false,testJob.isValid());
    }

    @Test
    // Test isValid() with 0 costOfLiving.
    public void testIsValidS17() {
        setParaJob(120000,2000,2,10,300,0);
        assertEquals(false,testJob.isValid());
    }
    @Test
    // Test isValid() with 1 costOfLiving.
    public void testIsValidS18() {
        setParaJob(120000,2000,2,10,300,1);
        assertEquals(true,testJob.isValid());
    }
    @Test
    // Test isValid() with max positive costOfLiving.
    public void testIsValidS19() {
        setParaJob(120000,2000,2,10,300,Integer.MAX_VALUE);
        assertEquals(true,testJob.isValid());
    }
    @Test
    // Test isValid() with 2 random  0 value .
    public void testIsValidS20() {
        setParaJob(0,0,2,10,300,100);
        assertEquals(true,testJob.isValid());
    }
    @Test
    // Test isValid() with 2 random  0 value .
    public void testIsValidS21() {
        setParaJob(120000.22,0,0,10,300,100);
        assertEquals(true,testJob.isValid());
    }
    @Test
    // Test isValid() with 2 random  0 value .
    public void testIsValidS22() {
        setParaJob(120000.22,0,1,0,300,100);
        assertEquals(true,testJob.isValid());
    }
    @Test
    // Test isValid() with 2 random  0 value .
    public void testIsValidS23() {
        setParaJob(120000.22,2000.22,1,0,0,100);
        assertEquals(true,testJob.isValid());
    }
    @Test
    // Test isValid() with 2 random  out of Range value .
    public void testIsValidS24() {
        setParaJob(120000.22,2000.22,7,10,700,100);
        assertEquals(false,testJob.isValid());
    }
    @Test
    // Test isValid() with 2 random  out of Range value .
    public void testIsValidS25() {
        setParaJob(120000.22,2000.22,4,368,700,100);
        assertEquals(false,testJob.isValid());
    }

    @Test
    // Test isValid() with  random  0 and out of Range value .
    public void testIsValidS26() {
        setParaJob(120000.22, 0,4,368,700,100);
        assertEquals(false,testJob.isValid());
    }
    @Test
    // Test isValid() with  random  0 and out of Range value .
    public void testIsValidS27() {
        setParaJob(0, 0,8,368,700,100);
        assertEquals(false,testJob.isValid());
    }
    @Test
    // Test isValid() with  random  0 and out of Range value .
    public void testIsValidS28() {
        setParaJob(12000.2, 0,8,0,700,100);
        assertEquals(false,testJob.isValid());
    }
    @Test
    // Test isValid() with  random  0 and out of Range value .
    public void testIsValidS29() {
        setParaJob(12000.2, 0,8,400,0,100);
        assertEquals(false,testJob.isValid());
    }
    @Test
    // Test isValid() with  random  0 and out of Range value .
    public void testIsValidS30() {
        setParaJob(0, 2000.01,0,400,501,100);
        assertEquals(false,testJob.isValid());
    }
    @Test
    // Set title to empty.
    public void testIsValidS31() {
        testJob.setTitle("");
        assertEquals(false,testJob.isValid());
    }
    @Test
    // Set company to empty.
    public void testIsValidS32() {
        testJob.setCompany("");
        assertEquals(false,testJob.isValid());
    }
    @Test
    // Set city to empty.
    public void testIsValidS33() {
        testJob.setLocation(new Location(241,"CA",""));
        assertEquals(false,testJob.isValid());
    }
    @Test
    // Set state to empty.
    public void testIsValidS34() {
        testJob.setLocation(new Location(241,"","testCity"));
        assertEquals(false,testJob.isValid());
    }

}
