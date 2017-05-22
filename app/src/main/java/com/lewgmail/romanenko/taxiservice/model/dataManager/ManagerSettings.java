package com.lewgmail.romanenko.taxiservice.model.dataManager;

import com.lewgmail.romanenko.taxiservice.model.api.Services;
import com.lewgmail.romanenko.taxiservice.model.api.apiMain.SettingsApi;

import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Observable;

/**
 * Created by Lev on 18.05.2017.
 */

public class ManagerSettings {

    public Observable<Response<ResponseBody>> setSettings(boolean setNotify) {
        SettingsApi services = Services.createService(SettingsApi.class);
        Observable<Response<ResponseBody>> observer = services.setSettingsNotifi(LoggedUser.getmInstance().getToken(), setNotify);
        return observer;
    }

    public Observable<ResponseBody> getSettings() {
        SettingsApi services = Services.createService(SettingsApi.class);
        Observable<ResponseBody> observer = services.getSettingsNotifi(LoggedUser.getmInstance().getToken());
        return observer;
    }
}
