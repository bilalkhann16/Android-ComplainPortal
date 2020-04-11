package com.example.sbchscomplainportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText inputname,email,inputnumber,subject,complain,house;
    Button submit;
    DatabaseReference reff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputname= findViewById(R.id.inputname);
        email= findViewById(R.id.email);
        house= findViewById(R.id.house);
        inputnumber= findViewById(R.id.inputnumber);
        subject= findViewById(R.id.subject);
        complain= findViewById(R.id.complain);

        submit=(Button)findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,Object> map=new HashMap<>();
                map.put("Name-",inputname.getText().toString());
                map.put("Contact No-",inputnumber.getText().toString());
                map.put("House No-",house.getText().toString());
                map.put("Email-",email.getText().toString());
                map.put("Subject-",subject.getText().toString());
                map.put("Complain-",complain.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("Post").push()
                        .setValue(map)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getApplicationContext(),"Complain Submitted Successfully",Toast.LENGTH_LONG).show();
                                Log.i("No Error", "onComplete: ");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.i("Something Went Wrong", "onFailure: "+e.toString());
                            }
                        }).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.i("onSucces Log complete.", "onSuccess: ");
                    }
                });


            }
        });

    }
}