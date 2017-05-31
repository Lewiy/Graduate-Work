package com.lewgmail.romanenko.taxiservice.model.api.apiGoogleMaps;

import com.lewgmail.romanenko.taxiservice.model.pojo.pojoResponseDistance.DistanceGoogleResponse;

import okhttp3.ResponseBody;
import retrofit2.Response;
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

    @GET("xml")
    Observable<Response<ResponseBody>> getRoute(@Query("origin") String address1,
                                                @Query("destination") String address2,
                                                @Query("waypoints") String address3,
                                                @Query("sensor") String sensValue,
                                                @Query("units") String units,
                                                @Query("mode") String modeRoute);


}
