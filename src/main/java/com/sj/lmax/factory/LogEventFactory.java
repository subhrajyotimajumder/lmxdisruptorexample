package com.sj.lmax.factory;

import com.lmax.disruptor.EventFactory;
import com.sj.lmax.model.LogEvent;

/**
 * Created by subhrajyoti_majumder on 27/07/16.
 */
public class LogEventFactory implements EventFactory<LogEvent> {

    @Override
    public LogEvent newInstance() {
        return new LogEvent();
    }
}
