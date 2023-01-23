package com.example.myandroidpro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder>{

    private final Context context;
    private final ArrayList<PostModel> postModelArrayList;

    public PostAdapter(Context context, ArrayList<PostModel> postModelArrayList) {
        this.context = context;
        this.postModelArrayList = postModelArrayList;
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
        holder.user_name.setText(model.getAuthor());
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
        private final TextView user_name;
        private final TextView post_title;
        private final ImageView post_image;
        private final TextView post;
        private final TextView likes_count;
        private final TextView comment_count;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            user_image = itemView.findViewById(R.id.user_image);
            user_name = itemView.findViewById(R.id.user_name);
            post_title = itemView.findViewById(R.id.post_title);
            post_image = itemView.findViewById(R.id.post_image);
            post = itemView.findViewById(R.id.post);
            likes_count = itemView.findViewById(R.id.txt_likes);
            comment_count = itemView.findViewById(R.id.txt_comment);
        }
        }
}
