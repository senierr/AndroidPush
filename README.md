# AndroidPush

> Android推送整合、自动切换最优推送渠道

1. 支持FCM、小米推送、信鸽推送
2. 支持自动切换最优推送渠道
3. 支持通知栏、透传两种消息类型

1. 权限
    ```
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.GET_TASKS" />
    <uses-permission android:name="android.permission.VIBRATE"/>
    <permission
        android:name="${PACKAGE_NAME}.permission.MIPUSH_RECEIVE"
        android:protectionLevel="signature" />
    <uses-permission
        android:name="${PACKAGE_NAME}.permission.MIPUSH_RECEIVE" />

    <uses-permission android:name="android.permission.WAKE_LOCK" />
    <uses-permission android:name="android.permission.RECEIVE_USER_PRESENT" />
    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_SETTINGS" />
    ```

1. 消息类型
    推送消息包含两种类型：通知栏消息和透传消息
    类名：PushMessage
    区别：通知栏消息有title和content，而透传消息没有

2. 推送注册
    ```
    PushManager.register(this);
    ```
    注册结果通过继承PushBaseReceiver，在onRegisterResult中处理；

3. 消息处理
    ```
    PushManager.handleMessage(this)
    ```
    启动Activity中加入此方法，拦截并统一处理消息。通过继承PushBaseReceiver，在onNotificationMessageClicked中处理；
    注：透传消息暂未加入



