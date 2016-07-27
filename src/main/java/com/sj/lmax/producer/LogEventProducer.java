package com.sj.lmax.producer;

import com.lmax.disruptor.RingBuffer;
import com.sj.lmax.model.LogEvent;

import java.nio.ByteBuffer;

/**
 * Created by subhrajyoti_majumder on 27/07/16.
 */
public class LogEventProducer {
    private RingBuffer<LogEvent> ringBuffer;

    public LogEventProducer(final RingBuffer<LogEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer bb) {
        long sequence = ringBuffer.next();
        try {
            final LogEvent logEvent = ringBuffer.get(sequence);
            logEvent.setValue(bb.getLong());
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
