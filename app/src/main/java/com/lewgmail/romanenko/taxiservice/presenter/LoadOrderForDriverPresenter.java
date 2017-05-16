package com.lewgmail.romanenko.taxiservice.presenter;

import com.lewgmail.romanenko.taxiservice.model.dataManager.ManagerOrderApiDrivCust;
import com.lewgmail.romanenko.taxiservice.model.pojo.GetOrder;
import com.lewgmail.romanenko.taxiservice.model.pojo.MarkOrder;
import com.lewgmail.romanenko.taxiservice.view.viewDriver.OrderInf;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lev on 08.05.2017.
 */

public class LoadOrderForDriverPresenter {

    private ManagerOrderApiDrivCust managerOrderApiDrivCust = new ManagerOrderApiDrivCust();
    private OrderInf orderInfView;

    public LoadOrderForDriverPresenter(OrderInf orderInf) {
        this.orderInfView = orderInf;
    }

    public void loadOrderSpecificId(int orderId) {

        Observable<GetOrder> observer = managerOrderApiDrivCust.loadOrderId(orderId);

        observer.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetOrder>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        //if (e instanceof HttpException)
                        // viewAddOrderUpdate.responseError("Code:" + ((HttpException) e).code() + "Message:" + e.getMessage());
                        if (e instanceof HttpException) {
                            HttpException exception = (HttpException) e;
                            Response response = exception.response();
                            try {
                                orderInfView.showError(response.errorBody().string());
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                        //  else
                        //  mCustomerPresenter.onFinishRequest(((HttpException) e).code(), e.getMessage());
                        //   viewAddOrderUpdate.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(GetOrder orderId) {

                        orderInfView.setDistanceInf(Double.toString(orderId.getDistance()));
                        orderInfView.setDuration(orderId.getDuration());
                        orderInfView.setPrice(orderId.getPrice());
                        orderInfView.setRoute(orderId.getRoutePoint());
                        orderInfView.setTimeRide(orderId.getStartTime());
                        orderInfView.setOrderId(orderId.getOrderId());
                        orderInfView.setStatus(orderId.getStatus());
                        orderInfView.setComments(orderId.getComment());
                        //orderInfView.setBaggage(orderId);
                        orderInfView.setAdditionalRequirements(orderId.getAdditionalRequirements());
                       /* viewAddOrderUpdate.responseStartTime(orderId.getStartTime());
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
                        viewAddOrderUpdate.responseOrderId(orderId.getOrderId());*/
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

    public void changeStatusOrder(long orderId, String statusOrder) {
        String stat = new String();
        stat = statusOrder;
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
                            orderInfView.showError("Code:" + ((HttpException) e).code() + "Message:" + e.getMessage());

                    }

                    @Override
                    public void onNext(Response<ResponseBody> responseBodyResponse) {
                        orderInfView.showError("Code:" + responseBodyResponse.code());
                       /* if(responseBodyResponse.code() != 200)
                            orderInfView.resStatusOrderNotChanged();*/
                    }
                });
    }

    private MarkOrder createChangeStatusOrderObject(String statusOrder) {
        MarkOrder markOrder = new MarkOrder();
        markOrder.setType(statusOrder);
        return markOrder;
    }
}
