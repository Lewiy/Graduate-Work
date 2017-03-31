package com.lewgmail.romanenko.taxiservice.view.activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.lewgmail.romanenko.taxiservice.R;

/**
 * Created by Lev on 30.03.2017.
 */

public class Notification {

    private NotificationCompat.Builder mSimpleBuilder;
    private Context mContext;

    public Notification(Context context) {
        this.mContext = context;
        this.mSimpleBuilder =
                new NotificationCompat.Builder(context);
    }

    public void showSimpleNotification(String title, String text) {
        mSimpleBuilder.setSmallIcon(R.drawable.ic_taxi)
                .setContentTitle(title)
                .setContentText(text);
// Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(mContext, LoginActivity.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(mContext);
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(LoginActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mSimpleBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(1, mSimpleBuilder.build());

    }
}
