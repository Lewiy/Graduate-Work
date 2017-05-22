package com.lewgmail.romanenko.taxiservice.model.dataManager;

import com.lewgmail.romanenko.taxiservice.model.DTO.DataGoogleMapDTO;
import com.lewgmail.romanenko.taxiservice.model.DTO.Mapper;
import com.lewgmail.romanenko.taxiservice.model.api.ServicesGoogleMaps;
import com.lewgmail.romanenko.taxiservice.model.api.apiGoogleMaps.ApiGoogleMaps;
import com.lewgmail.romanenko.taxiservice.model.pojo.pojoResponseDistance.DistanceGoogleResponse;
import com.lewgmail.romanenko.taxiservice.presenter.DriverCustPresenter;
import com.lewgmail.romanenko.taxiservice.view.adapters.DTORoute;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Lev on 28.11.2016.
 */

public class ManagerGoogleMaps {

    private static final String SENSOR = "false";
    private static final String UNITS = "metric";
    private static final String DRIVING = "driving";
    private DriverCustPresenter driverCustPresenter;

    public ManagerGoogleMaps() {

    }

    public ManagerGoogleMaps(DriverCustPresenter driverCustPresenter) {
        this.driverCustPresenter = driverCustPresenter;
    }
    public Observable<DataGoogleMapDTO> getDistance(double longitude1, double latitude1, double longitude2, double latitude2) {

        ApiGoogleMaps servises = ServicesGoogleMaps.createService(ApiGoogleMaps.class);
        Observable<DistanceGoogleResponse> observer = servises.getDistace(
                Double.valueOf(longitude1) + "," + Double.valueOf(latitude1),
                Double.valueOf(longitude2) + "," + Double.valueOf(latitude2));

        /*observer.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DistanceGoogleResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            driverCustPresenter.onFinishRequest(((HttpException) e).code(), e.getMessage());
                            System.out.println(e.getMessage());
                        } else {
                            driverCustPresenter.onFinishRequest(0, e.getMessage());
                            System.out.println(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(DistanceGoogleResponse s) {
                        System.out.println(
                                s.getRows().get(0).getElements().get(0).getDistance().getText());
                    }
                });*/

        return observer.map(new Func1<DistanceGoogleResponse, DataGoogleMapDTO>() {
            @Override
            public DataGoogleMapDTO call(DistanceGoogleResponse distanceGoogleResponse) {
                return new Mapper(distanceGoogleResponse).getDTO();
            }
        });
    }

    public Observable<Response<ResponseBody>> getRoute(List<DTORoute> route) {
        ApiGoogleMaps servises = ServicesGoogleMaps.createService(ApiGoogleMaps.class);
        Observable<Response<ResponseBody>> observer =
                servises.getRoute(Double.valueOf(route.get(0).getLat()) + "," + Double.valueOf(route.get(0).getLng()),
                        Double.valueOf(route.get(route.size() - 1).getLat()) + "," + Double.valueOf(route.get(route.size() - 1).getLng()),
                        prepareWaypoints(route),
                        SENSOR, UNITS, DRIVING);
        return observer;
    }

   /* public Observable<Result> getLocalAddress(){

        ApiGoogleMaps servises = ServicesGoogleMaps.createService(ApiGoogleMaps.class);
        Observable<Response<ResponseBody>> observer =
                servises.getLocalAddress(,Locale.getDefault().getLanguage());
        return observer;

    }*/

    private String prepareWaypoints(List<DTORoute> route) {
        String wayPoints = "";
        for (int i = 1; i < route.size() - 1; i++) {
            wayPoints = wayPoints + Double.toString(route.get(i).getLat()) + ","
                    + Double.toString(route.get(i).getLng())
                    + "|";
        }
        return wayPoints;
    }

}
