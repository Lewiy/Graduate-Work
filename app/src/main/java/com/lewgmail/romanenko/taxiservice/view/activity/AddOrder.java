package com.lewgmail.romanenko.taxiservice.view.activity;

import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.lewgmail.romanenko.taxiservice.R;
import com.lewgmail.romanenko.taxiservice.model.pojo.AdditionalRequirementN;
import com.lewgmail.romanenko.taxiservice.model.pojo.RoutePointN;
import com.lewgmail.romanenko.taxiservice.presenter.CustomerPresenter;
import com.lewgmail.romanenko.taxiservice.presenter.adapters.AdapterCodeFromServer;
import com.lewgmail.romanenko.taxiservice.view.activity.elementsOfActivity.SlidingTabLayout;
import com.lewgmail.romanenko.taxiservice.view.adapters.AdapterAddPointOfRoute;
import com.lewgmail.romanenko.taxiservice.view.adapters.DTORoute;
import com.lewgmail.romanenko.taxiservice.view.fragments.addOrder.FragmentPage1;
import com.lewgmail.romanenko.taxiservice.view.fragments.addOrder.FragmentPage2;

import java.util.ArrayList;

import static com.lewgmail.romanenko.taxiservice.R.id.end_point_act;
import static com.lewgmail.romanenko.taxiservice.R.id.start_point_act;

public class AddOrder extends AppCompatActivity implements FragmentPage1.AddOrderGatherDataFirstWindow,
        FragmentPage2.AddOrderGatherDataSecondWindow, AddOrderInterface {

    final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1,
            MAP_SEARCH_ADDRESS_CODE = 2,
            MAP_SEARCH_ADDRESS_CODE_ROUTE = 3;
    AdapterAddPointOfRoute addapterListAddresses;
    ArrayList<DTORoute> dtoRoute = new ArrayList<>();
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
    // position first s
    private int positionFirsSeconAdd;
    private String startTime;
    private String comment;
    private ArrayList<RoutePointN> routePoints;
    private ArrayList<AdditionalRequirementN> additionalRequirementNs;


    // view element from fragment2
    private TextView valuePrice;
    private Context contextFragm2;

    //  private Fragment fragmenObjs;
    private FragmentPage1 fragmentObj1;
    private FragmentPage2 fragmentObj2;

    // private String
    // private String
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order_);
        routePoints = new ArrayList<>();
        customerPresenter = new CustomerPresenter(this);
        // customerPresenter.addOrder(12.23, 23.34, 123.34, 1234.3);
        //   Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mSectionsPagerAdapter.setHostFragmentReferense(this);
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
        fragmentObj1 = (FragmentPage1) mSectionsPagerAdapter.getItem(0);
        fragmentObj2 = (FragmentPage2) mSectionsPagerAdapter.getItem(1);
        //  fragmentObj1 = (FragmentPage1) FragmentPage1.newInstance(0);
        // fragmentObj2 = (FragmentPage2) FragmentPage2.newInstance(1);
        //fragmentObj1 = (FragmentPage1) getSupportFragmentManager().findFragmentByTag("android:switcher:2131558517:0");
        // fragmentObj2 = (FragmentPage2) getSupportFragmentManager().findFragmentByTag("android:switcher:2131558517:1");

    }

    /*
    Observ fragment referense from SectionsPagerAdapter;
    */

    public void setFragment1Referense(Fragment fragment1) {
        this.fragmentObj1 = (FragmentPage1) fragment1;
    }

    public void setFragment2Referense(Fragment fragment2) {
        this.fragmentObj2 = (FragmentPage2) fragment2;
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
    public void setActivityComment(String comment) {
        this.comment = comment;
    }

    @Override
    public void setActivityAdditionalRequirements(ArrayList<AdditionalRequirementN> additionalRequirements) {
        this.additionalRequirementNs = additionalRequirements;
    }

    @Override
    public void runReqaddOrder(Context contextFragm2) {
        this.contextFragm2 = contextFragm2;

        if (check2PointOfRoute(contextFragm2))
        customerPresenter.addOrder();
    }

    private boolean check2PointOfRoute(Context contextFragm2) {
        EditText firstPoint = (EditText) fragmentObj1.getView().findViewById(start_point_act);
        EditText secondPoint = (EditText) fragmentObj1.getView().findViewById(end_point_act);
        if (firstPoint.getText().toString().matches("") ||
                secondPoint.getText().toString().matches("")) {
            Toast.makeText(contextFragm2, getResources().getString(R.string.not_inputted_2_point),
                    Toast.LENGTH_SHORT).show();
            return false;
        } else
            return true;

    }

    @Override
    public void runReqCalculayePrice(TextView valuePrice, Context contextFragm2) {
        this.valuePrice = valuePrice;
        if (check2PointOfRoute(contextFragm2))
        customerPresenter.calculatePrice();
    }

    @Override
    public void setActivityStartTime(String startTime) {
        this.startTime = startTime;
    }


    @Override
    public void runAutoComplete(int viewId, int position) {
        viewIdEditText = viewId;
        positionFirsSeconAdd = position;
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
        intent.putExtra("Route", dtoRoute);
        transferRouteToMap();
        startActivityForResult(intent, MAP_SEARCH_ADDRESS_CODE);
    }

    private void transferRouteToMap() {
        for (int i = 0; i < routePoints.size(); i++) {
            DTORoute dtoRoute1 = new DTORoute();
            dtoRoute1.setLat(Double.parseDouble(routePoints.get(i).getLatitude()));
            dtoRoute1.setLng(Double.parseDouble(routePoints.get(i).getLongtitude()));
            dtoRoute1.setAddress(routePoints.get(i).getAddress());
            dtoRoute.add(dtoRoute1);
        }
    }

    @Override
    public void startActivityForResultMapRoute(int position) {
        this.position = position;

        Intent intent = new Intent(this, MapActivity.class);

        intent.putExtra("Route", dtoRoute);
        transferRouteToMap();

        startActivityForResult(intent, MAP_SEARCH_ADDRESS_CODE_ROUTE);
    }

    @Override
    public void removeAddress(int position) {
        routePoints.remove(position + 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                Fragment frag1 = getSupportFragmentManager().findFragmentByTag("android:switcher:2131558517:0");

                if (typeOfViewElement.equals("fragment")) {

                    EditText editText = ((EditText) frag1.getView().findViewById(viewIdEditText));
                    RoutePointN routePointN = new RoutePointN();
                    routePointN.setLongtitude(Double.toString(place.getLatLng().longitude));
                    routePointN.setLatitude(Double.toString(place.getLatLng().latitude));
                    routePointN.setAddress(place.getAddress().toString());
                    if (editText.getText().toString().matches(""))
                        routePoints.add(routePointN);
                    else routePoints.set(positionFirsSeconAdd, routePointN);
                    editText.setText(place.getAddress());
                    editText.setError(null);

                } else if (typeOfViewElement.equals("List")) {

                    addapterListAddresses.myAddList(place.getAddress().toString());
                    RoutePointN routePointN = new RoutePointN();
                    routePointN.setLongtitude(Double.toString(place.getLatLng().longitude));
                    routePointN.setLatitude(Double.toString(place.getLatLng().latitude));
                    routePointN.setAddress(place.getAddress().toString());
                    routePoints.add(routePointN);

                } else {

                    addapterListAddresses.replaseItem(place.getAddress().toString(), position);
                    RoutePointN routePointN = new RoutePointN();
                    routePointN.setLongtitude(Double.toString(place.getLatLng().longitude));
                    routePointN.setLatitude(Double.toString(place.getLatLng().latitude));
                    routePointN.setAddress(place.getAddress().toString());
                    routePoints.set(position + 2, routePointN);

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
                   /* String address = data.getStringExtra("addressFromMap");
                    String latitude = data.getStringExtra("latitude");
                    String longitude = data.getStringExtra("longitude");*/
                    Fragment frag1 = getSupportFragmentManager().findFragmentByTag("android:switcher:2131558517:0");
                    EditText editText = ((EditText) frag1.getView().findViewById(viewIdEditText));
                    if (editText.getText().toString().matches("")) {
                        RoutePointN routePointN = new RoutePointN();
                        routePointN.setLongtitude(data.getStringExtra("longitude"));
                        routePointN.setLatitude(data.getStringExtra("latitude"));
                        routePointN.setAddress(data.getStringExtra("addressFromMap"));
                        routePoints.add(routePointN);
                    } else {
                        RoutePointN routePointN = new RoutePointN();
                        routePointN.setLongtitude(data.getStringExtra("longitude"));
                        routePointN.setLatitude(data.getStringExtra("latitude"));
                        routePointN.setAddress(data.getStringExtra("addressFromMap"));
                        routePoints.set(positionFirsSeconAdd, routePointN);
                    }
                    editText.setText(data.getStringExtra("addressFromMap"));
                    break;
                case MAP_SEARCH_ADDRESS_CODE_ROUTE:
                    addapterListAddresses.replaseItem(data.getStringExtra("addressFromMap"), position);
                    RoutePointN routePointN = new RoutePointN();
                    routePointN.setLongtitude(data.getStringExtra("longitude"));
                    routePointN.setLatitude(data.getStringExtra("latitude"));
                    routePointN.setAddress(data.getStringExtra("addressFromMap"));
                    routePoints.set(position + 2, routePointN);

            }
        }
    }

    public String getTime() {
        return startTime;
    }

    public String getComment() {
        return comment;
    }

    public ArrayList getRoute() {
        return routePoints;
    }

    public ArrayList getAdditionalRequirementNs() {
        return additionalRequirementNs;
    }

    // Response update view
    /*public void responseCalculatePrice(String text) {
        // TextView textView = (TextView)fragment2res.getView().findViewById(R.id.value_price);
        //textView.setText(text);
        valuePrice.setText(text);
    }*/

    public void responseAddorder(int code, String fromServer) {
        fragmentObj2.setErrorProgress();
        // Toast.makeText(contextFragm2, "Code of Operation:" + text, Toast.LENGTH_SHORT).show();
        Toast.makeText(contextFragm2, AdapterCodeFromServer.AdapterCode(code, this, fromServer), Toast.LENGTH_SHORT).show();


    }


    /*
     Update view fragment from response
     /////////////////////////////////////////////////////////////////////////
     */
    public void frag2responseDuration(String duration) {
        fragmentObj2.setDuration(duration);
    }

    public void frag2responseDistance(String distance) {
        fragmentObj2.setDistance(distance);
    }

    public void frag2responseCalculatedPrice(String calculatedPrice) {
        fragmentObj2.setErrorProgress();
        fragmentObj2.setCalculatedPrice(calculatedPrice);
    }

    public void frag1responseError(String string) {
        //  fragmentObj1.se
    }

    public void frag2responseError(int code, String fromServer) {
        fragmentObj2.setErrorProgress();
        fragmentObj2.setError(AdapterCodeFromServer.AdapterCode(code, this, fromServer));
    }

    /*
    ////////////////////////////////////////////////////////////////////////
     */
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private AddOrder addOrderHost;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void setHostFragmentReferense(AddOrder host) {
            this.addOrderHost = host;
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
                    addOrderHost.setFragment1Referense(fragment1);
                    return fragment1;
                case 1:
                    Fragment fragment2 = FragmentPage2.newInstance(position);
                    //  FragmentManager fragmentManager2 = getSupportFragmentManager();
                    // fragmentManager2.beginTransaction().add(fragment2,"fragmentAddOrder2").commit();
                    addOrderHost.setFragment2Referense(fragment2);
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
