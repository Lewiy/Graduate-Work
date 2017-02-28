package com.lewgmail.romanenko.taxiservice.presenter;

import com.lewgmail.romanenko.taxiservice.model.DTO.DataGoogleMapDTO;
import com.lewgmail.romanenko.taxiservice.model.dataManager.ManagerGoogleMaps;
import com.lewgmail.romanenko.taxiservice.model.dataManager.ManagerOrderApiCust;
import com.lewgmail.romanenko.taxiservice.model.pojo.AdditionalRequirement;
import com.lewgmail.romanenko.taxiservice.model.pojo.CalculatePrice;
import com.lewgmail.romanenko.taxiservice.presenter.adapters.AdapterAdditionalRequiremnets;
import com.lewgmail.romanenko.taxiservice.view.activity.EditOrderInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by Lev on 01.12.2016.
 */

public class MapGooglePresenter implements BasePresenterInterface {

    private ManagerGoogleMaps managerGoogleMaps = new ManagerGoogleMaps();
    private ManagerOrderApiCust managerOrderApiCust;
    private EditOrderInterface view;
    private Subscription subscription = Subscriptions.empty();
    private DataGoogleMapDTO dataGoogleMapDTOPres;
    private CustomerPresenter customerPresenter;

    public MapGooglePresenter(EditOrderInterface view) {
        this.view = view;
        managerOrderApiCust = new ManagerOrderApiCust(this);
    }

    private void onPutMapMarkers(double longitude1, double latitude1, double longitude2, double latitude2) {

        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

        Observable<DataGoogleMapDTO> observer = managerGoogleMaps.getDistance(longitude1, latitude1, longitude2, latitude2);

        subscription = observer
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataGoogleMapDTO>() {
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
                    public void onNext(DataGoogleMapDTO dataGoogleMapDTO) {
                        dataGoogleMapDTOPres = dataGoogleMapDTO;
                        view.setDistance(dataGoogleMapDTO.getDistanceTx());
                        view.setDuration(dataGoogleMapDTO.getDuration());
                        managerOrderApiCust.calculatePrice(createRequestCalcPrice(dataGoogleMapDTO.getDistance(), customerPresenter.getView()), customerPresenter);
                    }
                });


    }


    public void calculatePrice(double longitude1, double latitude1, double longitude2, double latitude2, CustomerPresenter customerPresenter) {

        this.customerPresenter = customerPresenter;
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

        onPutMapMarkers(longitude1, latitude1, longitude2, latitude2);


    }

    @Override
    public void onStop() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    private CalculatePrice createRequestCalcPrice(double distance, EditOrderInterface editOrderView) {
        CalculatePrice calculatePrice = new CalculatePrice();
        double distanceKm = distance / 1000;
        calculatePrice.setDistance(distanceKm);
        AdditionalRequirement additionalRequirement = new AdditionalRequirement();
        additionalRequirement.setReqId(1);
        String sdf = editOrderView.getTypeCar();
        additionalRequirement.setReqValueId(new AdapterAdditionalRequiremnets().getCarRev(editOrderView.getTypeCar()));
        AdditionalRequirement additionalRequirement2 = new AdditionalRequirement();
        additionalRequirement2.setReqId(2);
        additionalRequirement2.setReqValueId(new AdapterAdditionalRequiremnets().getRecRev(editOrderView.getTypeReckoning()));
        List<AdditionalRequirement> list = new ArrayList<>();
        list.add(additionalRequirement);
        list.add(additionalRequirement2);
        calculatePrice.setAdditionalRequirements(list);
        return calculatePrice;
    }


}
