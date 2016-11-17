package com.example.duacomputer.helloapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.Key;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference ref;

    EditText txtName,txtEmail,txtAge;
    Button btn,btn1;
    String name, email;
    int age;
    ListView txt;
    ArrayList<MyClass> arr;
    CustomListView cstm;
    Intent intent;
    AlertDialog.Builder alert;

    int count;
    String localname,localemail;
    int localAge;

    String myKey;


    LayoutInflater inf ;

    EditText txtname;
    EditText txtemail;
    EditText txtage;
    DataSnapshot shot;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseDatabase.getInstance();


        ref = db.getReference("Name:");


       inf = this.getLayoutInflater();

        // ref.push().setValue("hello world");
        txt = (ListView) findViewById(R.id.txt);
        txtName = (EditText) findViewById(R.id.name);
        txtEmail = (EditText) findViewById(R.id.email);
        txtAge = (EditText) findViewById(R.id.age);
        btn1 = (Button) findViewById(R.id.bb);
        btn = (Button) findViewById(R.id.submit);
        arr = new ArrayList<>();
        alert = new AlertDialog.Builder(this);














        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    count=1;
                    name = txtName.getText().toString();
                    email = txtEmail.getText().toString();
                    age = Integer.parseInt( txtAge.getText().toString());

                    ref.push().setValue(new MyClass(name,email,age,ref.getKey()));


                    /////////////////////////////////////////////////////////////////////////////
                    ref.addChildEventListener(new ChildEventListener() {


                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                           // MyClass ab = dataSnapshot.getValue(MyClass.class);
//
                            if(count==1) {
                                myKey = dataSnapshot.getKey();
                                Toast.makeText(MainActivity.this, myKey + " " + dataSnapshot.getKey(), Toast.LENGTH_SHORT).show();


                                ref.child(myKey).setValue(new MyClass(name,email,age,myKey));



                                Log.d("db", "on child aded key: " + dataSnapshot.getKey() + "  " + myKey + " value " + dataSnapshot.getValue() + "");
                            }
                                ++count;
                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                         //   MyClass ab = dataSnapshot.getValue(MyClass.class);
                          //  shot = dataSnapshot;

                            Log.d("db",""+"key: "+dataSnapshot.getKey()+" value "+dataSnapshot.getValue()+"");

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {
                      //      MyClass ab = dataSnapshot.getValue(MyClass.class);
                            //shot = dataSnapshot;
                            //    func(txt);
                            Log.d("db","key: "+dataSnapshot.getKey()+" value "+dataSnapshot.getValue()+"");

                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                 //           MyClass ab = dataSnapshot.getValue(MyClass.class);
//                        txt.setText(" ");
//                        txt.setText("on child moved"+txt.getText().toString()+ab.getName()+" "+ab.getEmail()+" "+ab.getAge()+"\n");
                          //  shot = dataSnapshot;
                            Log.d("db","key: "+dataSnapshot.getKey()+" value "+dataSnapshot.getValue()+"");

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });












                    ////////////////////////////////////////////////////////////////////////////





                }catch (Exception e){


                }



            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.addChildEventListener(new ChildEventListener() {


                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                       MyClass ab = dataSnapshot.getValue(MyClass.class);
//                        txt.setText(" ");
//                        txt.setText("on child added"+txt.getText().toString()+ab.getName()+" "+ab.getEmail()+" "+ab.getAge()+"\n");
                            arr.add(ab);
                            cstm = new CustomListView(arr,getApplicationContext());
                        txt.setAdapter(cstm);
                      //  func(txt);

                        Log.d("db","on child aded key: "+dataSnapshot.getKey()+" value "+dataSnapshot.getValue()+"");
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        MyClass ab = dataSnapshot.getValue(MyClass.class);
//                        txt.setText("on child change"+txt.getText().toString()+ab.getName()+" "+ab.getEmail()+" "+ab.getAge()+"\n");
//                        txt.setText(" ");

                        arr.add(ab);
                        cstm = new CustomListView(arr,getApplicationContext());
                        txt.setAdapter(cstm);

                        Log.d("db",""+"key: "+dataSnapshot.getKey()+" value "+dataSnapshot.getValue()+"");

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                        MyClass ab = dataSnapshot.getValue(MyClass.class);
//                        txt.setText(" ");
//                        txt.setText("on child remove"+txt.getText().toString()+ab.getName()+" "+ab.getEmail()+" "+ab.getAge()+"\n");
                        arr.add(ab);
                        cstm = new CustomListView(arr,getApplicationContext());
                        txt.setAdapter(cstm);
                    //    func(txt);
                        Log.d("db","key: "+dataSnapshot.getKey()+" value "+dataSnapshot.getValue()+"");

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                        MyClass ab = dataSnapshot.getValue(MyClass.class);
//                        txt.setText(" ");
//                        txt.setText("on child moved"+txt.getText().toString()+ab.getName()+" "+ab.getEmail()+" "+ab.getAge()+"\n");
                        arr.add(ab);
                        cstm = new CustomListView(arr,getApplicationContext());
                        txt.setAdapter(cstm);
                  //      func(txt);
                        Log.d("db","key: "+dataSnapshot.getKey()+" value "+dataSnapshot.getValue()+"");

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

///////////////////////////////

        txt.setOnItemClickListener(new AdapterView.OnItemClickListener() {



            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int temp =i;
                View v =inf.inflate(R.layout.for_alert, null);
                alert.setView(v);
                txtname = (EditText) v.findViewById(R.id.aupname);
                txtemail = (EditText) v.findViewById(R.id.aupemail);
                txtage = (EditText) v.findViewById(R.id.aupage);

                alert.setPositiveButton("Update", new DialogInterface.OnClickListener() {



                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        try {

                            Toast.makeText(MainActivity.this, "Update button", Toast.LENGTH_SHORT).show();



                             localname = txtname.getText().toString();
                             localemail = txtemail.getText().toString();
                             localAge = Integer.parseInt( txtage.getText().toString());
                            //ref.child(arr.get(temp).getKey()).setValue(new MyClass(localname,localemail,localAge,arr.get(temp).getKey()));

                            ref.child(arr.get(temp).getKey()).setValue(new MyClass(localname,localemail,localAge,arr.get(temp).getKey()));
                            Toast.makeText(MainActivity.this, "Ok", Toast.LENGTH_SHORT).show();
                        }catch (Exception e){
                            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();

                        }

                    }
                });


                alert.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {



                        ref.child(arr.get(temp).getKey()).removeValue();
                    }
                });


                alert.show();

            }

        });









    }




}
