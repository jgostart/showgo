package edu.swpu.gps;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.POST;
public interface ApiService {
    @GET("posts/{id}")
    Call<POST> getPost(@Path("id") int postId);

    @GET("music-list.json/")
    Call<ApiService> getWeather();
}