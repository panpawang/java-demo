package lmaxdisruptor;

import com.lmax.disruptor.EventFactory;

public class PCDataFactory implements EventFactory<PCData>
{
    public PCData newInstance()
    {
        return new PCData();
    }
}