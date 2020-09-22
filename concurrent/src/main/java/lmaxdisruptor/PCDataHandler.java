package lmaxdisruptor;

import com.lmax.disruptor.EventHandler;

public class PCDataHandler implements EventHandler<PCData>
{
    public void onEvent(PCData event, long sequence, boolean endOfBatch)
    {
        System.out.println(Thread.currentThread().getId()+":Event: **" + event.get()*event.get()+"**");
    }
}