package edu.gatech.seclass.jobcompare6300.ui;

import edu.gatech.seclass.jobcompare6300.controller.JobsController;
import edu.gatech.seclass.jobcompare6300.dao.DBHelper;

public class BackEndFactory {
    private JobsController controller;
    private DBHelper helper;

    public void updateHelper(DBHelper helper){
        this.helper = helper;
        this.controller.updateHelper(helper);
    }
    public JobsController getController(){
        return controller;
    }
    public void setController(JobsController controller){
        this.controller = controller;
    }
}
