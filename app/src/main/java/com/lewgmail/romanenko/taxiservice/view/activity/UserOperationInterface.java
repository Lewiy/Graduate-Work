package com.lewgmail.romanenko.taxiservice.view.activity;

/**
 * Created by Lev on 15.12.2016.
 */

public interface UserOperationInterface {

    void setNameSideBar(String name);

    void setEmailSideBar(String email);

    void showError(String error);

    void doneOperation(int code, String done);
}
