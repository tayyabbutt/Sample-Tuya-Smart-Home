package com.smart.wbm.networking;


import com.smart.wbm.models.HomeModel;
import com.smart.wbm.models.HomePost;
import com.smart.wbm.models.RoomDto;
import com.smart.wbm.models.RoomPost;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiService {

    @FormUrlEncoded
    @POST
    Call<HomePost> postHome(@Url String url, @Field("name") String homeName);

    @POST
    Call<List<RoomPost>> postRoom(@Url String url, @Body RequestBody roomPostBody);


    @GET
    Call<List<HomeModel>> getHome(@Url String url);

    @GET
    Call<RoomDto> getRooms(@Url String url);


}
