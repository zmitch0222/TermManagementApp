package com.mobile.zackmitchellc196.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mobile.zackmitchellc196.DB.Repository;
import com.mobile.zackmitchellc196.Entities.Term;
import com.mobile.zackmitchellc196.Entities.User;
import com.mobile.zackmitchellc196.R;

public class UserDetails extends AppCompatActivity {
    int id;
    String username;
    String password;
    String first;
    String last;
    String phone;
    String email;
    boolean falculty;
    Repository repository;
    User user;
    EditText editFirst;
    EditText editLast;
    EditText editPhone;
    EditText editEmail;
    EditText editUsername;
    EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        editFirst = findViewById(R.id.editFirst);
        editLast = findViewById(R.id.editLast);
        editPhone = findViewById(R.id.editPhone);
        editEmail = findViewById(R.id.editEmail);
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        editPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        id = getIntent().getIntExtra("id", -1);
        username = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");
        first = getIntent().getStringExtra("first");
        last = getIntent().getStringExtra("last");
        phone = getIntent().getStringExtra("phone");
        email = getIntent().getStringExtra("email");
        falculty = getIntent().getBooleanExtra("faculty", false);
        editFirst.setText(first);
        editLast.setText(last);
        editPhone.setText(phone);
        editEmail.setText(email);
        editUsername.setText(username);
        editPassword.setText(password);
        repository = new Repository(getApplication());

        Button savebutton = findViewById(R.id.saveUser);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id == -1) {
                    user = new User(0, editUsername.getText().toString(), editPassword.getText().toString(), editFirst.getText().toString(), editLast.getText().toString(), editPhone.getText().toString(), editEmail.getText().toString(), falculty);
                    repository.insert(user);
                    finish();

                } else {
                    user = new User(id, editUsername.getText().toString(), editPassword.getText().toString(), editFirst.getText().toString(), editLast.getText().toString(), editPhone.getText().toString(), editEmail.getText().toString(), falculty);
                    repository.update(user);
                    finish();
                }
            }
        });
    }

}