package com.lewgmail.romanenko.taxiservice.api;

import com.lewgmail.romanenko.taxiservice.model.api.apiMain.UserServices;
import com.lewgmail.romanenko.taxiservice.model.dataManager.ManagerOrderApiCust;
import com.lewgmail.romanenko.taxiservice.model.dataManager.ManagerOrderApiDrivCust;
import com.lewgmail.romanenko.taxiservice.model.pojo.AdditionalRequirAddOrderSend;
import com.lewgmail.romanenko.taxiservice.model.pojo.MarkOrder;
import com.lewgmail.romanenko.taxiservice.model.pojo.OrderStatus;
import com.lewgmail.romanenko.taxiservice.model.pojo.Token;
import com.lewgmail.romanenko.taxiservice.model.pojo.UpdateOrder;
import com.lewgmail.romanenko.taxiservice.presenter.BasePresenter;
import com.lewgmail.romanenko.taxiservice.presenter.CustomerPresenter;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.schedulers.Schedulers;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Lev on 20.11.2016.
 */

public class TestOrderApi extends BaseTest {

    private Token token;
    private MockWebServer server = new MockWebServer();

    private com.google.gson.stream.JsonReader jsonReader;
    private UserServices service;
    private BasePresenter basePresenter = new BasePresenter();

    private ManagerOrderApiDrivCust manager = new ManagerOrderApiDrivCust(basePresenter);


    @Before
    public void beforeTest() throws Exception {

        // Невэдома хрэнь, але пока без неї ніяк

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
                if (request.getPath().equals("/order/125")) {
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
                            "\"additionalRequirements\" : [{  \n" +
                            " \"reqId\" : 123,\n" +
                            " \"reqValueId\":321\n" +
                            "    }]" +
                            "}");
                }
                if (request.getPath().equals("/order/95/status")) {
                    return new MockResponse().setResponseCode(200);
                }
                if (request.getMethod().equals("POST") && request.getPath().equals("/order")) {
                    return new MockResponse().setResponseCode(200);
                }
                if (request.getMethod().equals("DELETE") && request.getPath().equals("/order/405")) {
                    return new MockResponse().setResponseCode(200);
                }
                if (request.getMethod().equals("PUT") && request.getPath().equals("/order/405")) {
                    return new MockResponse().setResponseCode(200);
                }
                if (request.getMethod().equals("GET") && request.getPath().equals("/order?orderStatus=NEW")) {
                    return new MockResponse().setResponseCode(200).setBody("" +

                            "[{" +
                            "\"orderId\":127," +
                            "\"startTime\":\"2016-11-24T20:10\"," +
                            "\"startPoint\":\"lolol\"," +
                            "\"endPoint\":\"lo\"," +
                            "\"price\":123.134" +
                            "}," +
                            "{" +
                            "\"orderId\":253," +
                            "\"startTime\":\"2016-11-24T20:11\"," +
                            "\"startPoint\":\"lala\"," +
                            "\"endPoint\" : \"lalala\"," +
                            "\"price\":126.04" +
                            "}]");
                } else {
                    return new MockResponse().setResponseCode(404);
                }
            }
        };
        server.setDispatcher(dispatch);
        HttpUrl baseUrl = server.url(serverAdress);

    }

    @Test
    public void getSpecificOrderId() throws Exception {


        //////////////////////      //test Error//////////////////////////

        manager.loadOrderId(121);

        // Request
        RecordedRequest request = server.takeRequest();
        assertEquals("GET /order/121 HTTP/1.1", request.getRequestLine());
        assertEquals("application/json", request.getHeader("Content-Type"));
        assertEquals("21334sfg23", request.getHeader("Authorization"));
        TimeUnit.SECONDS.sleep(3);
        // Testing errors
        //  assertEquals("HTTP 404 OK", basePresenter.getResponseMsg());


        //////////////////    /// test data///////////////////////////////////
        manager.loadOrderId(125);
        TimeUnit.SECONDS.sleep(3);
        // Request
        request = server.takeRequest();
        assertEquals("GET /order/125 HTTP/1.1", request.getRequestLine());
        assertEquals("application/json", request.getHeader("Content-Type"));
        assertEquals("21334sfg23", request.getHeader("Authorization"));
        TimeUnit.SECONDS.sleep(3);
        // Received data
        assertEquals(121, basePresenter.getOrderSpecificId().getOrderId());
        assertEquals(321, basePresenter.getOrderSpecificId().getAdditionalRequirements().get(0).getReqValueId());
        assertEquals("пупсовод", basePresenter.getOrderSpecificId().getTaxiDriver().getName());

    }

    @Test
    public void acceptRefuseDoneOrder() throws Exception {

        MarkOrder markOrder = new MarkOrder();
        markOrder.setUserId(100);
        markOrder.setType(OrderStatus.ACCEPTED.toString());
        manager.acceptRefuseDoneOrder(95, markOrder);
        /////////////////////Test Error////////////////////////////
        RecordedRequest request = server.takeRequest();
        assertEquals("application/json; charset=UTF-8", request.getHeader("Content-Type"));
        assertEquals("21334sfg23", request.getHeader("Authorization"));
        assertEquals("PUT /order/95/status HTTP/1.1", request.getRequestLine());
        assertEquals("{" +
                "\"userId\":100," +
                "\"type\":\"ACCEPTED\"" +
                "}", request.getBody().readUtf8());
        TimeUnit.SECONDS.sleep(3);
        /////////////////////Test Data/////////////////////////////
        assertEquals(0, basePresenter.getResponceCode());
    }

    @Test
    public void addOrder() throws Exception {
        // Configuration request "add order"
     /*   AddOrder addOrder = new AddOrder();
        addOrder.setCustomerId(123421);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm",
                Locale.getDefault());
        Date date = new Date();

        addOrder.setStartTime("2016-11-24T20:10");
        addOrder.setStartPoint("Вул. лоховська 12");
        addOrder.setEndPoint("Вул. татата");
        List<AdditionalRequirAddOrderSend> additionalRequirAddOrderSends
                = new ArrayList<>();

        AdditionalRequirAddOrderSend additionalRequirAddOrderSend
                = new AdditionalRequirAddOrderSend();
        additionalRequirAddOrderSend.setReqId(123);
        additionalRequirAddOrderSend.setReqValueId(34);

        AdditionalRequirAddOrderSend additionalRequir
                = new AdditionalRequirAddOrderSend();
        additionalRequir.setReqId(567);
        additionalRequir.setReqValueId(12);

        additionalRequirAddOrderSends.add(additionalRequir);
        additionalRequirAddOrderSends.add(additionalRequirAddOrderSend);

        addOrder.setAdditionalRequirements(additionalRequirAddOrderSends);

        // Send Request
        CustomerPresenter customerPresenter = new CustomerPresenter();
        ManagerOrderApiCust managerOrderApiCusta
                = new ManagerOrderApiCust(customerPresenter);
        managerOrderApiCusta.addOrder(addOrder);

        // Test reqvest, servers`s parties
        RecordedRequest request = server.takeRequest();
        assertEquals("application/json; charset=UTF-8", request.getHeader("Content-Type"));
        assertEquals("21334sfg23", request.getHeader("Authorization"));
        assertEquals("POST /order HTTP/1.1", request.getRequestLine());
        assertEquals("{" +
                "\"customerId\":123421," +
                "\"startTime\":\"2016-11-24T20:10\"," +
                "\"startPoint\":\"Вул. лоховська 12\"," +
                "\"endPoint\":\"Вул. татата\"," +
                "\"additionalRequirements\":" +
                "[{\"reqId\":567,\"reqValueId\":12}," +
                "{\"reqId\":123,\"reqValueId\":34}]" +
                "}", request.getBody().readUtf8());
        TimeUnit.SECONDS.sleep(3);

        // Test response code
        assertEquals(0, customerPresenter.getCodeMsg());
*/
    }

    @Test
    public void testDeleteOrder() throws Exception {

        // Send Request
        CustomerPresenter customerPresenter = new CustomerPresenter();
        ManagerOrderApiCust managerOrderApiCusta
                = new ManagerOrderApiCust(customerPresenter);
        managerOrderApiCusta.deleteOrder(405);

        // Test request, servers`s parties
        RecordedRequest request = server.takeRequest();
        assertEquals("application/json", request.getHeader("Content-Type"));
        assertEquals("21334sfg23", request.getHeader("Authorization"));
        assertEquals("DELETE /order/405 HTTP/1.1", request.getRequestLine());

        //Test response code
        assertEquals(0, customerPresenter.getCodeMsg());

    }

    @Test
    public void testUpdateOrder() throws Exception {

     /*   // Testing Data

        List<AdditionalRequirAddOrderSend> additionalRequirAddOrderSends
                = new ArrayList<>();

        AdditionalRequirAddOrderSend additionalRequirAddOrderSend
                = new AdditionalRequirAddOrderSend();
        additionalRequirAddOrderSend.setReqId(123);
        additionalRequirAddOrderSend.setReqValueId(34);

        AdditionalRequirAddOrderSend additionalRequir
                = new AdditionalRequirAddOrderSend();

        additionalRequir.setReqId(567);
        additionalRequir.setReqValueId(12);

        additionalRequirAddOrderSends.add(additionalRequir);
        additionalRequirAddOrderSends.add(additionalRequirAddOrderSend);

        UpdateOrder updateOrder = new UpdateOrder();
        updateOrder.setStartTime("2016-11-24T20:10");
        updateOrder.setStartPoint("Вул. Лалка");
        updateOrder.setEndPoint("Вул. Крарыр");

        updateOrder.setAdditionalRequirements(additionalRequirAddOrderSends);
        // Send Request
        CustomerPresenter customerPresenter = new CustomerPresenter();
        ManagerOrderApiCust managerOrderApiCusta
                = new ManagerOrderApiCust(customerPresenter);
        managerOrderApiCusta.updateOrder(updateOrder, 405);

        // Test request, servers`s parties
        RecordedRequest request = server.takeRequest();
        assertEquals("application/json; charset=UTF-8", request.getHeader("Content-Type"));
        assertEquals("21334sfg23", request.getHeader("Authorization"));
        assertEquals("PUT /order/405 HTTP/1.1", request.getRequestLine());
        assertEquals("{" +
                "\"startTime\":\"2016-11-24T20:10\"," +
                "\"startPoint\":\"Вул. Лалка\"," +
                "\"endPoint\":\"Вул. Крарыр\"," +
                "\"additionalRequirements\":" +
                "[{\"reqId\":567,\"reqValueId\":12}," +
                "{\"reqId\":123,\"reqValueId\":34}]" +
                "}", request.getBody().readUtf8());

        //Test response code
        assertEquals(0, customerPresenter.getCodeMsg());*/
    }


    @Test
    public void testGetAllOrders() throws Exception {

        // Test data
        BasePresenter basePresenter
                = new BasePresenter();
        ManagerOrderApiDrivCust managerOrderApiDriver
                = new ManagerOrderApiDrivCust(basePresenter);
        managerOrderApiDriver.getAllOrdersType("new");

        // Test request
        RecordedRequest request = server.takeRequest();
//        assertEquals("application/json", request.getHeader("Content-Type"));
        //  assertEquals("21334sfg23", request.getHeader("Authorization"));
        assertEquals("GET /order?orderStatus=NEW HTTP/1.1", request.getRequestLine());

        //test response
        // TimeUnit.SECONDS.sleep(3);
      /*  assertEquals(127, driverPresenter.getOrder().get(0).getOrderId());
        assertEquals("lolol", driverPresenter.getOrder().get(0).getStartPoint());
        assertEquals("2016-11-24T20:10", driverPresenter.getOrder().get(0).getStartTime());
        assertEquals("lo", driverPresenter.getOrder().get(0).getEndPoint());
        assertEquals(123.134, driverPresenter.getOrder().get(0).getPrice());

        assertEquals(253, driverPresenter.getOrder().get(1).getOrderId());
        assertEquals("lala", driverPresenter.getOrder().get(1).getStartPoint());
        assertEquals("2016-11-24T20:11", driverPresenter.getOrder().get(1).getStartTime());
        assertEquals("lalala", driverPresenter.getOrder().get(1).getEndPoint());
        assertEquals(126.04, driverPresenter.getOrder().get(1).getPrice());

        assertEquals(0, driverPresenter.getResponseCode());*/
    }

    @After
    public void afterTest() throws Exception {

        server.shutdown();
        RxAndroidPlugins.getInstance().reset();
    }
}
