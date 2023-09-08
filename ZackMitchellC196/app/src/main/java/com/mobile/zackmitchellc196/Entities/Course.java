package com.mobile.zackmitchellc196.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Courses")
public class Course {
    @PrimaryKey(autoGenerate = true)
    private int CourseID;
    private String CourseTitle;
    private String CourseStart;
    private String CourseEnd;
    private String Status;
    private String InstructorName;
    private String InstructorPhoneNumber;
    private String InstructorEmail;
    private int TermID;
    private int UserID;
    private String Notes;

    public Course(int courseID, String courseTitle, String courseStart, String courseEnd, String status, String instructorName, String instructorPhoneNumber, String instructorEmail, int termID, int userID, String notes) {
        CourseID = courseID;
        CourseTitle = courseTitle;
        CourseStart = courseStart;
        CourseEnd = courseEnd;
        Status = status;
        InstructorName = instructorName;
        InstructorPhoneNumber = instructorPhoneNumber;
        InstructorEmail = instructorEmail;
        TermID = termID;
        UserID = userID;
        Notes = notes;
    }

    public Course() {
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int courseID) {
        CourseID = courseID;
    }

    public String getCourseTitle() {
        return CourseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        CourseTitle = courseTitle;
    }

    public String getCourseStart() {
        return CourseStart;
    }

    public void setCourseStart(String courseStart) {
        CourseStart = courseStart;
    }

    public String getCourseEnd() {
        return CourseEnd;
    }

    public void setCourseEnd(String courseEnd) {
        CourseEnd = courseEnd;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getInstructorName() {
        return InstructorName;
    }

    public void setInstructorName(String instructorName) {
        InstructorName = instructorName;
    }

    public String getInstructorPhoneNumber() {
        return InstructorPhoneNumber;
    }

    public void setInstructorPhoneNumber(String instructorPhoneNumber) {
        InstructorPhoneNumber = instructorPhoneNumber;
    }

    public String getInstructorEmail() {
        return InstructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        InstructorEmail = instructorEmail;
    }

    public int getTermID() {
        return TermID;
    }

    public void setTermID(int termID) {
        TermID = termID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }
}
