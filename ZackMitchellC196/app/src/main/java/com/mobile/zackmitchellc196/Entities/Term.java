package com.mobile.zackmitchellc196.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Terms")
public class Term {
    @PrimaryKey(autoGenerate = true)
    private int TermID;
    private String TermTitle;
    private String TermStart;
    private String TermEnd;
    private int UserID;

    public Term(int termID, String termTitle, String termStart, String termEnd, int userID) {
        TermID = termID;
        TermTitle = termTitle;
        TermStart = termStart;
        TermEnd = termEnd;
        UserID = userID;
    }

    public Term() {
    }

    public int getTermID() {
        return TermID;
    }

    public void setTermID(int termID) {
        TermID = termID;
    }

    public String getTermTitle() {
        return TermTitle;
    }

    public void setTermTitle(String termTitle) {
        TermTitle = termTitle;
    }

    public String getTermStart() {
        return TermStart;
    }

    public void setTermStart(String termStart) {
        TermStart = termStart;
    }

    public String getTermEnd() {
        return TermEnd;
    }

    public void setTermEnd(String termEnd) {
        TermEnd = termEnd;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }
}
