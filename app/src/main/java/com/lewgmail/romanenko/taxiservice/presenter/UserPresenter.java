package com.lewgmail.romanenko.taxiservice.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.lewgmail.romanenko.taxiservice.model.dataManager.LoggedUser;
import com.lewgmail.romanenko.taxiservice.model.dataManager.ManagerUser;
import com.lewgmail.romanenko.taxiservice.model.pojo.Token;
import com.lewgmail.romanenko.taxiservice.model.pojo.UpdateUser;
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

    // Номер проекта в Google Developer Console
    public final static String PROJECT_NUMBER = "450048184273";
    // Пространство для получение токена. Равно строке "GCM"
    public final static String SCOPE = GoogleCloudMessaging.INSTANCE_ID_SCOPE;
    // Ключ токена
    private static final String PREF_DEVICE_TOKEN = "DeviceToken";
    // Имя Shared Preferences
    private static final String SHARED_PREF_NAME = "Preferences";
    // Флаг, отвечающий за то, отправлен токен на сервер или нет
    private static final String PREF_DEVICE_TOKEN_SENT = "DeviceTokenSent";
    private ManagerUser managerUser = new ManagerUser();
    private UserOperationInterfaceInfoCustom view;
    private UserOperationInterface view2;
    private Subscription subscription = Subscriptions.empty();
    private String statusLogIn = null;

    public UserPresenter(UserOperationInterfaceInfoCustom view) {
        this.view = view;
    }
    public UserPresenter(UserOperationInterface view2) {
        this.view2 = view2;
    }

    // Возвращает Shared Preferences
    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    // Сохраняет токен в Shared Preferences
    public static void setDeviceToken(Context context, String deviceToken) {
        SharedPreferences sp = getSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(PREF_DEVICE_TOKEN, deviceToken);
        editor.commit();
    }

    // Достает токен из Shared Preferences
    public static String getDeviceToken(Context context) {
        SharedPreferences sp = getSharedPreferences(context);
        return sp.getString(PREF_DEVICE_TOKEN, null);
    }

    // Возвращает true, если токен отправлен на сервер
    public static boolean isDeviceTokenSent(Context context) {
        SharedPreferences sp = getSharedPreferences(context);
        return sp.getBoolean(PREF_DEVICE_TOKEN_SENT, false);
    }

    // Задает флаг, отправлен токен на сервер или нет
    public static void setDeviceTokenSend(Context context, boolean sent) {
        SharedPreferences sp = getSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(PREF_DEVICE_TOKEN_SENT, sent);
        editor.commit();
    }

    // Возвращает токен устройства.
// Если его еще не существует, то создает его и сохраняет в Shared Preferences
    public static String createDeviceToken(Context context) {
        String token = getDeviceToken(context);
        if (token == null) {
            try {
                token = InstanceID.getInstance(context).getToken(PROJECT_NUMBER, SCOPE, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
            setDeviceToken(context, token);
        }
        return token;
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

    public void updateUser(UpdateUser user) {

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

    public String logIn(Context context, String username, String password, String tokenGCM) {

        Call<Token> call = null;
        Token token = null;
        try {
            call = managerUser.logIn(username, password, tokenGCM);

            token = call.execute().body();
            if (token != null) {
                LoggedUser.getmInstance().setToken(token.getTokenType() + " " + token.getmToken());
                LoggedUser.getmInstance().setUserType(token.getUserType());
                LoggedUser.getmInstance().setUserId(token.getUserid());
                setDeviceTokenSend(context, true);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR";
        }

        if (token != null)
            return token.getUserType();
        else return "ERROR";
    }

    // Отправляет токен на сервер.
// Если токена не существует, создает его.
    public String createAndSendDeviceToken(final Context context, final String username, final String password) {
        // Если токен уже отправлен на сервер, то ничего не делаем
        String tok = LoggedUser.getmInstance().getPREF_DEVICE_TOKEN();
       /* if(isDeviceTokenSent(context)) {
            statusLogIn = logIn(context,username,password,LoggedUser.getmInstance().getPREF_DEVICE_TOKEN());
            return statusLogIn;
        }*/

        String tokenNotify = createDeviceToken(context);
        if (tokenNotify != null) {
            //  sendTokenToServer(s);
            statusLogIn = logIn(context, username, password, tokenNotify);
        } else {
            // Действие при ошибке
            statusLogIn = "ERROR";
        }

       /* new A<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                return createDeviceToken(context);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                if (s != null) {
                    //  sendTokenToServer(s);
                    statusLogIn = logIn(context,username,password,s);
                } else {
                    // Действие при ошибке
                    statusLogIn = "ERROR";
                }
            }
        }.execute();*/
        return statusLogIn;
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
                        //  view2.setEmailSideBar(user.getEmail());
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
                        // view.setCarId(user.getCar().getCarId());
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

                        if (user.getMobileNumbers().size() > 0) {
                            view.setUserMobileId(user.getMobileNumbers().get(0).getIdMobileNumber());
                            view.setUserMobile(user.getMobileNumbers().get(0).getMobileNumber());
                        }
                        if (user.getMobileNumbers().size() == 2) {
                            view.setUserMobileId2(user.getMobileNumbers().get(1).getIdMobileNumber());
                            view.setUserMobile2(user.getMobileNumbers().get(1).getMobileNumber());
                        }
                            /*for (MobileNumbers number: user.getMobileNumbers()) {
                                view.setUserMobileId(user.getMobileNumbers().get(0).getIdMobileNumber());
                            }*/

                        view.setExpirationTime(user.getDriverLicense().getExpirationTime());
                        view.setCodeLicense(user.getDriverLicense().getCode());
                    }
                });
    }
}
