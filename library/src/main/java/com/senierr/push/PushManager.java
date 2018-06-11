package com.senierr.push;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.senierr.push.internal.PushBaseReceiver;
import com.senierr.push.internal.PushMessage;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushManager;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

/**
 * 推送入口
 *
 * @author zhouchunjie
 * @date 2018/6/6
 */
public class PushManager {

    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_FCM = 0;
    public static final int TYPE_MI = 1;
    public static final int TYPE_XG = 2;

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
//        if (RomUtil.isMiui()) {
//            String appId = SystemUtil.getStringMetaData(context, "MI_APP_ID");
//            String appKey = SystemUtil.getStringMetaData(context, "MI_APP_KEY");
//            if (!TextUtils.isEmpty(appId) && !TextUtils.isEmpty(appKey)) {
//                MiPushClient.registerPush(context, appId, appKey);
//                return;
//            }
//        }
        // 3. 其他类型 -> XG
        XGPushManager.registerPush(context);
    }

    /**
     * 解析推送消息
     *
     * @param activity
     */
    public static boolean handleMessage(Activity activity) {
        if (activity == null) return false;

        PushMessage pushMessage = null;
        // 小米
        MiPushMessage message = (MiPushMessage) activity.getIntent()
                .getSerializableExtra(PushMessageHelper.KEY_MESSAGE);
        if (message != null) {
            pushMessage = new PushMessage();
            pushMessage.setTitle(message.getTitle());
            pushMessage.setContent(message.getDescription());
            pushMessage.setExtra(message.getExtra());
        }
        // 信鸽
        XGPushClickedResult clickedResult = XGPushManager.onActivityStarted(activity);
        if (clickedResult != null) {
            pushMessage = new PushMessage();
            pushMessage.setTitle(clickedResult.getTitle());
            pushMessage.setContent(clickedResult.getContent());
            try {
                JSONObject jsonObject = new JSONObject(clickedResult.getCustomContent());
                HashMap<String, String> extra = new HashMap<>();
                Iterator<String> keys = jsonObject.keys();
                while (keys != null && keys.hasNext()) {
                    String key = keys.next();
                    extra.put(key, jsonObject.getString(key));
                }
                pushMessage.setExtra(extra);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        // 发送推送消息
        if (pushMessage != null) {
            Intent intent = new Intent();
            intent.setAction(PushBaseReceiver.ACTION_NOTIFICATION);
            intent.putExtra(PushBaseReceiver.KEY_MESSAGE, pushMessage);
            activity.sendBroadcast(intent);
            return true;
        }

        return false;
    }
}
