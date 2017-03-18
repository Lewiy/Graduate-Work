package com.lewgmail.romanenko.taxiservice.model.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lev on 13.11.2016.
 */

public class Services {

     /*public static final String API_ENDPOINT =
             "http://77.47.207.176:8081/taxiservice/";*/
     /*public static final String API_ENDPOINT =
             "http://77.47.218.163:8081/taxiservice/";*/
    public static final String API_ENDPOINT =
            "https://taxiserviceserver.herokuapp.com/";
    //  private static final int TIMEOUT = 60;
    //  private static final int WRITE_TIMEOUT = 120;
    //  private static final int CONNECT_TIMEOUT = 10;
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

            //  String str= bodyToString(original);


            //     Response response = chain.proceed(chain.request());
            // Log.w("Retrofit@Response", response.body().string());
            //  String string = original.body().toString();
            Request request = original.newBuilder()
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json; charset=utf-8")
                    .method(original.method(), original.body())
                    .build();
            //   Log.w("Retrofit@Response", request.body().toString());
            // String str = bodyToString(request);
            //   Log.i("req",bodyToString(request));
            // String req = request.body().;
          /*  try {
                final Request copy = request.newBuilder().build();
                final Buffer buffer = new Buffer();
                copy.body().writeTo(buffer);
                 String sad = buffer.readUtf8();
            } catch (final IOException e) {
              //  return "did not work";
            }*/

            return chain.proceed(request);
        }

    };

    private static String bodyToString(final Request request) {

        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

   /* private static String bodyToString(final Response request){

        try {
            final Response copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }*/

  /*  static {
        CLIENT.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        CLIENT.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        CLIENT.readTimeout(TIMEOUT, TimeUnit.SECONDS);
    }*/

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

    /**
     * Creates test service. For unit testing purposes.
     *
     * @param url          endpoint
     * @param serviceClass Java interface of the retrofit service
     * @param <S>
     * @return test service
     */
    public static <S> S createTestService(String url, Class<S> serviceClass) {
        OkHttpClient httpClient = new OkHttpClient();

        Retrofit.Builder builder = new Retrofit.Builder().
                baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        builder.client(httpClient);
        S apiInterface = builder.build().create(serviceClass);
        return apiInterface;
    }

}
