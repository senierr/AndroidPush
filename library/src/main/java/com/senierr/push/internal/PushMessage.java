package com.senierr.push.internal;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

/**
 * 推送消息
 *
 * @author zhouchunjie
 * @date 2018/6/8
 */
public class PushMessage implements Parcelable {

    private String title;
    private String content;
    private Map<String, String> extra = new HashMap<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, String> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, String> extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "PushMessage{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", extra=" + extra +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeInt(this.extra.size());
        for (Map.Entry<String, String> entry : this.extra.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeString(entry.getValue());
        }
    }

    public PushMessage() {
    }

    protected PushMessage(Parcel in) {
        this.title = in.readString();
        this.content = in.readString();
        int extraSize = in.readInt();
        this.extra = new HashMap<>(extraSize);
        for (int i = 0; i < extraSize; i++) {
            String key = in.readString();
            String value = in.readString();
            this.extra.put(key, value);
        }
    }

    public static final Parcelable.Creator<PushMessage> CREATOR = new Parcelable.Creator<PushMessage>() {
        @Override
        public PushMessage createFromParcel(Parcel source) {
            return new PushMessage(source);
        }

        @Override
        public PushMessage[] newArray(int size) {
            return new PushMessage[size];
        }
    };
}
