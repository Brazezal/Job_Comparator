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
import android.widget.EditText;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.dao.DBHelper;
import edu.gatech.seclass.jobcompare6300.vo.ComparisonSettings;
import edu.gatech.seclass.jobcompare6300.controller.JobsController;

public class adjustComparisonSettings extends AppCompatActivity {

    private EditText salaryWeight;
    private EditText bonusWeight;
    private EditText remoteDaysWeight;
    private EditText leaveTimeWeight;
    private EditText gymAllowanceWeight;

    private JobsController controller;
    private DBHelper dbHelper;
    private ComparisonSettings comparisonWeights;

    private AlertDialog alertDialog;

    private void populateControls(){
        salaryWeight = findViewById(R.id.salaryWeight);
        bonusWeight = findViewById(R.id.bonusWeight);
        remoteDaysWeight = findViewById(R.id.remoteWeight);
        leaveTimeWeight = findViewById(R.id.leaveWeight);
        gymAllowanceWeight = findViewById(R.id.gymWeight);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setEnterTransition(new Explode());
        setContentView(R.layout.activity_adjust_comparison_settings);
        populateControls();
        dbHelper = new DBHelper(this);
        controller = new JobsController(dbHelper);
        comparisonWeights = controller.getWeights();

        salaryWeight.setText(String.valueOf(comparisonWeights.getYearlySalary()));
        bonusWeight.setText(String.valueOf(comparisonWeights.getYearlyBonus()));
        remoteDaysWeight.setText(String.valueOf(comparisonWeights.getAllowedWeeklyTWDays()));
        leaveTimeWeight.setText(String.valueOf(comparisonWeights.getLeaveTime()));
        gymAllowanceWeight.setText(String.valueOf(comparisonWeights.getGymAllowance()));

    }

    public void backToHome(View view){
        Intent homeActivity = new Intent(this, MainActivity.class);
        startActivity(homeActivity);
    }

    private void createDialog(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Return to main menu ?");

        alertDialogBuilder.setPositiveButton("yes",
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

    public void saveWeights(View view){

        if(salaryWeight.length() < 1) {
            salaryWeight.setError("Can't be empty");
            return;
        }
        if(bonusWeight.length() < 1) {
            bonusWeight.setError("Can't be empty");
            return;
        }
        if(remoteDaysWeight.length() < 1) {
            remoteDaysWeight.setError("Can't be empty");
            return;
        }
        if(leaveTimeWeight.length() < 1) {
            leaveTimeWeight.setError("Can't be empty");
            return;
        }
        if(gymAllowanceWeight.length() < 1) {
            gymAllowanceWeight.setError("Can't be empty");
            return;
        }

        int yearlySalaryWeight = Integer.parseInt(salaryWeight.getText().toString());
        int yearlyBonusWeight = Integer.parseInt(bonusWeight.getText().toString());
        int twDaysWeight = Integer.parseInt(remoteDaysWeight.getText().toString());
        int leaveWeight = Integer.parseInt(leaveTimeWeight.getText().toString());
        int allowanceWeight =  Integer.parseInt(gymAllowanceWeight.getText().toString());
        if(yearlySalaryWeight <= 0) {
            salaryWeight.setError("Can't be zero");
            return;
        }
        if(yearlyBonusWeight <= 0) {
            bonusWeight.setError("Can't be zero");
            return;
        }
        if(twDaysWeight <= 0) {
            remoteDaysWeight.setError("Can't be zero");
            return;
        }
        if(leaveWeight <= 0) {
            leaveTimeWeight.setError("Can't be zero");
            return;
        }
        if(allowanceWeight <= 0) {
            gymAllowanceWeight.setError("Can't be zero");
            return;
        }
        ComparisonSettings comparisonWeights = new ComparisonSettings(yearlySalaryWeight, yearlyBonusWeight, twDaysWeight, leaveWeight, allowanceWeight);



        boolean result = controller.UpdateSettings(comparisonWeights);
        if(result == true){
            findViewById(R.id.saveMessage).setVisibility(View.VISIBLE);

            Handler handler = new Handler(Looper.getMainLooper());
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    findViewById(R.id.saveMessage).setVisibility(View.INVISIBLE);
                    createDialog(view);
                    alertDialog.show();
                }
            };
            handler.postDelayed(runnable, 3000);
        }

    }
}