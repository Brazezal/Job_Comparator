package edu.gatech.seclass.jobcompare6300.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Formatter;
import java.util.List;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.controller.JobsController;
import edu.gatech.seclass.jobcompare6300.dao.DBHelper;
import edu.gatech.seclass.jobcompare6300.vo.Job;

public class compareJobs extends AppCompatActivity {

    private long jobId1;
    private long jobId2;
    private JobsController controller;

    private void populateTable(){
        List<Job> jobDetails = controller.compareJobs(this.jobId1, this.jobId2);

        Job job1 = jobDetails.get(0);
        Job job2 = jobDetails.get(1);

        LinearLayout listParent =  findViewById(R.id.parentCompare);;

        LinearLayout companyParent = new LinearLayout(this.getApplicationContext());
        companyParent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        companyParent.setOrientation(LinearLayout.HORIZONTAL);
        companyParent.setPadding(80, 100, 0,0);

        TextView labelView = new TextView(this.getApplicationContext());
        labelView.setLayoutParams(new LinearLayout.LayoutParams(500, LinearLayout.LayoutParams.WRAP_CONTENT));
        labelView.setText("Company");
        labelView.setTextSize(20);

        TextView company1View = new TextView(this.getApplicationContext());
        company1View.setLayoutParams(new LinearLayout.LayoutParams(500, LinearLayout.LayoutParams.WRAP_CONTENT));
        company1View.setPadding(50,0,0,0);
        company1View.setText(job1.getCompany());
        company1View.setTextSize(18);

        TextView company2View = new TextView(this.getApplicationContext());
        company2View.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        company2View.setPadding(50,0,0,0);
        company2View.setText(job2.getCompany());
        company2View.setTextSize(18);

        companyParent.addView(labelView);
        companyParent.addView(company1View);
        companyParent.addView(company2View);

        LinearLayout positionParent = new LinearLayout(this.getApplicationContext());
        positionParent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        positionParent.setOrientation(LinearLayout.HORIZONTAL);
        positionParent.setPadding(80, 100, 0,0);

        TextView label2View = new TextView(this.getApplicationContext());
        label2View.setLayoutParams(new LinearLayout.LayoutParams(500, LinearLayout.LayoutParams.WRAP_CONTENT));
        label2View.setText("Position");
        label2View.setTextSize(20);

        TextView position1View = new TextView(this.getApplicationContext());
        position1View.setLayoutParams(new LinearLayout.LayoutParams(500, LinearLayout.LayoutParams.WRAP_CONTENT));
        position1View.setPadding(50,0,0,0);
        position1View.setText(job1.getTitle());
        position1View.setTextSize(18);

        TextView position2View = new TextView(this.getApplicationContext());
        position2View.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        position2View.setPadding(50,0,0,0);
        position2View.setText(job2.getTitle());
        position2View.setTextSize(18);

        positionParent.addView(label2View);
        positionParent.addView(position1View);
        positionParent.addView(position2View);

        LinearLayout locationParent = new LinearLayout(this.getApplicationContext());
        locationParent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        locationParent.setOrientation(LinearLayout.HORIZONTAL);
        locationParent.setPadding(80, 100, 0,0);

        TextView label3View = new TextView(this.getApplicationContext());
        label3View.setLayoutParams(new LinearLayout.LayoutParams(500, LinearLayout.LayoutParams.WRAP_CONTENT));
        label3View.setText("Location");
        label3View.setTextSize(20);

        TextView location1View = new TextView(this.getApplicationContext());
        location1View.setLayoutParams(new LinearLayout.LayoutParams(500, LinearLayout.LayoutParams.WRAP_CONTENT));
        location1View.setPadding(50,0,0,0);
        location1View.setText(job1.getLocation().getCity()+", "+job1.getLocation().getState());
        location1View.setTextSize(18);

        TextView location2View = new TextView(this.getApplicationContext());
        location2View.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        location2View.setPadding(50,0,0,0);
        location2View.setText(job2.getLocation().getCity()+", "+job2.getLocation().getState());
        location2View.setTextSize(18);

        locationParent.addView(label3View);
        locationParent.addView(location1View);
        locationParent.addView(location2View);

        LinearLayout colParent = new LinearLayout(this.getApplicationContext());
        colParent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        colParent.setOrientation(LinearLayout.HORIZONTAL);
        colParent.setPadding(80, 100, 0,0);

        TextView label4View = new TextView(this.getApplicationContext());
        label4View.setLayoutParams(new LinearLayout.LayoutParams(500, LinearLayout.LayoutParams.WRAP_CONTENT));
        label4View.setText("Salary");
        label4View.setTextSize(20);

        double adjustedSalaryJ1 = job1.getAdjustedSalary().getAmount();
        TextView col1View = new TextView(this.getApplicationContext());
        col1View.setLayoutParams(new LinearLayout.LayoutParams(500, LinearLayout.LayoutParams.WRAP_CONTENT));
        col1View.setPadding(50,0,0,0);
        col1View.setText(new Formatter().format("%.2f", adjustedSalaryJ1).toString());
        col1View.setTextSize(18);

        double adjustedSalaryJ2 = job2.getAdjustedSalary().getAmount();
        TextView col2View = new TextView(this.getApplicationContext());
        col2View.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        col2View.setPadding(50,0,0,0);
        col2View.setText(new Formatter().format("%.2f", adjustedSalaryJ2).toString());
        col2View.setTextSize(18);

        colParent.addView(label4View);
        colParent.addView(col1View);
        colParent.addView(col2View);

        LinearLayout bonusParent = new LinearLayout(this.getApplicationContext());
        bonusParent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        bonusParent.setOrientation(LinearLayout.HORIZONTAL);
        bonusParent.setPadding(80, 100, 0,0);

        TextView label5View = new TextView(this.getApplicationContext());
        label5View.setLayoutParams(new LinearLayout.LayoutParams(500, LinearLayout.LayoutParams.WRAP_CONTENT));
        label5View.setText("Bonus");
        label5View.setTextSize(20);

        double adjustedbonusJ1 = job1.getAdjustedBonus().getAmount();
        TextView bonus1View = new TextView(this.getApplicationContext());
        bonus1View.setLayoutParams(new LinearLayout.LayoutParams(500, LinearLayout.LayoutParams.WRAP_CONTENT));
        bonus1View.setPadding(50,0,0,0);
        bonus1View.setText(new Formatter().format("%.2f", adjustedbonusJ1).toString());
        bonus1View.setTextSize(18);

        double adjustedbonusJ2 = job2.getAdjustedBonus().getAmount();
        TextView bonus2View = new TextView(this.getApplicationContext());
        bonus2View.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        bonus2View.setPadding(50,0,0,0);
        bonus2View.setText(new Formatter().format("%.2f", adjustedbonusJ2).toString());
        bonus2View.setTextSize(18);

        bonusParent.addView(label5View);
        bonusParent.addView(bonus1View);
        bonusParent.addView(bonus2View);

        LinearLayout leaveParent = new LinearLayout(this.getApplicationContext());
        leaveParent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        leaveParent.setOrientation(LinearLayout.HORIZONTAL);
        leaveParent.setPadding(80, 100, 0,0);

        TextView label6View = new TextView(this.getApplicationContext());
        label6View.setLayoutParams(new LinearLayout.LayoutParams(500, LinearLayout.LayoutParams.WRAP_CONTENT));
        label6View.setText("Leave Time");
        label6View.setTextSize(20);

        TextView leave1View = new TextView(this.getApplicationContext());
        leave1View.setLayoutParams(new LinearLayout.LayoutParams(500, LinearLayout.LayoutParams.WRAP_CONTENT));
        leave1View.setPadding(50,0,0,0);
        leave1View.setText(String.valueOf(job1.getLeaveTime()));
        leave1View.setTextSize(18);

        TextView leave2View = new TextView(this.getApplicationContext());
        leave2View.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        leave2View.setPadding(50,0,0,0);
        leave2View.setText(String.valueOf(job2.getLeaveTime()));
        leave2View.setTextSize(18);

        leaveParent.addView(label6View);
        leaveParent.addView(leave1View);
        leaveParent.addView(leave2View);

        LinearLayout remoteParent = new LinearLayout(this.getApplicationContext());
        remoteParent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        remoteParent.setOrientation(LinearLayout.HORIZONTAL);
        remoteParent.setPadding(80, 100, 0,0);

        TextView label7View = new TextView(this.getApplicationContext());
        label7View.setLayoutParams(new LinearLayout.LayoutParams(500, LinearLayout.LayoutParams.WRAP_CONTENT));
        label7View.setText("Telework Days");
        label7View.setTextSize(20);

        TextView remote1View = new TextView(this.getApplicationContext());
        remote1View.setLayoutParams(new LinearLayout.LayoutParams(500, LinearLayout.LayoutParams.WRAP_CONTENT));
        remote1View.setPadding(50,0,0,0);
        remote1View.setText(String.valueOf(job1.getAllowedWeeklyTWDays()));
        remote1View.setTextSize(18);

        TextView remote2View = new TextView(this.getApplicationContext());
        remote2View.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        remote2View.setPadding(50,0,0,0);
        remote2View.setText(String.valueOf(job2.getAllowedWeeklyTWDays()));
        remote2View.setTextSize(18);

        remoteParent.addView(label7View);
        remoteParent.addView(remote1View);
        remoteParent.addView(remote2View);

        LinearLayout allowanceParent = new LinearLayout(this.getApplicationContext());
        allowanceParent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        allowanceParent.setOrientation(LinearLayout.HORIZONTAL);
        allowanceParent.setPadding(80, 100, 0,0);

        TextView label8View = new TextView(this.getApplicationContext());
        label8View.setLayoutParams(new LinearLayout.LayoutParams(500, LinearLayout.LayoutParams.WRAP_CONTENT));
        label8View.setText("Gym Allowance");
        label8View.setTextSize(20);

        TextView allowance1View = new TextView(this.getApplicationContext());
        allowance1View.setLayoutParams(new LinearLayout.LayoutParams(500, LinearLayout.LayoutParams.WRAP_CONTENT));
        allowance1View.setPadding(50,0,0,0);
        allowance1View.setText(String.valueOf(job1.getGymAllowance().getAmount()));
        allowance1View.setTextSize(18);

        TextView allowance2View = new TextView(this.getApplicationContext());
        allowance2View.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        allowance2View.setPadding(50,0,0,0);
        allowance2View.setText(String.valueOf(job2.getGymAllowance().getAmount()));
        allowance2View.setTextSize(18);

        allowanceParent.addView(label8View);
        allowanceParent.addView(allowance1View);
        allowanceParent.addView(allowance2View);

        listParent.addView(companyParent);
        listParent.addView(positionParent);
        listParent.addView(locationParent);
        listParent.addView(colParent);
        listParent.addView(bonusParent);
        listParent.addView(leaveParent);
        listParent.addView(remoteParent);
        listParent.addView(allowanceParent);

    }

    private void setBackEnd(){
        DBHelper dbHelper = new DBHelper(this);
        controller = new JobsController(dbHelper);
        this.controller = controller;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setEnterTransition(new Explode());
        setContentView(R.layout.activity_compare_jobs);

        Bundle extras = getIntent().getExtras();
        this.jobId1 = (long)extras.get("ID1");
        this.jobId2 = (long)extras.get("ID2");
        setBackEnd();
        populateTable();
    }

    public void backToHome(View view){
        Intent homeActivity = new Intent(this, MainActivity.class);
        startActivity(homeActivity);
    }

    public void backToList(View view){
        Intent homeActivity = new Intent(this, listCompare.class);
        startActivity(homeActivity);
    }
}