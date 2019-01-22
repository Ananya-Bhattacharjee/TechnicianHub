package com.example.user.tchub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main3Activity extends AppCompatActivity {

    EditText editName, editPassword, editConPassword, editAddress, editRate, editContactNo;
    Spinner LocationList,TypeList;
    CheckBox Response;
    Button btn,btnhome;
    DatabaseReference databaseReference,ref;
    //private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btn = (Button) findViewById(R.id.buttonAdd);
        databaseReference= FirebaseDatabase.getInstance().getReference("techs");
        ref= FirebaseDatabase.getInstance().getReference("techPhoto");

        editName = (EditText) findViewById(R.id.editTextName);
        editPassword = (EditText) findViewById(R.id.editTextPassword);
        editConPassword = (EditText) findViewById(R.id.editTextConPassword);
        editAddress = (EditText) findViewById(R.id.editTextAddress);
        editRate = (EditText) findViewById(R.id.editTextRate);
        editContactNo = (EditText) findViewById(R.id.editTextContactNo);
        TypeList = (Spinner) findViewById(R.id.SpinnerType);
        LocationList = (Spinner) findViewById(R.id.SpinnerLocation);
        Response = (CheckBox) findViewById(R.id.CheckBoxResponse);
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
//        Add();
        AddTechnician();


    }

    public void AddTechnician() {
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Log.d("A","B");
                        if(Response.isChecked() && editPassword.getText().toString().equals(editConPassword.getText().toString())) {


                            if (!editName.getText().toString().isEmpty() && !editPassword.getText().toString().isEmpty() &&!editAddress.getText().toString().isEmpty() && !editContactNo.getText().toString().isEmpty())


                            {
                                int s=1;

                                String a,b,c,d,e,f,g;
                                a=editName.getText().toString();
                                b=editPassword.getText().toString();
                                c=editAddress.getText().toString();
                                d=LocationList.getSelectedItem().toString();
                                e=editContactNo.getText().toString();
                                f=TypeList.getSelectedItem().toString();
                                g=editRate.getText().toString();

                                if(b.length()<6)
                                    s=2;

                                if(e.length()!=11 || !e.startsWith("01"))
                                {
                                    s=3;

                                }


                                if(s==1) {
                                    String id = databaseReference.push().getKey();
                                    //Customer customer=new Customer(id,a,b,d,c,e);
                                    Tech tech = new Tech(id, a, b, d, c, f, g, e);
                                    databaseReference.child(id).setValue(tech);

                                    TechPhoto userPhoto = new TechPhoto(id, e, null);
                                    ref.child(id).setValue(userPhoto);
                                    Toast.makeText(Main3Activity.this, "Signed Up", Toast.LENGTH_LONG).show();
                                    setContentView(R.layout.goback);
                                    btnhome = (Button) findViewById(R.id.buttonhome);

                                    btnhome.setOnClickListener(
                                            new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Intent i = new Intent(Main3Activity.this, MainActivity.class);
                                                    startActivity(i);

                                                }
                                            }
                                    );
                                }
                                else if(s==2)
                                {
                                    Toast.makeText(Main3Activity.this, "Password should be of at least 6 characters", Toast.LENGTH_LONG).show();

                                }
                                else if(s==3)
                                    Toast.makeText(Main3Activity.this, "Check your number", Toast.LENGTH_LONG).show();




                            }
                            else
                                Toast.makeText(Main3Activity.this, "Sign Up Failed. Check All Fields.", Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(Main3Activity.this, "Sign Up Failed. Check All Fields.", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }



}

