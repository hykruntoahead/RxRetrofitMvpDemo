package com.example.heyukun.rxretrofitmvpdemo.manager;

import android.content.Context;

import com.example.heyukun.rxretrofitmvpdemo.domain.entity.Book;
import com.example.heyukun.rxretrofitmvpdemo.request.RetrofitHelper;
import com.example.heyukun.rxretrofitmvpdemo.request.RetrofitService;

import rx.Observable;

/**
 * Created by heyukun on 2017/8/17.
 */

public class DataManager {
    private RetrofitService mRetrofitService;
    public DataManager(Context context){
        this.mRetrofitService = RetrofitHelper.getInstance(context).getServer();
    }
    public Observable<Book> getSerchBooks(String name,String tag,int satrt,int count){
        return mRetrofitService.getSearchBook(name,tag,satrt,count);
    }
}
