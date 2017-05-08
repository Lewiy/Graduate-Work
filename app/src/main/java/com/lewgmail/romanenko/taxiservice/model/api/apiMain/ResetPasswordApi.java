package com.lewgmail.romanenko.taxiservice.model.api.apiMain;

import com.lewgmail.romanenko.taxiservice.model.pojo.SendCodeResetPassword;
import com.lewgmail.romanenko.taxiservice.model.pojo.SendEmailResetPassword;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Lev on 07.05.2017.
 */

public interface ResetPasswordApi {

    @POST("user/password")
    Observable<ResponseBody> sendEmail(@Body SendEmailResetPassword sendEmailResetPassword);

    @POST("user/password/{requestId}")
    Observable<Response<ResponseBody>> sendNewPassword(@Body SendCodeResetPassword sendCodeResetPassword, @Path("requestId") String requestId);

}
