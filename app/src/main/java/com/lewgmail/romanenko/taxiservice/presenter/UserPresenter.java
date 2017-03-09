package com.lewgmail.romanenko.taxiservice.presenter;

import com.lewgmail.romanenko.taxiservice.model.dataManager.LoggedUser;
import com.lewgmail.romanenko.taxiservice.model.dataManager.ManagerUser;
import com.lewgmail.romanenko.taxiservice.model.pojo.Token;
import com.lewgmail.romanenko.taxiservice.model.pojo.User;
import com.lewgmail.romanenko.taxiservice.model.pojo.UserRegistration;
import com.lewgmail.romanenko.taxiservice.view.activity.UserOperationInterface;
import com.lewgmail.romanenko.taxiservice.view.activity.UserOperationInterfaceInfoCustom;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by Lev on 15.12.2016.
 */

public class UserPresenter {

    private ManagerUser managerUser = new ManagerUser();
    private UserOperationInterfaceInfoCustom view;
    private UserOperationInterface view2;
    private Subscription subscription = Subscriptions.empty();

    public UserPresenter(UserOperationInterfaceInfoCustom view) {
        this.view = view;
    }

    public UserPresenter(UserOperationInterface view2) {
        this.view2 = view2;
    }

    public void registerUser(UserRegistration user) {

        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        Observable<Response<ResponseBody>> observer = managerUser.registration(user);

        observer.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException)
                            view2.showError(e.getMessage().toString());
                        else
                            view2.showError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(Response<ResponseBody> responseBodyResponse) {
                        Integer.toString(responseBodyResponse.code());
                        responseBodyResponse.message().toString();
                        view2.doneOperation(responseBodyResponse.code(), Integer.toString(responseBodyResponse.code()) + "successfully");
                    }
                });
    }

    public void updateUser(User user) {

        Observable<Response<ResponseBody>> observer = managerUser.upDateUser(user);

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
                        Integer.toString(responseBodyResponse.code());
                        responseBodyResponse.message().toString();
                        view.showError(Integer.toString(responseBodyResponse.code()) + "Done");

                    }
                });
    }

    public void recoveryPassword() {

    }

    public String logIn(String username, String password) {

        Call<Token> call = null;
        Token token = null;
        try {
            call = managerUser.logIn(username, password);

            token = call.execute().body();
            if (token != null) {
                LoggedUser.getmInstance().setToken(token.getTokenType() + " " + token.getmToken());
                LoggedUser.getmInstance().setUserType(token.getUserType());
                LoggedUser.getmInstance().setUserId(token.getUserid());
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR";
        }

        if (token != null)
            return token.getUserType();
        else return "ERROR";
    }

    public void logOut() {
        Observable<Response<ResponseBody>> observer = managerUser.logOut();

        observer.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException)
                            view2.showError(e.getMessage().toString());
                        else
                            view2.showError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(Response<ResponseBody> responseBodyResponse) {
                        Integer.toString(responseBodyResponse.code());
                        responseBodyResponse.message().toString();
                        view2.doneOperation(responseBodyResponse.code(), Integer.toString(responseBodyResponse.code()) + "Done");
                    }
                });
    }

    public void getUserIdHomePage(long id) {
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
                            view2.showError(e.getMessage().toString());
                        else
                            view2.showError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(User user) {
                        view2.setNameSideBar(user.getName());
                        view2.setEmailSideBar(user.getEmail());
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
                            view.showError(e.getMessage().toString());
                        else
                            view.showError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(User user) {
                        //  if (LoggedUser.getmInstance().getUserType().equals("TAXI_DRIVER")) {
                        view.setCarId(user.getCar().getCarId());
                        view.setUserDriverBrand(user.getCar().getManufacturer());
                        view.setUserDriverModel(user.getCar().getModel());
                        view.setUserDriverCarType(user.getCar().getCarType());
                        view.setUserDriverPlateNumber(user.getCar().getPlateNumber());
                        view.setUserDriverSeatsNumber(Integer.toString(user.getCar().getSeatsNumber()));
                        //  }

                        view.setUserName(user.getName());
                        view.setUserEmail(user.getEmail());
                        //view.setPassword(user.getPassword());
                        // view.setRepeatePassword(user.getPassword());
                        view.setUserMobileId(user.getMobileNumbers().get(0).getIdMobileNumber());
                        view.setUserMobileId2(user.getMobileNumbers().get(1).getIdMobileNumber());
                        view.setUserMobile(user.getMobileNumbers().get(0).getMobileNumber());
                        view.setUserMobile2(user.getMobileNumbers().get(1).getMobileNumber());

                    }
                });
    }
}
