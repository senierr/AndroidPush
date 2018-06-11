package com.senierr.push.fcm;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * @author zhouchunjie
 * @date 2018/6/6
 */
public class FCMMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e("FCMMessagingService", "onMessageReceived: " + remoteMessage.toString());
    }
}
