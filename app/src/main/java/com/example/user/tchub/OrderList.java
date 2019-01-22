package com.example.user.tchub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class OrderList extends AppCompatActivity {
    DatabaseReference ref,ref1,ref2,ref3;
    TextView a,b,c,d,e,f,g;
    Button acc,dec,mp,goback;
    String cn;
    Tech t;
    @Override
    public void onBackPressed() {
        Toast.makeText(OrderList.this, "Back Key Disabled", Toast.LENGTH_LONG).show();

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        a=(TextView) findViewById(R.id.Contact);
        b=(TextView) findViewById(R.id.Type);
        c=(TextView) findViewById(R.id.Desc);
        d=(TextView) findViewById(R.id.Location);
        e=(TextView) findViewById(R.id.Address);
        f=(TextView) findViewById(R.id.Date);
        g=(TextView) findViewById(R.id.tname);
        acc=(Button) findViewById(R.id.buttonAccept);
        dec=(Button) findViewById(R.id.buttonDecline);
        mp=(Button) findViewById(R.id.buttonMap);
        goback=(Button) findViewById(R.id.gb);
        Bundle bundle = getIntent().getExtras();
        final String text= bundle.getString("Contact");


        ref= FirebaseDatabase.getInstance().getReference("orders");
        ref1=FirebaseDatabase.getInstance().getReference("technis");
        ref2=FirebaseDatabase.getInstance().getReference("torders");
        ref3=FirebaseDatabase.getInstance().getReference("dorders");
        ref1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Tech customer = postSnapshot.getValue(Tech.class);
                    System.out.println(customer);
                    if(customer.getContactNo().equals(text)) {
                        cn = text;
                        t=customer;
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
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    final Order customer = postSnapshot.getValue(Order.class);
                    System.out.println(customer);
                    //Bundle bundle = getIntent().getExtras();
                    //final String text= bundle.getString("Contact");
                    //cn=text;

                    //setContentView(R.layout.activity_list);
                    if(customer.getLocation().equals(t.getLocation()) && customer.getType().equals(t.getType())) {

                        a.setText("Contact:"+customer.getContactNo());
                        b.setText("Type:"+customer.getType());
                        c.setText("Location:"+customer.getLocation());
                        d.setText("Address:"+customer.getAddress());
                        e.setText("Date:"+customer.getDate());
                        f.setText("Description:"+customer.getDesc());
                        g.setText("techName:"+t.getName());

                        acc.setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String id=ref2.push().getKey();
                                        TakenOrder takenOrder=new TakenOrder(id,customer.getContactNo(),customer.getType(),customer.getDesc(),customer.getLocation(),customer.getAddress(),customer.getDate(),t,false,false);
                                        //Customer customer=new Customer(id,a,b,d,c,e);
                                        //Tech tech=new Tech(id,customer.getName(),customer.getPassword(),customer.getLocation(),customer.getAddress(),customer.getType(),customer.getRate(),customer.getContactNo());
                                        ref2.child(id).setValue(takenOrder);
                                        DatabaseReference reff = FirebaseDatabase.getInstance().getReference();
                                        Query applesQuery = reff.child("orders").orderByChild("id").equalTo(customer.getId());


                                        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                                                    appleSnapshot.getRef().removeValue();
                                                }
                                                Intent i=new Intent(OrderList.this,OrderList.class);
                                                Bundle bundle = new Bundle();
                                                bundle.putString("Contact",cn);
                                                i.putExtras(bundle);

                                                startActivity(i);

                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {
                                                Log.e("a", "onCancelled", databaseError.toException());
                                            }
                                        });
                                    }
                                }
                        );
                        dec.setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String id=ref3.push().getKey();
                                        DeclinedOrder takenOrder=new DeclinedOrder(id,t,customer);
                                        //Customer customer=new Customer(id,a,b,d,c,e);
                                        //Tech tech=new Tech(id,customer.getName(),customer.getPassword(),customer.getLocation(),customer.getAddress(),customer.getType(),customer.getRate(),customer.getContactNo());
                                        ref3.child(id).setValue(takenOrder);
                                        DatabaseReference reff = FirebaseDatabase.getInstance().getReference();
                                        Query applesQuery = reff.child("orders").orderByChild("id").equalTo(customer.getId());


                                        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                                                    appleSnapshot.getRef().removeValue();
                                                }
                                                Intent i=new Intent(OrderList.this,OrderList.class);
                                                Bundle bundle = new Bundle();
                                                bundle.putString("Contact",cn);
                                                i.putExtras(bundle);

                                                startActivity(i);

                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {
                                                Log.e("a", "onCancelled", databaseError.toException());
                                            }
                                        });
                                    }
                                }
                        );



                        mp.setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Intent i = new Intent(OrderList.this, MapsActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("Address",customer.getAddress());
                                        i.putExtras(bundle);
                                        startActivity(i);


                                    }
                                }
                        );



                    }









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
                        ref3.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                //Bundle bundle = getIntent().getExtras();
                                //final String text= bundle.getString("Contact");
                                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                    DeclinedOrder customer = postSnapshot.getValue(DeclinedOrder.class);
                                    System.out.println(customer);
                                    Order od=customer.getOrder();
                                    String id=customer.getId();

                                    ref.child(id).setValue(od);
                                    postSnapshot.getRef().removeValue();


                                }}

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                System.out.println("The read failed: " + databaseError.getCode());
                            }
                        });
                        Intent i = new Intent(OrderList.this, ProfileActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("Contact",text);
                        i.putExtras(bundle);
                        startActivity(i);


                    }
                }
        );




    }
}
