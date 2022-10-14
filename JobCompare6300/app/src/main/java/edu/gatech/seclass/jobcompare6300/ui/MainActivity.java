package edu.gatech.seclass.jobcompare6300.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.controller.JobsController;
import edu.gatech.seclass.jobcompare6300.dao.DBHelper;

public class MainActivity extends AppCompatActivity {
    JobsController controller;
    BackEndFactory factory;
    DBHelper dbHelper;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intializebackEnd();
        int currentCount = controller.getJobsCount();
        if(currentCount > 1){
            findViewById(R.id.compareJO).setEnabled(true);
        }
    }

    public void handleJDClick(View view){
        Intent addEditJD = new Intent(this, addEditJD.class);
        startActivity(addEditJD, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void handleWeightClick(View view){
        Intent comparisonWeights = new Intent(this, adjustComparisonSettings.class);
        startActivity(comparisonWeights, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void handleOfferClick(View view){
        Intent comparisonWeights = new Intent(this, addEditOffer.class);
        startActivity(comparisonWeights, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void handleOfferCompare(View view){
        Intent comparisonWeights = new Intent(this, listCompare.class);
        startActivity(comparisonWeights, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}