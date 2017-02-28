package com.lewgmail.romanenko.taxiservice.model.api.apiGoogleMaps;

import com.lewgmail.romanenko.taxiservice.model.pojo.pojoResponseDistance.DistanceGoogleResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Lev on 28.11.2016.
 */

public interface ApiGoogleMaps {

    @GET("json")
    Observable<DistanceGoogleResponse> getDistace(@Query("origins") String address1,
                                                  @Query("destinations") String address2);
}
