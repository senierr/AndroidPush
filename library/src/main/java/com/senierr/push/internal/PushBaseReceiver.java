package com.senierr.push.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.senierr.push.PushManager;

/**
 * 推送消息广播
 *
 * @author zhouchunjie
 * @date 2018/6/8
 */
public abstract class PushBaseReceiver extends BroadcastReceiver {

    public static final String ACTION_NOTIFICATION = "com.senierr.push.notification";
    public static final String ACTION_REGISTER = "com.senierr.push.register";

    public static final String KEY_MESSAGE = "message";
    public static final String KEY_PUSH_TYPE = "push_type";
    public static final String KEY_REGISTER = "register";

    @Override
    final public void onReceive(Context context, Intent intent) {
        if (intent == null) return;
        if (ACTION_NOTIFICATION.equalsIgnoreCase(intent.getAction())) {
            onNotificationMessageClicked(context, (PushMessage) intent.getParcelableExtra(KEY_MESSAGE));
        } else if (ACTION_REGISTER.equalsIgnoreCase(intent.getAction())) {
            onRegisterResult(context,
                    intent.getIntExtra(KEY_PUSH_TYPE, PushManager.TYPE_UNKNOWN),
                    intent.getStringExtra(KEY_REGISTER));
        }
    }

    /**
     * 通知栏点击
     *
     * @param context
     * @param pushMessage
     */
    public abstract void onNotificationMessageClicked(Context context, PushMessage pushMessage);

    /**
     * 注册回调
     *
     * @param context
     * @param pushType
     * @param token
     */
    public abstract void onRegisterResult(Context context, int pushType, String token);
}
