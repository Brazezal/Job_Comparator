package edu.gatech.seclass.jobcompare6300.vo;

public class RankedJobEntry {
    private String title;
    private String company;
    private boolean isCurrent;
    private long id;
    private double ranking;

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public long getId() {
        return id;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public double getRanking() {
        return ranking;
    }

    public RankedJobEntry(String title, String company, boolean isCurrent, long id, double ranking) {
        this.title = title;
        this.company = company;
        this.isCurrent = isCurrent;
        this.id = id;
        this.ranking = ranking;
    }
}