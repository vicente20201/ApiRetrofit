package com.vicente.apiretrofit.interfaz;

import com.vicente.apiretrofit.Model.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceholder {
    @GET("posts")
    Call<List<Posts>> getPosts();
}
