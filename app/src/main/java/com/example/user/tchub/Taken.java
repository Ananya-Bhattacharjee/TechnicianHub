package com.example.user.tchub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Taken extends AppCompatActivity {

    TextView a,b,c,d,e,f,g,h;
    Button next,prev,goback;
    DatabaseReference ref,ref1,ref2;
    String cn,q,w,ee,r,t,y;
    CheckBox finished;
    @Override
    public void onBackPressed() {
        Toast.makeText(Taken.this, "Back Key Disabled", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taken);

        Bundle bundle = getIntent().getExtras();
        final String text= bundle.getString("Contact");
        a=(TextView) findViewById(R.id.Contact);
        b=(TextView) findViewById(R.id.Type);
        c=(TextView) findViewById(R.id.Desc);
        d=(TextView) findViewById(R.id.Location);
        e=(TextView) findViewById(R.id.Address);
        f=(TextView) findViewById(R.id.Date);
        finished=(CheckBox) findViewById(R.id.Finished);

        next=(Button) findViewById(R.id.buttonAccept);
        //prev=(Button) findViewById(R.id.buttonDecline);
        goback=(Button) findViewById(R.id.buttonMap);

        ref= FirebaseDatabase.getInstance().getReference("torders");
        ref1=FirebaseDatabase.getInstance().getReference("dumtorders");


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                for (final DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    final TakenOrder customer = postSnapshot.getValue(TakenOrder.class);
                    System.out.println(customer);
                    if(customer.getTech().getContactNo().equals(text) && customer.isDone()==false) {
                        cn = text;



                        a.setText("Contact:"+customer.getContactNo());
                        b.setText("Type:"+customer.getType());
                        c.setText("Description:"+customer.getDesc());
                        d.setText("Location:"+customer.getLocation());
                        e.setText("Address:"+customer.getAddress());
                        f.setText("Date:"+customer.getDate());

                        next.setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        if(finished.isChecked())
                                        {
                                            String id=customer.getId();
                                            String cc=customer.getContactNo();
                                            String t=customer.getType();
                                            String d=customer.getDesc();
                                            String l=customer.getLocation();
                                            String a=customer.getAddress();
                                            String dt=customer.getDate();
                                            Tech tech=customer.getTech();
                                            boolean done=true;
                                            boolean paid=customer.isPaid();

                                            TakenOrder td=new TakenOrder(id,cc,t,d,l,a,dt,tech,done,paid);
                                            //dataSnapshot.getRef().removeValue();
                                            ref.child(id).setValue(td);
//                                            dataSnapshot.getRef().removeValue();

                                            Intent i = new Intent(Taken.this, Taken.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putString("Contact",cn);
                                            i.putExtras(bundle);
                                            startActivity(i);



                                        }
                                        else
                                        {
                                            String id=customer.getId();
                                            String cc=customer.getContactNo();
                                            String t=customer.getType();
                                            String d=customer.getDesc();
                                            String l=customer.getLocation();
                                            String a=customer.getAddress();
                                            String dt=customer.getDate();
                                            Tech tech=customer.getTech();
                                            boolean done=true;
                                            boolean paid=customer.isPaid();

                                            TakenOrder td=new TakenOrder(id,cc,t,d,l,a,dt,tech,done,paid);

                                            ref1.child(id).setValue(td);
                                            postSnapshot.getRef().removeValue();
                                            Intent i = new Intent(Taken.this, Taken.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putString("Contact",cn);
                                            i.putExtras(bundle);
                                            startActivity(i);


                                        }

                                        Intent i = new Intent(Taken.this, Taken.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("Contact",cn);
                                        i.putExtras(bundle);
                                        startActivity(i);


                                    }
                                }
                        );





                    }
                    //setContentView(R.layout.activity_list);


                    //a.setText("Contact:"+customer.getContactNo());
                    //b.setText("Type:"+customer.getType());
                    //c.setText("Location:"+customer.getLocation());
                    //d.setText("Address:"+customer.getAddress());
                    //e.setText("Date:"+customer.getDate());
                    //f.setText("Description:"+customer.getDesc());


                }}

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


        goback.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                //Bundle bundle = getIntent().getExtras();
                                //final String text= bundle.getString("Contact");
                                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                    TakenOrder customer = postSnapshot.getValue(TakenOrder.class);
                                    System.out.println(customer);
                                    String id=customer.getId();
                                    ref.child(id).setValue(customer);
                                    postSnapshot.getRef().removeValue();


                                }}

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                System.out.println("The read failed: " + databaseError.getCode());
                            }
                        });
                        Intent i = new Intent(Taken.this, ProfileActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("Contact",text);
                        i.putExtras(bundle);
                        startActivity(i);


                    }
                }
        );




//        acc();
        //dec();
  //      gb();
    }
    public void acc()
    {





    }
    public void gb()
    {







    }
}
