package com.sj.lmax;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.sj.lmax.factory.LogEventFactory;
import com.sj.lmax.handler.LogEventHandler;
import com.sj.lmax.model.LogEvent;
import com.sj.lmax.producer.LogEventProducer;
import com.sj.lmax.translator.LogEventProducerWithTranslator;

import java.nio.ByteBuffer;
import java.util.concurrent.ThreadFactory;

/**
 * Created by subhrajyoti_majumder on 27/07/16.
 */
public class LogEventMain {
    public static void main(String[] args) throws InterruptedException {
        ThreadFactory threadFactory = r -> new Thread(r);
        LogEventFactory logEventFactory = new LogEventFactory();
        int byteBuffer = 1024;
        Disruptor<LogEvent> disruptor = new Disruptor<>(logEventFactory, byteBuffer, threadFactory);

        disruptor.handleEventsWith(new LogEventHandler());

        disruptor.start();

        final RingBuffer<LogEvent> ringBuffer = disruptor.getRingBuffer();

        ByteBuffer bb = ByteBuffer.allocate(8);
//        LogEventProducer logEventProducer = new LogEventProducer(ringBuffer);
        LogEventProducerWithTranslator logEventProducerWithTranslator = new LogEventProducerWithTranslator(ringBuffer);
        for(int i=1;i<10;i++){
            bb.putLong(0, i);
//            ringBuffer.publishEvent((event, sequence, buffer) -> event.setValue(buffer.getLong(0)), bb);
//            logEventProducer.onData(bb);
//            ringBuffer.publish(Long.valueOf(i));
            logEventProducerWithTranslator.onData(bb);
            Thread.sleep(1000);
        }

    }
}
