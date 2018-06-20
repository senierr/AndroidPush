package com.senierr.push.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

/**********************************************************
 * @文件作者：zhouchunjie
 * @创建时间：2018/6/19 14:02
 * @文件描述：通知工具类
 * @修改历史：2018/6/19 创建初始版本
 **********************************************************/
public class NotificationUtil {

    private static final String CHANNEL_ID_SYSTEM = "system";
    private static final String CHANNEL_NAME_SYSTEM = "system";

    /**
     * 获取通知管理器
     *
     * @param context
     * @return
     */
    public static NotificationManager getNotificationManager(Context context) {
        return (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    /**
     * 获取通知构造器
     *
     * @param context
     * @param channelId
     * @param channelName
     * @param importance
     * @return
     */
    public static NotificationCompat.Builder getBuilder(Context context, String channelId, String channelName, int importance) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager manager = getNotificationManager(context);
            if (manager != null) {
                manager.createNotificationChannel(new NotificationChannel(
                        channelId, channelName, importance));
            }
        }
        return new NotificationCompat.Builder(context, channelId);
    }

    /**
     * 获取通知构造器
     *
     * @param context
     * @param channelId
     * @param channelName
     * @return
     */
    public static NotificationCompat.Builder getBuilder(Context context, String channelId, String channelName) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager manager = getNotificationManager(context);
            if (manager != null) {
                manager.createNotificationChannel(new NotificationChannel(
                        channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT));
            }
        }
        return new NotificationCompat.Builder(context, channelId);
    }
}
