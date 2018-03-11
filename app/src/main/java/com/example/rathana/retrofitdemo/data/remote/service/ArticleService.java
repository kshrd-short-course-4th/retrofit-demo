package com.example.rathana.retrofitdemo.data.remote.service;

import com.example.rathana.retrofitdemo.entity.ArticleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by RATHANA on 3/11/2018.
 */

public interface ArticleService {
    @GET("/v1/api/articles")
    Call<ArticleResponse> getArticles(@Query("page") int page, @Query("limit") int limit);
}
