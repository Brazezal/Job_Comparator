package edu.gatech.seclass.jobcompare6300.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.jobcompare6300.vo.ComparisonSettings;
import edu.gatech.seclass.jobcompare6300.vo.Job;
import edu.gatech.seclass.jobcompare6300.vo.Location;
import edu.gatech.seclass.jobcompare6300.vo.Money;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "jobcompare.db";
    // Job Details table constants.
    public static final String JOBDETAILS_TABLE_NAME = "jobdetails";
    public static final String JOBDETAILS_COLUMN_ID = "id";
    public static final String JOBDETAILS_COLUMN_TITLE = "title";
    public static final String JOBDETAILS_COLUMN_COMPANY = "company";
    public static final String JOBDETAILS_COLUMN_STATE = "state";
    public static final String JOBDETAILS_COLUMN_CITY = "city";
    public static final String JOBDETAILS_COLUMN_COL = "livingcost";
    public static final String JOBDETAILS_COLUMN_SALARY = "salary";
    public static final String JOBDETAILS_COLUMN_BONUS = "bonus";
    public static final String JOBDETAILS_COLUMN_REMOTEDAYS = "remotedays";
    public static final String JOBDETAILS_COLUMN_LEAVETIME = "leavetime";
    public static final String JOBDETAILS_COLUMN_GYMALLOWANCE = "allowance";
    public static final String JOBDETAILS_COLUMN_CURRENTINDICATOR = "currind";

    // Comparison weight table constants
    public static final String COMPARISON_TABLE_NAME = "comparisonweights";
    public static final String COMPARISON_COLUMN_SALWEIGHT = "salaryweight";
    public static final String COMPARISON_COLUMN_BONSUWEIGHT = "bonusweight";
    public static final String COMPARISON_COLUMN_REMOTEWEIGHT = "remoteweight";
    public static final String COMPARISON_COLUMN_LEAVEWEIGHT = "leaveweight";
    public static final String COMPARISON_COLUMN_ALLOWANCEWEIGHT = "allowanceweight";

    public long currentId;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
        // Uncomment these lines if the db gets corrupted and needs to be rebuilt
        // this.cleanup();
    }

    public void cleanup(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS jobdetails");
        db.execSQL("DROP TABLE IF EXISTS comparisonweights");
        db.execSQL ("create table jobdetails " +
                "(id integer primary key, title text,company text,state text,city text, currind integer " +
                ",livingcost real,salary integer, bonus integer, remotedays integer" +
                ", leavetime integer, allowance real)");
        db.execSQL(
                "create table "+COMPARISON_TABLE_NAME+
                        "("+COMPARISON_COLUMN_SALWEIGHT+" integer,"+COMPARISON_COLUMN_BONSUWEIGHT+" integer,"+COMPARISON_COLUMN_REMOTEWEIGHT+" integer," +
                        COMPARISON_COLUMN_LEAVEWEIGHT+" integer, "+COMPARISON_COLUMN_ALLOWANCEWEIGHT+" integer)");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table "+COMPARISON_TABLE_NAME+
                        "("+COMPARISON_COLUMN_SALWEIGHT+" integer,"+COMPARISON_COLUMN_BONSUWEIGHT+" integer,"+COMPARISON_COLUMN_REMOTEWEIGHT+" integer," +
                        COMPARISON_COLUMN_LEAVEWEIGHT+" integer, "+COMPARISON_COLUMN_ALLOWANCEWEIGHT+", integer)");

        sqLiteDatabase.execSQL(
                "create table jobdetails " +
                        "(id integer primary key, title text,company text,state text,city text, currind integer " +
                        ",livingcost real,salary integer, bonus integer, remotedays integer" +
                        ", leavetime integer, allowance real)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS jobdetails");
        onCreate(sqLiteDatabase);
    }

    public boolean insertJD (Job inputJob) {

        long jobId = inputJob.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        //No entry for current job adding currnet job for first time
        if(jobId == 0){
            ContentValues contentValues = new ContentValues();
            contentValues.put(JOBDETAILS_COLUMN_TITLE, inputJob.getTitle());
            contentValues.put(JOBDETAILS_COLUMN_COMPANY, inputJob.getCompany());
            Location currentLocation = inputJob.getLocation();
            contentValues.put(JOBDETAILS_COLUMN_STATE, currentLocation.getState());
            contentValues.put(JOBDETAILS_COLUMN_CITY, currentLocation.getCity());
            contentValues.put(JOBDETAILS_COLUMN_COL, currentLocation.getCostOfLiving());
            contentValues.put(JOBDETAILS_COLUMN_SALARY, inputJob.getYearlySalary().getAmount());
            contentValues.put(JOBDETAILS_COLUMN_BONUS, inputJob.getYearlyBonus().getAmount());
            contentValues.put(JOBDETAILS_COLUMN_REMOTEDAYS, inputJob.getAllowedWeeklyTWDays());
            contentValues.put(JOBDETAILS_COLUMN_LEAVETIME, inputJob.getLeaveTime());
            contentValues.put(JOBDETAILS_COLUMN_GYMALLOWANCE, inputJob.getGymAllowance().getAmount());
            contentValues.put(JOBDETAILS_COLUMN_CURRENTINDICATOR, inputJob.isCurrent() == true ? 1 : 0);

            long id = db.insert(JOBDETAILS_TABLE_NAME, null, contentValues);
            this.currentId = id;
        }else{
            String strUpdate = "update "+JOBDETAILS_TABLE_NAME+" set "+JOBDETAILS_COLUMN_TITLE+" = '"+inputJob.getTitle()+"', "+
                    JOBDETAILS_COLUMN_COMPANY+" = '"+inputJob.getCompany()+"', "+JOBDETAILS_COLUMN_STATE+" = '"+inputJob.getLocation().getState()+"', "+
                    JOBDETAILS_COLUMN_CITY+" = '"+inputJob.getLocation().getCity()+"', "+JOBDETAILS_COLUMN_COL+" = "+inputJob.getLocation().getCostOfLiving()+", "+
                    JOBDETAILS_COLUMN_SALARY+" = "+inputJob.getYearlySalary().getAmount()+", "+JOBDETAILS_COLUMN_BONUS+" = "+inputJob.getYearlyBonus().getAmount()+", "+
                    JOBDETAILS_COLUMN_REMOTEDAYS+" = "+inputJob.getAllowedWeeklyTWDays()+", "+JOBDETAILS_COLUMN_LEAVETIME+" = "+inputJob.getLeaveTime()+", "+
                    JOBDETAILS_COLUMN_GYMALLOWANCE+" = "+inputJob.getGymAllowance().getAmount()+ " where "+JOBDETAILS_COLUMN_ID+" = "+jobId;

            db.execSQL(strUpdate);
        }
        return true;
    }


    public boolean updateWeights(ComparisonSettings inputWeights){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res =  db.rawQuery("Select * from "+COMPARISON_TABLE_NAME, null);
        // Update if we already have weights.
        if(res.getCount() > 0){
            String strUpdate = "update "+COMPARISON_TABLE_NAME+" set "+COMPARISON_COLUMN_SALWEIGHT+" = "+inputWeights.getYearlySalary()+", "+
                    COMPARISON_COLUMN_BONSUWEIGHT+" = "+inputWeights.getYearlyBonus()+", "+COMPARISON_COLUMN_REMOTEWEIGHT+" = "+inputWeights.getAllowedWeeklyTWDays()+", "+
                    COMPARISON_COLUMN_LEAVEWEIGHT+" = "+inputWeights.getLeaveTime()+", "+COMPARISON_COLUMN_ALLOWANCEWEIGHT+" = "+inputWeights.getGymAllowance();
            db.execSQL(strUpdate);
        }else{// Insert if we don't have weights
            ContentValues contentValues = new ContentValues();

            contentValues.put(COMPARISON_COLUMN_SALWEIGHT, inputWeights.getYearlySalary());
            contentValues.put(COMPARISON_COLUMN_BONSUWEIGHT, inputWeights.getYearlyBonus());
            contentValues.put(COMPARISON_COLUMN_REMOTEWEIGHT, inputWeights.getAllowedWeeklyTWDays());
            contentValues.put(COMPARISON_COLUMN_LEAVEWEIGHT, inputWeights.getLeaveTime());
            contentValues.put(COMPARISON_COLUMN_ALLOWANCEWEIGHT, inputWeights.getGymAllowance());

            db.insert(COMPARISON_TABLE_NAME, null, contentValues);
        }
        return true;
    }

    @SuppressLint("Range")
    public ComparisonSettings fetchWeights(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+COMPARISON_TABLE_NAME, null );
        ComparisonSettings settings;
        if(res.getCount() == 1){
            res.moveToFirst();

            int salaryWeight =  res.getInt(res.getColumnIndex(COMPARISON_COLUMN_SALWEIGHT));
            int bonusWeight = res.getInt(res.getColumnIndex(COMPARISON_COLUMN_BONSUWEIGHT));
            int remoteDaysWeight = res.getInt(res.getColumnIndex(COMPARISON_COLUMN_REMOTEWEIGHT));
            int leaveWeight = res.getInt(res.getColumnIndex(COMPARISON_COLUMN_LEAVEWEIGHT));
            int allowanceWeight = res.getInt(res.getColumnIndex(COMPARISON_COLUMN_ALLOWANCEWEIGHT));

            settings = new ComparisonSettings(salaryWeight, bonusWeight, remoteDaysWeight, leaveWeight, allowanceWeight);
        }else{
            settings = new ComparisonSettings();
        }
        return settings;
    }

    public Job getJob(long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+JOBDETAILS_TABLE_NAME+" where "+JOBDETAILS_COLUMN_ID+"="+id+"", null );
        Job currentjob = new Job();
        // We have a saved job
        if(res.getCount() == 1){
            res.moveToFirst();
            currentjob.setId(res.getInt(res.getColumnIndex(JOBDETAILS_COLUMN_ID)));
            currentjob.setTitle(res.getString(res.getColumnIndex(JOBDETAILS_COLUMN_TITLE)));
            currentjob.setCompany(res.getString(res.getColumnIndex(JOBDETAILS_COLUMN_COMPANY))); ;
            currentjob.setAllowedWeeklyTWDays(res.getInt(res.getColumnIndex(JOBDETAILS_COLUMN_REMOTEDAYS)));
            currentjob.setLeaveTime(res.getInt(res.getColumnIndex(JOBDETAILS_COLUMN_LEAVETIME)));

            Location joblocation = new Location(res.getInt(res.getColumnIndex(JOBDETAILS_COLUMN_COL)),
                    res.getString(res.getColumnIndex(JOBDETAILS_COLUMN_STATE)),
                    res.getString(res.getColumnIndex(JOBDETAILS_COLUMN_CITY)));

            currentjob.setLocation(joblocation);

            Money salary = new Money(res.getDouble(res.getColumnIndex(JOBDETAILS_COLUMN_SALARY)), "USD");
            currentjob.setYearlySalary(salary);

            Money bonus = new Money(res.getDouble(res.getColumnIndex(JOBDETAILS_COLUMN_BONUS)), "USD");
            currentjob.setYearlyBonus(bonus);

            Money allowance = new Money(res.getDouble(res.getColumnIndex(JOBDETAILS_COLUMN_GYMALLOWANCE)),"USD");
            currentjob.setGymAllowance(allowance);

            currentjob.setCurrent(true);
        }
        return currentjob;
    }


    @SuppressLint("Range")
    public Job getCurrentjob() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+JOBDETAILS_TABLE_NAME+" where "+JOBDETAILS_COLUMN_CURRENTINDICATOR+"="+1+"", null );
        //Cursor res =  db.rawQuery( "select * from "+JOBDETAILS_TABLE_NAME, null );
        Job currentjob = new Job();
        // We have a saved job
        if(res.getCount() == 1){
            res.moveToFirst();
            currentjob.setId(res.getInt(res.getColumnIndex(JOBDETAILS_COLUMN_ID)));
            currentjob.setTitle(res.getString(res.getColumnIndex(JOBDETAILS_COLUMN_TITLE)));
            currentjob.setCompany(res.getString(res.getColumnIndex(JOBDETAILS_COLUMN_COMPANY))); ;
            currentjob.setAllowedWeeklyTWDays(res.getInt(res.getColumnIndex(JOBDETAILS_COLUMN_REMOTEDAYS)));
            currentjob.setLeaveTime(res.getInt(res.getColumnIndex(JOBDETAILS_COLUMN_LEAVETIME)));

            Location joblocation = new Location(res.getInt(res.getColumnIndex(JOBDETAILS_COLUMN_COL)),
                    res.getString(res.getColumnIndex(JOBDETAILS_COLUMN_STATE)),
                    res.getString(res.getColumnIndex(JOBDETAILS_COLUMN_CITY)));

            currentjob.setLocation(joblocation);

            Money salary = new Money(res.getDouble(res.getColumnIndex(JOBDETAILS_COLUMN_SALARY)), "USD");
            currentjob.setYearlySalary(salary);

            Money bonus = new Money(res.getDouble(res.getColumnIndex(JOBDETAILS_COLUMN_BONUS)), "USD");
            currentjob.setYearlyBonus(bonus);

            Money allowance = new Money(res.getDouble(res.getColumnIndex(JOBDETAILS_COLUMN_GYMALLOWANCE)),"USD");
            currentjob.setGymAllowance(allowance);

            currentjob.setCurrent(true);
        }
        return currentjob;
    }

    public int getTotaljobCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select count(*) from "+JOBDETAILS_TABLE_NAME, null );
        res.moveToFirst();
        int cnt = res.getInt(0);
        return cnt;
    }

    public List<Job> getAllJobs(){
        ArrayList<Job> resultList = new ArrayList<Job>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+JOBDETAILS_TABLE_NAME, null );

        if(res.getCount() > 0){
            res.moveToFirst();
            while(res.isAfterLast() == false){

                String jobTitle = res.getString(res.getColumnIndex(JOBDETAILS_COLUMN_TITLE));
                String jobCompany = res.getString(res.getColumnIndex(JOBDETAILS_COLUMN_COMPANY));
                String state = res.getString(res.getColumnIndex(JOBDETAILS_COLUMN_STATE));
                String city = res.getString(res.getColumnIndex(JOBDETAILS_COLUMN_CITY));

                int costOfLiving = res.getInt(res.getColumnIndex(JOBDETAILS_COLUMN_COL));
                int remoteDays = res.getInt(res.getColumnIndex(JOBDETAILS_COLUMN_REMOTEDAYS));
                int leaveTime = res.getInt(res.getColumnIndex(JOBDETAILS_COLUMN_LEAVETIME));

                double salary = res.getDouble(res.getColumnIndex(JOBDETAILS_COLUMN_SALARY));
                double bonus = res.getDouble(res.getColumnIndex(JOBDETAILS_COLUMN_BONUS));
                double allowance = res.getDouble(res.getColumnIndex(JOBDETAILS_COLUMN_GYMALLOWANCE));

                long jobId =  res.getLong(res.getColumnIndex(JOBDETAILS_COLUMN_ID));

                boolean isCurrent = res.getInt(res.getColumnIndex(JOBDETAILS_COLUMN_CURRENTINDICATOR)) == 1 ? true : false;

                Location jobLocation = new Location(costOfLiving, state, city);

                Money annualSalary = new Money(salary, "");
                Money annualBonus = new Money(bonus, "");
                Money gymAllowance = new Money(allowance, "");


                Job jobEntry = new Job(jobId, jobTitle, jobCompany, jobLocation, annualSalary, annualBonus, remoteDays, leaveTime, gymAllowance, isCurrent);
                resultList.add(jobEntry);

                res.moveToNext();
            }
        }

        return resultList;
    }
}