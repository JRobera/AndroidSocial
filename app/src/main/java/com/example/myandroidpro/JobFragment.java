package com.example.myandroidpro;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JobFragment extends Fragment {
private JsonData jsonData;
RecyclerView jobRV;
    public JobFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        jobRV = view.findViewById(R.id.RVJob);

        ArrayList<JobModel> jobModelArrayList = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:4000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonData = retrofit.create(JsonData.class);
        JobAdapter jobAdapter = new JobAdapter(this.getContext(),jobModelArrayList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);

        Call<List<JobModel>> call = jsonData.getJobs();
        call.enqueue(new Callback<List<JobModel>>() {
            @Override
            public void onResponse(Call<List<JobModel>> call, Response<List<JobModel>> response) {
                if(!response.isSuccessful()){
                    return;
                }
                List<JobModel> jobs = response.body();
                Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();

                for(JobModel job: jobs){
                    jobModelArrayList.add(new JobModel(job.getTitle(),job.getDescription(),job.getRequirements(),job.getSalary(),job.getLocation(),job.getPosted_date()));
                }
                jobRV.setLayoutManager(linearLayoutManager);
                jobRV.setAdapter(jobAdapter);
            }

            @Override
            public void onFailure(Call<List<JobModel>> call, Throwable t) {

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_job, container, false);
    }
}