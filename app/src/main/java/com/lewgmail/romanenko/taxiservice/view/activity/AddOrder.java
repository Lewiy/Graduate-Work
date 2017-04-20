package com.lewgmail.romanenko.taxiservice.view.activity;

import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.lewgmail.romanenko.taxiservice.R;
import com.lewgmail.romanenko.taxiservice.presenter.CustomerPresenter;
import com.lewgmail.romanenko.taxiservice.view.activity.elementsOfActivity.SlidingTabLayout;
import com.lewgmail.romanenko.taxiservice.view.adapters.AdapterAddPointOfRoute;
import com.lewgmail.romanenko.taxiservice.view.fragments.addOrder.FragmentPage1;
import com.lewgmail.romanenko.taxiservice.view.fragments.addOrder.FragmentPage2;

import java.util.HashMap;

public class AddOrder extends AppCompatActivity implements FragmentPage1.AddOrderGatherDataFirstWindow, FragmentPage2.AddOrderGatherDataSecondWindow {

    final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1,
            MAP_SEARCH_ADDRESS_CODE = 2,
            MAP_SEARCH_ADDRESS_CODE_ROUTE = 3;
    AdapterAddPointOfRoute addapterListAddresses;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private SlidingTabLayout tabs;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private int viewIdEditText;
    private CustomerPresenter customerPresenter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private Fragment fragment1;
    private String typeOfViewElement;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order_);
        customerPresenter = new CustomerPresenter(this);
        customerPresenter.addOrder(12.23, 23.34, 123.34, 1234.3);
        //   Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                int a = 10;
            }
        });
        mViewPager.setAdapter(mSectionsPagerAdapter);


        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width
        // Setting Custom Color for the Scroll bar indicator of the Tab View
        // TextView textView = (TextView) tabs.getChildAt(0);
        // textView.setTextColor(getResources().getColor(R.color.tab_color_scrol));
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tab_color_scrol);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(mViewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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

    @Override
    public void setActivityComment(String Comment) {

    }

    @Override
    public void setActivityAdditionalRequirements(HashMap<Integer, Integer> AdditionalRequirements) {

    }

    @Override
    public void setActivityaddOrder() {

    }

    @Override
    public void setActivityStartTime(String startTime) {

    }

    @Override
    public void setActivityAdminArea(String adminArea) {

    }

    @Override
    public void setActivityLatitude(String latitude) {

    }

    @Override
    public void setActivityLongetude(String longetude) {

    }

    @Override
    public void setActivityStreet(String street) {

    }

    @Override
    public void setActivityHouseNumber(String houseNumber) {

    }

    @Override
    public void setActivityCity(String city) {

    }

    @Override
    public void runAutoComplete(int viewId) {
        viewIdEditText = viewId;
        startAutocompleteFragment("fragment");
    }

    @Override
    public void runAutoComplete(AdapterAddPointOfRoute addapterListAddresses) {
        this.addapterListAddresses = addapterListAddresses;
        this.addapterListAddresses.setActivityCallBack(this);
        startAutocompleteFragment("List");
    }

    @Override
    public void runAutoCompliteReplaceAddress(int position) {
        this.position = position;
        startAutocompleteFragment("ListReplace");

    }

    private void startAutocompleteFragment(String idParameter) {

        try {
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(this);
            // intent.putExtra("typeOfViewElement", idParameter);
            typeOfViewElement = new String(idParameter);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
            // setResult();
        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }
    }

    @Override
    public void startActivityForResultMap(int viewId) {
        viewIdEditText = viewId;
        Intent intent = new Intent(this, MapActivity.class);
        startActivityForResult(intent, MAP_SEARCH_ADDRESS_CODE);
    }

    @Override
    public void startActivityForResultMapRoute(int position) {
        this.position = position;
        Intent intent = new Intent(this, MapActivity.class);
        startActivityForResult(intent, MAP_SEARCH_ADDRESS_CODE_ROUTE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                Fragment frag1 = getSupportFragmentManager().findFragmentByTag("android:switcher:2131558517:0");
                //    if (data.getStringExtra("typeOfViewElement") != null) {
                if (typeOfViewElement.equals("fragment")) {
                    ((EditText) frag1.getView().findViewById(viewIdEditText)).setText(place.getAddress());
                } else if (typeOfViewElement.equals("List")) {
                    addapterListAddresses.myAddList(place.getAddress().toString());
                } else {
                    addapterListAddresses.replaseItem(place.getAddress().toString(), position);
                }
                //  }
                Log.i("", "Place: " + place.getName());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.i("", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case MAP_SEARCH_ADDRESS_CODE:
                    String address = data.getStringExtra("addressFromMap");
                    Fragment frag1 = getSupportFragmentManager().findFragmentByTag("android:switcher:2131558517:0");
                    ((EditText) frag1.getView().findViewById(viewIdEditText)).setText(address);
                    break;
                case MAP_SEARCH_ADDRESS_CODE_ROUTE:
                    addapterListAddresses.replaseItem(data.getStringExtra("addressFromMap"), position);

            }
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    Fragment fragment1 = FragmentPage1.newInstance(position);
                    // FragmentManager fragmentManager = getSupportFragmentManager();
                    // fragmentManager.beginTransaction().add(fragment1,"fragmentAddOrder1").commit();
                    return fragment1;
                case 1:
                    Fragment fragment2 = FragmentPage2.newInstance(position);
                    // FragmentManager fragmentManager2 = getSupportFragmentManager();
                    //  fragmentManager2.beginTransaction().add(fragment2,"fragmentAddOrder2").commit();
                    return fragment2;
                default:
                    return new Fragment();
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.tab_main_settings);
                case 1:
                    return getString(R.string.tab_addition_requirest);
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }

}
