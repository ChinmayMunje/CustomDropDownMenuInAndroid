package com.gtappdevelopers.googlemapsroutes.Retrofit;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitAPI {

    @POST("addCourse.php")
    Call<ResponseBody> addCourse(@Body RequestBody dataModal);

    @POST("deleteCourse.php")
    Call<ResponseBody> deleteCourse(@Body RequestBody dataModal);

    @POST("updateCourse.php")
    Call<ResponseBody> updateCourse(@Body RequestBody dataModal);


    @GET("readCourse.php")
    Call<List<DataModal>> getCourses();


}
