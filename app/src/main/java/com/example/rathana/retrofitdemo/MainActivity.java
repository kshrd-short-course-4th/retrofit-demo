package com.example.rathana.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.rathana.retrofitdemo.adapter.ArticleAdapter;
import com.example.rathana.retrofitdemo.data.remote.ServiceGenerator;
import com.example.rathana.retrofitdemo.data.remote.request.ArticleRequest;
import com.example.rathana.retrofitdemo.data.remote.service.ArticleService;
import com.example.rathana.retrofitdemo.entity.Article;
import com.example.rathana.retrofitdemo.entity.ArticleResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArticleAdapter articleAdapter;
    List<Article> articles=new ArrayList<>();
    private static final String TAG = "MainActivity";
    ArticleService articleService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        articleService= ServiceGenerator.generateService(ArticleService.class);
        setupRV();
        //ArticleRequest articleRequest=new ArticleRequest();
        //articleRequest.getArticle(1,15);
        getArticle(1,15);

    }
    public void  setupRV(){
        recyclerView=findViewById(R.id.rvArticle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        articleAdapter=new ArticleAdapter(this,articles);
        recyclerView.setAdapter(articleAdapter);
    }

    public void getArticle(int page,int limit) {
        Call<ArticleResponse> call = articleService.getArticles(page, limit);
        call.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                ArticleResponse articleResponse = response.body();
                Log.e(TAG, "onResponse: " + articleResponse.getCode());
                List<Article> arts = articleResponse.getData();
                articles.addAll(arts);
                /*for (Article article : articles) {
                    Log.e(TAG, "onResponse: " + article.toString());
                    this.articles.add(article);
                }*/
                articleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {

            }
        });
    }
}
