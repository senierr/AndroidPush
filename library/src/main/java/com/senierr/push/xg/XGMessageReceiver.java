package com.senierr.push.xg;

import android.content.Context;
import android.content.Intent;

import com.senierr.push.PushManager;
import com.senierr.push.internal.PushBaseReceiver;
import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;

/**
 * 信鸽推送消息接收
 *
 * @author zhouchunjie
 * @date 2018/6/6
 */
public class XGMessageReceiver extends XGPushBaseReceiver {

    @Override
    public void onRegisterResult(Context context, int i, XGPushRegisterResult xgPushRegisterResult) {
        Intent intent = new Intent();
        intent.setAction(PushBaseReceiver.ACTION_REGISTER);
        intent.putExtra(PushBaseReceiver.KEY_PUSH_TYPE, PushManager.TYPE_XG);
        if (i == XGPushBaseReceiver.SUCCESS) {
            intent.putExtra(PushBaseReceiver.KEY_REGISTER, xgPushRegisterResult.getToken());
        }
        context.sendBroadcast(intent);
    }

    @Override
    public void onUnregisterResult(Context context, int i) {
    }

    @Override
    public void onSetTagResult(Context context, int i, String s) {
    }

    @Override
    public void onDeleteTagResult(Context context, int i, String s) {
    }

    @Override
    public void onTextMessage(Context context, XGPushTextMessage xgPushTextMessage) {
    }

    @Override
    public void onNotifactionClickedResult(Context context, XGPushClickedResult xgPushClickedResult) {
    }

    @Override
    public void onNotifactionShowedResult(Context context, XGPushShowedResult xgPushShowedResult) {
    }
}
