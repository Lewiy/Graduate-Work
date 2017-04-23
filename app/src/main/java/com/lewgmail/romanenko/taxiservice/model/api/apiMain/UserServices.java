package com.lewgmail.romanenko.taxiservice.model.api.apiMain;

import com.lewgmail.romanenko.taxiservice.model.pojo.LogInRequest;
import com.lewgmail.romanenko.taxiservice.model.pojo.Token;
import com.lewgmail.romanenko.taxiservice.model.pojo.UpdateUser;
import com.lewgmail.romanenko.taxiservice.model.pojo.User;
import com.lewgmail.romanenko.taxiservice.model.pojo.UserRegistration;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Lev on 13.11.2016.
 */

public interface UserServices {

    @POST("login")
    Call<Token> logIn(@Body LogInRequest logInRequest);

    @POST("user/register")
    Observable<Response<ResponseBody>> registration(@Body UserRegistration user);

    @GET("user/{userId}")
    Observable<User> getUserProfile(@Header("Authorization") String authorization, @Path("userId") long id);

    @PATCH("user")
    Observable<Response<ResponseBody>> updateUserProfile(@Header("Authorization") String authorization, @Body UpdateUser user);

    @GET("logout")
    Observable<Response<ResponseBody>> logOut(@Header("Authorization") String authorization);

}
