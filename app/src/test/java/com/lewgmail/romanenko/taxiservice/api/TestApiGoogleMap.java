package com.lewgmail.romanenko.taxiservice.api;

import com.lewgmail.romanenko.taxiservice.model.dataManager.ManagerGoogleMaps;
import com.lewgmail.romanenko.taxiservice.presenter.BasePresenter;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.schedulers.Schedulers;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Lev on 01.12.2016.
 */

public class TestApiGoogleMap extends BaseTest {

    private MockWebServer server = new MockWebServer();

    @Before
    public void beforeTest() throws Exception {

        // Не вэдома хрэнь, але пока без неї ніяк

        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });

        /////

        server.start(8080);
        final Dispatcher dispatch = new Dispatcher() {

            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                if (request.getPath().equals("/order/121")) {
                    return new MockResponse().setResponseCode(200).setBody("{\n" +
                            "  \"orderId\" : 121,\n" +
                            "  \"startTime\" : \"DateTime\",\n" +
                            "  \"startPoint\" : \"ул. лоховская 12\",\n" +
                            "  \"endPoint\" : \"ул. лоховская 13\",\n" +
                            "  \"status\" : \"DONE\",\n" +
                            "  \"customer\" :{\n" +
                            "    \"customerId\" : 123531,\n" +
                            "    \"name\" : \"Пупсик\"\n" +
                            "  },\n" +
                            "  \"taxiDriver\":{\n" +
                            "    \"taxiDriverId\" : 642134,\n" +
                            "    \"name\" : \"пупсовод\"\n" +
                            "  },\n" +
                            "  \"price\" : 12,\n" +
                            "  \"additionalRequirements\" : [\n" +
                            "    {\"id\" : 1253,\n" +
                            "      \"name\" : \"Розовые ушки у водителя\",\n" +
                            "      \"description\" : \"Водитель должен быть с розовыми ушками на голове\"\n" +
                            "    }]\n" +
                            "}");
                } else {

                    return new MockResponse().setResponseCode(404);
                }
            }
        };
        server.setDispatcher(dispatch);
        HttpUrl baseUrl = server.url(serverAdress);

    }

    @Test
    public void testDistanceGoogleMap() throws InterruptedException {
        BasePresenter basePresenter = new BasePresenter();
        ManagerGoogleMaps managerGoogleMaps = new ManagerGoogleMaps(basePresenter);

        managerGoogleMaps.getDistance(50.45466, 30.52380, 49.59373, 34.54073);
        TimeUnit.SECONDS.sleep(5);
        RecordedRequest request = server.takeRequest();
        assertEquals("GET ? HTTP/1.1", request.getRequestLine());
    }

    @After
    public void afterTest() throws Exception {

        server.shutdown();
        RxAndroidPlugins.getInstance().reset();
    }
}
