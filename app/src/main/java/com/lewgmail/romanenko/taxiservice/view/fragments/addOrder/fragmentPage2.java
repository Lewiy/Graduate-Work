package com.lewgmail.romanenko.taxiservice.view.fragments.addOrder;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.lewgmail.romanenko.taxiservice.R;
import com.lewgmail.romanenko.taxiservice.model.pojo.AdditionalRequirementN;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lev on 18.03.2017.
 */

public class FragmentPage2 extends android.support.v4.app.Fragment {

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
    @BindView(R.id.spinner_addit_requirem)
    Spinner additionRequirm;
    @BindView(R.id.comment_element)
    EditText commentField;
    private AddOrderGatherDataSecondWindow addOrderGatherDataSecondWindow;
    private int lastPressed = 0;
    private ImageButton lastPressedB = null;
    private int lastPressedDraw = 0;
    private ArrayList<AdditionalRequirementN> additionalRequirements;
    public FragmentPage2() {
    }
    // private Context context;

    public static FragmentPage2 newInstance(int sectionNumber) {
        FragmentPage2 fragment = new FragmentPage2();
        Bundle args = new Bundle();
        args.putInt("key", sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_order_2, container, false);
        ButterKnife.bind(this, rootView);
        // initializeSpinner(R.array.type_baggage,4);
        additionalRequirements = new ArrayList<>();
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity addOrder;
        if (context instanceof Activity) {
            addOrder = (Activity) context;
            addOrderGatherDataSecondWindow = (AddOrderGatherDataSecondWindow) addOrder;
        }


    }

    @OnClick(R.id.add_order)
    public void onClickAddOrder() {
        addOrderGatherDataSecondWindow.setActivityaddOrder();
        addOrderGatherDataSecondWindow.setActivityAdditionalRequirements(additionalRequirements);
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
        initializeSpinner(R.array.type_baggage, 4);
    }

    @OnClick(R.id.icon_type_reckoning)
    public void onClickTypeReckoning() {
        typeReckoning.setBackgroundResource(R.drawable.ic_card_in_use_yellow);
        onlyOnePaint(lastPressedB, lastPressedDraw);
        lastPressedB = typeReckoning;
        lastPressedDraw = R.drawable.ic_card_in_use;
        initializeSpinner(R.array.type_reconing, 2);
    }

    @OnClick(R.id.icon_extra_price)
    public void onClickExtraPrice() {
        extraPrice.setBackgroundResource(R.drawable.ic_extra_price_yellow);
        onlyOnePaint(lastPressedB, lastPressedDraw);
        lastPressedB = extraPrice;
        lastPressedDraw = R.drawable.ic_extra_price;
        initializeSpinner(R.array.type_extra_price, 5);
    }

    @OnClick(R.id.icon_pets)
    public void onClickPets() {
        pets.setBackgroundResource(R.drawable.ic_pets_yellow);
        //  onlyOnePaint(lastPressed);
        onlyOnePaint(lastPressedB, lastPressedDraw);
        lastPressedB = pets;
        lastPressedDraw = R.drawable.ic_pets;
        initializeSpinner(R.array.type_pets, 3);
    }

    @OnClick(R.id.icon_driver_service)
    public void onClickDriverService() {
        driverService.setBackgroundResource(R.drawable.ic_driver_yellow);
        // onlyOnePaint(lastPressed);
        onlyOnePaint(lastPressedB, lastPressedDraw);
        lastPressedB = driverService;
        lastPressedDraw = R.drawable.ic_driver;
        initializeSpinner(R.array.type_driver_services, 6);
    }

    @OnClick(R.id.icon_type_car)
    public void onClickTypeCar() {
        typeCar.setBackgroundResource(R.drawable.ic_car_type_yellow);
        //onlyOnePaint(lastPressed);
        onlyOnePaint(lastPressedB, lastPressedDraw);
        lastPressedB = typeCar;
        lastPressedDraw = R.drawable.ic_car_type;
        initializeSpinner(R.array.type_car, 1);
    }

    @OnClick(R.id.icon_number_passengers)
    public void onClickNumberPassengers() {
        numberPassengers.setBackgroundResource(R.drawable.ic_passengers_yellow);
        // onlyOnePaint(lastPressed);
        onlyOnePaint(lastPressedB, lastPressedDraw);
        lastPressedB = numberPassengers;
        lastPressedDraw = R.drawable.ic_passengers;
        initializeSpinner(R.array.type_number_passengers, 7);
    }

    private void initializeSpinner(int arrayType, final int typeRequirements) {
        ArrayAdapter<CharSequence> adapterSpinner =
                ArrayAdapter.createFromResource(getActivity(), arrayType, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        additionRequirm.setAdapter(adapterSpinner);
        additionRequirm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos,
                                       long id) {
                // basePresenter.loadOrdersList(parent.getItemAtPosition(pos).toString());
                // basePresenter.changeStatusOrder(7);
                //Log.d("My loooooog","clic
                //if(pos==0)

                AdditionalRequirementN additionalRequirementN = new AdditionalRequirementN();
                additionalRequirementN.setReqId(typeRequirements);
                additionalRequirementN.setReqValueId(pos);
                additionalRequirements.add(additionalRequirementN);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
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

    public interface AddOrderGatherDataSecondWindow {

        void setActivityComment(String Comment);

        void setActivityAdditionalRequirements(ArrayList<AdditionalRequirementN> AdditionalRequirements);

        void setActivityaddOrder();
    }
}
