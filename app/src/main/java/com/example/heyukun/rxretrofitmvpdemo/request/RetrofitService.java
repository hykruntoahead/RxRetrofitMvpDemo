package com.example.heyukun.rxretrofitmvpdemo.request;

import com.example.heyukun.rxretrofitmvpdemo.entity.Book;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by heyukun on 2017/8/17.
 */

public interface RetrofitService {
    /**
     *
     * @param name
     * @param tag
     * @param start
     * @param count
     * @return Book
     * eg : https://api.douban.com/v2/book/search?q=金瓶梅&tag=&start=0&count=1
     */
    @GET("book/search")
    Call<Book> getSearchBook(@Query("q") String name,
                             @Query("tag") String tag,
                             @Query("start") int start,
                             @Query("count") int count);

}
