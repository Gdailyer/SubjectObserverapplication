package com.example.lucia.observeapplicaton.lib.Subject;

import com.example.lucia.observeapplicaton.lib.Observer.EventObserver;

/**
 * Created by Lucia on 2015/9/10.
 * 抽象的观察对象
 */
public interface EventSubjectInterface {
    /**
     * 注册观察者
     * @param observer
     */
    public void registerObserver(EventObserver observer);

    /**
     * 反注册观察者
     * @param observer
     */
    public void removeObserver(EventObserver observer);

    /**
     * 通知注册的观察者进行数据或者UI的更新
     */
    public void notifyObserver(String eventType);
}
