package com.cary.stq.to;

import java.io.Serializable;

public class WorkDayTo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String naturalDay;

    private String isWorkDay;

    public String getNaturalDay() {
        return naturalDay;
    }

    public void setNaturalDay(String naturalDay) {
        this.naturalDay = naturalDay;
    }

    public String getIsWorkDay() {
        return isWorkDay;
    }

    public void setIsWorkDay(String workDay) {
        isWorkDay = workDay;
    }
}