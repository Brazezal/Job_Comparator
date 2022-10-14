package edu.gatech.seclass.jobcompare6300.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import android.transition.Explode;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

public class addEditJD extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
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
    private BackEndFactory factory;


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

    private Job currentJob;

    private int findIndexOfValue (String[] source, String value){
        int returnIndex = 0;
        for(int i=0;i<source.length;i++){
            if(source[i].equalsIgnoreCase(value)){
                returnIndex = i;
                break;
            }
        }
        return returnIndex;
    }
    private void intializebackEnd(){
        if(this.factory == null) {
            BackEndFactory factory =  new BackEndFactory();
            dbHelper = new DBHelper(this);
            controller = new JobsController(dbHelper);
            factory.setController(controller);
            this.factory = factory;
        }
        this.controller = this.factory.getController();
    }
    private void createDialog(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Return to main menu ?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        backToHome(view);
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    hideDialog();
                }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        this.alertDialog = alertDialog;
    }

    private void hideDialog(){
        this.alertDialog.hide();
    }

    private void setupStateSpinner(){
        Spinner stateDropDown = findViewById(R.id.state);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, this.stateLabels);
        stateDropDown.setAdapter(adapter);
        stateDropDown.setOnItemSelectedListener(this);
        this.stateSpinner = stateDropDown;
        // We already have current job so populate the spinner with selected state.
        if(this.selectedState != ""){
            int selectedIndex = findIndexOfValue(this.stateCodes, this.selectedState);
            this.stateSpinner.setSelection(selectedIndex);
        }
    }
    private void setupTeleworkSpinner(){
        Spinner teleWorkDD = findViewById(R.id.remoteDays);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, this.teleworkDays);
        teleWorkDD.setAdapter(adapter);
        teleWorkDD.setOnItemSelectedListener(this);
        // We already have current telework days so populate the spinner with selected telework days.
        if(this.selectedRemoteDays != 0){
            int selectedIndex = findIndexOfValue(this.teleworkDays, Integer.toString(this.selectedRemoteDays));
            teleWorkDD.setSelection(selectedIndex);
        }
    }
    private void setAddEditDetails(){
        this.currentJob = controller.getCurrentJob();
        // Condition to set the input fields only if current job exists else don't pre-populate anything
        if(this.currentJob.getId() > 0){
            title.setText(currentJob.getTitle());
            company.setText(currentJob.getCompany());
            Location currentLocation = currentJob.getLocation();
            col.setText(String.valueOf(currentLocation.getCostOfLiving()));
            city.setText(currentLocation.getCity());
            yearlySalary.setText(String.valueOf(currentJob.getYearlySalary().getAmount()));
            yearlyBonus.setText(String.valueOf(currentJob.getYearlyBonus().getAmount()));
            leaveTime.setText(String.valueOf(currentJob.getLeaveTime()));
            gymAllowance.setText(String.valueOf(currentJob.getGymAllowance().getAmount()));
            this.selectedRemoteDays = currentJob.getAllowedWeeklyTWDays();
            this.selectedState = currentLocation.getState();
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
        setContentView(R.layout.activity_add_edit_jd);
        // Populate UI objects.
        populateControls();
        // Set the back end and database objects
        intializebackEnd();
        // Get the data from backend to populate the fields in case of an already existing job.
        setAddEditDetails();
        // Initiate spinners if data already exists to select the corresponding element
        setupStateSpinner();
        setupTeleworkSpinner();
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
        }if(leaveTime.length() < 1) {
            leaveTime.setError("Can't be empty");
            return;
        }if(gymAllowance.length() < 1) {
            gymAllowance.setError("Can't be empty");
            return;
        }
        Job jobDetails = this.currentJob;

        jobDetails.setTitle(title.getText().toString());
        jobDetails.setCompany(company.getText().toString());

        jobDetails.setAllowedWeeklyTWDays(this.selectedRemoteDays);
        jobDetails.setLeaveTime(Integer.parseInt(this.leaveTime.getText().toString()));

        Money allowance = new Money(Double.parseDouble(this.gymAllowance.getText().toString()),"USD");
        jobDetails.setGymAllowance(allowance);

        Money salary = new Money(Double.parseDouble(this.yearlySalary.getText().toString()), "USD");
        jobDetails.setYearlySalary(salary);

        Money bonus = new Money(Double.parseDouble(this.yearlyBonus.getText().toString()), "USD");
        jobDetails.setYearlyBonus(bonus);

        Location jobLocation = new Location(Integer.parseInt(col.getText().toString()), this.selectedState, this.city.getText().toString());
        jobDetails.setLocation(jobLocation);
        jobDetails.setCurrent(true);

        boolean isSavedDetails = controller.setOrUpdateCurrentJob(jobDetails);

        if(isSavedDetails){
            this.currentJob = controller.getCurrentJob();
            findViewById(R.id.saveJDMessage).setVisibility(View.VISIBLE);

            Handler handler = new Handler(Looper.getMainLooper());
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    findViewById(R.id.saveJDMessage).setVisibility(View.INVISIBLE);
                    createDialog(view);
                    alertDialog.show();
                }
            };
            handler.postDelayed(runnable, 1000);
        }else{
            Toast errorToast = Toast.makeText(this, "Error, please check your input and try again!", Toast.LENGTH_SHORT);
            errorToast.show();
        }
    }
    public void backToHome(View view){
        Intent homeActivity = new Intent(this, MainActivity.class);
        startActivity(homeActivity);
    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
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

}