package com.lewgmail.romanenko.taxiservice.view.activity;

/**
 * Created by Lev on 19.12.2016.
 */

public interface InfoUser {
    void setUserName(String name);

    void setUserEmail(String email);

    void setPassword(String password);

    void setRepeatePassword(String password);

    void setUserMobile(String mobile);

    void setUserMobileId(int id);

    void setUserMobile2(String mobile2);

    void setUserMobileId2(int id2);

    void setUserDriverBrand(String brand);

    void setUserDriverModel(String model);

    void setUserDriverPlateNumber(String plateNumber);

    void setUserDriverSeatsNumber(String seatsNumber);

    void setUserDriverCarType(String carType);

    void setCarId(int carId);

    void setUserDriverId();

    void showError(String error);

    void doneOperation(String done);

    void setCodeLicense(String code);

    void setExpirationTime(String expirationTime);
}
