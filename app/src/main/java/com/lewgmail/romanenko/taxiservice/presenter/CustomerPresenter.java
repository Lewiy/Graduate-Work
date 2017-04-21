package com.lewgmail.romanenko.taxiservice.presenter;

import android.util.Log;

import com.lewgmail.romanenko.taxiservice.model.DTO.DataGoogleMapDTO;
import com.lewgmail.romanenko.taxiservice.model.dataManager.ManagerOrderApiCust;
import com.lewgmail.romanenko.taxiservice.model.pojo.AddOrderN;
import com.lewgmail.romanenko.taxiservice.model.pojo.AdditionalRequirementN;
import com.lewgmail.romanenko.taxiservice.model.pojo.CalculatePrice;
import com.lewgmail.romanenko.taxiservice.model.pojo.OrderPriceN;
import com.lewgmail.romanenko.taxiservice.model.pojo.Price;
import com.lewgmail.romanenko.taxiservice.model.pojo.RoutePointN;
import com.lewgmail.romanenko.taxiservice.view.activity.AddOrder;
import com.lewgmail.romanenko.taxiservice.view.activity.EditOrderInterface;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lev on 20.11.2016.
 */

public class CustomerPresenter {

    private int codeMsg;
    private String responseMsg;
    private EditOrderInterface view;
    private AddOrder viewAddOrder;
    private ManagerOrderApiCust managerOrderApiCust = new ManagerOrderApiCust(this);
    private DataGoogleMapDTO dataGoogleMapDTOPres;

    public CustomerPresenter(EditOrderInterface view) {
        this.view = view;
    }

    public CustomerPresenter() {

    }

    public CustomerPresenter(AddOrder viewAddOrder) {
        this.viewAddOrder = viewAddOrder;
    }

    public EditOrderInterface getView() {
        return view;
    }

    public void addOrder() {

        Observable<Response<ResponseBody>> observer = managerOrderApiCust.addOrder(createOrder());
        observer.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException)
                            viewAddOrder.responseAddorder(e.getMessage());
                    }

                    @Override
                    public void onNext(Response<ResponseBody> responseBodyResponse) {
                        viewAddOrder.responseAddorder(new String(Integer.toString(responseBodyResponse.code())));
                    }
                });

    }

    public void calculatePrice() {

        Observable<Price> observer = managerOrderApiCust.calculatePrice(createCalculatePriceObject());

        observer.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Price>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        viewAddOrder.responseCalculatePrice(e.toString());
                    }

                    @Override
                    public void onNext(Price price) {
                        viewAddOrder.responseCalculatePrice(price.getPrice().toString());
                    }
                });
    }

    public void addOrderResponse() {
        // createObjectAddOrder();
    }


    public void deleteOrder(long orderId) {
        Observable<Response<ResponseBody>> observer = managerOrderApiCust.deleteOrder(orderId);
        observer.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException)
                            view.showError(e.getMessage().toString());
                        else
                            view.showError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(Response<ResponseBody> responseBodyResponse) {
                        Log.d("MyLooooooooooog", Integer.toString(responseBodyResponse.code()));
                        view.showError(Integer.toString(responseBodyResponse.code()));
                    }
                });
    }

    public void updateOrder() {
        try {
            managerOrderApiCust.updateOrder(createObjectAddOrder(), 7);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void onFinishRequest(int codeMsg, String responseMsg) {

        this.codeMsg = codeMsg;
        this.responseMsg = responseMsg;

    }

    public int getCodeMsg() {
        return codeMsg;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setObserverPriseResponse(Observable<Price> observer) {

        observer.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Price>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException)
                            view.showError(e.getMessage().toString());
                        else
                            view.showError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(Price price) {
                        view.setPrice(price.getPrice());
                    }
                });
    }

    private AddOrderN createOrder() {
        AddOrderN addOrderN = new AddOrderN();
        addOrderN.setComment(viewAddOrder.getComment());
        addOrderN.setStartTime(viewAddOrder.getTime());
        addOrderN.setAdditionalRequirementN(viewAddOrder.getAdditionalRequirementNs());
        addOrderN.setRoutePoint(viewAddOrder.getRoute());

        return addOrderN;
    }

    private CalculatePrice createCalculatePriceObject() {

        CalculatePrice price = new CalculatePrice();
        price.setRoutePoint(viewAddOrder.getRoute());
        price.setAdditionalRequirementN(viewAddOrder.getAdditionalRequirementNs());

        return price;
    }

    private AddOrderN createObjectAddOrder(/*double distanse*/) throws ParseException {

        //   List<AdditionalRequirementN> list = new ArrayList<>();


        //   AddOrderN addOrderN = new AddOrderN();



       /* List<AdditionalRequirementN> list = new ArrayList<>();
        AdditionalRequirementN additionalRequirementCar = new AdditionalRequirementN();
        additionalRequirementCar.setReqId(1);
        additionalRequirementCar.setReqValueId(new AdapterAdditionalRequiremnets().getCarRev(getTypeCar()));
        list.add(additionalRequirementCar);

        AdditionalRequirementN additionalRequirementReckoning = new AdditionalRequirementN();
        additionalRequirementReckoning.setReqId(2);
        additionalRequirementReckoning.setReqValueId(new AdapterAdditionalRequiremnets().getRecRev(getTypeReckoning()));
        list.add(additionalRequirementReckoning);

        OrderPriceN orderPrice = new OrderPriceN();
        orderPrice.setAdditionalRequirements(list);
        double distanse1 = distanse / 1000;
        orderPrice.setDistance(distanse1);

        String dateString = convertDateTime(getDate() + " " + getTime());
        AddOrderN addOrder = new AddOrderN();
        addOrder.setCustomerId(LoggedUser.getmInstance().getUserId());
        String string = getDate() + " " + getTime();
        String dateTime = convertDateTime(string);
        addOrder.setStartTime(dateTime);
        addOrder.setStartPoint(getStartPoint());
        addOrder.setEndPoint(getEndPoint());
        addOrder.setOrderPrice(orderPrice);*/

        List<AdditionalRequirementN> list = new ArrayList<>();
        AdditionalRequirementN additionalRequirementCar = new AdditionalRequirementN();
        additionalRequirementCar.setReqId(1);
        additionalRequirementCar.setReqValueId(2);
        list.add(additionalRequirementCar);
        AdditionalRequirementN additionalRequirementC = new AdditionalRequirementN();
        additionalRequirementC.setReqId(2);
        additionalRequirementC.setReqValueId(1);
        list.add(additionalRequirementC);


        OrderPriceN orderPrice = new OrderPriceN();
        orderPrice.setAdditionalRequirements(list);
        double distanse1 = 1000;
        orderPrice.setDistance(distanse1);


        // String dateString = convertDateTime(getDate() + " " + getTime());
        AddOrderN addOrder = new AddOrderN();
        //  String string = getDate() + " " + getTime();
        //  String dateTime = convertDateTime(string);
        addOrder.setStartTime("2017-08-09T18:31:42");
        addOrder.setComment("dlfgjkldnfgdfklgjdlkgjklfg");
        //addOrder.setOrderPrice(orderPrice);

        RoutePointN routePointN = new RoutePointN();
        // routePointN.setAdminArea("dfgdfgfg");
        routePointN.setLongtitude("34.123");
        routePointN.setLatitude("34.1233");
        // routePointN.setStreet("sdfsdfsdf");
        //  routePointN.setCity("sdfsdf");
        //  routePointN.setHouseNumber("3");

        List<RoutePointN> listRoute = new ArrayList<>();

        listRoute.add(routePointN);
        listRoute.add(routePointN);
        addOrder.setRoutePoint(listRoute);
        // return null;
        return addOrder;
    }

    /////////////////////////////////////////get data from fields////////////////////////////

   /* private String getTime() {
        return view.getTime();
    }

    private String getDate() {
        return view.getDate();
    }

    private String getStartPoint() {
        return view.getStartPoint();
    }

    private String getEndPoint() {
        return view.getEndPoint();
    }

    private String getTypeCar() {
        return view.getTypeCar();
    }

    private String getTypeReckoning() {
        return view.getTypeReckoning();
    }*/
}
