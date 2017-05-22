package com.lewgmail.romanenko.taxiservice.model.api;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lev on 28.11.2016.
 */

public class ServicesGoogleMaps {
    /* public static final String API_ENDPOINT =
             "https://maps.googleapis.com/maps/api/distancematrix/";*/
    public static final String API_ENDPOINT =
            "https://maps.googleapis.com/maps/api/directions/";
    private static final int TIMEOUT = 60;
    private static final int WRITE_TIMEOUT = 120;
    private static final int CONNECT_TIMEOUT = 10;
    private static final OkHttpClient.Builder CLIENT = new OkHttpClient
            .Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

    private static Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();

            Request request = original.newBuilder()
                    .header("Accept", "application/xml")
                    .header("Content-Type", "application/xml")
                    .method(original.method(), original.body())
                    .build();
            try {
                final Request copy = request.newBuilder().build();
                final Buffer buffer = new Buffer();
                if (copy.body() != null) {
                    copy.body().writeTo(buffer);
                    String sad = buffer.readUtf8();
                    Log.i("req", sad);
                }

            } catch (final IOException e) {
                //  return "did not work";
            }
            return chain.proceed(request);
        }


    };

    static {
        CLIENT.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        CLIENT.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        CLIENT.readTimeout(TIMEOUT, TimeUnit.SECONDS);
    }

    /**
     * Creates a retrofit service from an arbitrary class
     *
     * @param serviceClass Java interface of the retrofit service
     * @return retrofit service with defined endpoint
     */
    public static <S> S createService(Class<S> serviceClass) {
        CLIENT.interceptors().add(interceptor);
        Retrofit retrofit = builder.client(CLIENT.build()).build();
        return retrofit.create(serviceClass);
    }

}
