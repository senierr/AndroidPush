package com.senierr.push.fcm;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.senierr.push.PushManager;
import com.senierr.push.internal.PushBaseReceiver;

/**
 * FCM注册广播
 *
 * @author zhouchunjie
 * @date 2018/6/6
 */
public class FCMInstanceIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        Intent intent = new Intent();
        intent.setAction(PushBaseReceiver.ACTION_REGISTER);
        intent.putExtra(PushBaseReceiver.KEY_PUSH_TYPE, PushManager.TYPE_FCM);
        intent.putExtra(PushBaseReceiver.KEY_REGISTER, FirebaseInstanceId.getInstance().getToken());
        sendBroadcast(intent);
    }
}
