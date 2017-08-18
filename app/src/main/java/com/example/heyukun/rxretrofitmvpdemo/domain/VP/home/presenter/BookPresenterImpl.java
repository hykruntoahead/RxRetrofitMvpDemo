package com.example.heyukun.rxretrofitmvpdemo.domain.VP.home.presenter;

import android.content.Context;
import android.content.Intent;

import com.example.heyukun.rxretrofitmvpdemo.domain.VP.home.IView.BookView;
import com.example.heyukun.rxretrofitmvpdemo.domain.VP.home.IView.IView;
import com.example.heyukun.rxretrofitmvpdemo.domain.entity.Book;
import com.example.heyukun.rxretrofitmvpdemo.manager.DataManager;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by heyukun on 2017/8/17.
 */

public class BookPresenterImpl implements IPresenter{
    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private BookView mBookView;
    private Book mBook;

    public BookPresenterImpl(Context mContext){
        this.mContext = mContext;

    }

    @Override
    public void onCreate() {
        manager = new DataManager(mContext);
        mCompositeSubscription = new CompositeSubscription();

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
      if(mCompositeSubscription.hasSubscriptions()){
          mCompositeSubscription.unsubscribe();
      }
      mContext = null;
    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(IView view) {
         mBookView = (BookView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getSearchBooks(String name,String tag,int start,int count){
        mCompositeSubscription.add(manager.getSerchBooks(name,tag,start,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Book>() {
                    @Override
                    public void onCompleted() {
                        if(mBook != null){
                            mBookView.onSuccess(mBook);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mBookView.onError("请求失败");
                    }

                    @Override
                    public void onNext(Book book) {
                      mBook = book;
                    }
                })
        );
    }
}
