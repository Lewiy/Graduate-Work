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
import com.lewgmail.romanenko.taxiservice.model.pojo.RoutePoint;
import com.lewgmail.romanenko.taxiservice.model.pojo.RoutePointUpdateOrder;
import com.lewgmail.romanenko.taxiservice.presenter.BasePresenter;
import com.lewgmail.romanenko.taxiservice.presenter.CustomerPresenter;
import com.lewgmail.romanenko.taxiservice.view.activity.elementsOfActivity.SlidingTabLayout;
import com.lewgmail.romanenko.taxiservice.view.adapters.AdapterAddPointOfRoute;
import com.lewgmail.romanenko.taxiservice.view.fragments.addOrderUpdate.FragmentPage1Update;
import com.lewgmail.romanenko.taxiservice.view.fragments.addOrderUpdate.FragmentPage2Update;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lev on 24.04.2017.
 */

public class AddOrderUpdate extends AppCompatActivity implements FragmentPage1Update.AddOrderGatherDataFirstWindow,
        FragmentPage2Update.AddOrderGatherDataSecondWindow, AddOrderInterface {

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
    private AddOrderUpdate.SectionsPagerAdapter mSectionsPagerAdapter;
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
    private ArrayList<RoutePointUpdateOrder> routePoints;
    private ArrayList<AdditionalRequirementN> additionalRequirementNs;


    // view element from fragment2
    private TextView valuePrice;
    private Context contextFragm2;

    //  private Fragment fragmenObjs;
    private FragmentPage1Update fragmentObj1Update;
    private FragmentPage2Update fragmentObj2Update;
    private BasePresenter basePresenter;
    private Intent intentMy;


    /*
    metaData from updateOrder///////////////////////////////////////////////////////
     */

    private long orderId;
    /*
    ///////////////////////////////////////////////////////////////////////////////
     */

    // private String
    // private String
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order_);
        routePoints = new ArrayList<>();
        customerPresenter = new CustomerPresenter(this);
        basePresenter = new BasePresenter(this);
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
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tab_color_scrol);
            }
        });

        intentMy = getIntent();
        // Setting the ViewPager For the SlidingTabsLayout

        tabs.setViewPager(mViewPager);
        fragmentObj1Update = (FragmentPage1Update) mSectionsPagerAdapter.getItem(0);
        fragmentObj2Update = (FragmentPage2Update) mSectionsPagerAdapter.getItem(1);

        basePresenter.loadOrderSpecificId(Integer.parseInt(intentMy.getStringExtra("keyNumberOfOrder")));

    }

    /*
    Observ fragment referense from SectionsPagerAdapter;
    */

    public void setFragment1Referense(Fragment fragment1) {
        this.fragmentObj1Update = (FragmentPage1Update) fragment1;
    }

    public void setFragment2Referense(Fragment fragment2) {
        this.fragmentObj2Update = (FragmentPage2Update) fragment2;
    }

    /*
    Responses for getOrderSpecificID/////////////////////////////////////////////////////
     */

    public void responseError(String error) {
        fragmentObj1Update.setError(error);
    }

    public void responseOrderId(long orderId) {
        this.orderId = orderId;
    }

    public void responseStartTime(String dateTime) {
        if (dateTime == null)
            fragmentObj1Update.setStartTime(dateTime);
        else {
            String time = getTimeFromResponDateTime(dateTime);
            fragmentObj1Update.setStartTime(time);
        }
    }

    public void responseRoutePoint(List<RoutePoint> resRoutePoints) {
        RoutePointUpdateOrder routePointN1 = new RoutePointUpdateOrder();
        addapterListAddresses = fragmentObj1Update.getAddressAdapter();
        addapterListAddresses.setActivityCallBack(this);
        fragmentObj1Update.setFirstPointOfRoute(addressBilder(resRoutePoints.get(0).getStreet()
                , resRoutePoints.get(0).getHouseNumber(), resRoutePoints.get(0).getCity()));
        routePointN1.setLatitude(resRoutePoints.get(0).getLatitude());
        routePointN1.setLongtitude(resRoutePoints.get(0).getLongtitude());
        routePointN1.setRoutePointId(resRoutePoints.get(0).getRoutePointId());
        routePointN1.setRoutePointIndex(new Long(0));
        routePoints.add(routePointN1);

        RoutePointUpdateOrder routePointN2 = new RoutePointUpdateOrder();
        fragmentObj1Update.setSecondPointOfRoute(addressBilder(resRoutePoints.get(1).getStreet()
                , resRoutePoints.get(1).getHouseNumber(), resRoutePoints.get(1).getCity()));
        routePointN2.setLatitude(resRoutePoints.get(1).getLatitude());
        routePointN2.setLongtitude(resRoutePoints.get(1).getLongtitude());
        routePointN2.setRoutePointId(resRoutePoints.get(1).getRoutePointId());
        routePointN2.setRoutePointIndex(new Long(0));
        routePoints.add(routePointN2);

        for (int i = 2; i <= resRoutePoints.size() - 1; i++) {
            RoutePointUpdateOrder routePointN3 = new RoutePointUpdateOrder();
            addapterListAddresses.myAddList(addressBilder(resRoutePoints.get(i).getStreet()
                    , resRoutePoints.get(i).getHouseNumber(), resRoutePoints.get(i).getCity()));
            routePointN3.setLatitude(resRoutePoints.get(i).getLatitude());
            routePointN3.setLongtitude(resRoutePoints.get(i).getLongtitude());
            routePointN3.setRoutePointId(resRoutePoints.get(i).getRoutePointId());
            routePointN3.setRoutePointIndex(new Long(0));
            routePoints.add(routePointN3);
        }

    }

    public void responseStatusOrder(String orderStatus) {
        fragmentObj2Update.setStatus(orderStatus);
    }

    public void responseCustomerId(int customerId) {

    }

    public void responseDriverId(int driverId) {

    }

    public void responseDistance(double distance) {
        fragmentObj2Update.setDistance(Double.toString(distance));
    }

    public void responseDuration(String time) {
        fragmentObj2Update.setDuration(time);
    }

    public void responsePrice(double price) {
        fragmentObj2Update.setCalculatedPrice(Double.toString(price));
    }

    public void responseExtraPrice(double extraPrice) {

    }

    public void responseComment(String comment) {

    }

    public void responseAdditionalRequirements(ArrayList<AdditionalRequirementN> additionalRequirementNs) {

        this.additionalRequirementNs = additionalRequirementNs;
        for (AdditionalRequirementN requirementN : additionalRequirementNs) {
            settersAdditionRequirements(requirementN.getReqId());
        }

    }

    /*
    ///////////////////////////////////////////////////////////////////////////////////
     */


    /*
    Transformation Date//////////////////////////////////////////////////////////////////
     */

    private String getTimeFromResponDateTime(String dateTime) {
        return dateTime.substring(dateTime.indexOf("T") + 1, dateTime.indexOf("T") + 5);
    }

    private String addressBilder(String street, String numberOfHause, String citi) {
        String address = "";
        if (citi != null)
            address = address + citi + " ";
        if (street != null)
            address = address + street + " ";
        if (numberOfHause != null)
            address = address + numberOfHause + " ";
        return address;
    }

    private void settersAdditionRequirements(int idRequirement) {
        switch (idRequirement) {
            case 1:
                fragmentObj2Update.setTYPECAR(additionalRequirementNs.get(searchAdditionReq(idRequirement)).getReqValueId());
                break;
            case 2:
                fragmentObj2Update.setTYPERECKONING(additionalRequirementNs.get(searchAdditionReq(idRequirement)).getReqValueId());
                break;
            case 3:
                fragmentObj2Update.setPETS(additionalRequirementNs.get(searchAdditionReq(idRequirement)).getReqValueId());
                break;
            case 4:
                fragmentObj2Update.setBAGGAGE(additionalRequirementNs.get(searchAdditionReq(idRequirement)).getReqValueId());
                break;
            case 5:
                fragmentObj2Update.setEXTRAPRICE(additionalRequirementNs.get(searchAdditionReq(idRequirement)).getReqValueId());
                break;
            case 6:
                fragmentObj2Update.setDRIVERSERVICE(additionalRequirementNs.get(searchAdditionReq(idRequirement)).getReqValueId());
                break;
            case 7:
                fragmentObj2Update.setNUMBERPASSENGERS(additionalRequirementNs.get(searchAdditionReq(idRequirement)).getReqValueId());
                break;
        }
    }

    private int searchAdditionReq(int idRequirement) {
        for (int i = 0; i <= additionalRequirementNs.size(); i++) {
            if (additionalRequirementNs.get(i).getReqId() == idRequirement)
                return i;
        }
        return 0;
    }

    // private string

    /*
    /////////////////////////////////////////////////////////////////////////////////
     */
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
        customerPresenter.updateOrder(orderId);
    }

    @Override
    public void runReqCalculayePrice(TextView valuePrice) {
        this.valuePrice = valuePrice;
        customerPresenter.calculatePrice();
    }

    @Override
    public void deleteOrder() {
        customerPresenter.deleteOrder(orderId);
    }

    @Override
    public void changeOrderStatus(String orderStatus) {
        basePresenter.changeStatusOrder(orderId, orderStatus);
    }

    @Override
    public void setActivityStartTime(String startTime) {
        this.startTime = startTime;
    }

    /*
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
    */
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
        startActivityForResult(intent, MAP_SEARCH_ADDRESS_CODE);
    }

    @Override
    public void startActivityForResultMapRoute(int position) {
        this.position = position;
        Intent intent = new Intent(this, MapActivity.class);
        startActivityForResult(intent, MAP_SEARCH_ADDRESS_CODE_ROUTE);
    }

    @Override
    public void removeAddress(int position) {
        routePoints.get(position + 2).setRoutePointIndex(null);
        // routePoints.remove(position + 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                Fragment frag1 = getSupportFragmentManager().findFragmentByTag("android:switcher:2131558517:0");

                if (typeOfViewElement.equals("fragment")) {

                    EditText editText = ((EditText) frag1.getView().findViewById(viewIdEditText));
                    RoutePointUpdateOrder routePointN = new RoutePointUpdateOrder();
                    routePointN.setLongtitude(Double.toString(place.getLatLng().longitude));
                    routePointN.setLatitude(Double.toString(place.getLatLng().latitude));
                    if (editText.getText().toString().matches(""))
                        routePoints.add(routePointN);
                    else routePoints.set(positionFirsSeconAdd, routePointN);
                    editText.setText(place.getAddress());

                } else if (typeOfViewElement.equals("List")) {

                    addapterListAddresses.myAddList(place.getAddress().toString());
                    RoutePointUpdateOrder routePointN = new RoutePointUpdateOrder();
                    routePointN.setLongtitude(Double.toString(place.getLatLng().longitude));
                    routePointN.setLatitude(Double.toString(place.getLatLng().latitude));
                    routePoints.add(routePointN);

                } else {

                    addapterListAddresses.replaseItem(place.getAddress().toString(), position);
                    RoutePointUpdateOrder routePointN = new RoutePointUpdateOrder();
                    routePointN.setLongtitude(Double.toString(place.getLatLng().longitude));
                    routePointN.setLatitude(Double.toString(place.getLatLng().latitude));
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
                        RoutePointUpdateOrder routePointN = new RoutePointUpdateOrder();
                        routePointN.setLongtitude(data.getStringExtra("longitude"));
                        routePointN.setLatitude(data.getStringExtra("latitude"));
                        routePoints.add(routePointN);
                    } else {
                        RoutePointUpdateOrder routePointN = new RoutePointUpdateOrder();
                        routePointN.setLongtitude(data.getStringExtra("longitude"));
                        routePointN.setLatitude(data.getStringExtra("latitude"));
                        routePoints.set(positionFirsSeconAdd, routePointN);
                    }
                    editText.setText(data.getStringExtra("addressFromMap"));
                    break;
                case MAP_SEARCH_ADDRESS_CODE_ROUTE:
                    addapterListAddresses.replaseItem(data.getStringExtra("addressFromMap"), position);
                    RoutePointUpdateOrder routePointN = new RoutePointUpdateOrder();
                    routePointN.setLongtitude(data.getStringExtra("longitude"));
                    routePointN.setLatitude(data.getStringExtra("latitude"));
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

    public ArrayList<RoutePointUpdateOrder> getRoute() {
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

    public void responseAddorder(String text) {

        Toast.makeText(contextFragm2, "Code of Operation:" + text, Toast.LENGTH_SHORT).show();

    }


    /*
     Update view fragment from response
     /////////////////////////////////////////////////////////////////////////
     */
  /*  public void frag2responseDuration(String duration) {
        fragmentObj2Update.setDuration(duration);
    }

    public void frag2responseDistance(String distance) {
        fragmentObj2Update.setDistance(distance);
    }

    public void frag2responseCalculatedPrice(String calculatedPrice) {
        fragmentObj2Update.setCalculatedPrice(calculatedPrice);
    }

    public void frag1responseError(String string) {
        //  fragmentObj1.se
    }

    public void frag2responseError(String string) {
        fragmentObj2Update.setError(string);
    }*/

    /*
    ////////////////////////////////////////////////////////////////////////
     */

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private AddOrderUpdate addOrderHost;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void setHostFragmentReferense(AddOrderUpdate host) {
            this.addOrderHost = host;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    Fragment fragment1 = FragmentPage1Update.newInstance(position);
                    // FragmentManager fragmentManager = getSupportFragmentManager();
                    // fragmentManager.beginTransaction().add(fragment1,"fragmentAddOrder1").commit();
                    addOrderHost.setFragment1Referense(fragment1);
                    return fragment1;
                case 1:
                    Fragment fragment2 = FragmentPage2Update.newInstance(position);
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
