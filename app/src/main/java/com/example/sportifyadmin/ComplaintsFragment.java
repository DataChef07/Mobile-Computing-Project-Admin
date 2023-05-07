package com.example.sportifyadmin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sportifyadmin.model.ComplaintsModel;
import com.example.sportifyadmin.model.EquipmentModel;
import com.example.sportifyadmin.recylcerView.ComplaintsAdapter;
import com.example.sportifyadmin.recylcerView.EquipmentAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ComplaintsFragment extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference complaintsDb;
    ComplaintsAdapter complaintsAdapter;
    ArrayList<ComplaintsModel> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_complaints, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.complaints_recyclerView);
        complaintsDb = FirebaseDatabase.getInstance().getReference("complaints");
        list = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        complaintsAdapter = new ComplaintsAdapter(getContext(), list);
        recyclerView.setAdapter(complaintsAdapter);

        complaintsDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, HashMap> temp;
//                Map<String, HashMap> fetchedData = (HashMap<String, HashMap>) snapshot.getValue();
//                for (Map.Entry<String, HashMap> entry : fetchedData.entrySet()){
//                    temp = entry.getValue();
////                    list.add(model);
//                }
//                Toast.makeText(getContext(), "sample toast", Toast.LENGTH_SHORT).show();
                Log.d("snapshot", "snapshot: ===> " + snapshot);
                list.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    Log.d("snapshot", "dataSnapshot: ===> "+ dataSnapshot);
//                    String key = dataSnapshot.getKey();
                    for(DataSnapshot ds: dataSnapshot.getChildren()){
                        Log.d("snapshot", "ds: ===> "+ ds);
                        ComplaintsModel model = ds.getValue(ComplaintsModel.class);
                        list.add(model);
//                        for(DataSnapshot last: ds.getChildren()){
//                            ComplaintsModel model = last.getValue(ComplaintsModel.class);
//                            list.add(model);
//                        }
                    }
//                    ComplaintsModel model = dataSnapshot.getValue(ComplaintsModel.class);
//                    list.add(model);
                }
                complaintsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}