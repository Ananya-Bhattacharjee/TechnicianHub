package com.example.user.tchub;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {


//    DatabaseHelper myDb;
    EditText eContactNo,ePassword;
    Button login,btnviewAll;
    //final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("technis");
    ArrayList<Tech> c=new ArrayList<Tech>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        myDb = new DatabaseHelper(this);
        login = (Button) findViewById(R.id.email_sign_in_button);
  //      btnviewAll = (Button) findViewById(R.id.see);
        eContactNo = (EditText) findViewById(R.id.contact);
        ePassword = (EditText) findViewById(R.id.password);
  /*      ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Customer customer = dataSnapshot.getValue(Customer.class);
                c.add(customer);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
        //create();
        logIn();
//        viewAll();
    }

    public void logIn() {
        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String a = eContactNo.getText().toString(), b = ePassword.getText().toString();
//                        for(Customer cr;c )
                        ref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                    Tech customer = postSnapshot.getValue(Tech.class);
                                    System.out.println(customer);
                                    if (customer.getContactNo().equals(a) && customer.getPassword().equals(b)) {
                                        Intent i = new Intent(LoginActivity.this, ProfileActivity.class);


                                        String c = eContactNo.getText().toString();
                                        Bundle bundle = new Bundle();
                                        bundle.putString("Contact", c);
                                        i.putExtras(bundle);
                                        startActivity(i);
                                        return;
                                    }


                                }
                                Toast.makeText(LoginActivity.this, "Log in Failed. Check all fields.", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                System.out.println("The read failed: " + databaseError.getCode());
                            }
                        });


                    }
                });


    }






    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
