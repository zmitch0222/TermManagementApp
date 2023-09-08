package com.mobile.zackmitchellc196.UI;

import static com.mobile.zackmitchellc196.UI.MainActivity.userID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.zackmitchellc196.DB.Repository;
import com.mobile.zackmitchellc196.Entities.Assessment;
import com.mobile.zackmitchellc196.Entities.Course;
import com.mobile.zackmitchellc196.R;
import com.mobile.zackmitchellc196.Receiver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CourseDetails extends AppCompatActivity {
    int id;
    int TermID;
    int numAssessments;
    String title;
    String start;
    String end;
    String status;
    String instructorName;
    String instructorPhoneNumber;
    String instructorEmail;
    String notes;
    Course course;
    Course currentCourse;
    EditText editTitle;
    TextView editStart;
    TextView editEnd;
    EditText editStatus;
    EditText editInstructorName;
    EditText editInstructorPhoneNumber;
    EditText editInstructorEmail;
    EditText editNote;
    int uID;

    DatePickerDialog.OnDateSetListener startDate;
    final Calendar myCalendarStart = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener endDate;
    final Calendar myCalendarEnd = Calendar.getInstance();

    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        editTitle = findViewById(R.id.editCourseTitle);
        editStart = findViewById(R.id.editCourseStartDate);
        editEnd = findViewById(R.id.editCourseEndDate);
        editStatus = findViewById(R.id.editStatus);
        editNote = findViewById(R.id.editTextNote);
        editInstructorName = findViewById(R.id.editInstructorName);
        editInstructorPhoneNumber = findViewById(R.id.editInstructorPhoneNumber);
        editInstructorEmail = findViewById(R.id.editInstructorEmail);
        uID = userID;
        id = getIntent().getIntExtra("id", -1);
        title = getIntent().getStringExtra("title");
        start = getIntent().getStringExtra("start");
        end = getIntent().getStringExtra("end");
        status = getIntent().getStringExtra("status");
        instructorName = getIntent().getStringExtra("name");
        instructorPhoneNumber = getIntent().getStringExtra("phone");
        instructorEmail = getIntent().getStringExtra("email");
        TermID = getIntent().getIntExtra("termID", -1);
        notes = getIntent().getStringExtra("notes");
        editTitle.setText(title);
        editStart.setText(start);
        editEnd.setText(end);
        editStatus.setText(status);
        editInstructorName.setText(instructorName);
        editInstructorPhoneNumber.setText(instructorPhoneNumber);
        editInstructorEmail.setText(instructorEmail);
        editNote.setText(notes);
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        repository = new Repository(getApplication());
        RecyclerView recyclerView = findViewById(R.id.associatedAssessmentsView);
        repository = new Repository(getApplication());
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Assessment> filteredAssessments = new ArrayList<>();
        for (Assessment p : repository.getAllAssessment()) {
            if (p.getCourseID() == id) filteredAssessments.add(p);
        }
        assessmentAdapter.setAssessment(filteredAssessments);

        Button savebutton = findViewById(R.id.saveCourse);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id == -1) {
                    course = new Course(0, editTitle.getText().toString(), editStart.getText().toString(), editEnd.getText().toString(), editStatus.getText().toString(), editInstructorName.getText().toString(), editInstructorPhoneNumber.getText().toString(), editInstructorEmail.getText().toString(), TermID, uID, editNote.getText().toString());
                    repository.insert(course);
                    finish();

                } else {
                    course = new Course(id, editTitle.getText().toString(), editStart.getText().toString(), editEnd.getText().toString(), editStatus.getText().toString(), editInstructorName.getText().toString(), editInstructorPhoneNumber.getText().toString(), editInstructorEmail.getText().toString(), TermID, uID, editNote.getText().toString());
                    repository.update(course);
                    finish();

                }
            }
        });

        editStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String info = editStart.getText().toString();
                try {
                    myCalendarStart.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(CourseDetails.this, startDate, myCalendarStart
                        .get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH),
                        myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        editEnd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String info = editEnd.getText().toString();
                try {
                    myCalendarEnd.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(CourseDetails.this, endDate, myCalendarEnd
                        .get(Calendar.YEAR), myCalendarEnd.get(Calendar.MONTH),
                        myCalendarEnd.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        startDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH, monthOfYear);
                myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateLabelStart();
            }

        };

        endDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                myCalendarEnd.set(Calendar.YEAR, year);
                myCalendarEnd.set(Calendar.MONTH, monthOfYear);
                myCalendarEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateLabelEnd();
            }

        };
        Button deleteButton = findViewById(R.id.deleteCourse);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Course course : repository.getAllCourses()) {
                    if (course.getCourseID() == id) currentCourse = course;
                }
                numAssessments = 0;
                for (Assessment assessment : repository.getAllAssessment()) {
                    if (assessment.getCourseID() == id) ++numAssessments;
                }

                if (numAssessments == 0) {
                    repository.delete(currentCourse);
                    Toast.makeText(CourseDetails.this, currentCourse.getCourseTitle() + " was deleted", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(CourseDetails.this, "Can't delete a Course with Assessments", Toast.LENGTH_LONG).show();
                }
            }
        });
        Button button = findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseDetails.this, AssessmentDetails.class);
                intent.putExtra("courseID",id);
                startActivity(intent);
            }
        });
    }

    private void updateLabelStart() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editStart.setText(sdf.format(myCalendarStart.getTime()));
    }

    private void updateLabelEnd() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editEnd.setText(sdf.format(myCalendarEnd.getTime()));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share_alerts, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, editNote.getText().toString());
                sendIntent.putExtra(Intent.EXTRA_TITLE, "Message Title");
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;
            case R.id.notifystart:
                String dateFromScreen = editStart.getText().toString();
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Date myDate = null;
                try {
                    myDate = sdf.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger = myDate.getTime();
                Intent intent = new Intent(CourseDetails.this, Receiver.class);
                intent.putExtra("StartEndAlert", dateFromScreen + " Course Start");
                PendingIntent sender = PendingIntent.getBroadcast(CourseDetails.this, ++MainActivity.numAlert, intent, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;
            case R.id.notifyend:
                dateFromScreen = editEnd.getText().toString();
                myFormat = "MM/dd/yy";
                sdf = new SimpleDateFormat(myFormat, Locale.US);
                myDate = null;
                try {
                    myDate = sdf.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                trigger = myDate.getTime();
                intent = new Intent(CourseDetails.this, Receiver.class);
                intent.putExtra("StartEndAlert", dateFromScreen + " Course End");
                sender = PendingIntent.getBroadcast(CourseDetails.this, ++MainActivity.numAlert, intent, PendingIntent.FLAG_IMMUTABLE);
                alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {

        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.associatedAssessmentsView);
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Assessment> filteredAssessments = new ArrayList<>();
        for (Assessment p : repository.getAllAssessment()) {
            if (p.getCourseID() == id) filteredAssessments.add(p);
        }
        assessmentAdapter.setAssessment(filteredAssessments);

    }

}