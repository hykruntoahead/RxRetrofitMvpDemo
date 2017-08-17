package com.example.heyukun.rxretrofitmvpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.heyukun.rxretrofitmvpdemo.entity.Book;
import com.example.heyukun.rxretrofitmvpdemo.request.RetrofitService;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//支持Rxjava
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        Observable<Book> observable = service.getSearchBook("三国演义",null,0,1);
        observable.subscribeOn(Schedulers.io()) //请求数据的事件发生在io线程
                  .observeOn(AndroidSchedulers.mainThread()) //完成请求后在主线程更新UI
                  .subscribe(new Observer<Book>() { //订阅
                      @Override
                      public void onCompleted() {
                          //所有事件都完成
                      }

                      @Override
                      public void onError(Throwable e) {
                          //请求过程中发生错误
                          e.printStackTrace();
                      }

                      @Override
                      public void onNext(Book book) {
                         //请求返回的实例（Book）
                          textView.setText(book.toString());
                      }
                  });


    }
}
