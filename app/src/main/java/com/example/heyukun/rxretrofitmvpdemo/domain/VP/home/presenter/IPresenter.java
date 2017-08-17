package com.example.heyukun.rxretrofitmvpdemo.domain.VP.home.presenter;

import android.content.Intent;
import android.view.View;

import com.example.heyukun.rxretrofitmvpdemo.domain.VP.home.IView.IView;

/**
 * Created by heyukun on 2017/8/17.
 */

public interface IPresenter {
    void onCreate();

    void onStart();
    void onStop();

    void pause();
    void attachView(IView view);
    void attachIncomingIntent(Intent intent);
}
