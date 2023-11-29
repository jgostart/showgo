package edu.swpu.gps;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface HttpJson {
    @GET("music-list.json")
    Call<ResponseBody> getHaoMa();//做的是原生数据请求，之前没有注意，只用其他方式，导致一直拿不到数据


//    @GET("music-list.json/")
//    Call<ApiService> getWeather();
}