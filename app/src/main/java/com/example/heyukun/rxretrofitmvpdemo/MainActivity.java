package com.example.heyukun.rxretrofitmvpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.heyukun.rxretrofitmvpdemo.entity.Book;
import com.example.heyukun.rxretrofitmvpdemo.request.RetrofitService;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/")
                .addConverterFactory(GsonConverterFactory.
                        create(new GsonBuilder().create()))
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        Call<Book> call = service.getSearchBook("三国演义",null,0,1);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                textView.setText(response.body()+"");
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {

            }
        });

    }
}
