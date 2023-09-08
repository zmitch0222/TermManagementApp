package com.mobile.zackmitchellc196.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mobile.zackmitchellc196.DAO.AssessmentDAO;
import com.mobile.zackmitchellc196.DAO.CourseDAO;
import com.mobile.zackmitchellc196.DAO.TermDAO;
import com.mobile.zackmitchellc196.DAO.UserDAO;
import com.mobile.zackmitchellc196.Entities.Assessment;
import com.mobile.zackmitchellc196.Entities.Course;
import com.mobile.zackmitchellc196.Entities.Term;
import com.mobile.zackmitchellc196.Entities.User;

@Database(entities = {Term.class, Course.class, Assessment.class, User.class}, version = 11, exportSchema = false)
public abstract class SchoolDatabaseBuilder extends RoomDatabase {
    public abstract TermDAO TermDAO();

    public abstract CourseDAO CourseDAO();

    public abstract AssessmentDAO AssessmentDAO();

    public abstract UserDAO UserDAO();

    private static volatile SchoolDatabaseBuilder INSTANCE;



    static SchoolDatabaseBuilder getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SchoolDatabaseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), SchoolDatabaseBuilder.class, "MySchoolDatabase.db")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }

            }

        }
        return INSTANCE;
    }
}
