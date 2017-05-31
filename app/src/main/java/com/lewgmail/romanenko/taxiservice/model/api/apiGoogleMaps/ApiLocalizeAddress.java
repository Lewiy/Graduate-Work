package com.lewgmail.romanenko.taxiservice.model.api.apiGoogleMaps;

import com.lewgmail.romanenko.taxiservice.model.pojo.localAddress.LocalAddress;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Lev on 23.05.2017.
 */

public interface ApiLocalizeAddress {
    @GET("json")
    Call<LocalAddress> getLocalAddress(@Query("latlng") String latlng,
                                       @Query("sensor") String sensValue,
                                       @Query("language") String language);
}
