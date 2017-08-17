package com.example.heyukun.rxretrofitmvpdemo.domain.VP.home.IView;

import com.example.heyukun.rxretrofitmvpdemo.domain.entity.Book;

/**
 * Created by heyukun on 2017/8/17.
 */

public interface BookView extends IView {
    void onSuccess(Book mBook);
    void onError(String error);
}
