package com.lewgmail.romanenko.taxiservice.model.dataManager;

import com.lewgmail.romanenko.taxiservice.model.api.Services;
import com.lewgmail.romanenko.taxiservice.model.api.apiMain.UserServices;
import com.lewgmail.romanenko.taxiservice.model.pojo.LogInRequest;
import com.lewgmail.romanenko.taxiservice.model.pojo.Token;
import com.lewgmail.romanenko.taxiservice.model.pojo.UpdateUser;
import com.lewgmail.romanenko.taxiservice.model.pojo.User;
import com.lewgmail.romanenko.taxiservice.model.pojo.UserRegistration;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;

/**
 * Created by Lev on 14.11.2016.
 */

public class ManagerUser {


    public Call<Token> logIn(String username, String password) throws IOException {
        LogInRequest logInRequest = new LogInRequest();
        logInRequest.setUserName(username);
        logInRequest.setPassword(password);
        UserServices servises = Services.createService(UserServices.class);
        Call<Token> call = servises.logIn(logInRequest);
        //Log.d("My log1"," Токен" +call.execute().body().getAccessToken());
        // Log.d("My log2","Номер токена" + call.execute().body().getExpiresIn());
    //    return call.execute().body();
        return call;
       /* call.enqueue(new Callback<Token>() {

            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.isSuccessful()) {
                    Token user = response.body();
                } else {
                    int statusCode = response.code();

                    // Обрабатываем HTTP ошибку
                    ResponseBody errorBody = response.errorBody();
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {

            }
        });*/
    }

    public Observable<Response<ResponseBody>> registration(UserRegistration user) {
        UserServices servises = Services.createService(UserServices.class);
        Observable<Response<ResponseBody>> observer  = servises.registration(user);
        return observer;
    }

    public Observable<User> getUserProfile(long id) {
        UserServices services = Services.createService(UserServices.class);
        Observable<User> observer = services.getUserProfile(LoggedUser.getmInstance().getToken(), id);
        return observer;
    }

    public Observable<Response<ResponseBody>> upDateUser(UpdateUser user) {
        UserServices servises = Services.createService(UserServices.class);
        Observable<Response<ResponseBody>> observer = servises.updateUserProfile(LoggedUser.getmInstance().getToken(), user);
        return observer;
    }

    public Observable<Response<ResponseBody>> logOut() {
        UserServices servises = Services.createService(UserServices.class);
        Observable<Response<ResponseBody>> observer = servises.logOut(LoggedUser.getmInstance().getToken());
        return observer;
    }
}
