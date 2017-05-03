package com.lewgmail.romanenko.taxiservice.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lewgmail.romanenko.taxiservice.R;
import com.lewgmail.romanenko.taxiservice.model.dataManager.LoggedUser;
import com.lewgmail.romanenko.taxiservice.view.activity.AddOrderUpdate;
import com.lewgmail.romanenko.taxiservice.view.viewDriver.OrderInf;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Lev on 27.11.2016.
 */

public class AdapterForListOrderClient extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expListTitle;
    private HashMap<String, List<String>> expListDetail;
    private Fragment fragmentMy;
    private String copyTitle;

    public AdapterForListOrderClient(Context context, List<String> expListTitle,
                                     HashMap<String, List<String>> expListDetail, Fragment fragment) {
        this.context = context;
        this.expListTitle = expListTitle;
        this.expListDetail = expListDetail;
        this.fragmentMy = fragment;
    }

    @Override
    public int getGroupCount() {
        return expListTitle.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return expListDetail.get(
                expListTitle.get(groupPosition)
        ).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return expListTitle.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return expListDetail.get(
                expListTitle.get(groupPosition)
        ).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        // получаем родительский элемент
        String listTitle = (String) getGroup(listPosition);
        copyTitle = new String(listTitle);
        // copyTitle = "7";
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_group, null);
            ImageButton buttonOnTitleGroup = (ImageButton) convertView.findViewById(R.id.buttonOnTitleGroup);
            buttonOnTitleGroup.setFocusable(false);
            buttonOnTitleGroup.setOnClickListener(new MyOnClickListener(getIdOrder(listTitle), context));
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    public String getIdOrder(String string) {

        if (string.indexOf("|") == -1)
            return string;
        else {
            return string.substring(string.indexOf("|") + 1);
        }
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        // получаем дочерний элемент
        String expListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_orders_item_drop_down, null);
        }
        TextView expListTextView = (TextView) convertView.findViewById(R.id.expandedListItem);
        expListTextView.setText(expListText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}

class MyOnClickListener implements View.OnClickListener {

    String copyTitle;
    Context context;

    public MyOnClickListener(String copyTitle, Context context) {
        this.copyTitle = copyTitle;
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        if (LoggedUser.getmInstance().getUserType().equals("CUSTOMER")) {
            Intent myIntent = new Intent(context, AddOrderUpdate.class);
            myIntent.putExtra("keyUpdate", "update"); //Optional parameters
            myIntent.putExtra("keyNumberOfOrder", copyTitle);
            context.startActivity(myIntent);
        } else {
            Intent myIntent = new Intent(context, OrderInf.class);
            myIntent.putExtra("keyNumberOfOrder", copyTitle);
            myIntent.putExtra("keyUpdate", "update"); //Optional parameters
            context.startActivity(myIntent);
        }
    }

}