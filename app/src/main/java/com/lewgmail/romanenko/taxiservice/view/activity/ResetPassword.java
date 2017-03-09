package com.lewgmail.romanenko.taxiservice.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.lewgmail.romanenko.taxiservice.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetPassword extends AppCompatActivity implements UserOperationInterface {

    @BindView(R.id.reset_password_field)
    EditText resetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);
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

    private boolean checkEmailValue() {
        if (checkInputedField()) {
            if (!resetPassword.getText().toString().contains("@")) {
                Toast.makeText(this, "Please check your email value", Toast.LENGTH_LONG).show();
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
