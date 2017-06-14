package com.lewgmail.romanenko.taxiservice.model.api.apiMain;

import com.lewgmail.romanenko.taxiservice.model.pojo.GetOrder;
import com.lewgmail.romanenko.taxiservice.model.pojo.MarkOrder;
import com.lewgmail.romanenko.taxiservice.model.pojo.Order;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Lev on 18.11.2016.
 */

public interface OrderApiDrivCust {
    @GET("order/{orderId}")
    Observable<GetOrder> getOrderId(@Header("Authorization") String authorization, @Path("orderId") long orderId);

    @PATCH("order/{orderId}/status")
    Observable<Response<ResponseBody>> acceptOrder(@Header("Authorization") String authorization, @Path("orderId") long orderId,
                                                   @Body MarkOrder markOrder);

    @GET("order")
    Observable<List<Order>> getAllOrderType(@Header("Authorization") String authorization, @Query("orderStatus") String orderStatus,
                                            @Query("use-coordinates") boolean value);
}
