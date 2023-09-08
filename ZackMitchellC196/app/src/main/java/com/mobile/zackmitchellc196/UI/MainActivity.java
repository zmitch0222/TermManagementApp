package com.mobile.zackmitchellc196.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mobile.zackmitchellc196.DB.Repository;
import com.mobile.zackmitchellc196.Entities.User;
import com.mobile.zackmitchellc196.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static boolean x = false;
    private static boolean y = false;
    public static int numAlert;
    public static int userID;
    private Repository repository;
    private static final String PREF_FIRST_LAUNCH = "pref_first_launch";
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button buttonStudents = findViewById(R.id.buttonStudents);


        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        boolean isFirstLaunch = sharedPreferences.getBoolean(PREF_FIRST_LAUNCH, true);
        if (isFirstLaunch) {
            addTestUsers();
            sharedPreferences.edit().putBoolean(PREF_FIRST_LAUNCH, false).apply();
        }

        if (!x) {
            showDialog();
            x = true;
        }



        buttonStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StudentList.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TermList.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CourseList.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AssessmentList.class);
                startActivity(intent);
            }
        });
    }
    public void addTestUsers(){
        repository = new Repository(getApplication());
        User admin = new User(0,"admin", "admin", "Admin", "Teacher", "555-555-5555", "email@email", true);
        User student1 = new User(0,"student1", "student1", "Zack", "Mitchell", "555-555-5555", "email@email", false);
        User student2 = new User(0,"student2", "student2", "Lydia", "Mitchell", "555-555-5555", "email@email", false);
        User student3 = new User(0,"student3", "student3", "Gilly", "TheCat", "555-555-5555", "email@email", false);
        repository.insert(admin);
        repository.insert(student1);
        repository.insert(student2);
        repository.insert(student3);
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Login");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText usernameEditText = new EditText(this);
        usernameEditText.setHint("Username");
        layout.addView(usernameEditText);

        final EditText passwordEditText = new EditText(this);
        passwordEditText.setHint("Password");
        passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        layout.addView(passwordEditText);

        builder.setView(layout);

        builder.setPositiveButton("OK", null);

        AlertDialog dialog = builder.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                Button buttonStudents = findViewById(R.id.buttonStudents);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String username = usernameEditText.getText().toString();
                        String password = passwordEditText.getText().toString();

                        repository = new Repository(getApplication());
                        User user = repository.getUsername(username);

                        if (user != null && user.getPassword().equals(password)) {
                                userID = user.getUserID();

                                if(user.isFaculty()){
                                    buttonStudents.setVisibility(View.VISIBLE);
                                    buttonStudents.setClickable(true);
                                    y = true;

                                } else {
                                    buttonStudents.setVisibility(View.GONE);
                                    buttonStudents.setClickable(false);
                                }

                            dialog.dismiss();
                        } else {
                            Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        dialog.setCancelable(false);

        dialog.show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.details:
                repository = new Repository(getApplication());
                List<User> currentUser = new ArrayList<>();

                for (User p : repository.getAllUsers()) {
                    if (p.getUserID() == userID) {
                        currentUser.add(p);
                        break;
                    }
                }

                if (!currentUser.isEmpty()) {
                    User current = currentUser.get(0);

                    Intent intent = new Intent(MainActivity.this, UserDetails.class);
                    intent.putExtra("id", userID);
                    intent.putExtra("username", current.getUsername());
                    intent.putExtra("password", current.getPassword());
                    intent.putExtra("first", current.getFirstName());
                    intent.putExtra("last", current.getLastName());
                    intent.putExtra("phone", current.getPhoneNumber());
                    intent.putExtra("email", current.getEmail());
                    intent.putExtra("faculty", current.isFaculty());
                    startActivity(intent);
                }
                return true;
            case R.id.logout:

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Logout");
                builder.setMessage("Are you sure you want to logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        userID = -1;
                        showDialog();
                    }
                });
                builder.setNegativeButton("No", null);
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Button buttonStudents = findViewById(R.id.buttonStudents);
        repository = new Repository(getApplication());

        if(y){
            buttonStudents.setVisibility(View.VISIBLE);
            buttonStudents.setClickable(true);
        } else {
            buttonStudents.setVisibility(View.GONE);
            buttonStudents.setClickable(false);
        }
    }

}
