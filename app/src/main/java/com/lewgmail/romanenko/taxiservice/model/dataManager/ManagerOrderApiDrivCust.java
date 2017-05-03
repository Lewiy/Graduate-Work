package com.lewgmail.romanenko.taxiservice.model.dataManager;

import android.util.Log;

import com.lewgmail.romanenko.taxiservice.model.DTO.MapperListOrders;
import com.lewgmail.romanenko.taxiservice.model.api.Services;
import com.lewgmail.romanenko.taxiservice.model.api.apiMain.OrderApiDrivCust;
import com.lewgmail.romanenko.taxiservice.model.pojo.GetOrder;
import com.lewgmail.romanenko.taxiservice.model.pojo.MarkOrder;
import com.lewgmail.romanenko.taxiservice.model.pojo.Order;
import com.lewgmail.romanenko.taxiservice.model.pojo.OrderId;
import com.lewgmail.romanenko.taxiservice.presenter.BasePresenter;

import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Lev on 19.11.2016.
 */

public class ManagerOrderApiDrivCust {

    private BasePresenter mBasePresenter;
    /* for test*/
    private OrderId orderIdTEST;

    public ManagerOrderApiDrivCust() {

    }

    public ManagerOrderApiDrivCust(BasePresenter presenter) {

        this.mBasePresenter = presenter;

    }

    public Observable<GetOrder> loadOrderId(int orderId) {
        OrderApiDrivCust servises = Services.createService(OrderApiDrivCust.class);
        Observable<GetOrder> observer = servises.getOrderId(LoggedUser.getmInstance().getToken(), orderId);

      /*  observer.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OrderId>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException)
                            mBasePresenter.onFinishRequest(((HttpException) e).code(), e.getMessage());
                        else mBasePresenter.onFinishRequest(0, e.getMessage());
                    }

                    @Override
                    public void onNext(OrderId orderId) {
                        System.out.println(" Дані пришли - кастомер" + orderId.getCustomer());
                        mBasePresenter.setOrderSpecificId(orderId);
                    }
                });*/
        return observer;
    }

    public Observable<Response<ResponseBody>> acceptRefuseDoneOrder(long orderId,MarkOrder markOrder) {

        // markOrder.setUserId(10);
        Log.d("MyLoooooooooooooogs", markOrder.getType().toString());
        // Log.d("MyLoooooooooooooogs", Long.toString(markOrder.getUserId()));

        OrderApiDrivCust servises = Services.createService(OrderApiDrivCust.class);
        Observable<Response<ResponseBody>> observer = servises.acceptOrder(LoggedUser.getmInstance().getToken(), orderId,
                markOrder);

        return observer;
    }

    public Observable<HashMap<String, List<String>>> getAllOrdersType(String orderStatus) {

        OrderApiDrivCust servises = Services.createService(OrderApiDrivCust.class);
        Observable<List<Order>> observer = servises.getAllOrderType(LoggedUser.getmInstance().getToken(), orderStatus);

       /* observer.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Order>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        if (e instanceof HttpException)
                            mDriverPresenter.onFinishRequest(((HttpException) e).code(), e.getMessage());
                        else mDriverPresenter.onFinishRequest(0, e.getMessage());

                    }

                    @Override
                    public void onNext(List<Order> orders) {

                        System.out.println("Дані прийшли" + getClass().getName());
                        mDriverPresenter.loadOrderByType(orders);
                    }
                });*/
        return observer.map(new Func1<List<Order>, HashMap<String,List<String>>>() {
            @Override
            public HashMap<String,List<String>> call(List<Order> orders) {
                return new MapperListOrders(orders).getVOObject();
            }
        });
    }

}
