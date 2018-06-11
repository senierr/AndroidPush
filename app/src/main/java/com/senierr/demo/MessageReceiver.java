package com.senierr.demo;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.senierr.push.internal.PushBaseReceiver;
import com.senierr.push.internal.PushMessage;

/**
 * @author zhouchunjie
 * @date 2018/6/8
 */
public class MessageReceiver extends PushBaseReceiver {

    @Override
    public void onNotificationMessageClicked(Context context, PushMessage pushMessage) {
        Log.e("PushBaseReceiver", "onNotificationMessageClicked: " + pushMessage.toString());
    }

    @Override
    public void onRegisterResult(Context context, int pushType, String token) {
        if (!TextUtils.isEmpty(token)) {
            Log.e("PushBaseReceiver", "注册成功: " + token);
        } else {
            Log.e("PushBaseReceiver", "注册失败");
        }
    }
}
