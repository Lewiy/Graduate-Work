package com.lewgmail.romanenko.taxiservice.model.api.apiMain;

import com.lewgmail.romanenko.taxiservice.model.pojo.MarkOrder;
import com.lewgmail.romanenko.taxiservice.model.pojo.Order;
import com.lewgmail.romanenko.taxiservice.model.pojo.OrderId;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Lev on 18.11.2016.
 */

public interface OrderApiDrivCust {
    @GET("order/{orderId}")
    Observable<OrderId> getOrderId(@Header("Authorization") String authorization, @Path("orderId") long orderId);

    @PUT("order/{orderId}/status")
    Observable<Response<ResponseBody>> acceptOrder(@Header("Authorization") String authorization, @Path("orderId") long orderId,
                                                   @Body MarkOrder markOrder);

    @GET("order")
    Observable<List<Order>> getAllOrderType(@Header("Authorization") String authorization, @Query("orderStatus") String orderStatus);
}
