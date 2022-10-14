package edu.gatech.seclass.jobcompare6300.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.controller.JobsController;
import edu.gatech.seclass.jobcompare6300.dao.DBHelper;
import edu.gatech.seclass.jobcompare6300.vo.RankedJobEntry;

public class listCompare extends AppCompatActivity {

    private int clickedCnt = 0;
    private List<RankedJobEntry> rankedList;
    private JobsController controller;
    Set<Long> selectedJobs = new HashSet<Long>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setEnterTransition(new Explode());
        setContentView(R.layout.activity_list_compare);
        setBackEnd();
        this.rankedList = controller.getRankedJobs();
        addOffers();
    }

    private void addOffers(){
        LinearLayout listParent = findViewById(R.id.listParent);

        for(RankedJobEntry rankedJob: this.rankedList){
            LinearLayout parent = new LinearLayout(this.getApplicationContext());
            parent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            parent.setOrientation(LinearLayout.HORIZONTAL);
            parent.setPadding(50, 100, 0,0);

            CheckBox cb = new CheckBox(this.getApplicationContext());
            cb.setId((int)(rankedJob.getId()));
            cb.setLayoutParams((new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)));
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onCBClick(view);
                }
            });

            TextView positionView = new TextView(this.getApplicationContext());
            positionView.setLayoutParams(new LinearLayout.LayoutParams(540, 100));
            positionView.setPadding(70,0,0,0);
            positionView.setText(rankedJob.getTitle());
            positionView.setTextSize(20);

            TextView companyView = new TextView(this.getApplicationContext());
            companyView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            companyView.setPadding(25,0,0,0);
            companyView.setText(rankedJob.getCompany());
            companyView.setTextSize(20);

            parent.addView(cb);
            parent.addView(positionView);
            parent.addView(companyView);


            listParent.addView(parent);
        }
    }



    private void setBackEnd(){
        DBHelper dbHelper = new DBHelper(this);
        controller = new JobsController(dbHelper);
        this.controller = controller;
    }

    public void onCBClick(View view){
        boolean checked = ((CheckBox) view).isChecked();
        long id = view.getId();
        if(checked) {
            selectedJobs.add(id);
            this.clickedCnt ++;
        }
        else {
            if(selectedJobs.contains(id)) selectedJobs.remove(id);
            this.clickedCnt --;
        }
    }

    public void compareOffers(View view){
        if(this.clickedCnt != 2){
            Toast errorToast = Toast.makeText(this, "Error, please select exactly 2 jobs to compare and try again !!!", Toast.LENGTH_SHORT);
            errorToast.show();
        }else{
            List<Long> idList = new ArrayList<>();
            for(long i: this.selectedJobs) idList.add(i);
            long id1 = idList.get(0);
            long id2 = idList.get(1);

            Intent jobCompare = new Intent(this, compareJobs.class);
            jobCompare.putExtra("ID1",id1);
            jobCompare.putExtra("ID2",id2);
            startActivity(jobCompare, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }

    }

    public void backToHome(View view){
        Intent homeActivity = new Intent(this, MainActivity.class);
        startActivity(homeActivity);
    }
}