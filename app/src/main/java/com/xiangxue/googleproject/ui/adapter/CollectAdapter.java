package com.xiangxue.googleproject.ui.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xiangxue.googleproject.R;
import com.xiangxue.googleproject.data.database.Student;

import java.util.ArrayList;
import java.util.List;

public class CollectAdapter extends RecyclerView.Adapter<CollectAdapter.MyViewHolder> {

    private List<Student> allStudents = new ArrayList<>();

    public void setAllStudents(List<Student> allStudents) {
        this.allStudents = allStudents;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_collect_list,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        Student student = allStudents.get(position);
        holder.tvID.setText(String.valueOf(position + 1));
        holder.tvName.setText(student.getName());
        holder.tvAge.setText(String.valueOf(student.getAge()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://m.youdao.com/dict?le=eng&q=" + holder.tvName.getText());
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allStudents.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvID, tvName, tvAge;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvID = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAge = itemView.findViewById(R.id.tv_age);
        }
    }
}
