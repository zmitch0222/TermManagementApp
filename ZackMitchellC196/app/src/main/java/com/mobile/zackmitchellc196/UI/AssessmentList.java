package com.mobile.zackmitchellc196.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.mobile.zackmitchellc196.DB.Repository;
import com.mobile.zackmitchellc196.Entities.Assessment;
import com.mobile.zackmitchellc196.R;

import java.util.List;

public class AssessmentList extends AppCompatActivity {

    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_list);
        RecyclerView recyclerView = findViewById(R.id.assessmentrecyclerview);
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        repository = new Repository(getApplication());
        List<Assessment> allAssessments = repository.getAllAssessment();
        assessmentAdapter.setAssessment(allAssessments);
    }
    @Override
    protected void onResume() {
        super.onResume();

        List<Assessment> allAssessment = repository.getAllAssessment();
        RecyclerView recyclerView = findViewById(R.id.assessmentrecyclerview);
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assessmentAdapter.setAssessment(allAssessment);
    }
}