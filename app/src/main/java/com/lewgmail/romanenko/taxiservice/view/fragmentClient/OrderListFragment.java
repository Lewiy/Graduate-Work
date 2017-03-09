package com.lewgmail.romanenko.taxiservice.view.fragmentClient;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.lewgmail.romanenko.taxiservice.R;
import com.lewgmail.romanenko.taxiservice.presenter.BasePresenter;
import com.lewgmail.romanenko.taxiservice.presenter.adapters.TestDataForList;
import com.lewgmail.romanenko.taxiservice.view.adapters.AdapterForListOrderClient;
import com.lewgmail.romanenko.taxiservice.view.adapters.AdapterSpinnerLocalization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lev on 25.11.2016.
 */

public class OrderListFragment extends android.support.v4.app.Fragment implements OrderListFragmentInterface {

    @BindView(R.id.expListView)
    ExpandableListView expListView;
    @BindView(R.id.sort_list)
    Spinner sortSpinner;
    ExpandableListAdapter expListAdapter;
    List<String> expListTitle;
    private HashMap<String, List<String>> expListDetail;
    private BasePresenter basePresenter;
    private ProgressDialog progress;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_list_client, container, false);
        ButterKnife.bind(this, view);
        initialiseExpList();
        basePresenter = new BasePresenter(this);
        setRetainInstance(true);
        return view;
    }


    private void initialiseExpList() {

        expListDetail = TestDataForList.loadData();

        expListTitle = new ArrayList<>(expListDetail.keySet());
        expListAdapter = new AdapterForListOrderClient(this.getActivity(), expListTitle, expListDetail, this);
        expListView.setAdapter(expListAdapter);
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
               /* Toast.makeText(getApplicationContext(),
                        expListTitle.get(groupPosition) + " Список раскрыт.",onItemSelected

                        Toast.LENGTH_SHORT).show();*/
            }
        });

        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
               /* Toast.makeText(getApplicationContext(),
                        expListTitle.get(groupPosition) + " Список скрыт.",
                        Toast.LENGTH_SHORT).show();*/

            }
        });

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
               /* Toast.makeText(getApplicationContext(),
                        expListTitle.get(groupPosition)
                                + " : " + expListDetail.get(expListTitle.get(groupPosition))
                                .get(childPosition), Toast.LENGTH_SHORT).show();*/
                return false;
            }
        });

        ArrayAdapter<CharSequence> adapterSpinnerTypeReckoning =
                ArrayAdapter.createFromResource(getActivity(), R.array.sort_value, android.R.layout.simple_spinner_item);
        adapterSpinnerTypeReckoning.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(adapterSpinnerTypeReckoning);

        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos,
                                       long id) {

                progress = new ProgressDialog(getActivity());
                progress.setTitle(R.string.main_theme_loading);
                progress.setMessage(getResources().getString(R.string.text_of_loading));
                progress.setCancelable(true); // disable dismiss by tapping outside of the dialog
                progress.show();
                basePresenter.loadOrdersList(AdapterSpinnerLocalization.adaptSpinnerTypeOrder(sortSpinner.getSelectedItemPosition()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

   /* @OnClick(R.id.sort_list)
    public void loadListOrders(){

    }*/

    public void setOrderList(HashMap<String, List<String>> list) {

        expListDetail = list;
        expListTitle = new ArrayList<>(expListDetail.keySet());
        expListAdapter = new AdapterForListOrderClient(this.getActivity(), expListTitle, expListDetail, this);
        expListView.setAdapter(expListAdapter);
        progress.dismiss();
    }

    //////////////////////////////////get data from view/////////////////////////////////
    public String getTypeOrder() {

        return sortSpinner.getSelectedItem().toString();
    }

    public void showEror(String massage) {
        Toast.makeText(getActivity(), massage, Toast.LENGTH_LONG).show();
    }
}
