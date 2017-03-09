package com.lewgmail.romanenko.taxiservice.view.fragments;

/**
 * Created by Lev on 05.03.2017.
 */

public interface ReadInform {
    void readName(String text);

    void readEmail(String text);

    void readPassword(String text);

    void readPassword2(String text);

    void readPhone1(String text);

    void readPhone2(String text);

    void readFieldOther(boolean checkBox);
}
