package com.example.myandroidpro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonData {

    @GET("/login")
    Call<List<UserModel>> getUsers();

    @GET("posts")
    Call<List<PostModel>> getPosts();
    @FormUrlEncoded
    @POST("posts")
    Call<PostModel> createPost(
            @Field("title")String ed_title,
            @Field("body")String ed_post,
            @Field("author")String author
    );

    @GET("job")
    Call<List<JobModel>> getJobs();

    @POST("user")
    Call<UserModel> createUser(@Body UserModel user);
    @FormUrlEncoded
    @POST("register")
    Call<UserModel> createUser(
            @Field("user_name") String user_name,
            @Field("email") String user_email,
            @Field("password") String password
    );


}
