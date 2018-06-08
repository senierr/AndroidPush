package com.senierr.push.mi;

import android.content.Context;
import android.content.Intent;

import com.senierr.push.internal.PushBaseReceiver;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

import java.util.List;

/**
 * 小米推送接收广播
 *
 * @author zhouchunjie
 * @date 2018/6/7
 */
public class MiMessageReceiver extends PushMessageReceiver {

    @Override
    public void onReceiveRegisterResult(Context context, MiPushCommandMessage miPushCommandMessage) {
        List<String> arguments = miPushCommandMessage.getCommandArguments();
        String cmdArg1 = ((arguments != null && arguments.size() > 0) ? arguments.get(0) : null);
        Intent intent = new Intent();
        intent.setAction(PushBaseReceiver.ACTION_REGISTER);
        intent.putExtra(PushBaseReceiver.KEY_REGISTER, cmdArg1);
        context.sendBroadcast(intent);
    }
}
