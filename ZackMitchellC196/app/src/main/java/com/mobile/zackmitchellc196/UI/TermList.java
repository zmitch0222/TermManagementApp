package com.mobile.zackmitchellc196.UI;

import static com.mobile.zackmitchellc196.UI.MainActivity.userID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mobile.zackmitchellc196.DB.Repository;
import com.mobile.zackmitchellc196.Entities.Term;
import com.mobile.zackmitchellc196.R;

import java.util.ArrayList;
import java.util.List;

public class TermList extends AppCompatActivity {

    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);
        RecyclerView recyclerView = findViewById(R.id.termrecyclerview);
        final TermAdapter termAdapter = new TermAdapter(this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        repository = new Repository(getApplication());
        List<Term> userTerms = new ArrayList<>();
        for (Term p : repository.getAllTerms()){
            if (p.getUserID() == userID) userTerms.add(p);
        }
        termAdapter.setTerms(userTerms);
        Button button = findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TermList.this, TermDetails.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        RecyclerView recyclerView = findViewById(R.id.termrecyclerview);
        final TermAdapter termAdapter = new TermAdapter(this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Term> userTerms = new ArrayList<>();
        for (Term p : repository.getAllTerms()){
            if (p.getUserID() == userID) userTerms.add(p);
        }
        termAdapter.setTerms(userTerms);

    }
}