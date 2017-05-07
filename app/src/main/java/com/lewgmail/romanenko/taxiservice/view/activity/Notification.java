package com.lewgmail.romanenko.taxiservice.view.activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.google.android.gms.gcm.GcmListenerService;
import com.lewgmail.romanenko.taxiservice.R;

/**
 * Created by Lev on 30.03.2017.
 */

public class Notification extends GcmListenerService {

    private NotificationCompat.Builder mSimpleBuilder;
    private Context mContext;

    public Notification() {

    }

    public void showSimpleNotification(String title, String text) {
        this.mSimpleBuilder =
                new NotificationCompat.Builder(getBaseContext());
        mSimpleBuilder.setSmallIcon(R.drawable.ic_taxi)
                .setContentTitle(title)
                .setContentText(text);
// Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(getBaseContext(), LoginActivity.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getBaseContext());
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
                (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(1, mSimpleBuilder.build());
    }

    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("orderStatus");
        //  Log.d(TAG, "From: " + from);
        //  Log.d(TAG, "Message: " + message);

        if (from.startsWith("/topics/")) {
            // message received from some topic.
        } else {
            // normal downstream message.
        }

        showSimpleNotification(from, message);

        // [START_EXCLUDE]
        /**
         * Production applications would usually process the message here.
         * Eg: - Syncing with server.
         *     - Store message in local database.
         *     - Update UI.
         */

        /**
         * In some cases it may be useful to show a notification indicating to the user
         * that a message was received.
         */
        //   sendNotification(message);
        // [END_EXCLUDE]
    }
}
