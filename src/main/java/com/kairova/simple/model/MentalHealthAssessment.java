package com.kairova.simple.model;

public class MentalHealthAssessment {
    private String feeling;
    private int stressLevel;
    private boolean sleepingWell;
    private boolean concentrationIssues;
    private boolean socializing;

    // Getters and Setters
    public String getFeeling() {
        return feeling;
    }

    public void setFeeling(String feeling) {
        this.feeling = feeling;
    }

    public int getStressLevel() {
        return stressLevel;
    }

    public void setStressLevel(int stressLevel) {
        this.stressLevel = stressLevel;
    }

    public boolean isSleepingWell() {
        return sleepingWell;
    }

    public void setSleepingWell(boolean sleepingWell) {
        this.sleepingWell = sleepingWell;
    }

    public boolean hasConcentrationIssues() {
        return concentrationIssues;
    }

    public void setConcentrationIssues(boolean concentrationIssues) {
        this.concentrationIssues = concentrationIssues;
    }

    public boolean isSocializing() {
        return socializing;
    }

    public void setSocializing(boolean socializing) {
        this.socializing = socializing;
    }
}
