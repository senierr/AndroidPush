package com.senierr.push;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.senierr.push.internal.PushBaseReceiver;
import com.senierr.push.internal.PushMessage;
import com.senierr.push.util.RomUtil;
import com.senierr.push.util.SystemUtil;
import com.tencent.android.tpush.XGCustomPushNotificationBuilder;
import com.tencent.android.tpush.XGPushManager;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageHelper;

/**
 * 推送入口
 *
 * @author zhouchunjie
 * @date 2018/6/6
 */
public class PushManager {

    private static final String TAG = PushManager.class.getSimpleName();

    /**
     * 注册推送
     *
     * @param context
     */
    public static void register(Context context) {
        // 1. 判断谷歌服务是否可用 -> FCM
//        if (checkGooglePlay(context)) {
//            FirebaseMessaging.getInstance().setAutoInitEnabled(true);
//            return;
//        }
        // 2. 判断当前系统 -> 系统推送
        if (RomUtil.isMiui()) {
            String appId = SystemUtil.getStringMetaData(context, "MI_APP_ID");
            String appKey = SystemUtil.getStringMetaData(context, "MI_APP_KEY");
            if (!TextUtils.isEmpty(appId) && !TextUtils.isEmpty(appKey)) {
                MiPushClient.registerPush(context, appId, appKey);
                return;
            }
        }
        // 3. 其他类型 -> XG
        XGPushManager.registerPush(context);
    }

    /**
     * 解析通知栏
     *
     * @param activity
     */
    public static void handleMessage(Activity activity) {
        if (activity == null) return;
        // 小米
        MiPushMessage message = (MiPushMessage) activity.getIntent()
                .getSerializableExtra(PushMessageHelper.KEY_MESSAGE);
        if (message != null) {
            PushMessage pushMessage = new PushMessage();
            pushMessage.setTitle(message.getTitle());
            pushMessage.setContent(message.getDescription());
            pushMessage.setExtra(message.getExtra());

            Intent intent = new Intent();
            intent.setAction(PushBaseReceiver.ACTION_NOTIFICATION);
            intent.putExtra(PushBaseReceiver.KEY_MESSAGE, pushMessage);
            activity.sendBroadcast(intent);
            return;
        }
    }

    /**
     * 设置推送通知栏图标
     *
     * @param resId
     */
    public static void setNotificationIcon(Context context, int resId) {
        // TODO: 2018/6/8 设置通知栏图标
        // 小米
        // 信鸽
        XGCustomPushNotificationBuilder builder = new XGCustomPushNotificationBuilder();
        builder.setNotificationLargeIcon(resId);
        builder.setSmallIcon(resId);
        builder.setIcon(resId);
        builder.setLayoutIconId(resId);
        builder.setLayoutIconDrawableId(resId);
        XGPushManager.setPushNotificationBuilder(context, 1, builder);
    }
}
