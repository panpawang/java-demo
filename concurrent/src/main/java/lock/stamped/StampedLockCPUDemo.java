package lock.stamped;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.StampedLock;

/**
 * @author Administrator
 *
 */
public class StampedLockCPUDemo {
    static Thread[] holdCpuThreads = new Thread[3];
    static final StampedLock lock = new StampedLock();
    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            long readLong = lock.writeLock();
            //LockSupport.parkNanos(600000000000L);
            LockSupport.parkNanos(15L*1000*1000*1000);
            lock.unlockWrite(readLong);
        }).start();
        Thread.sleep(100);
        for (int i = 0; i < 3; ++i) {
            holdCpuThreads[i] = new Thread(new HoldCPUReadThread());
            holdCpuThreads[i].start();
        }
        Thread.sleep(10000);
        //线程中断后，会占用CPU
        for (int i = 0; i < 3; ++i) {
            holdCpuThreads[i].interrupt();
        }
    }

    private static class HoldCPUReadThread implements Runnable {
        public void run() {
            long lockr = lock.readLock();
            System.out.println(Thread.currentThread().getName()+ " 获得读锁");
            lock.unlockRead(lockr);
        }
    }
}
