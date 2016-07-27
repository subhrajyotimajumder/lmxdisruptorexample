package com.sj.lmax.handler;

import com.lmax.disruptor.EventHandler;
import com.sj.lmax.model.LogEvent;

/**
 * Created by subhrajyoti_majumder on 27/07/16.
 */
public class LogEventHandler implements EventHandler<LogEvent> {
    public void onEvent(LogEvent logEvent, long l, boolean b) throws Exception {
        System.out.println("Log event: " + logEvent.getValue());
    }
}
