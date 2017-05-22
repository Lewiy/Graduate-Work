package com.lewgmail.romanenko.taxiservice.presenter;

import com.lewgmail.romanenko.taxiservice.model.dataManager.ManagerSettings;
import com.lewgmail.romanenko.taxiservice.view.fragments.FragmentSettings;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lev on 18.05.2017.
 */

public class SettinNotificPresenter {

    private FragmentSettings viewSettings;
    private ManagerSettings managerSettings = new ManagerSettings();

    public SettinNotificPresenter(FragmentSettings fragmentSettings) {
        this.viewSettings = fragmentSettings;
    }

    public void setSettingNotific() {
        Observable<Response<ResponseBody>> observer = managerSettings.setSettings(getSwitchValue());
        observer.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException)
                            viewSettings.showError("Code:" + ((HttpException) e).code() + "Message:" + e.getMessage());

                    }

                    @Override
                    public void onNext(Response<ResponseBody> responseBodyResponse) {
                        viewSettings.showError("Code:" + responseBodyResponse.code());
                       /* if(responseBodyResponse.code() != 200)
                            orderInfView.resStatusOrderNotChanged();*/
                    }
                });
    }

    public void getSettingNotific() {
        Observable<ResponseBody> observer = managerSettings.getSettings();
        observer.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException)
                            viewSettings.showError("Code:" + ((HttpException) e).code() + "Message:" + e.getMessage());

                    }

                    @Override
                    public void onNext(ResponseBody responseBodyResponse) {
                        //  viewSettings.showError("Code:" + responseBodyResponse.code());

                        try {
                            viewSettings.setNotifStatus(Boolean.parseBoolean(responseBodyResponse.string()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }


    public boolean getSwitchValue() {
        return viewSettings.getSwitchValue();
    }


}
