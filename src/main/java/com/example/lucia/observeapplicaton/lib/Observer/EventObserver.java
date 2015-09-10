package com.example.lucia.observeapplicaton.lib.Observer;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by Lucia on 2015/9/10.
 */
public abstract class EventObserver implements EventObserverInterface {
    private Handler mHandler;

    public EventObserver(){
        mHandler=new Handler(Looper.getMainLooper());
    }


    //public abstract void onChange(String eventType);

    public abstract void onChange(String eventType);

    @Override
    public void dispatchChange(String eventType){
        mHandler.post(new NotificationRunnable(eventType));
    }

    private final class NotificationRunnable implements Runnable{
        private String mEventType;
        public NotificationRunnable(String eventType){
            this.mEventType=eventType;
        }
        @Override
        public void run() {
            EventObserver.this.onChange(mEventType);
        }
    }
}
