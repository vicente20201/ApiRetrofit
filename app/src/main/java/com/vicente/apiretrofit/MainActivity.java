package com.vicente.apiretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.vicente.apiretrofit.Model.Posts;
import com.vicente.apiretrofit.interfaz.JsonPlaceholder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView mJsonTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mJsonTxtView = findViewById(R.id.jsonText);
        getPosts();

    }
    private void getPosts(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceholder  JsonPlaceholder = retrofit.create(com.vicente.apiretrofit.interfaz.JsonPlaceholder.class);
        Call<List<Posts>> call= JsonPlaceholder.getPosts();
        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if (!response.isSuccessful()){
                    mJsonTxtView.setText("Codigo:"+ response.code());
                    return;
                }
                List<Posts> postsList= response.body();
                for (Posts posts: postsList){
                    String content ="";
                    content += "userId:"+ posts.getUserId()+"\n";
                    content += "id:"+ posts.getId()+"\n";
                    content += "title:"+ posts.getTitle()+"\n";
                    content += "body:"+ posts.getBody()+"\n";
                    mJsonTxtView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                mJsonTxtView.setText(t.getMessage());
            }
        });
    }
}