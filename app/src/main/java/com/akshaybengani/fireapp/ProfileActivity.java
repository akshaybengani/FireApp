package com.akshaybengani.fireapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {

    private Button buttonLogout;
    private TextView textViewUserEmail;
    private FirebaseAuth firebaseAuth;

    //Wih this refernece we can store data to firebase database
    private DatabaseReference databaseReference;

    private EditText editTextName,editTextAddress;
    private Button buttonSaveInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Here we initialised the FirebaseAuth object
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser()==null)
        {
            finish();
            //It means User is not logged in so now opens Login Activity
            Intent intent = new Intent(ProfileActivity.this,LoginActivity.class);
            startActivity(intent);
        }
        // here we initialise the object of firebase user
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        // here we initialise the object of firebase database reference
        databaseReference = FirebaseDatabase.getInstance().getReference();

        //here we initialise the variables
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        editTextName = (EditText)findViewById(R.id.editTextName);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        buttonSaveInfo = (Button) findViewById(R.id.buttonSaveInfo);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);


        textViewUserEmail.setText("Welcome "+firebaseUser.getEmail());


            buttonLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    firebaseAuth.signOut();
                    finish();
                    Intent intent = new Intent(ProfileActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            });

            buttonSaveInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                saveUserInfo();

                }
            });

    }

    public void saveUserInfo()
    {
        String name =editTextName.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();

        // Here we created the object of our UserInformation Class
        UserInformation userInformation= new UserInformation(name,address);

        //This takes out the unique id of the current user
        FirebaseUser user= firebaseAuth.getCurrentUser();

        // Here we send data to the firebase by a unique id of the user by the
        // special function .getUid through user id in our database
        databaseReference.child(user.getUid()).setValue(userInformation);
        // Now we show the data saved info
        Toast.makeText(this, "Information Saved",Toast.LENGTH_SHORT).show();

    }




}
