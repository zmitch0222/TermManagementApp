package com.mobile.zackmitchellc196.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Assessments")
public class Assessment {
    @PrimaryKey(autoGenerate = true)
    private int AssessmentID;
    private String AssessmentTitle;
    private String AssessmentType;
    private String AssessmentStart;
    private String AssessmentEnd;
    private int CourseID;
    private int UserID;

    public Assessment(int assessmentID, String assessmentTitle, String assessmentType, String assessmentStart, String assessmentEnd, int courseID, int userID) {
        AssessmentID = assessmentID;
        AssessmentTitle = assessmentTitle;
        AssessmentType = assessmentType;
        AssessmentStart = assessmentStart;
        AssessmentEnd = assessmentEnd;
        CourseID = courseID;
        UserID = userID;
    }

    public Assessment() {
    }

    public int getAssessmentID() {
        return AssessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        AssessmentID = assessmentID;
    }

    public String getAssessmentTitle() {
        return AssessmentTitle;
    }

    public void setAssessmentTitle(String assessmentTitle) {
        AssessmentTitle = assessmentTitle;
    }

    public String getAssessmentType() {
        return AssessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        AssessmentType = assessmentType;
    }

    public String getAssessmentStart() {
        return AssessmentStart;
    }

    public void setAssessmentStart(String assessmentStart) {
        AssessmentStart = assessmentStart;
    }

    public String getAssessmentEnd() {
        return AssessmentEnd;
    }

    public void setAssessmentEnd(String assessmentEnd) {
        AssessmentEnd = assessmentEnd;
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int courseID) {
        CourseID = courseID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }
}

