package com.example.myandroidpro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JsonData {

    @GET("/login")
    Call<List<UserModel>> getUsers(@Query("email") String user_email);
    @GET("/user_id")
    Call<List<UserModel>> getUserById(@Query("id") String user_id);

    @GET("posts")
    Call<List<PostModel>> getPosts();
    @FormUrlEncoded
    @POST("posts")
    Call<PostModel> createPost(
            @Field("title")String ed_title,
            @Field("body")String ed_post,
            @Field("author")String author
    );
    @FormUrlEncoded
    @POST("like")
    Call<PostModel> updateLike(
            @Field("_id")String _id,
            @Field("likes")int like
    );
    @GET("/comment")
    Call<List<CommentModel>> getComments();

    @GET("/search")
    Call<List<PostModel>> searchPost(@Query("title")String title);


    @GET("job")
    Call<List<JobModel>> getJobs();
    @FormUrlEncoded
    @POST("job-post")
    Call<JobModel> createJobPost(
            @Field("title")String job_title,
            @Field("description")String job_description,
            @Field("requirements")String job_requirements,
            @Field("salary")String job_salary,
            @Field("location")String job_location
    );

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
