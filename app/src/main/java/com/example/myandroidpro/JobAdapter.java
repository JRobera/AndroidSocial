package com.example.myandroidpro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {

    private final Context context;
    private final ArrayList<JobModel> jobModelArrayList;

    public JobAdapter(Context context, ArrayList<JobModel> jobModelArrayList) {
        this.context = context;
        this.jobModelArrayList = jobModelArrayList;
    }

    @NonNull
    @Override
    public JobAdapter.JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_card_layout,parent,false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobAdapter.JobViewHolder holder, int position) {
        JobModel model = jobModelArrayList.get(position);
        holder.job_title.setText(model.getTitle());
        holder.job_description.setText(model.getDescription());
        holder.job_requirements.setText(model.getRequirements());
        holder.job_salary.setText(String.valueOf(model.getSalary()));
        holder.job_location.setText(model.getLocation());
        holder.job_posted_date.setText(String.valueOf(model.getPosted_date()));
    }

    @Override
    public int getItemCount() {
        return jobModelArrayList.size();
    }

    public static class JobViewHolder extends RecyclerView.ViewHolder {
        private final TextView job_title, job_description, job_requirements, job_salary, job_location, job_posted_date;
        public JobViewHolder(@NonNull View itemView) {
            super(itemView);
            job_title = itemView.findViewById(R.id.job_title);
            job_description = itemView.findViewById(R.id.job_description);
            job_requirements = itemView.findViewById(R.id.job_requirements);
            job_salary = itemView.findViewById(R.id.job_salary);
            job_location = itemView.findViewById(R.id.job_location);
            job_posted_date = itemView.findViewById(R.id.job_posted_date);
        }
    }

}
