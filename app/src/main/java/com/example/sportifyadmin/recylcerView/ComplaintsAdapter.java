package com.example.sportifyadmin.recylcerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportifyadmin.R;
import com.example.sportifyadmin.model.ComplaintsModel;

import java.util.ArrayList;

public class ComplaintsAdapter extends RecyclerView.Adapter<ComplaintsAdapter.ComplaintsViewHolder> {

    Context context;
    ArrayList<ComplaintsModel> complaintsList;

    public ComplaintsAdapter(Context context, ArrayList<ComplaintsModel> complaintsList) {
        this.context = context;
        this.complaintsList = complaintsList;
    }

    @NonNull
    @Override
    public ComplaintsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_complaint, parent, false);
        return new ComplaintsViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ComplaintsViewHolder holder, int position) {
        ComplaintsModel model = complaintsList.get(position);

        int myColor1 = Color.parseColor("#FFCCCB");
        int myColor2 = Color.parseColor("#ffe140");
        int myColor3 = Color.parseColor("#90EE90");

        if(model.getState().equals("initiated"))
        {
            holder.complaint.setBackgroundColor(myColor1);
        }
        if(model.getState().equals("progress"))
        {
            holder.complaint.setBackgroundColor(myColor2);
        }
        if(model.getState().equals("complete"))
        {
            holder.complaint.setBackgroundColor(myColor3);
        }


        holder.sport.setText(model.getSport());
        holder.type.setText(model.getType());
        holder.text.setText(model.getText());
        holder.complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog1=new Dialog(v.getContext());
                dialog1.setContentView(R.layout.complaint_dialogue);
                dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog1.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return complaintsList.size();
    }

    // complaints view holder
    public static class ComplaintsViewHolder extends RecyclerView.ViewHolder
    {
        TextView sport, type, text;
        CardView complaint;

        public ComplaintsViewHolder(@NonNull View itemView) {
            super(itemView);
            complaint=itemView.findViewById(R.id.complaint1);
            sport = itemView.findViewById(R.id.complaint_sport);
            type = itemView.findViewById(R.id.complaint_type);
            text = itemView.findViewById(R.id.complaint_text);
        }
    }
}
