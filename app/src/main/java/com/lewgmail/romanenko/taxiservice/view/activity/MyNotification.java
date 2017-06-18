package com.lewgmail.romanenko.taxiservice.view.activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.widget.RemoteViews;

import com.google.android.gms.gcm.GcmListenerService;
import com.lewgmail.romanenko.taxiservice.R;
import com.lewgmail.romanenko.taxiservice.model.pojo.NotificationObjAddedOrder;
import com.lewgmail.romanenko.taxiservice.model.pojo.NotificationObjDriverWaiting;
import com.lewgmail.romanenko.taxiservice.presenter.LocalizeAddress;
import com.lewgmail.romanenko.taxiservice.view.adapters.AdapterTimeDate;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Lev on 30.03.2017.
 */

public class MyNotification extends GcmListenerService {

    private NotificationCompat.Builder mSimpleBuilder;
    private Context mContext;

    public MyNotification() {
        //this.mContext = context;
    }

    public void showNotificAddOrder(NotificationObjAddedOrder notifObjAddedOrder) {
        LocalizeAddress localizeAddress = new LocalizeAddress();

        Resources res = mContext.getResources();

        RemoteViews contentView = new RemoteViews(mContext.getPackageName(), R.layout.notific_new_order);
        // contentView.setImageViewResource(R.id.notific_image, R.drawable.main_theme);
        contentView.setTextViewText(R.id.notif_time_resive, getCurrentDate());
        if (notifObjAddedOrder.getStartTime().getTime().equals("Now"))
            contentView.setTextViewText(R.id.notific_when, notifObjAddedOrder.getStartTime().getTime());
        else
            contentView.setTextViewText(R.id.notific_when,
                    notifObjAddedOrder.getStartTime().getDate() + "  "
                            + notifObjAddedOrder.getStartTime().getTime());

        notifObjAddedOrder.setEndPoint(localizeAddress.LocalizeAddress(notifObjAddedOrder.getEndPoint()));
        notifObjAddedOrder.setStartPoint(localizeAddress.LocalizeAddress(notifObjAddedOrder.getStartPoint()));


        contentView.setTextViewText(R.id.notific_where, notifObjAddedOrder.getStartPoint()
                + " " + getResources().getString(R.string.to) + " " + notifObjAddedOrder.getEndPoint());
        contentView.setTextViewText(R.id.notific_price, notifObjAddedOrder.getPrice() + " " + getResources().getString(R.string.UAH));


        this.mSimpleBuilder =
                new NotificationCompat.Builder(mContext);
        mSimpleBuilder.setSmallIcon(R.drawable.ic_taxi)
                .setCustomBigContentView(contentView);

        Intent resultIntent = new Intent(mContext, LoginActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(mContext);

        stackBuilder.addParentStack(LoginActivity.class);

        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mSimpleBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(1, mSimpleBuilder.build());
    }

    public void showNotifChangeOrderStatus(String orderStatus, String name, String orderId) {
        Resources res = mContext.getResources();

        RemoteViews contentView = new RemoteViews(mContext.getPackageName(), R.layout.notific_changed_order_status);
        // contentView.setImageViewResource(R.id.notific_image, R.drawable.default_license);
        contentView.setTextViewText(R.id.notif_time_resive, getCurrentDate());
        contentView.setTextViewText(R.id.notific_changed_status, orderStatus);
        contentView.setTextViewText(R.id.notific_name, name);

        this.mSimpleBuilder =
                new NotificationCompat.Builder(mContext);
        mSimpleBuilder.setSmallIcon(R.drawable.ic_taxi)
                .setCustomBigContentView(contentView);

        Intent resultIntent = new Intent(mContext, LoginActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(mContext);

        stackBuilder.addParentStack(LoginActivity.class);

        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        2,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mSimpleBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(1, mSimpleBuilder.build());
    }

    public void showNotificDriverWaiting(NotificationObjDriverWaiting notifObjDriverWaiting) {
        Resources res = mContext.getResources();

        RemoteViews contentView = new RemoteViews(mContext.getPackageName(), R.layout.notific_driver_is_waiting);
        //  contentView.setImageViewResource(R.id.notific_image, R.drawable.default_license);
        contentView.setTextViewText(R.id.notif_time_resive, getCurrentDate());
        contentView.setTextViewText(R.id.notific_plate_number, notifObjDriverWaiting.getPlateNumber());
        contentView.setTextViewText(R.id.notific_model, notifObjDriverWaiting.getModel());
        contentView.setTextViewText(R.id.notific_brend, notifObjDriverWaiting.getManufacturer());
        contentView.setTextViewText(R.id.notific_name, notifObjDriverWaiting.getName());

        this.mSimpleBuilder =
                new NotificationCompat.Builder(mContext);
        mSimpleBuilder.setSmallIcon(R.drawable.ic_taxi)
                .setCustomBigContentView(contentView);

        Intent resultIntent = new Intent(mContext, LoginActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(mContext);

        stackBuilder.addParentStack(LoginActivity.class);

        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        1,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mSimpleBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(1, mSimpleBuilder.build());
    }

    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("orderStatus");
        //  Log.d(TAG, "From: " + from);
        //  Log.d(TAG, "Message: " + message);
        mContext = getApplicationContext();
        if (from.startsWith("/topics/")) {
            // message received from some topic.
        } else {
            // normal downstream message.
        }


        String messageNewOrder = data.getString("price");
        if (messageNewOrder != null) {
            NotificationObjAddedOrder notificationObjAddedOrder = new NotificationObjAddedOrder();
            notificationObjAddedOrder.setOrderId(Integer.parseInt(data.getString("orderId")));
            notificationObjAddedOrder.setStartPoint(data.getString("startPoint"));
            notificationObjAddedOrder.setEndPoint(data.getString("endPoint"));
            notificationObjAddedOrder.setStartTime(new AdapterTimeDate(data.getString("startTime"), mContext));
            notificationObjAddedOrder.setPrice(messageNewOrder);
            showNotification(notificationObjAddedOrder);
        }

        String messageOrderStatus = data.getString("plateNumber");
        if (messageOrderStatus != null) {
            NotificationObjDriverWaiting notificationObjDriverWaiting = new NotificationObjDriverWaiting();
            notificationObjDriverWaiting.setOrderId(data.getString("orderId"));
            notificationObjDriverWaiting.setOrderStatus(data.getString("orderStatus"));
            notificationObjDriverWaiting.setPlateNumber(data.getString("plateNumber"));
            notificationObjDriverWaiting.setModel(data.getString("model"));
            notificationObjDriverWaiting.setManufacturer(data.getString("manufacturer"));
            notificationObjDriverWaiting.setName(data.getString("name"));
            showNotification(notificationObjDriverWaiting);
        } else {
            if (data.getString("name") != null) {
                showNotification(data.getString("orderStatus"), data.getString("name"), data.getString("orderId"));
            }

        }
    }

    public void showNotification(NotificationObjAddedOrder notificationObjAddedOrder) {
        showNotificAddOrder(notificationObjAddedOrder);
    }

    public void showNotification(NotificationObjDriverWaiting notifObjDriverWaiting) {
        showNotificDriverWaiting(notifObjDriverWaiting);
    }

    public void showNotification(String orderStatus, String name, String orderId) {
        showNotifChangeOrderStatus(orderStatus, name, orderId);
    }

    private String getCurrentDate() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat();
        return dateFormat.format(currentDate);
    }
}
