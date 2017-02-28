package com.lewgmail.romanenko.taxiservice.api;

import com.google.gson.stream.JsonReader;
import com.lewgmail.romanenko.taxiservice.model.api.apiMain.UserServices;
import com.lewgmail.romanenko.taxiservice.model.dataManager.ManagerUser;
import com.lewgmail.romanenko.taxiservice.model.pojo.Car;
import com.lewgmail.romanenko.taxiservice.model.pojo.CarType;
import com.lewgmail.romanenko.taxiservice.model.pojo.Token;
import com.lewgmail.romanenko.taxiservice.model.pojo.User;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Lev on 16.11.2016.
 */

public class TestApi extends BaseTest {

    private Token token;
    private MockWebServer server;
    private JsonReader jsonReader;
    private UserServices service;

    @Before
    public void beforTest() throws Exception {


        server = new MockWebServer();

        // Schedule some responses.
        //server.enqueue(new MockResponse().setBody("sup, bra?"));
        // server.enqueue(new MockResponse().setBody("yo dog"));
        // Start the server.
        server.start(8080);
        final Dispatcher dispatch = new Dispatcher() {

            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                if (request.getPath().equals("/user")) {
                    return new MockResponse().setResponseCode(200).setBody("{\n" +
                            "  \"access_token\": \"some token\",\n" +
                            "  \"expires_in\": \"86400\"\n" +
                            "}");
                } else {
                    return new MockResponse().setResponseCode(404);
                }
            }
        };
        server.setDispatcher(dispatch);
        HttpUrl baseUrl = server.url(serverAdress);
        //  service = Services.createTestService(baseUrl.toString(),
        //     UserServices.class);

    }

    @Test
    public void testCreateUser() throws Exception {
        ManagerUser model = new ManagerUser();
        User user = new User();

       /* user.setName("lev");
        user.setEmail("romanenko.lew@gmail.com");
        user.setPassword("Belochka");
        List<String> phone = new ArrayList();
        phone.add("095823412");
        phone.add("09523423423");
       // user.setMobileNumbers(phone);
        user.setUserType("TAXI_DRIVER");
        Car car = new Car();
        car.setManufacturer("KOPEYKA");
        car.setModel("2101");
        car.setPlateNumber("ko3121");
        car.setSeatsNumber(5);
        car.setCarType(CarType.TRUCK);
        user.setCar(car);
        Token token = model.registration(user);
        System.out.println(" Токен" + token.getAccessToken());
        System.out.println("Номер токена" + token.getExpiresIn());

        RecordedRequest request = server.takeRequest();
        assertEquals("POST /user HTTP/1.1", request.getRequestLine());
        assertEquals("application/json; charset=UTF-8", request.getHeader("Content-Type"));
        assertEquals("{\"name\":\"lev\",\"email\":\"romanenko.lew@gmail.com\",\"password\":\"Belochka\"" +
                ",\"mobileNumbers\":[\"095823412\",\"09523423423\"],\"userType\":\"TAXI_DRIVER\",\"" +
                "car\":{\"manufacturer\":\"KOPEYKA\",\"model\":\"2101\",\"plateNumber\":\"ko3121\"," +
                "\"seatsNumber\":5,\"carType\":\"TRUCK\"}}", request.getBody().readUtf8());
        assertEquals("some token", token.getAccessToken());
        assertEquals(86400, token.getExpiresIn());*/


    }

    @After
    public void afterTest() throws Exception {

        server.shutdown();
    }
}
