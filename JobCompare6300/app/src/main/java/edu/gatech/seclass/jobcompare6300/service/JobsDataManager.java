package edu.gatech.seclass.jobcompare6300.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.gatech.seclass.jobcompare6300.dao.DBHelper;
import edu.gatech.seclass.jobcompare6300.vo.ComparisonSettings;
import edu.gatech.seclass.jobcompare6300.vo.Job;
import edu.gatech.seclass.jobcompare6300.vo.RankedJobEntry;

public class JobsDataManager {
    private HashMap<Long, Job> jobs;
    private long currentJobId = -1;
    private DBHelper helper;

    public JobsDataManager (DBHelper helper){
        this.helper = helper;
        jobs = new HashMap<>();
    }

    public void updateHelper(DBHelper helper){
        this.helper = helper;
    }
    public boolean setCurrentJob(Job jobDetails){
        helper.insertJD(jobDetails);
        this.currentJobId = helper.currentId;
        return true;
    }

    public boolean addJobOffer(Job job) {
        if (job.getId() > 0) return false;
        helper.insertJD(job);
        jobs.put(helper.currentId, job);
        return true;
    }

    public boolean updateJobOffer(long id, Job job) {
        if (id == currentJobId) return false; // should update current job via set current job, not an offer.
        if (jobs.containsKey(id) ) jobs.remove(id);
        else return false; // no job to update
        helper.insertJD(job);
        jobs.put(id, job);
        return true;
    }

    public Job getJob(long id) {
        if (jobs.containsKey(id)) return jobs.get(id);
        return helper.getJob(id);

    }

    public Job getCurrentJob() {
        Job currentJob = helper.getCurrentjob();
        // Save only that data which has been populated.
        if(currentJob.getId() != 0) {
            this.currentJobId = currentJob.getId();
            jobs.put(this.currentJobId, currentJob);
        }
        return currentJob;
    }

    public List<RankedJobEntry> getRankedJobs(ComparisonSettings settings) {
        List<RankedJobEntry> ranked = new ArrayList<>();
        List<Job> jobList = helper.getAllJobs();

        for (Job j : jobList) {
            String strcompany = j.getCompany();
            if (j.isCurrent()) strcompany = strcompany + " (Current)";
            ranked.add(new RankedJobEntry(
                    j.getTitle(), strcompany, j.isCurrent(), j.getId(), j.getRanking(settings) ));
        }


        /*for (Job j : jobs.values()) {
            ranked.add(new RankedJobEntry(
                    j.getTitle(), j.getCompany(), j.isCurrent(), j.getId(), j.getRanking(settings) ));
        }*/

        // reverse sorting order to resolve bug 
        ranked.sort(( j1,  j2)-> Double.compare( -j1.getRanking(), -j2.getRanking()));
        return ranked;
    }

    public int getJobCount(){
        return helper.getTotaljobCount();
    }
}
