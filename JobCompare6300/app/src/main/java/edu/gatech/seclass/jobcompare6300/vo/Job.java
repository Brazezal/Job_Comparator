package edu.gatech.seclass.jobcompare6300.vo;

public class Job {
    private long id;
    private String title;
    private String company;
    private Location location;
    private Money yearlySalary;
    private Money yearlyBonus;
    private int allowedWeeklyTWDays;
    private int leaveTime;
    private Money gymAllowance;
    private boolean isCurrent;


    public Money getAdjustedSalary() {
        return new Money(yearlySalary.getAmount() / (location.getCostOfLiving()/100.0), yearlySalary.getCurrency());
    }

    public Money getAdjustedBonus() {
        return new Money(yearlyBonus.getAmount() / (location.getCostOfLiving()/100.0), yearlyBonus.getCurrency());
    }

    public int getAllowedWeeklyTWDays() {
        return allowedWeeklyTWDays;
    }

    public int getLeaveTime() {
        return leaveTime;
    }

    public Location getLocation() {
        return location;
    }

    public long getId() {
        return id;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public Money getGymAllowance() {
        return gymAllowance;
    }

    public Money getYearlyBonus() {
        return yearlyBonus;
    }

    public Money getYearlySalary() {
        return yearlySalary;
    }

    public String getCompany() {
        return company;
    }

    public String getTitle() {
        return title;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setYearlySalary(Money yearlySalary) {

        this.yearlySalary = yearlySalary;

    }

    public void setYearlyBonus(Money yearlyBonus) {
        this.yearlyBonus = yearlyBonus;
    }

    public void setAllowedWeeklyTWDays(int allowedWeeklyTWDays) {
        this.allowedWeeklyTWDays = allowedWeeklyTWDays;
    }

    public void setLeaveTime(int leaveTime) {
        this.leaveTime = leaveTime;
    }

    public void setGymAllowance(Money gymAllowance) {
        this.gymAllowance = gymAllowance;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

    public Job(){
        super();
    }
    public Job(long id,
               String title,
               String company,
               Location location,
               Money yearlySalary,
               Money yearlyBonus,
               int allowedWeeklyTWDays,
               int leaveTime,
               Money gymAllowance,
               boolean isCurrent) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.location = location;
        this.yearlySalary = yearlySalary != null ? yearlySalary : new Money(0, "$");
        this.yearlyBonus = yearlyBonus != null ? yearlyBonus : new Money(0, "$");
        this.allowedWeeklyTWDays = allowedWeeklyTWDays;
        this.leaveTime = leaveTime;
        this.gymAllowance = gymAllowance != null ? gymAllowance : new Money(0, "$");
        this.isCurrent = isCurrent;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", company='" + company + '\'' +
                ", location=" + location +
                ", yearlySalary=" + yearlySalary +
                ", yearlyBonus=" + yearlyBonus +
                ", allowedWeeklyTWDays=" + allowedWeeklyTWDays +
                ", leaveTime=" + leaveTime +
                ", gymAllowance=" + gymAllowance +
                ", isCurrent=" + isCurrent +
                '}';
    }

    public boolean isValid() {
        return  title.length() > 0 &&
                company.length() > 0 &&
                location.isValid() &&
                yearlySalary.gte(0) &&
                yearlyBonus.gte(0) &&
                gymAllowance.gte( 0) &&  gymAllowance.lte( 500) &&
                leaveTime >=0 && leaveTime <= 365 &&
                allowedWeeklyTWDays >= 0 && allowedWeeklyTWDays <= 5 ;
    }

    public double getRanking(ComparisonSettings weights) {
        double adjustedSalary = getAdjustedSalary().getAmount();
        double adjustedBonus = getAdjustedBonus().getAmount();

        return weights.getYearlySalaryWeight() * adjustedSalary + weights.getYearlyBonusWeight() * adjustedBonus +
                weights.getGymAllowanceWeight() * gymAllowance.getAmount() +
                weights.getLeaveTimeWeight() * (leaveTime * adjustedSalary / 260) -
                weights.getAllowedWeeklyTWDaysWeight() * ((260.0 - 52.0*allowedWeeklyTWDays) * (adjustedSalary/260)/8.0);
    }

}
