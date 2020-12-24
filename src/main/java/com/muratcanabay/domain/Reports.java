package com.muratcanabay.domain;

public class Reports {
    private String state;
    private String country;
    /**
     * totalCase: By country
     */
    private Long totalCase;
    private Long todayCase;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getTotalCase() {
        return totalCase;
    }

    public void setTotalCase(Long totalCase) {
        this.totalCase = totalCase;
    }

    public Long getTodayCase() {
        return todayCase;
    }

    public void setTodayCase(Long todayCase) {
        this.todayCase = todayCase;
    }

    @Override
    public String toString() {
        return "Reports{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", totalCase=" + totalCase +
                ", todayCase=" + todayCase +
                '}';
    }
}
