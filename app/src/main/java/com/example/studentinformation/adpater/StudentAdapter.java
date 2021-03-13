package com.example.studentinformation.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.studentinformation.R;
import com.example.studentinformation.model.StudentData;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {

    ArrayList<StudentData> studentDataArrayList;
    Context context;

    public StudentAdapter(ArrayList<StudentData> studentDataArrayList, Context context) {
        this.studentDataArrayList = studentDataArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_row_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.MyViewHolder holder, int position) {
        holder.name.setText(studentDataArrayList.get(position).getName());
        holder.roll.setText(studentDataArrayList.get(position).getRoll());
        holder.address.setText(studentDataArrayList.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return studentDataArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, roll, address;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_row);
            roll = itemView.findViewById(R.id.roll_row);
            address = itemView.findViewById(R.id.address_row);
        }
    }
}
