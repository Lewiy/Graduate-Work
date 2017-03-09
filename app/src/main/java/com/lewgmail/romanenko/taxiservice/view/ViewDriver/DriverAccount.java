package com.lewgmail.romanenko.taxiservice.view.ViewDriver;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lewgmail.romanenko.taxiservice.R;
import com.lewgmail.romanenko.taxiservice.model.dataManager.LoggedUser;
import com.lewgmail.romanenko.taxiservice.presenter.UserPresenter;
import com.lewgmail.romanenko.taxiservice.view.activity.LoginActivity;
import com.lewgmail.romanenko.taxiservice.view.activity.UserOperationInterface;
import com.lewgmail.romanenko.taxiservice.view.fragmentClient.OrderListFragment;

import butterknife.ButterKnife;

import static android.R.attr.value;

public class DriverAccount extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, UserOperationInterface {

    //  @BindView(R.id.side_bar_name)
    TextView sideBarName;
    //  @BindView(R.id.textView)
    TextView sideBarEmailText;

    private UserPresenter userPresentr;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_account);

        userPresentr = new UserPresenter(this);
        userPresentr.getUserIdHomePage(LoggedUser.getmInstance().getUserId());

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        sideBarName = (TextView) header.findViewById(R.id.side_bar_name);
        sideBarEmailText = (TextView) header.findViewById(R.id.textView);

        loadXmlPage();
    }

    private void loadXmlPage() {
        Fragment fragment = null;
        Class fragmentClass = null;
        fragmentClass = OrderListFragment.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Вставляем фрагмент, заменяя текущий фрагмент
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_driver_account, fragment).commit();
        // Выделяем выбранный пункт меню в шторке
        // item.setChecked(true);
        // Выводим выбранный пункт в заголовке
        // setTitle(item.getTitle());
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.driver_account, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        Class fragmentClass = null;

        if (id == R.id.order_list) {
            fragmentClass = OrderListFragment.class;
            createFragment(fragment, item, fragmentClass);
        } else if (id == R.id.edit_profile) {
            fragmentClass = EditProfileFragmentDriver.class;
            createFragment(fragment, item, fragmentClass);
        } else if (id == R.id.log_out) {

            userPresentr.logOut();
            LoggedUser.getmInstance().logOut();

            Intent myIntent = new Intent(DriverAccount.this, LoginActivity.class);
            myIntent.putExtra("key", value); //Optional parameters
            DriverAccount.this.startActivity(myIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void createFragment(Fragment fragment, MenuItem item, Class fragmentClass) {
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Вставляем фрагмент, заменяя текущий фрагмент
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_driver_account, fragment).commit();
        // Выделяем выбранный пункт меню в шторке
        item.setChecked(true);
        // Выводим выбранный пункт в заголовке
        setTitle(item.getTitle());
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void setNameSideBar(String name) {
        sideBarName.setText(name);
    }

    @Override
    public void setEmailSideBar(String email) {
        sideBarEmailText.setText(email);
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
