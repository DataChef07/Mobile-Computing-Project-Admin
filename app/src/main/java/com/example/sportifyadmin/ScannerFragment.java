package com.example.sportifyadmin;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.sportifyadmin.model.AddUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ScannerFragment extends Fragment {
    private CodeScannerView codeScannerView;
    private int equipment=0;
    private CodeScanner codeScanner;
    Boolean[] matchedval = {false};
    String[] ts = {""};
    int[] rating = {0};
    int[] equip = {0};
    Boolean[] signin_val = {false};
    Handler handler = new Handler();
    Runnable runnable;
    String userName;
    Button checkout;

    private FirebaseAuth auth;
    DatabaseReference mDatabase;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scanner, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        codeScannerView = view.findViewById(R.id.QRScanner);

        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("registry");


//        if(!permission())
//            reqPermission();
        Handler mHandler = new Handler(Looper.getMainLooper());

        codeScanner = new CodeScanner(getContext(), codeScannerView);
        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {


                        Toast.makeText(getContext(), result.getText(), Toast.LENGTH_SHORT).show();
                        Dialog dialog=new Dialog(getContext());
                        dialog.setContentView(R.layout.camera_dialogue);

                        Spinner spinner=dialog.findViewById(R.id.spinner);
                        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line,getResources().getStringArray(R.array.equipment));
                        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                        spinner.setAdapter(adapter);
                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                equipment=position;
                                //Toast.makeText(getContext(), String.valueOf(equipment), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        TextView t1,t2;
                        Button submit;
                        submit=dialog.findViewById(R.id.submit);
                        String s1= result.getText();
                        t1=dialog.findViewById(R.id.email);
                        t2=dialog.findViewById(R.id.sport);
//                        t3=dialog.findViewById(R.id.time);

                        String [] s2=s1.split("--");
                        try {
//                            t1.setText(s2[0]);
                            t2.setText(s2[1]);
//                            t3.setText(s2[2]);
                            Log.d("date", "run: "+s2[2]);
                            
                            DatabaseReference obj = FirebaseDatabase.getInstance().getReference().child("Users");
                            obj.child(s2[0]).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String name = snapshot.getValue(String.class);
                                    userName = name;
                                    Log.d("name check", "name ===>    "+ name);
                                    t1.setText(name);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });


//                            DatabaseReference snapshot = FirebaseDatabase.getInstance().getReference();
//                            String user= String.valueOf(snapshot.getDatabase());
//                            Map<String, HashMap> temp;
//                            Map<String, HashMap> fetchedData = (HashMap<String, HashMap>) snapshot.get
//                            String name = String.valueOf(mDatabase.child("name"));
//                            Log.d("123", "run: "+user);
//                            t1.setText(user);
//                            t1.setText(name);
                        }
                        catch (Exception e)
                        {
                            Log.d("Data misssing", "run: "+e);
                        }

                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        String timestamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new java.util.Date());
                        Date d1 = null;
                        Dialog dialog1=new Dialog(getContext());
                        dialog1.setContentView(R.layout.camera_dialogue_invalidqr);
                        Dialog dialog2=new Dialog(getContext());
                        dialog2.setContentView(R.layout.camera_dialogue_checkout);
                        checkout=dialog2.findViewById(R.id.check_out);
                        try {
                            d1 = sdf.parse(timestamp);
                            Date d2 = sdf.parse(s2[2]);
                            long diff = d1.getTime() - d2.getTime();
                            diff = (diff / (1000 * 60)) % 60;
                            Log.d("datacheck", "timestamp: ===> " + d1);
                            Log.d("datacheck", "t2: ===> " + d2);
                            Log.d("datacheck", "diff: ===> " + diff);
                            if (diff >= 1) {
                                //invalid QR ===> ask user to refresh it
                                dialog1.show();

                            }
                            else{

                                DatabaseReference obj = FirebaseDatabase.getInstance().getReference();
                                obj.child("Users").child(s2[0]).child("signedin")
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            signin_val[0] = snapshot.getValue(Boolean.class);
                                            Log.d("signin_val", "signin_val[0] ===>  " + signin_val[0]);
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });

                                obj.child("Users").child(s2[0]).child("equipment")
                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                equip[0] = snapshot.getValue(Integer.class);
                                                Log.d("equipment", "equip[0] ===>  " + equip[0]);
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });

                                handler.postDelayed(runnable = new Runnable(){

                                    @Override
                                    public void run() {
                                        if(signin_val[0]){
                                            //user already signedin display his name and equipment taken by him
                                            TextView name1,sport1,equipment1;
                                            name1=dialog2.findViewById(R.id.name);
                                            sport1=dialog2.findViewById(R.id.sport);
                                            equipment1=dialog2.findViewById(R.id.equipment2);
                                            name1.setText(userName);
                                            sport1.setText(s2[1]);
                                            equipment1.setText(String.valueOf(equip[0]));
                                            Log.d("checked in value", "run: "+signin_val[0]);
                                            dialog2.show();

                                        }
                                        else{

                                            dialog.show();
                                        }
                                    }
                                }, 3000);
                            }
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }

                        checkout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DatabaseReference obj = FirebaseDatabase.getInstance().getReference();
                                obj.child("Users").child(s2[0]).child("signedin").setValue(false);
                                obj.child("Users").child(s2[0]).child("equipment").setValue(0);
                                dialog2.cancel();
                            }
                        });

                        submit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.d("time", "onClick: ===> " + s2[2]);
                                DatabaseReference obj = FirebaseDatabase.getInstance().getReference();
                                obj.child("Users").child(s2[0]).child("signedin").setValue(true);
                                obj.child("Users").child(s2[0]).child("equipment").setValue(equipment);

                                obj.child("Users").child(s2[0]).child("timer")
                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                ts[0] = snapshot.getValue(String.class);
                                                Log.d("timer", "ts[0] ===>  " + ts[0]);
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });


                                obj.child("Users").child(s2[0]).child("rating")
                                        .addListenerForSingleValueEvent((new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                rating[0] = snapshot.getValue(Integer.class);
                                                Log.d("rating", "rating[0] ===>  " + rating[0]);
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        }));


                                obj.child("Users").child(s2[0]).child("matched")
                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                matchedval[0] = snapshot.getValue(Boolean.class);
                                                Log.d("matchedval", "matchedval[0] ===>  " + matchedval[0]);
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });

                                handler.postDelayed(runnable = new Runnable(){

                                    @Override
                                    public void run() {
//                                        handler.postDelayed(runnable, 3000);
                                        if(matchedval[0] && !ts[0].equals("")){
                                            Log.d("check", "check");
                                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                                            String timestamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new java.util.Date());
                                            Date d1 = null;
                                            try {
                                                d1 = sdf.parse(timestamp);
                                                Date d2 = sdf.parse(ts[0]);
                                                long diff = d1.getTime() - d2.getTime();
                                                diff = (diff / (1000 * 60)) % 60;
                                                Log.d("datacheck", "timestamp: ===> " + d1);
                                                Log.d("datacheck", "t2: ===> " + d2);
                                                Log.d("datacheck", "diff: ===> " + diff);
                                                if(diff>=20){
                                                    obj.child("Users").child(s2[0]).child("rating").setValue(rating[0]-2);
                                                }
                                                else{
                                                    obj.child("Users").child(s2[0]).child("rating").setValue(rating[0]+1);
                                                }
                                                obj.child("Users").child(s2[0]).child("matched").setValue(false);
                                                obj.child("Users").child(s2[0]).child("timer").setValue("");
                                                obj.child("Users").child(s2[0]).child("sport").setValue("");
                                            } catch (ParseException e) {
                                                throw new RuntimeException(e);
                                            }

                                        }

//                                        obj.child("test").child(s2[1]).child("equipments").setValue(temp);
                                        dialog.cancel();

                                    }
                                }, 3000);

                                // code for storing the data in registry table
//                                String name;
//                                String sport;
//                                String equipment;
//                                String timestamp;
                                AddUser newUser = new AddUser(userName,s2[1],String.valueOf(equipment),s2[2]);
                                mDatabase.push().setValue(newUser);


                            }
                        });
                    }
                });
            }
        });

        codeScannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codeScanner.startPreview();
            }
        });
    }

    @Override
    public void onPause() {
        codeScanner.releaseResources();
        super.onPause();

    }

    @Override
    public void onResume() {

        super.onResume();
        codeScanner.startPreview();
    }
}