package com.example.lucia.observeapplicaton.lib.BaseActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.lucia.observeapplicaton.lib.Observer.EventObserver;
import com.example.lucia.observeapplicaton.lib.Subject.EventSubject;

import java.lang.ref.WeakReference;

/**
 * Created by Lucia on 2015/9/10.
 */
public abstract class BaseObserverActivity extends AppCompatActivity {

    private ActivityEventObserver mActivityEventObserver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityEventObserver=new ActivityEventObserver(this);
        registerObserver(mActivityEventObserver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeObserver(mActivityEventObserver);
    }


    public void registerObserver(EventObserver observer) {
        final String[] observerEventTypes=getObserverEventType();//获取所有需要监听的业务类型
        if(observerEventTypes!=null && observerEventTypes.length>0){
            final EventSubject eventSubject=EventSubject.getInstance();
            eventSubject.registerObserver(observer);

        }

    }

    public void removeObserver(EventObserver observer) {
        final String[] observerEventTypes=getObserverEventType();//获取所有需要监听的业务类型
        if(observerEventTypes!=null && observerEventTypes.length>0){
            final EventSubject eventSubject=EventSubject.getInstance();
            eventSubject.removeObserver(observer);
        }
    }

    /**
     * 该方法会在具体的观察者对象中调用，可以根据事件的类型来更新对应的UI，这个方法在UI线程中被调用，
     * 所以在该方法中不能进行耗时操作，可以另外开线程
     * @param eventType 事件类型
     */
    protected abstract void onChange(String eventType);

    /**
     * 通过这个方法来告诉具体的观察者需要监听的业务类型
     * @return
     */
    protected abstract String[] getObserverEventType();

    private static class ActivityEventObserver extends EventObserver {
        //添加弱引用，防止对象不能被回收
        private final WeakReference<BaseObserverActivity> mActivity;
        public ActivityEventObserver(BaseObserverActivity activity){
            super();
            mActivity=new WeakReference<BaseObserverActivity>(activity);
        }

        @Override
        public void onChange(String eventType) {
            BaseObserverActivity activity=mActivity.get();
            if(activity!=null){
                activity.onChange(eventType);
            }
        }
    }
}
