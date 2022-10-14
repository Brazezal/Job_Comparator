package edu.gatech.seclass.jobcompare6300.vo;

public class Location {
    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getCostOfLiving() {
        return costOfLiving;
    }

    private int costOfLiving;
    private String state;
    private String city;

    public Location(int costOfLiving, String state, String city) {
        this.costOfLiving = costOfLiving;
        this.state = state != null ? state : "";
        this.city = city != null ? city : "";
    }

    @Override
    public String toString() {
        return "Location{" +
                "costOfLiving=" + costOfLiving +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public boolean isValid() {
        return costOfLiving > 0 && state.length() > 0 && city.length() > 0;
    }
}