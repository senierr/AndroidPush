package com.senierr.push.fcm;

import android.app.PendingIntent;
import android.content.Intent;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.senierr.push.util.NotificationUtil;
import com.senierr.push.util.SystemUtil;

/**
 * @author zhouchunjie
 * @date 2018/6/6
 */
public class FCMMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(final RemoteMessage remoteMessage) {
        if (remoteMessage != null) {
            RemoteMessage.Notification notification = remoteMessage.getNotification();
            if (notification != null) {
                Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
                if (intent != null) {
                    for (String key: remoteMessage.getData().keySet()) {
                        intent.putExtra(key, remoteMessage.getData().get(key));
                    }
                    NotificationUtil.getNotificationManager(this)
                            .notify(1, NotificationUtil.getBuilder(this, "1", "system")
                                    .setContentTitle(notification.getTitle())
                                    .setContentText(notification.getBody())
                                    .setWhen(System.currentTimeMillis())
                                    .setAutoCancel(true)
                                    .setSmallIcon(SystemUtil.getAppIcon(this))
                                    .setContentIntent(PendingIntent.getActivity(this, 0, intent, 0))
                                    .build());
                }
            }
        }
    }
}
