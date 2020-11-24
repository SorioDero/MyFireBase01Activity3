package com.example.myfirebase01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button SubmitButton, ShowButton;
    EditText NameEditText, PhoneNumberEditText;
    public static final String Firebase_Server_URL = "https://testing01-4a983.firebaseio.com/";
    String NameHolder, NumberHolder;
    TextView ShowDataTextView;
    // Declaring Firebase object
    Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Adding MainActivity context into Firebase context.
        Firebase.setAndroidContext(MainActivity.this);
        //Passing firebase Server URL into firebase object.
        firebase = new Firebase(Firebase_Server_URL);

        NameEditText = (EditText) findViewById(R.id.name);
        PhoneNumberEditText = (EditText) findViewById(R.id.phone_number);
        SubmitButton = (Button) findViewById(R.id.submit);
        ShowButton = (Button) findViewById(R.id.show);
        ShowDataTextView = (TextView) findViewById(R.id.showData);
        // Adding MainActivity context into Firebase context.


        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student();
                GetDataFromEditText();
                student.setStudentName(NameHolder);
                student.setStudentPhoneNumber(NumberHolder);
                firebase.child("Student").setValue(student);
                Toast.makeText(MainActivity.this, "Data Inserted Successfully", Toast.LENGTH_LONG).show();
                NameEditText.setText("");
                PhoneNumberEditText.setText("");
            }
        });

        ShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Adding addValueEventListener method on firebase object
                firebase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot MainSnapshot) {
                        for (DataSnapshot SubSnapShot : MainSnapshot.getChildren()) {
                            Student student = SubSnapShot.getValue(Student.class);
                            // Adding name and phone number of student into string that is coming from server.
                            String ShowDataString = "Name : "+student.getStudentName()+"\nPhone Number : "
                                    +student.getStudentPhoneNumber()+"\n\n";
                            // Apply complete string variable into TextView
                            ShowDataTextView.setText(ShowDataString);
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        System.out.println("Data Access Failed" + firebaseError.getMessage());
                    }
                });
            }
        });

    }
    public void GetDataFromEditText()
    {
        NameHolder = NameEditText.getText().toString().trim();
        NumberHolder = PhoneNumberEditText.getText().toString().trim();
    }
}
