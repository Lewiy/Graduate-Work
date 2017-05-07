package com.lewgmail.romanenko.taxiservice.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.lewgmail.romanenko.taxiservice.R;
import com.lewgmail.romanenko.taxiservice.presenter.ResetPasswordPresenter;
import com.lewgmail.romanenko.taxiservice.view.ValidationOfFields;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetPassword extends AppCompatActivity implements UserOperationInterface {

    @BindView(R.id.reset_password_field)
    EditText resetPassword;
    @BindView(R.id.frame_layout_code_new_password)
    FrameLayout frameLayoutStep2;
    @BindView(R.id.reset_password_field_code)
    EditText resetPasswordCode;
    @BindView(R.id.reset_password_field_password)
    EditText resetPasswordNewPassword;
    @BindView(R.id.reset_password_field_password_repeat)
    EditText repeateNewPassword;


    private ResetPasswordPresenter resetPasswordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);
        resetPasswordPresenter = new ResetPasswordPresenter(this);
    }


    @OnClick(R.id.reset_password_button)
    public void onClickResetPasswordButton() {
        if (checkEmailValue()) {
            getEmailValueReset();
            Toast.makeText(this, "Пройшло", Toast.LENGTH_LONG).show();
        }
    }

    public String getEmailValueReset() {
        return resetPassword.getText().toString();
    }

    public String getResetPasswordCode() {
        return resetPasswordCode.getText().toString();
    }

    public String getResetPasswordNewPassword() {
        return resetPasswordNewPassword.getText().toString();
    }

    public String getRepeateNewPassword() {
        return repeateNewPassword.getText().toString();
    }

    private boolean checkEmailValue() {
        String massege;
        if (checkInputedField()) {
            massege = ValidationOfFields.checkEmail(this, resetPassword.getText().toString());
            if (!massege.equals("true")) {
                resetPassword.setError(massege);
                return false;
            } else return true;
        } else return false;
    }

    private boolean checkInputedField() {
        if (resetPassword.getText().toString().matches("")) {
            Toast.makeText(this, "Please input all fields", Toast.LENGTH_LONG).show();
            return false;
        } else return true;
    }

    private boolean checkInputedFieldCodeNewPassword() {
        if (resetPasswordCode.getText().toString().matches("") &&
                resetPasswordNewPassword.toString().matches("") &&
                repeateNewPassword.toString().matches("")) {
            Toast.makeText(this, "Please input all fields", Toast.LENGTH_LONG).show();
            return false;
        } else return true;
    }

    public void showHideCodeNewPasswordField() {
        frameLayoutStep2.setVisibility(View.VISIBLE);
    }

    @Override
    public void setNameSideBar(String name) {

    }

    @Override
    public void setEmailSideBar(String email) {

    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void doneOperation(int responseCod, String done) {
        Toast.makeText(this, done, Toast.LENGTH_SHORT).show();
    }


}
