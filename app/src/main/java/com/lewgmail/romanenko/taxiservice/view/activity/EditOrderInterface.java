package com.lewgmail.romanenko.taxiservice.view.activity;

/**
 * Created by Lev on 06.12.2016.
 */

public interface EditOrderInterface extends IView {

    void setDistance(String value);

    void setDuration(String duration);

    void setPrice(double price);

    void setDateRide(String date);

    void setTimeRide(String time);

    void setNameCastomer(String name);

    void setNameDriver(String name);

    void setTypeOrder(String status);

    String getStartPoint();

    void setStartPoint(String startPoint);

    String getEndPoint();

    void setEndPoint(String endPoint);

    String getTypeCar();

    void setTypeCar(String endPoint);

    String getTypeReckoning();

    void setTypeReckoning(String endPoint);

    String getTime();

    String getDate();

    void setCustomerId(long id);

    void setOrderId(long id);

    void setDriverId(long id);

}
