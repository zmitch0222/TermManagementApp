package com.mobile.zackmitchellc196.DB;

import android.app.Application;

import com.mobile.zackmitchellc196.DAO.AssessmentDAO;
import com.mobile.zackmitchellc196.DAO.CourseDAO;
import com.mobile.zackmitchellc196.DAO.TermDAO;
import com.mobile.zackmitchellc196.DAO.UserDAO;
import com.mobile.zackmitchellc196.Entities.Assessment;
import com.mobile.zackmitchellc196.Entities.Course;
import com.mobile.zackmitchellc196.Entities.Term;
import com.mobile.zackmitchellc196.Entities.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private final TermDAO mTermDAO;
    private final CourseDAO mCourseDAO;
    private final AssessmentDAO mAssessmentDAO;
    private final UserDAO mUserDAO;
    private List<Term> mAllTerms;
    private List<Course> mAllCourses;
    private List<Assessment> mAllAssessments;
    private List<User> mAllUsers;

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application) {
        SchoolDatabaseBuilder db = SchoolDatabaseBuilder.getDatabase(application);
        mTermDAO = db.TermDAO();
        mCourseDAO = db.CourseDAO();
        mAssessmentDAO = db.AssessmentDAO();
        mUserDAO = db.UserDAO();
    }

    public List<Term> getAllTerms() {
        databaseExecutor.execute(() -> {
            mAllTerms = mTermDAO.getAllTerms();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllTerms;
    }

    public void insert(Term Term) {
        databaseExecutor.execute(() -> {
            mTermDAO.insert(Term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Term Term) {
        databaseExecutor.execute(() -> {
            mTermDAO.update(Term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Term Term) {
        databaseExecutor.execute(() -> {
            mTermDAO.delete(Term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Course> getAllCourses() {
        databaseExecutor.execute(() -> {
            mAllCourses = mCourseDAO.getAllCourses();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCourses;
    }

    public void insert(Course Course) {
        databaseExecutor.execute(() -> {
            mCourseDAO.insert(Course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Course Course) {
        databaseExecutor.execute(() -> {
            mCourseDAO.update(Course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Course Course) {
        databaseExecutor.execute(() -> {
            mCourseDAO.delete(Course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Assessment> getAllAssessment() {
        databaseExecutor.execute(() -> {
            mAllAssessments = mAssessmentDAO.getAllAssessments();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllAssessments;
    }

    public void insert(Assessment Assessment) {
        databaseExecutor.execute(() -> {
            mAssessmentDAO.insert(Assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Assessment Assessment) {
        databaseExecutor.execute(() -> {
            mAssessmentDAO.update(Assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Assessment Assessment) {
        databaseExecutor.execute(() -> {
            mAssessmentDAO.delete(Assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        databaseExecutor.execute(() -> {
            mAllUsers = mUserDAO.getAllUsers();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllUsers;
    }

    public User getUsername(String username) {
        return mUserDAO.getUsername(username);
    }

    public void insert(User User) {
        databaseExecutor.execute(() -> {
            mUserDAO.insert(User);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(User User) {
        databaseExecutor.execute(() -> {
            mUserDAO.update(User);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(User User) {
        databaseExecutor.execute(() -> {
            mUserDAO.delete(User);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
