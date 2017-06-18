package com.lewgmail.romanenko.taxiservice.presenter;

import com.lewgmail.romanenko.taxiservice.model.dataManager.ManagerOrderApiDrivCust;
import com.lewgmail.romanenko.taxiservice.model.pojo.AdditionalRequirementN;
import com.lewgmail.romanenko.taxiservice.model.pojo.GetOrder;
import com.lewgmail.romanenko.taxiservice.model.pojo.MarkOrder;
import com.lewgmail.romanenko.taxiservice.model.pojo.OrderId;
import com.lewgmail.romanenko.taxiservice.view.activity.AddOrderUpdate;
import com.lewgmail.romanenko.taxiservice.view.fragmentClient.OrderListFragmentInterface;
import com.lewgmail.romanenko.taxiservice.view.viewDriver.OrderInf;

import java.io.IOException;
import java.util.ArrayList;
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

public class DriverCustPresenter implements BasePresenterInterface {

    private AddOrderUpdate viewAddOrderUpdate;
    private OrderInf viewOrderInfDriver;
    private OrderListFragmentInterface viewOrderListFrag;
    private OrderId orderId;
    private Subscription subscription = Subscriptions.empty();
    private ManagerOrderApiDrivCust managerOrderApiDrivCust = new ManagerOrderApiDrivCust();

    // for testing
    private String responseMsg;
    private int responceCode;

    public DriverCustPresenter(AddOrderUpdate view) {
        this.viewAddOrderUpdate = view;
    }

    public DriverCustPresenter(OrderInf viewOrderInfDriver) {
        this.viewOrderInfDriver = viewOrderInfDriver;
    }

    public DriverCustPresenter(OrderListFragmentInterface orderListFragment) {
        this.viewOrderListFrag = orderListFragment;
    }

    /* for testing*/
    public DriverCustPresenter() {

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

        Observable<GetOrder> observer = managerOrderApiDrivCust.loadOrderId(orderId);

        observer.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetOrder>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException)
                            //viewAddOrderUpdate.responseError("Code:" + ((HttpException) e).code() + "Message:" + e.getMessage());
                            try {
                                viewAddOrderUpdate.responseError(((HttpException) e).code(), ((HttpException) e).response().errorBody().string());
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        //  else
                        //  mCustomerPresenter.onFinishRequest(((HttpException) e).code(), e.getMessage());
                        //   viewAddOrderUpdate.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(GetOrder orderId) {
                        viewAddOrderUpdate.responseStartTime(orderId.getStartTime());
                        viewAddOrderUpdate.responseRoutePoint(orderId.getRoutePoint());
                        viewAddOrderUpdate.responseStatusOrder(orderId.getStatus());
                        viewAddOrderUpdate.responseCustomerId(orderId.getCustomerId());
                        viewAddOrderUpdate.responseDriverId(orderId.getDriverId());
                        viewAddOrderUpdate.responseDistance(orderId.getDistance());
                        viewAddOrderUpdate.responseDuration(orderId.getDuration());
                        viewAddOrderUpdate.responsePrice(orderId.getPrice());
                        // viewAddOrderUpdate.responseExtraPrice(orderId.getExtraPrice());
                        viewAddOrderUpdate.responseComment(orderId.getComment());
                        viewAddOrderUpdate.responseAdditionalRequirements((ArrayList<AdditionalRequirementN>) orderId.getAdditionalRequirements());
                        viewAddOrderUpdate.responseOrderId(orderId.getOrderId());
                       /* viewAddOrderUpdate.setOrderId(orderId.getOrderId());
                        viewAddOrderUpdate.setCustomerId(orderId.getCustomer().getCustomerId());
                        viewAddOrderUpdate.setDateRide(orderId.getStartTime().substring(0, 10));
                        viewAddOrderUpdate.setTimeRide(orderId.getStartTime().substring(11, 16));
                        viewAddOrderUpdate.setStartPoint(orderId.getStartPoint());
                        viewAddOrderUpdate.setEndPoint(orderId.getEndPoint());
                        viewAddOrderUpdate.setTypeOrder(orderId.getStatus());
                        viewAddOrderUpdate.setNameCastomer(orderId.getCustomer().getName());
                        viewAddOrderUpdate.setNameDriver(orderId.getTaxiDriver().getName());
                        viewAddOrderUpdate.setTypeCar(new AdapterAdditionalRequiremnets().
                                getCar(orderId.getAdditionalRequirements().get(0).getReqValueId()));
                        viewAddOrderUpdate.setPrice(orderId.getPrice());
                        viewAddOrderUpdate.setTypeReckoning(new AdapterAdditionalRequiremnets().
                                getRecon(orderId.getAdditionalRequirements().get(1).getReqValueId()));
                        viewAddOrderUpdate.setDriverId(orderId.getTaxiDriver().getTaxiDriverId());*/
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

    public void changeStatusOrder(long orderId, String statusOrder) {
        //  statusOrder = "CANCELLED";
        Observable<Response<ResponseBody>> observer = managerOrderApiDrivCust.acceptRefuseDoneOrder(orderId, createChangeStatusOrderObject(statusOrder));
        observer.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException)
                            try {
                                viewAddOrderUpdate.responseError(((HttpException) e).code(), ((HttpException) e).response().errorBody().string());
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                    }

                    @Override
                    public void onNext(Response<ResponseBody> responseBodyResponse) {
                        try {
                            viewAddOrderUpdate.responseError(responseBodyResponse.code(), responseBodyResponse.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (responseBodyResponse.code() != 200)
                            viewAddOrderUpdate.resStatusOrderNotChanged();
                    }
                });
    }

    @Override
    public void onStop() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    private MarkOrder createChangeStatusOrderObject(String statusOrder) {
        MarkOrder markOrder = new MarkOrder();
        markOrder.setType(statusOrder);
        return markOrder;
    }

}
