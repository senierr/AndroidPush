# AndroidPush

> Android推送整合、自动切换最优推送渠道

1. 支持~~FCM~~、小米推送、信鸽推送  
2. 支持自动切换最优推送渠道  
3. 支持通知栏、透传两种消息类型  

## 配置FCM

如果您还没有 Firebase 项目，请在 Firebase 控制台中创建一个。如果您已经有与自己的移动应用相关联的现有 Google 项目，请点击导入 Google 项目。如果没有，请点击添加项目。
点击将 Firebase 添加到您的 Android 应用，然后按设置步骤操作。如果您是导入现有 Google 项目，系统可能会自动执行这些操作，您只需下载配置文件即可。
出现提示时，输入应用的软件包名称。请务必输入应用在使用的软件包名称；只有在将应用添加到 Firebase 项目时您才能进行此设置。
最后，您要下载一个 google-services.json 文件。您可以随时重新下载此文件。
如果尚未将此文件复制到项目的模块文件夹（通常是 app/），请执行此操作。

1. 在Firebase上创建项目，下载google-services.json配置文件，并放入app/目录下

2. 根级 build.gradle 文件添加规则，以纳入 google-services 插件和 Google 的 Maven 代码库：
```
buildscript {
    // ...
    dependencies {
        // ...
        classpath 'com.google.gms:google-services:3.2.0' // google-services plugin
    }
}

allprojects {
    // ...
    repositories {
        // ...
        maven {
            url "https://maven.google.com" // Google's Maven repository
        }
    }
}
```
3. 然后，在模块 Gradle 文件（通常是 app/build.gradle）中，在文件的底部添加 apply plugin 代码行，以启用 Gradle 插件：
```
apply plugin: 'com.android.application'

android {
  // ...
}

dependencies {
  // ...
}

// ADD THIS AT THE BOTTOM
apply plugin: 'com.google.gms.google-services'
```

## 配置小米推送

小米开发者上注册应用，并在defaultConfig中配置PACKAGE_NAME、MI_APP_ID、MI_APP_KEY

```
defaultConfig {
        applicationId "com.mic.push"
        minSdkVersion 15
        targetSdkVersion 27
        versionCode 1
        versionName "1.0"

        manifestPlaceholders = [
                PACKAGE_NAME:"com.mic.push",
                XG_ACCESS_ID: "2100293400",
                XG_ACCESS_KEY: "AIG72431QUVD",
                MI_APP_ID: "2882303761517805379",
                MI_APP_KEY : "5871780552379",
        ]
    }
```

## 配置信鸽推送



## 1. 权限
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

## 1. 消息类型
推送消息包含两种类型：通知栏消息和透传消息  
类名：PushMessage  
区别：通知栏消息有title和content，而透传消息没有  

## 2. 推送注册
```
PushManager.register(this);
```
注册结果通过继承PushBaseReceiver，在onRegisterResult中处理；  

## 3. 消息处理
```
PushManager.handleMessage(this)
```
启动Activity中加入此方法，拦截并统一处理消息。通过继承PushBaseReceiver，在onNotificationMessageClicked中处理；  
注：透传消息暂未加入



