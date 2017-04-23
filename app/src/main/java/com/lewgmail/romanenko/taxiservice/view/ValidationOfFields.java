package com.lewgmail.romanenko.taxiservice.view;

import android.content.Context;

import com.lewgmail.romanenko.taxiservice.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lev on 23.04.2017.
 */

public class ValidationOfFields {

    /*
        Patterns
    */

    private static final String DRIVER_LICENSE_PLATE_NUMBER = "^([А-Я]{2})\\s([0-9]{4})\\s([А-Я]{2})$";
    private static final String DRIVER_LICENSE_CODE = "^([А-Я]{3})\\s([0-9]{6})$";
    private static final String PHONE_NUMBER = "^[+]38([0-9]{10})$";
    private static final String EXPIRATION_TIME = "^([0-9]{4})-([0-9]{2})-([0-9]{2})$";
    private static final String EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    public static String checkCodeLicense(Context context, String text) {
        Matcher matcher = runCheck(DRIVER_LICENSE_CODE, text);

        if (matcher.matches())
            return new String("true");
        else
            return new String(context.getResources().getString(R.string.validation_code_driver_license));
    }


    public static String checkExpirationTime(Context context, String text) {
        Matcher matcher = runCheck(EXPIRATION_TIME, text);

        if (matcher.matches())
            return new String("true");
        else
            return new String(context.getResources().getString(R.string.validation_expiration_time));
    }

    public static String checkPlateNumber(Context context, String text) {

        Matcher matcher = runCheck(DRIVER_LICENSE_PLATE_NUMBER, text);

        if (matcher.matches())
            return new String("true");
        else
            return new String(context.getResources().getString(R.string.validation_plate_number_driver_profile));
    }

    public static String checkPhoneNumber(Context context, String text) {
        Matcher matcher = runCheck(PHONE_NUMBER, text);

        if (matcher.matches())
            return new String("true");
        else
            return new String(context.getResources().getString(R.string.validation_phone_number));

    }

    public static String checkEmail(Context context, String text) {
        Matcher matcher = runCheck(EMAIL, text);

        if (matcher.matches())
            return new String("true");
        else
            return new String(context.getResources().getString(R.string.validation_email));

    }

    public static String checkPassword(Context context, String text) {
        if (text.length() > 6)
            return new String("true");
        else
            return new String(context.getResources().getString(R.string.validation_password));

    }

    private static Matcher runCheck(String pattern, String text) {
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(text);
        return matcher;
    }

}
