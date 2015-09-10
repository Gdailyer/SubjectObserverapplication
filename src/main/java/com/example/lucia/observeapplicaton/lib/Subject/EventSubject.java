package com.example.lucia.observeapplicaton.lib.Subject;

import com.example.lucia.observeapplicaton.lib.Observer.EventObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucia on 2015/9/10.
 */
public class EventSubject implements EventSubjectInterface {
    private List<EventObserver> mEventObservers=new ArrayList<EventObserver>();
    private static volatile EventSubject mEventSubject;
    private EventSubject(){

    }

    public synchronized static EventSubject getInstance(){
        if(mEventSubject ==null){
            mEventSubject =new EventSubject();
        }
        return mEventSubject;
    }

    @Override
    public void registerObserver(EventObserver observer) {
        synchronized (mEventObservers){
            if(observer!=null){
                if(mEventObservers.contains(observer)){
                    return;
                }
                mEventObservers.add(observer);
            }
        }

    }

    @Override
    public void removeObserver(EventObserver observer) {
        synchronized (mEventObservers){
            int index = mEventObservers.indexOf(observer);
            if (index >= 0) {
                mEventObservers.remove(observer);
            }
        }
    }

    @Override
    public void notifyObserver(String eventType) {
        if(mEventObservers!=null && mEventObservers.size()>0 && eventType!=null){
            for(EventObserver observer:mEventObservers){
                observer.dispatchChange(eventType);
            }
        }

    }
}
