package com.mobile.zackmitchellc196.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mobile.zackmitchellc196.DB.Repository;
import com.mobile.zackmitchellc196.Entities.User;
import com.mobile.zackmitchellc196.R;

import java.util.ArrayList;
import java.util.List;

public class StudentList extends AppCompatActivity {

    private Repository repository;
    private EditText searchStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        RecyclerView recyclerView = findViewById(R.id.studenterecyclerview);
        searchStudent = findViewById(R.id.searchStudent);
        final UserAdapter userAdapter = new UserAdapter(this);
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        repository = new Repository(getApplication());
        List<User> students = new ArrayList<>();
        for (User p : repository.getAllUsers()){
            if(!p.isFaculty()) students.add(p);
        }
        userAdapter.setUsers(students);
        Button searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = searchStudent.getText().toString().trim();
                filterByName(searchQuery);
            }
        });
        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetList();
            }
        });

    }
    private void filterByName(String searchQuery) {

        RecyclerView recyclerView = findViewById(R.id.studenterecyclerview);
        final UserAdapter userAdapter = new UserAdapter(this);
        recyclerView.setAdapter(userAdapter);
        List<User> students = new ArrayList<>();
        for (User p : repository.getAllUsers()){
            if(!p.isFaculty()) students.add(p);
        }
        List<User> filteredList = new ArrayList<>();
        for (User user : students) {
            String firstName = user.getFirstName().toLowerCase();
            String lastName = user.getLastName().toLowerCase();
            String fullName = firstName + " " + lastName;

            if (firstName.contains(searchQuery.toLowerCase()) ||
                    lastName.contains(searchQuery.toLowerCase()) ||
                    fullName.contains(searchQuery.toLowerCase())) {
                filteredList.add(user);
            }
        }
        userAdapter.setUsers(filteredList);
    }

    private void resetList() {
        RecyclerView recyclerView = findViewById(R.id.studenterecyclerview);
        final UserAdapter userAdapter = new UserAdapter(this);
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<User> students = new ArrayList<>();
        for (User p : repository.getAllUsers()){
            if(!p.isFaculty()) students.add(p);
        }
        userAdapter.setUsers(students);
    }

    @Override
    protected void onResume() {
        super.onResume();
        resetList();
    }
}