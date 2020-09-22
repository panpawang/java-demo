package lmaxdisruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

public class Producer
{
    private final RingBuffer<PCData> ringBuffer;

    public Producer(RingBuffer<PCData> ringBuffer)
    {
        this.ringBuffer = ringBuffer;
    }

    public void pushData(ByteBuffer bb)
    {
        //获取事件队列的下标位置
        long sequence = ringBuffer.next();  // Grab the next sequence
        try
        {
            PCData event = ringBuffer.get(sequence); // Get the entry in the Disruptor
                                                        // for the sequence
            event.set(bb.getLong(0));  // Fill with data
        }
        finally
        {
            //放这里是为了避免阻塞....
            ringBuffer.publish(sequence);
        }
    }
}