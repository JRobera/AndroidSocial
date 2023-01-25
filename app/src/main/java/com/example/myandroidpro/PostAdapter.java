package com.example.myandroidpro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder>{
    static JsonData jsonData;
    private final Context context;
    private final ArrayList<PostModel> postModelArrayList;


    public PostAdapter(Context context, ArrayList<PostModel> postModelArrayList) {
        this.context = context;
        this.postModelArrayList = postModelArrayList;

        //TODO retrofit instance to connect to the API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:4000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonData = retrofit.create(JsonData.class);
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_card_layout, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        PostModel model = postModelArrayList.get(position);
//        holder.user_image.setImageResource(model.getAuthor());
        holder._id.setText(model.get_id());
        Call<List<UserModel>> call = jsonData.getUserById(model.getAuthor());
        call.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                if (!response.isSuccessful()){return;}

                List<UserModel> users = response.body();
                for(UserModel user : users){
                    Toast.makeText(context.getApplicationContext(), "get", Toast.LENGTH_SHORT).show();
                    holder.user_name.setText(user.getUser_name());
                }
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                Toast.makeText(context.getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
            }

        });
        holder.post_title.setText(model.getTitle());
//        holder.post_image.setImageResource(model.getTitle());
        holder.post.setText(model.getBody());
        holder.likes_count.setText(String.valueOf(model.getLikes()));
//        holder.comment_count.setText(String.valueOf(model.getBody()));

    }

    @Override
    public int getItemCount() {
        return postModelArrayList.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        private final ImageView user_image;
        private final TextView _id;
        private final TextView user_name;
        private final TextView post_title;
        private final ImageView post_image;
        private final TextView post;
        private final TextView likes_count;
        private final ImageView like_image;
        private final ImageView comment_image;
        private final TextView comment_count;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            _id = itemView.findViewById(R.id._id);
            user_image = itemView.findViewById(R.id.user_image);
            user_name = itemView.findViewById(R.id.user_name);
            post_title = itemView.findViewById(R.id.post_title);
            post_image = itemView.findViewById(R.id.post_image);
            post = itemView.findViewById(R.id.post);
            likes_count = itemView.findViewById(R.id.txt_likes);
            comment_count = itemView.findViewById(R.id.txt_comment);

            comment_image = itemView.findViewById(R.id.comment_image);
            comment_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new CommentFragment()).commit();
                    Toast.makeText(comment_image.getContext(), "comment clicked", Toast.LENGTH_SHORT).show();
                }
            });

            like_image = itemView.findViewById(R.id.like_image);
            like_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Call<PostModel> call = jsonData.updateLike(_id.getText().toString(),Integer.valueOf(likes_count.getText().toString())+1);
                            call.enqueue(new Callback<PostModel>() {
                                @Override
                                public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                                    if(!response.isSuccessful()){
                                        return;
                                    }
                                }
                                @Override
                                public void onFailure(Call<PostModel> call, Throwable t) { }
                            });
                }
            });
        }
        }
}
