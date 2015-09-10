package com.example.lucia.observeapplicaton.lib.Observer;

/**
 * Created by Lucia on 2015/9/10.
 * 观察者接口
 */
public interface EventObserverInterface {
    /**
     * 根据事件进行数据或者UI的更新
     * @param eventType
     */
    public void dispatchChange(String eventType);
}
