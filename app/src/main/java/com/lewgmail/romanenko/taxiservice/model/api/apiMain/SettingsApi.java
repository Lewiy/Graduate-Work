package com.lewgmail.romanenko.taxiservice.model.api.apiMain;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Lev on 18.05.2017.
 */

public interface SettingsApi {

    @POST("user/notifications")
    Observable<Response<ResponseBody>> setSettingsNotifi(@Header("Authorization") String authorization, @Query("toggle") boolean value);

    @GET("user/notifications/toggle-position")
    Observable<ResponseBody> getSettingsNotifi(@Header("Authorization") String authorization);
}
