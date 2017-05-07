package com.lewgmail.romanenko.taxiservice.model.api.apiMain;

import com.lewgmail.romanenko.taxiservice.model.pojo.AddOrderN;
import com.lewgmail.romanenko.taxiservice.model.pojo.CalculatePrice;
import com.lewgmail.romanenko.taxiservice.model.pojo.OrderUpdate;
import com.lewgmail.romanenko.taxiservice.model.pojo.Price;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Lev on 18.11.2016.
 */

public interface OrderApiCust {
    @POST("order")
    Observable<Response<ResponseBody>> addOrder(@Header("Authorization") String authorization, @Body() AddOrderN addOrder);


    @DELETE("order/{orderId}")
    Observable<Response<ResponseBody>> deleteOrder(@Header("Authorization") String authorization,
                                                   @Path("orderId") long orderId);

    @PATCH("order/{orderId}")
    Observable<Response<ResponseBody>> updateOrder(@Header("Authorization") String authorization, @Path("orderId") long orderId, @Body OrderUpdate updateOrder);

    @POST("order/routeinfo")
    Observable<Price> calculatePrice(@Header("Authorization") String authorization, @Body CalculatePrice calculatePrice);
}
