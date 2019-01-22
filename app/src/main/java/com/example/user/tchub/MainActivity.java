package com.example.user.tchub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button btn,btnc ;

    DatabaseReference databaseReference,ref;

    @Override
    public void onBackPressed() {
        Toast.makeText(MainActivity.this, "Back Key Disabled", Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseReference= FirebaseDatabase.getInstance().getReference("techs");
        btn = (Button) findViewById(R.id.c);
        btnc = (Button) findViewById(R.id.ca);

        Go();
        CreateAccount();

        //String id=databaseReference.push().getKey();
        //Tech customer=new Tech(id,"a");
        //databaseReference.child(id).setValue(customer);
    }

    public void Go() {
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(i);
                    }
                }
        );
    }



    public void CreateAccount() {
        btnc.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent(MainActivity.this,Main3Activity.class);
                        startActivity(i);
                    }
                }
        );
    }


}
