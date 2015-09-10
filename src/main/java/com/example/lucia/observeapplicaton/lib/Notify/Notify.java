package com.example.lucia.observeapplicaton.lib.Notify;

import com.example.lucia.observeapplicaton.lib.EventType.EventType;
import com.example.lucia.observeapplicaton.lib.Subject.EventSubject;

/**
 * Created by Lucia on 2015/9/10.
 * 通知中心，用来通知更新数据或者UI，采用单例模式
 */
public class Notify {
    private static volatile Notify mNotify;
    private Notify(){

    }

    public static Notify getInstance(){
        if(mNotify==null){
            mNotify=new Notify();
        }
        return mNotify;
    }

    public void NotifyActivity(String eventType){
        EventSubject eventSubject=EventSubject.getInstance();
        EventType eventTypes=EventType.getInstance();
        if(eventTypes.contains(eventType)){
            eventSubject.notifyObserver(eventType);
        }
    }
}
