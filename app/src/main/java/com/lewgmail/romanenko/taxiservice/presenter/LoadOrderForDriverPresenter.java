package com.lewgmail.romanenko.taxiservice.presenter;

import com.lewgmail.romanenko.taxiservice.model.dataManager.ManagerOrderApiDrivCust;
import com.lewgmail.romanenko.taxiservice.model.dataManager.ManagerUser;
import com.lewgmail.romanenko.taxiservice.model.pojo.GetOrder;
import com.lewgmail.romanenko.taxiservice.model.pojo.MarkOrder;
import com.lewgmail.romanenko.taxiservice.model.pojo.User;
import com.lewgmail.romanenko.taxiservice.view.activity.InfoUser;
import com.lewgmail.romanenko.taxiservice.view.viewDriver.OrderInf;

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

    private ManagerOrderApiDrivCust managerOrderApiDrivCust;
    private ManagerUser managerUser;
    private OrderInf orderInfView;
    private InfoUser viewInfoUser;

    public LoadOrderForDriverPresenter(OrderInf orderInf) {
        this.orderInfView = orderInf;
        this.managerOrderApiDrivCust = new ManagerOrderApiDrivCust();
    }

    public LoadOrderForDriverPresenter(InfoUser viewInfoUser) {
        this.viewInfoUser = viewInfoUser;
        this.managerUser = new ManagerUser();
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

                            //orderInfView.showError(response.errorBody().string());
                            orderInfView.showError(response.code());

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
                        //orderInfView.setDriverId(orderId.getDriverId());
                        orderInfView.setCustomerId(orderId.getCustomerId());
                        orderInfView.setAdditionalRequirements(orderId.getAdditionalRequirements());

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
                            orderInfView.showError(((HttpException) e).code());

                    }

                    @Override
                    public void onNext(Response<ResponseBody> responseBodyResponse) {
                        orderInfView.showError(responseBodyResponse.code());
                       /* if(responseBodyResponse.code() != 200)
                            orderInfView.resStatusOrderNotChanged();*/
                    }
                });
    }

    public void getUserId(long id) {

        Observable<User> observer = managerUser.getUserProfile(id);

        observer.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException)
                            viewInfoUser.showError(((HttpException) e).code());
                        else
                            viewInfoUser.showError(((HttpException) e).code());
                    }

                    @Override
                    public void onNext(User user) {
                        //  if (LoggedUser.getmInstance().getUserType().equals("TAXI_DRIVER")) {
                        // view.setCarId(user.getCar().getCarId());
                        viewInfoUser.setUserDriverBrand(user.getCar().getManufacturer());
                        viewInfoUser.setUserDriverModel(user.getCar().getModel());
                        viewInfoUser.setUserDriverCarType(user.getCar().getCarType());
                        viewInfoUser.setUserDriverPlateNumber(user.getCar().getPlateNumber());
                        viewInfoUser.setUserDriverSeatsNumber(Integer.toString(user.getCar().getSeatsNumber()));
                        //  }

                        viewInfoUser.setUserName(user.getName());
                        viewInfoUser.setUserEmail(user.getEmail());
                        //view.setPassword(user.getPassword());
                        // view.setRepeatePassword(user.getPassword());

                        if (user.getMobileNumbers().size() > 0) {
                            viewInfoUser.setUserMobileId(user.getMobileNumbers().get(0).getIdMobileNumber());
                            viewInfoUser.setUserMobile(user.getMobileNumbers().get(0).getMobileNumber());
                        }
                        if (user.getMobileNumbers().size() == 2) {
                            viewInfoUser.setUserMobileId2(user.getMobileNumbers().get(1).getIdMobileNumber());
                            viewInfoUser.setUserMobile2(user.getMobileNumbers().get(1).getMobileNumber());
                        }
                            /*for (MobileNumbers number: user.getMobileNumbers()) {
                                view.setUserMobileId(user.getMobileNumbers().get(0).getIdMobileNumber());
                            }*/

                        viewInfoUser.setExpirationTime(user.getDriverLicense().getExpirationTime());
                        viewInfoUser.setCodeLicense(user.getDriverLicense().getCode());
                    }
                });
    }

    private MarkOrder createChangeStatusOrderObject(String statusOrder) {
        MarkOrder markOrder = new MarkOrder();
        markOrder.setType(statusOrder);
        return markOrder;
    }
}
