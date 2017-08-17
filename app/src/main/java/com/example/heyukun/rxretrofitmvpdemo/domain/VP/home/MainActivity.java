package com.example.heyukun.rxretrofitmvpdemo.domain.VP.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heyukun.rxretrofitmvpdemo.R;
import com.example.heyukun.rxretrofitmvpdemo.domain.VP.home.IView.BookView;
import com.example.heyukun.rxretrofitmvpdemo.domain.VP.home.presenter.BookPresenterImpl;
import com.example.heyukun.rxretrofitmvpdemo.domain.entity.Book;


public class MainActivity extends AppCompatActivity implements BookView{
    private TextView mTextView;
    private Button mBtn;
    private BookPresenterImpl mBookPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        bindPresenter();
//        usualReq();

    }

    private void bindPresenter() {
        mBookPresenter = new BookPresenterImpl(this);
        mBookPresenter.onCreate();
        mBookPresenter.attachView(this);
    }

    private void initWidgets() {
        mTextView = (TextView) findViewById(R.id.textView);
        mBtn = (Button) findViewById(R.id.button);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBookPresenter.getSearchBooks("西游记",null,0,1);
            }
        });
    }

    @Override
    public void onSuccess(Book mBook) {
        mTextView.setText(mBook.toString());
    }

    @Override
    public void onError(String error) {
        Toast.makeText(getApplicationContext(),error,Toast.LENGTH_SHORT).show();
    }


//    private void usualReq(){
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.douban.com/v2/")
//                .addConverterFactory(GsonConverterFactory.
//                        create(new GsonBuilder().create()))
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//支持Rxjava
//                .build();
//        RetrofitService service = retrofit.create(RetrofitService.class);
//        Observable<Book> observable = service.getSearchBook("三国演义",null,0,1);
//        observable.subscribeOn(Schedulers.io()) //请求数据的事件发生在io线程
//                .observeOn(AndroidSchedulers.mainThread()) //完成请求后在主线程更新UI
//                .subscribe(new Observer<Book>() { //订阅
//                    @Override
//                    public void onCompleted() {
//                        //所有事件都完成
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        //请求过程中发生错误
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onNext(Book book) {
//                        //请求返回的实例（Book）
//                        textView.setText(book.toString());
//                    }
//                });
//
//    }
}
