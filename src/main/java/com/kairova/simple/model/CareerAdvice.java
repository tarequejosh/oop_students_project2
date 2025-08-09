package com.kairova.simple.model;

import java.util.List;

public class CareerAdvice {
    private String interest;
    private List<String> careerOptions;
    private List<String> skillsNeeded;
    private List<String> resources;

    // Getters and Setters
    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public List<String> getCareerOptions() {
        return careerOptions;
    }

    public void setCareerOptions(List<String> careerOptions) {
        this.careerOptions = careerOptions;
    }

    public List<String> getSkillsNeeded() {
        return skillsNeeded;
    }

    public void setSkillsNeeded(List<String> skillsNeeded) {
        this.skillsNeeded = skillsNeeded;
    }

    public List<String> getResources() {
        return resources;
    }

    public void setResources(List<String> resources) {
        this.resources = resources;
    }
}
