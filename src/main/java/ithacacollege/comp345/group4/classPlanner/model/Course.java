package ithacacollege.comp345.group4.classPlanner.model;

import java.util.List;

public class Course {
    private String courseNum;
    private String name;
    private double credits;
    private List<SemestersOffered> semestersOffered;
    private CourseFrequency frequencyOffered;
    private List<String> prereqs;
    private List<List<String>> chooseOnes;

    public Course() {}

    public Course(String name, double credits, String courseNum, List<SemestersOffered> semestersOffered, CourseFrequency frequencyOffered, List<String> prereqs, List<List<String>> chooseOnes) {
        this.name = name;
        this.credits = credits;
        this.courseNum = courseNum;
        this.semestersOffered = semestersOffered;
        this.frequencyOffered = frequencyOffered;
        this.prereqs = prereqs;
        this.chooseOnes = chooseOnes;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    public List<SemestersOffered> getSemestersOffered() {
        return semestersOffered;
    }

    public void setSemestersOffered(List<SemestersOffered> semesters_offered) {
        this.semestersOffered = semesters_offered;
    }

    public CourseFrequency getFrequencyOffered() {
        return frequencyOffered;
    }

    public void setFrequencyOffered(CourseFrequency frequency_offered) {
        this.frequencyOffered = frequency_offered;
    }

    public List<String> getprereqs() {
        return prereqs;
    }

    public void setprereqs(List<String> preReqs) {
        this.prereqs = preReqs;
    }

    public List<List<String>> getChooseOnes() {
        return chooseOnes;
    }

    public void setChooseOnes(List<List<String>> chooseOnes) {
        this.chooseOnes = chooseOnes;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseNum='" + courseNum + '\'' +
                ", name='" + name + '\'' +
                ", credits=" + credits +
                ", semestersOffered=" + semestersOffered +
                ", frequencyOffered=" + frequencyOffered +
                ", prereqs=" + prereqs +
                ", chooseOnes=" + chooseOnes +
                '}';
    }
}
