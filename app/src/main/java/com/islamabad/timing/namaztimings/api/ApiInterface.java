package com.islamabad.timing.namaztimings.api;

import com.islamabad.timing.namaztimings.model.Notification;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Bilal Rashid on 1/28/2018.
 */

public interface ApiInterface {
    @GET("movie/top_rated")
    Call<Object> getTopRatedMovies(@Query("api_key") String apiKey);
    @POST("api/v1/notifications")
    Call<Object> post(@Body HashMap<String, Object> body);
    @FormUrlEncoded
    @POST("api/v1/notifications")
    Call<Object> postdata(@Body Notification notification);
//    @FormUrlEncoded
//    @POST("TestAPI")
//    Call<Object> test(@Field("request") String data);
//    @Headers({"Content-type:application/json"})
//    @POST("/SaveAttendence")
//    Call<Object> TEST(@Body List<Packet> Packets);
//
//    @GET("/api/Attendance")
//    Call<Object> getLastId(@Query("dbId") long id);
//
    @POST("api/v1/notifications")
    Call<Object> postPackets(@Body Notification data);

}
