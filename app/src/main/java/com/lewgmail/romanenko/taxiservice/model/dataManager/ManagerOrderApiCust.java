package com.lewgmail.romanenko.taxiservice.model.dataManager;

import android.util.Log;

import com.lewgmail.romanenko.taxiservice.model.api.Services;
import com.lewgmail.romanenko.taxiservice.model.api.apiMain.OrderApiCust;
import com.lewgmail.romanenko.taxiservice.model.pojo.AddOrderN;
import com.lewgmail.romanenko.taxiservice.model.pojo.CalculatePrice;
import com.lewgmail.romanenko.taxiservice.model.pojo.Price;
import com.lewgmail.romanenko.taxiservice.presenter.CustomerPresenter;
import com.lewgmail.romanenko.taxiservice.presenter.MapGooglePresenter;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by Lev on 20.11.2016.
 */

public class ManagerOrderApiCust {

    private CustomerPresenter mCustomerPresenter;
    private MapGooglePresenter mapGooglePresenter;
    private Subscription subscription = Subscriptions.empty();
  //  private EditOrderInterface view;

    private String error;

    public ManagerOrderApiCust(CustomerPresenter customerPresenter) {
        this.mCustomerPresenter = customerPresenter;
    }

    public ManagerOrderApiCust(MapGooglePresenter mapGooglePresenter) {
        this.mapGooglePresenter = mapGooglePresenter;
    }

    public Observable<Response<ResponseBody>> addOrder(AddOrderN addOrder) {
      /*  RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });*/
        OrderApiCust servises = Services.createService(OrderApiCust.class);
        Observable<Response<ResponseBody>> observer = servises.addOrder(LoggedUser.getmInstance().getToken(), addOrder);

        return observer;
    }

    public Observable<Response<ResponseBody>> deleteOrder(long orderId) {

        OrderApiCust servises = Services.createService(OrderApiCust.class);
        Observable<Response<ResponseBody>> observer = servises.deleteOrder(LoggedUser.getmInstance().getToken(), orderId);
        return observer;
    }

    public void updateOrder(AddOrderN updateOrder, long orderId) {

        OrderApiCust servises = Services.createService(OrderApiCust.class);
        Observable<Response<ResponseBody>> observer = servises.updateOrder(LoggedUser.getmInstance().getToken(),
                orderId,
                updateOrder);
        observer.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException)
                            Log.d("MyLooooooooooog",e.getMessage());
                        else
                            Log.d("MyLooooooooooog",e.getMessage());
                    }

                    @Override
                    public void onNext(Response<ResponseBody> responseBodyResponse) {
                        Log.d("MyLooooooooooog", Integer.toString(responseBodyResponse.code()));
                    }
                });

    }

    public Observable<Price> calculatePrice(CalculatePrice calculatePrice) {
        OrderApiCust servises = Services.createService(OrderApiCust.class);
        Observable<Price> observer = servises.calculatePrice(LoggedUser.getmInstance().getToken(), calculatePrice);
        return observer;
        // mCustomerPresenter.setObserverPriseResponse(observer);
    }
}
