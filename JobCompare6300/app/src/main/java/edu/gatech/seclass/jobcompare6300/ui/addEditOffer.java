package edu.gatech.seclass.jobcompare6300.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.transition.Explode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.controller.JobsController;
import edu.gatech.seclass.jobcompare6300.dao.DBHelper;
import edu.gatech.seclass.jobcompare6300.vo.Job;
import edu.gatech.seclass.jobcompare6300.vo.Location;
import edu.gatech.seclass.jobcompare6300.vo.Money;

public class addEditOffer extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private DBHelper dbHelper;
    private String[] stateLabels = {"Alabama (AL)", "Alaska (AK)", "Arizona (AZ)", "California (CA)", "Colorado (CO)", "Connecticut (CT)",
            "Delaware (DE)", "Florida (FL)", "Georgia (GA)","Hawaii (HI)", "Idaho (ID)","Illinois (IL)", "Indiana (IN)", "Iowa (IA)", "Kansas (KS)",
            "Kentucky (KY)", "Louisiana	(LA)", "Maine (ME)","Maryland (MD)","Massachusetts (MA)", "Michigan (MI)", "Minnesota (MN)", "Mississippi (MS)",
            "Missouri (MO)", "Montana (MT)", "Nebraska (NE)", "Nevada (NV)", "New Hampshire (NH)", "New Jersey (NJ)","New Mexico (NM)", "New York (NY)",
            "North Carolina (NC)", "North Dakota (ND)", "Ohio(OH)", "Oklahoma (OK)", "Oregon(OR)", "Pennsylvania (Pa)", "Rhode Island (RI)", "South Carolina (SC)",
            "South Dakota (SD)", "Tennessee(TN)", "Texas (TX)", "Utah (UT)", "Vermont (VT)", "Virginia (VA)", "Washington (WA)", "West Virginia (WV)",
            "Wisconsin (WI)", "Wyoming (WY)"};
    private String[] stateCodes = {"AL", "AK", "AZ", "CA","CO","CT","DE","Fl","GA","HI","ID","IL","IN", "IA","KS","KY","LA","ME","MD","MA","MI",
            "MN","MS","MO","MT", "NE", "NV","NH","NJ","NM","NY","NC","ND","OH","OK","OR","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY"};

    private String[] teleworkDays = {"1","2","3","4","5"};
    private String selectedState = "";
    private int selectedRemoteDays = 0;

    private JobsController controller;
    private Job savedOffer;


    private EditText title;
    private EditText company;
    private EditText city;
    private EditText col;
    private EditText yearlySalary;
    private EditText yearlyBonus;
    private EditText leaveTime;
    private EditText gymAllowance;
    private Spinner stateSpinner;
    private AlertDialog alertDialog;


    private void intializebackEnd(){
        dbHelper = new DBHelper(this);
        controller = new JobsController(dbHelper);
    }

    public void backToHome(View view){
        Intent homeActivity = new Intent(this, MainActivity.class);
        startActivity(homeActivity);
    }

    public void reload(View view){
        startActivity(getIntent());
    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        int id1 = arg0.getId();
        if(id1 == R.id.state){
            this.selectedState = this.stateCodes[position];
        }else{
            this.selectedRemoteDays = Integer.parseInt((String) arg0.getItemAtPosition(position));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }


    private void setupStateSpinner(){
        Spinner stateDropDown = findViewById(R.id.state);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, this.stateLabels);
        stateDropDown.setAdapter(adapter);
        stateDropDown.setOnItemSelectedListener(this);
        this.stateSpinner = stateDropDown;
    }
    private void setupTeleworkSpinner(){
        Spinner teleWorkDD = findViewById(R.id.remoteDays);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, this.teleworkDays);
        teleWorkDD.setAdapter(adapter);
        teleWorkDD.setOnItemSelectedListener(this);
    }

    public void saveDetails(View view){

        if(title.length() < 1) {
            title.setError("Can't be empty");
            return;
        }
        if(company.length() < 1) {
            company.setError("Can't be empty");
            return;
        }if(city.length() < 1) {
            city.setError("Can't be empty");
            return;
        }if(col.length() < 1) {
            col.setError("Can't be empty");
            return;
        }if(yearlySalary.length() < 1) {
            yearlySalary.setError("Can't be empty");
            return;
        }if(yearlyBonus.length() < 1) {
            yearlyBonus.setError("Can't be empty");
            return;
        }
        if(leaveTime.length() < 1) {
            leaveTime.setError("Can't be empty");
            return;
        }
        if (Integer.parseInt(leaveTime.getText().toString())>365){
            leaveTime.setError("Error, please input number less than 365");
            return;
        }
        if(gymAllowance.length() < 1) {
            gymAllowance.setError("Can't be empty");
            return;
        }
        if (Integer.parseInt(gymAllowance.getText().toString())>500){
            gymAllowance.setError("Error, please input number less than 500");
            return;
        }

        this.savedOffer.setTitle(title.getText().toString());
        this.savedOffer.setCompany(company.getText().toString());

        this.savedOffer.setAllowedWeeklyTWDays(this.selectedRemoteDays);
        this.savedOffer.setLeaveTime(Integer.parseInt(this.leaveTime.getText().toString()));

        Money allowance = new Money(Double.parseDouble(this.gymAllowance.getText().toString()),"USD");
        this.savedOffer.setGymAllowance(allowance);

        Money salary = new Money(Double.parseDouble(this.yearlySalary.getText().toString()), "USD");
        this.savedOffer.setYearlySalary(salary);

        Money bonus = new Money(Double.parseDouble(this.yearlyBonus.getText().toString()), "USD");
        this.savedOffer.setYearlyBonus(bonus);

        Location jobLocation = new Location(Integer.parseInt(col.getText().toString()), this.selectedState, this.city.getText().toString());
        this.savedOffer.setLocation(jobLocation);
        this.savedOffer.setCurrent(false);

        boolean isSavedDetails = controller.addJobOffer(this.savedOffer);

        if(isSavedDetails){
            this.savedOffer.setId(controller.getInsertedId());
            findViewById(R.id.compareCurrent).setEnabled(true);
            findViewById(R.id.saveJDMessage).setVisibility(View.VISIBLE);
            Handler handler = new Handler(Looper.getMainLooper());
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    findViewById(R.id.saveJDMessage).setVisibility(View.INVISIBLE);
                }
            };
            handler.postDelayed(runnable, 1000);
        }
    }

    private void populateControls(){
        title = findViewById(R.id.title);
        company = findViewById(R.id.company);
        city = findViewById(R.id.city);
        yearlySalary = findViewById(R.id.salary);
        yearlyBonus = findViewById(R.id.bonus);
        leaveTime = findViewById(R.id.leaveTime);
        gymAllowance = findViewById(R.id.gymAllowance);
        col = findViewById(R.id.costOfLiving);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setEnterTransition(new Explode());
        setContentView(R.layout.activity_add_edit_offer);
        populateControls();
        // Set the back end and database objects
        intializebackEnd();
        // Initiate spinners if data already exists to select the corresponding element
        setupStateSpinner();
        setupTeleworkSpinner();
        this.savedOffer = new Job();
    }

    public void compareCurrent(View view){
        long currentJobId = controller.getCurrentJob().getId();
        if(currentJobId == 0) {
            Toast errorToast = Toast.makeText(this, "Error, No current job exists, please add current job details!", Toast.LENGTH_SHORT);
            errorToast.show();
            return;
        }
        long savedOfferId = controller.getInsertedId();

        Intent jobCompare = new Intent(this, compareJobs.class);
        jobCompare.putExtra("ID1",currentJobId);
        jobCompare.putExtra("ID2",savedOfferId);
        startActivity(jobCompare, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}