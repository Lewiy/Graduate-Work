package com.lewgmail.romanenko.taxiservice.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lewgmail.romanenko.taxiservice.R;
import com.lewgmail.romanenko.taxiservice.model.dataManager.LoggedUser;
import com.lewgmail.romanenko.taxiservice.presenter.UserPresenter;
import com.lewgmail.romanenko.taxiservice.view.viewDriver.DriverAccount;

import static android.R.attr.value;

public class MainActivity extends AppCompatActivity implements UserOperationInterface {
    private UserPresenter userPresenter;
    private ProgressDialog progress;
    private UserCheckTask mAuthTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        progress = new ProgressDialog(this);
        progress.setTitle(getResources().getString(R.string.main_theme_loading));
        progress.setMessage(getResources().getString(R.string.text_of_loading));
        progress.setCancelable(true); // disable dismiss by tapping outside of the dialog
        progress.show();
        userPresenter = new UserPresenter(this);
        String tok = LoggedUser.getmInstance().getPREF_DEVICE_TOKEN(this);
        if (!tok.equals("ERROR")) {

            mAuthTask = new UserCheckTask(progress, getBaseContext(), userPresenter);
            mAuthTask.execute((Void) null);

        } else {
            progress.dismiss();
            Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
            myIntent.putExtra("key", value); //Optional parameters
            MainActivity.this.startActivity(myIntent);
        }
    }

    @Override
    public void setNameSideBar(String name) {

    }

    @Override
    public void setEmailSideBar(String email) {

    }

    @Override
    public void showError(int code, String fromServer) {

    }

    @Override
    public void doneOperation(int code, String fromServer) {

    }

    public class UserCheckTask extends AsyncTask<Void, Void, Boolean> {
        private final Context context;
        private final UserPresenter userPresenter;
        private final ProgressDialog progress;

        UserCheckTask(ProgressDialog progressDialog, Context context, UserPresenter userPresenter) {
            this.context = context;
            progress = progressDialog;
            this.userPresenter = userPresenter;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            // Simulate network access.
            // Thread.sleep(2000);
            //String statusLogIn = userPresenter.logIn(mEmail, mPassword);
            //   String statusLogIn = userPresenter.createAndSendDeviceToken(context, mEmail, mPassword);
            userPresenter.checkTocken(LoggedUser.getmInstance().getUserId(), context);
            /*for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }*/
        /*if (statusLogIn.equals("ERROR")) {
            return false;
        }*/

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (LoggedUser.getmInstance().getUserType() != null) {


                if (LoggedUser.getmInstance().getUserType().equals("CUSTOMER")) {
                    progress.dismiss();
                    Intent myIntent = new Intent(MainActivity.this, ClientAccount.class);
                    myIntent.putExtra("key", value); //Optional parameters
                    MainActivity.this.startActivity(myIntent);
                } else {
                    progress.dismiss();
                    Intent myIntent = new Intent(MainActivity.this, DriverAccount.class);
                    myIntent.putExtra("key", value); //Optional parameters
                    MainActivity.this.startActivity(myIntent);
                }
            } else {
                progress.dismiss();
                Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
                myIntent.putExtra("key", value); //Optional parameters
                MainActivity.this.startActivity(myIntent);
            }


        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            progress.dismiss();
            // showProgress(false);
        }
    }
}


