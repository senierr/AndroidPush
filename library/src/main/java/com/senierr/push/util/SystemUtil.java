package com.senierr.push.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

/**
 * 系统工具
 *
 * @author zhouchunjie
 * @date 2018/6/8
 */
public class SystemUtil {

    /**
     * 从Application中获取meta-data中String值
     *
     * @param context 上下文
     * @param key meta-data的name
     * @return meta-data的value
     */
    public static String getStringMetaData(Context context, String key) {
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(),
                    PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            if (bundle != null && bundle.containsKey(key)) {
                return bundle.getString(key);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 检查谷歌服务是否可用
     *
     * @param context
     * @return
     */
    public static boolean checkGooglePlay(Context context) {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = googleApiAvailability.isGooglePlayServicesAvailable(context);
        return resultCode == ConnectionResult.SUCCESS;
    }
}
