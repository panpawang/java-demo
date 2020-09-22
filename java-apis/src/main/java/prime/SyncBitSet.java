package prime;

import java.util.BitSet;

public class SyncBitSet {
    private BitSet bs=null;
    
    public SyncBitSet(int nbits){
        bs=new BitSet(nbits);
        //默认都是质数，剔除不是质数的
        bs.set(0, nbits,true);
    }
    
    public synchronized void set(int bitIndex, boolean value) {
        bs.set(bitIndex,value);
    }
    
    public synchronized void set(int fromIndex, int toIndex, boolean value){
        bs.set(fromIndex,toIndex,value);
    }
    
    public synchronized boolean get(int bitIndex){
        return bs.get(bitIndex);
    }
}
