package edu.gatech.seclass.jobcompare6300.vo;

public class ComparisonSettings {
    private int yearlySalary;
    private int yearlyBonus;
    private int allowedWeeklyTWDays;
    private int leaveTime;
    private int gymAllowance;
    private int sum;


    public ComparisonSettings(){
        super();
        this.yearlySalary = 1;
        this.yearlyBonus = 1;
        this.allowedWeeklyTWDays = 1;
        this.leaveTime = 1;
        this.gymAllowance = 1;
        this.sum = yearlySalary + yearlyBonus + allowedWeeklyTWDays + leaveTime + gymAllowance;
    }
    public ComparisonSettings(int yearlySalary, int yearlyBonus, int allowedWeeklyTWDays, int leaveTime, int gymAllowance) {
        this.yearlySalary = yearlySalary;
        this.yearlyBonus = yearlyBonus;
        this.allowedWeeklyTWDays = allowedWeeklyTWDays;
        this.leaveTime = leaveTime;
        this.gymAllowance = gymAllowance;
        this.sum = yearlySalary + yearlyBonus + allowedWeeklyTWDays + leaveTime + gymAllowance;
    }

    public int[] toList() {
        return new int[] {yearlySalary, yearlyBonus, allowedWeeklyTWDays, leaveTime, gymAllowance};
    }

    public int getLeaveTime() {
        return leaveTime;
    }

    public int getAllowedWeeklyTWDays() {
        return allowedWeeklyTWDays;
    }

    public int getGymAllowance() {
        return gymAllowance;
    }

    public int getYearlyBonus() {
        return yearlyBonus;
    }

    public int getYearlySalary() {
        return yearlySalary;
    }

    public double getLeaveTimeWeight() {

        return (double)leaveTime/sum;
    }

    public double getAllowedWeeklyTWDaysWeight() {
        return (double)allowedWeeklyTWDays/sum;
    }

    public double getGymAllowanceWeight() {
        return (double)gymAllowance/sum;
    }

    public double getYearlyBonusWeight() {
        return (double)yearlyBonus/sum;
    }

    public double getYearlySalaryWeight() {
        return (double)yearlySalary/sum;
    }

    @Override
    public String toString() {
        return "ComparisonSettings{" +
                "yearlySalary=" + yearlySalary +
                ", yearlyBonus=" + yearlyBonus +
                ", allowedWeeklyTWDays=" + allowedWeeklyTWDays +
                ", leaveTime=" + leaveTime +
                ", gymAllowance=" + gymAllowance +
                '}';
    }
}
