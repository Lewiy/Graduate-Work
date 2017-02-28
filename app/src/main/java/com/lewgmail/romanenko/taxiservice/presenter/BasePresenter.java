package com.lewgmail.romanenko.taxiservice.presenter;

import android.util.Log;

import com.lewgmail.romanenko.taxiservice.model.dataManager.ManagerOrderApiDrivCust;
import com.lewgmail.romanenko.taxiservice.model.pojo.MarkOrder;
import com.lewgmail.romanenko.taxiservice.model.pojo.OrderId;
import com.lewgmail.romanenko.taxiservice.presenter.adapters.AdapterAdditionalRequiremnets;
import com.lewgmail.romanenko.taxiservice.view.activity.EditOrderInterface;
import com.lewgmail.romanenko.taxiservice.view.fragmentClient.OrderListFragmentInterface;

import java.util.HashMap;
import java.util.List;

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
 * Created by Lev on 06.11.2016.
 */

public class BasePresenter implements BasePresenterInterface {

    private EditOrderInterface view;
    private OrderListFragmentInterface viewOrderListFrag;
    private OrderId orderId;
    private Subscription subscription = Subscriptions.empty();
    private ManagerOrderApiDrivCust managerOrderApiDrivCust = new ManagerOrderApiDrivCust();

    // for testing
    private String responseMsg;
    private int responceCode;

    public BasePresenter(EditOrderInterface view) {
        this.view = view;
    }

    public BasePresenter(OrderListFragmentInterface orderListFragment) {
        this.viewOrderListFrag = orderListFragment;
    }

    /* for testing*/
    public BasePresenter() {

    }


    /* for testing*/
    public OrderId getOrderSpecificId() {
        return orderId;
    }

    public void setOrderSpecificId(OrderId orderId) {
        this.orderId = orderId;
    }

    public void loadOrderSpecificId(int orderId) {

        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

        Observable<OrderId> observer = managerOrderApiDrivCust.loadOrderId(orderId);

        observer.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OrderId>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException)
                            view.showError(e.getMessage());
                        else
                            //  mCustomerPresenter.onFinishRequest(((HttpException) e).code(), e.getMessage());
                            view.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(OrderId orderId) {
                        view.setOrderId(orderId.getOrderId());
                        view.setCustomerId(orderId.getCustomer().getCustomerId());
                        view.setDateRide(orderId.getStartTime().substring(0, 10));
                        view.setTimeRide(orderId.getStartTime().substring(11, 16));
                        view.setStartPoint(orderId.getStartPoint());
                        view.setEndPoint(orderId.getEndPoint());
                        view.setTypeOrder(orderId.getStatus());
                        view.setNameCastomer(orderId.getCustomer().getName());
                        view.setNameDriver(orderId.getTaxiDriver().getName());
                        view.setTypeCar(new AdapterAdditionalRequiremnets().
                                getCar(orderId.getAdditionalRequirements().get(0).getReqValueId()));
                        view.setPrice(orderId.getPrice());
                        view.setTypeReckoning(new AdapterAdditionalRequiremnets().
                                getRecon(orderId.getAdditionalRequirements().get(1).getReqValueId()));
                        view.setDriverId(orderId.getTaxiDriver().getTaxiDriverId());
                    }
                });
    }


    /////////////////////RequestMethod///////////////////////////////////////////////

    ////////////////Response method///////////////////////////////////////////////
    public String getResponseMsg() {
        return this.responseMsg;
    }

    public int getResponceCode() {
        return this.responceCode;
    }

    /*
     Error processing
     */
    public void onFinishRequest(int responceCode, String responseMsg) {
        this.responceCode = responceCode;
        this.responseMsg = responseMsg;
    }

    public void calculateDistance() {

    }


    public void loadOrdersList(String orderStatus) {

        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

        Observable<HashMap<String, List<String>>> observer = managerOrderApiDrivCust.getAllOrdersType(orderStatus);

        observer.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HashMap<String, List<String>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException)
                            viewOrderListFrag.showEror(e.getMessage());
                        else viewOrderListFrag.showEror(e.getMessage());
                    }

                    @Override
                    public void onNext(HashMap<String, List<String>> stringListHashMap) {
                        viewOrderListFrag.setOrderList(stringListHashMap);
                    }
                });
    }

    public void changeStatusOrder(long orderId, long userId, String statusOrder) {

        Observable<Response<ResponseBody>> observer = managerOrderApiDrivCust.acceptRefuseDoneOrder(orderId, createChangeStatusOrderObject(userId, statusOrder));
        observer.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException)
                            viewOrderListFrag.showEror(e.getMessage());
                        else
                            viewOrderListFrag.showEror(e.getMessage());
                    }

                    @Override
                    public void onNext(Response<ResponseBody> responseBodyResponse) {
                        Log.d("MyLooooooooooog", Integer.toString(responseBodyResponse.code()));
                        //  viewOrderListFrag.showEror(Integer.toString(responseBodyResponse.code()));
                    }
                });
    }

    @Override
    public void onStop() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    private MarkOrder createChangeStatusOrderObject(long userid, String statusOrder) {
        MarkOrder markOrder = new MarkOrder();
        markOrder.setUserId(userid);
        markOrder.setType(statusOrder);
        return markOrder;
    }

}
