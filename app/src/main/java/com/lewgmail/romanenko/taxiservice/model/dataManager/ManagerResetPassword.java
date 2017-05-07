package com.lewgmail.romanenko.taxiservice.model.dataManager;

import com.lewgmail.romanenko.taxiservice.model.api.Services;
import com.lewgmail.romanenko.taxiservice.model.api.apiMain.ResetPasswordApi;
import com.lewgmail.romanenko.taxiservice.model.pojo.SendCodeResetPassword;
import com.lewgmail.romanenko.taxiservice.model.pojo.SendEmailResetPassword;

import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Observable;

/**
 * Created by Lev on 07.05.2017.
 */

public class ManagerResetPassword {

    public Observable<String> sendEmail(SendEmailResetPassword sendEmailResetPassword) {
        ResetPasswordApi services = Services.createService(ResetPasswordApi.class);
        Observable<String> observer = services.sendEmail(sendEmailResetPassword);
        return observer;
    }

    public Observable<Response<ResponseBody>> sendNewPassword(SendCodeResetPassword sendCodeResetPassword, String resetId) {
        ResetPasswordApi services = Services.createService(ResetPasswordApi.class);
        Observable<Response<ResponseBody>> observer = services.sendNewPassword(sendCodeResetPassword, resetId);
        return observer;
    }
}
