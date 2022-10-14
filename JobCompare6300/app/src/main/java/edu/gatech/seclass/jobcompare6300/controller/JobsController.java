package edu.gatech.seclass.jobcompare6300.controller;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.jobcompare6300.dao.DBHelper;
import edu.gatech.seclass.jobcompare6300.service.JobsDataManager;
import edu.gatech.seclass.jobcompare6300.vo.ComparisonSettings;
import edu.gatech.seclass.jobcompare6300.vo.Job;
import edu.gatech.seclass.jobcompare6300.vo.RankedJobEntry;

public class JobsController {
    private JobsDataManager jobs;
    private ComparisonSettings settings;
    private DBHelper helper;
    private long insertedId;

    public JobsController(DBHelper helper){
        this.helper = helper;
        // Since manager is doing DB processing it will be passed an instance of DBHelper
        this.jobs = new JobsDataManager(helper);
        settings  = helper.fetchWeights();
    }

    // We are using this method to update DBHelper acrosds pages so that we can maintain data between pages.
    public void updateHelper(DBHelper helper){
        this.helper = helper;
        this.jobs.updateHelper(helper);
    }

    public boolean setOrUpdateCurrentJob(Job job) {
        if(!job.isValid()) return false;
        jobs.setCurrentJob(job);
        return true;
    }

    public boolean addJobOffer(Job job) {
        if (!job.isValid()) return false;
        //New job
        if(job.getId() == 0){
            jobs.addJobOffer(job);
            this.insertedId = helper.currentId;
        }else{
            jobs.updateJobOffer(job.getId(), job);
            this.insertedId = job.getId();
        }
        return true;
    }

    public List<Job> compareJobs(long j1, long j2) {
        List<Job> jl = new ArrayList<>();
        jl.add(jobs.getJob(j1));
        jl.add(jobs.getJob(j2));
        return jl;
    }

    // Logic for saving the updated comparison settings.
    public boolean UpdateSettings(ComparisonSettings settings){
        helper.updateWeights(settings);
        this.settings = settings;
        return true;
    }

    public long getInsertedId(){
        return this.insertedId;
    }

    public List<RankedJobEntry> getRankedJobs() {
        return jobs.getRankedJobs(settings);
    }

    public int getJobsCount(){
           return jobs.getJobCount();
       }

    public Job getCurrentJob(){
        return jobs.getCurrentJob();
    }

    public ComparisonSettings getWeights(){
            return this.settings;
       }
}