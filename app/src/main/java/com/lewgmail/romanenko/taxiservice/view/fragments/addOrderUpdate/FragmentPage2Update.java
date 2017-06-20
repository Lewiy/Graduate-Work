package com.lewgmail.romanenko.taxiservice.view.fragments.addOrderUpdate;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lewgmail.romanenko.taxiservice.R;
import com.lewgmail.romanenko.taxiservice.model.pojo.AdditionalRequirementN;
import com.lewgmail.romanenko.taxiservice.view.adapters.AdapterSpinnerLocalization;
import com.lewgmail.romanenko.taxiservice.view.adapters.AdapterStatusController;
import com.lewgmail.romanenko.taxiservice.view.viewDriver.AccountInfUserFrag;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lev on 24.04.2017.
 */

public class FragmentPage2Update extends android.support.v4.app.Fragment {
    private static int BAGGAGE = 1,
            TYPERECKONING = 2,
            EXTRAPRICE = 3,
            PETS = 4,
            DRIVERSERVICE = 5,
            TYPECAR = 6,
            NUMBERPASSENGERS = 7;
    @BindView(R.id.icon_addit_baggage)
    ImageButton baggage;
    @BindView(R.id.icon_type_reckoning)
    ImageButton typeReckoning;
    @BindView(R.id.icon_extra_price)
    ImageButton extraPrice;
    @BindView(R.id.icon_pets)
    ImageButton pets;
    @BindView(R.id.icon_driver_service)
    ImageButton driverService;
    @BindView(R.id.icon_type_car)
    ImageButton typeCar;
    @BindView(R.id.icon_number_passengers)
    ImageButton numberPassengers;
    @BindView(R.id.show_account_driver)
    Button showAccountDriver;
    //////////////////////////////////////////////
    @BindView(R.id.spinner_status_order)
    Spinner spinnerStatusOrder;
    @BindView(R.id.spinner_addit_baggage)
    Spinner spinnerAdditBaggage;
    @BindView(R.id.spinner_type_reckoning)
    Spinner spinnerTypeReconning;
    @BindView(R.id.spinner_extra_price)
    Spinner spinnerExtraPrice;
    @BindView(R.id.spinner_pets)
    Spinner spinnerPets;
    @BindView(R.id.spinner_driver_service)
    Spinner spinnerDriverService;
    @BindView(R.id.spinner_type_car)
    Spinner spinnerTypeCar;
    @BindView(R.id.spinner_number_passengers)
    Spinner spinnerNumberPassenger;

    /////////////////////////////////////////////

    @BindView(R.id.delete_order)
    Button deleteOrder;
    @BindView(R.id.comment_element)
    EditText commentField;

    @BindView(R.id.value_price)
    TextView valuePrice;
    @BindView(R.id.value_duration)
    TextView valueDuration;
    @BindView(R.id.value_distance)
    TextView valueDistance;


    private FragmentPage2Update.AddOrderGatherDataSecondWindow addOrderGatherDataSecondWindow;
    private int lastPressed = 0;
    private ImageButton lastPressedB = null;
    private int lastPressedDraw = 0;
    private ArrayList<AdditionalRequirementN> additionalRequirements;
    private boolean flagFirstSelected = false;
    private long driverId;
    /*
    Set metaData for order/////////////////////////
     */

    private String orderStatus;
    /*
    /////////////////////////////////////////////
     */


    public FragmentPage2Update() {
    }
    // private Context context;

    public static FragmentPage2Update newInstance(int sectionNumber) {
        FragmentPage2Update fragment = new FragmentPage2Update();
        Bundle args = new Bundle();
        args.putInt("key", sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_order_2_update, container, false);
        ButterKnife.bind(this, rootView);
        // initializeSpinner(R.array.type_baggage,4);
        additionalRequirements = new ArrayList<>();
        initializeSpinner(R.array.type_baggage, 4, spinnerAdditBaggage);
        initializeSpinner(R.array.type_car, 1, spinnerTypeCar);
        initializeSpinner(R.array.type_driver_services, 6, spinnerDriverService);
        initializeSpinner(R.array.type_extra_price, 5, spinnerExtraPrice);
        initializeSpinner(R.array.type_number_passengers, 7, spinnerNumberPassenger);
        initializeSpinner(R.array.type_pets, 3, spinnerPets);
        initializeSpinner(R.array.type_reconing, 2, spinnerTypeReconning);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity addOrder;
        if (context instanceof Activity) {
            addOrder = (Activity) context;
            addOrderGatherDataSecondWindow = (FragmentPage2Update.AddOrderGatherDataSecondWindow) addOrder;
        }


    }

    @OnClick(R.id.delete_order)
    public void onClickDeleteOrder() {
        addOrderGatherDataSecondWindow.deleteOrder(getContext());
    }

    @OnClick(R.id.add_order)
    public void onClickAddOrder() {
        addOrderGatherDataSecondWindow.setActivityAdditionalRequirements(additionalRequirements);
        addOrderGatherDataSecondWindow.setActivityComment(commentField.getText().toString());
        addOrderGatherDataSecondWindow.runReqaddOrder(getContext());
    }

    @OnClick(R.id.calculate_price_button)
    public void onClickCalculatePrice() {
        addOrderGatherDataSecondWindow.setActivityAdditionalRequirements(additionalRequirements);
        addOrderGatherDataSecondWindow.runReqCalculayePrice(valuePrice);
    }

    public String getComment() {
        return commentField.getText().toString();
    }

    public ArrayList<AdditionalRequirementN> getAdditionalRequirements() {
        return additionalRequirements;
    }

    @OnClick(R.id.icon_addit_baggage)
    public void onClickBaggage() {
        baggage.setBackgroundResource(R.drawable.ic_bugets_yellow);
        onlyOnePaint(lastPressedB, lastPressedDraw);
        lastPressedB = baggage;
        lastPressedDraw = R.drawable.ic_bagets;
        // initializeSpinner(R.array.type_baggage, 4);
    }


    @OnClick(R.id.icon_type_reckoning)
    public void onClickTypeReckoning() {
        typeReckoning.setBackgroundResource(R.drawable.ic_card_in_use_yellow);
        onlyOnePaint(lastPressedB, lastPressedDraw);
        lastPressedB = typeReckoning;
        lastPressedDraw = R.drawable.ic_card_in_use;
        // initializeSpinner(R.array.type_reconing, 2);
    }

    @OnClick(R.id.icon_extra_price)
    public void onClickExtraPrice() {
        extraPrice.setBackgroundResource(R.drawable.ic_extra_price_yellow);
        onlyOnePaint(lastPressedB, lastPressedDraw);
        lastPressedB = extraPrice;
        lastPressedDraw = R.drawable.ic_extra_price;
        // initializeSpinner(R.array.type_extra_price, 5);
    }

    @OnClick(R.id.icon_pets)
    public void onClickPets() {
        pets.setBackgroundResource(R.drawable.ic_pets_yellow);
        //  onlyOnePaint(lastPressed);
        onlyOnePaint(lastPressedB, lastPressedDraw);
        lastPressedB = pets;
        lastPressedDraw = R.drawable.ic_pets;
        // initializeSpinner(R.array.type_pets, 3);
    }

    @OnClick(R.id.icon_driver_service)
    public void onClickDriverService() {
        driverService.setBackgroundResource(R.drawable.ic_driver_yellow);
        // onlyOnePaint(lastPressed);
        onlyOnePaint(lastPressedB, lastPressedDraw);
        lastPressedB = driverService;
        lastPressedDraw = R.drawable.ic_driver;
        //  initializeSpinner(R.array.type_driver_services, 6);
    }

    @OnClick(R.id.icon_type_car)
    public void onClickTypeCar() {
        typeCar.setBackgroundResource(R.drawable.ic_car_type_yellow);
        //onlyOnePaint(lastPressed);
        onlyOnePaint(lastPressedB, lastPressedDraw);
        lastPressedB = typeCar;
        lastPressedDraw = R.drawable.ic_car_type;
        // initializeSpinner(R.array.type_car, 1);
    }

    @OnClick(R.id.icon_number_passengers)
    public void onClickNumberPassengers() {
        numberPassengers.setBackgroundResource(R.drawable.ic_passengers_yellow);
        // onlyOnePaint(lastPressed);
        onlyOnePaint(lastPressedB, lastPressedDraw);
        lastPressedB = numberPassengers;
        lastPressedDraw = R.drawable.ic_passengers;
        // initializeSpinner(R.array.type_number_passengers, 7);
    }

    @OnClick(R.id.show_account_driver)
    public void onClickShowDriverInfo() {
        AccountInfUserFrag passengerInfFragment = new AccountInfUserFrag();
        Bundle args = new Bundle();
        args.putLong("userId", driverId);
        passengerInfFragment.setArguments(args);
        passengerInfFragment.show(getActivity().getFragmentManager(), "sdf");
    }

    private void initializeSpinner(int arrayType, final int typeRequirements, Spinner spinner) {
        ArrayAdapter<CharSequence> adapterSpinner =
                ArrayAdapter.createFromResource(getActivity(), arrayType, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos,
                                       long id) {
                addAdditioanlRequirements(pos, typeRequirements);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    private void initializeSpinnerOrderStatus(final int arrayType, Spinner spinner) {
        ArrayAdapter<CharSequence> adapterSpinner =
                ArrayAdapter.createFromResource(getActivity(), arrayType, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos,
                                       long id) {
                //addAdditioanlRequirements(pos,typeRequirements);
                if (flagFirstSelected != false) {
                    addOrderGatherDataSecondWindow.changeOrderStatus(new AdapterSpinnerLocalization().setChangeStatusOrder(arrayType, pos, "CUSTOMER"));
                }
                flagFirstSelected = true;


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }



    private void addAdditioanlRequirements(int pos, int typeRequirements) {
        if ((pos - 1) != -1) {
            boolean flag = true;
            AdditionalRequirementN additionalRequirementN = new AdditionalRequirementN();
            additionalRequirementN.setReqId(typeRequirements);
            if (typeRequirements == 7 || typeRequirements == 5)
            additionalRequirementN.setReqValueId(pos);
            else
                additionalRequirementN.setReqValueId(pos - 1);////////////////////////////////////////////////
            int index = 0;
            for (AdditionalRequirementN i : additionalRequirements) {
                if (i.getReqId() == typeRequirements) {
                    flag = false;
                    index = additionalRequirements.indexOf(i);
                }
            }
            if (flag)
                additionalRequirements.add(additionalRequirementN);
            if (!flag)
                additionalRequirements.set(index, additionalRequirementN);
        }
    }

    private void onlyOnePaint(int index) {

        switch (index) {
            case 1:
                baggage.setBackgroundResource(R.drawable.ic_bagets);
                break;
            case 2:
                typeReckoning.setImageResource(R.drawable.ic_card_in_use);
                break;
            case 3:
                extraPrice.setImageResource(R.drawable.ic_extra_price);
                break;
            case 4:
                pets.setImageResource(R.drawable.ic_pets);
                break;
            case 5:
                driverService.setImageResource(R.drawable.ic_driver);
                break;
            case 6:
                typeCar.setImageResource(R.drawable.ic_car_type);
                break;
            case 7:
                numberPassengers.setImageResource(R.drawable.ic_passengers);
                break;
        }

    }

    private void onlyOnePaint(ImageButton button, int pictures) {
        if (button != null)
            button.setBackgroundResource(pictures);
    }


    /*
        Seters for view of this
    //////////////////////////////////////////////////////////////
     */

    public void setDuration(String duration) {
        valueDuration.setText(duration);
    }

    public void setDistance(String distance) {
        valueDistance.setText(distance);
    }

    public void setCalculatedPrice(String calculPrice) {
        valuePrice.setText(calculPrice);
    }

    public void setError(String error) {
        Toast.makeText(getContext(), "Error: " + error, Toast.LENGTH_LONG).show();
    }

    public void setDriverId(long driverId) {
        this.driverId = driverId;
    }

    public void setDoneResp(String massage) {
        Toast.makeText(getContext(), "Done " + massage, Toast.LENGTH_LONG).show();
    }

    public void setStatus(String status) {
        this.orderStatus = status;
        initializeSpinnerOrderStatus(new AdapterStatusController("CUSTOMER", status).typeStatus(), spinnerStatusOrder);

    }

    public void setBAGGAGE(int pos) {
        spinnerAdditBaggage.setSelection(pos + 1);
    }

    public void setTYPERECKONING(int pos) {
        spinnerTypeReconning.setSelection(pos + 1);
    }

    public void setEXTRAPRICE(int pos) {
        spinnerExtraPrice.setSelection(pos);
    }

    public void setPETS(int pos) {
        spinnerPets.setSelection(pos + 1);
    }

    public void setDRIVERSERVICE(int pos) {
        spinnerDriverService.setSelection(pos + 1);
    }

    public void setTYPECAR(int pos) {
        spinnerTypeCar.setSelection(pos + 1);
    }

    public void setNUMBERPASSENGERS(int pos) {
        spinnerNumberPassenger.setSelection(pos);
    }

    public void setSpinnerNotChangedStatus() {
        spinnerStatusOrder.setSelection(0);
    }



    /*
    /////////////////////////////////////////////////////////////
     */

    public interface AddOrderGatherDataSecondWindow {

        void setActivityComment(String Comment);

        void setActivityAdditionalRequirements(ArrayList<AdditionalRequirementN> AdditionalRequirements);

        void runReqaddOrder(Context contextFragm2);

        void runReqCalculayePrice(TextView valuePrice);

        void deleteOrder(Context contextFragm2);

        void changeOrderStatus(String orderStatus);
    }
}
