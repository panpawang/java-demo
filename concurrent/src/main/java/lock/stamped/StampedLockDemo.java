package lock.stamped;

/**
 *StampedLock是为了优化可重入读写锁性能的一个锁实现工具，jdk8开始引入
 * 相比于普通的ReentranReadWriteLock主要多了一种乐观读的功能
 * 在API上增加了stamp的入参和返回值
 * 不支持重入
 */
public class  StampedLockDemo {
	static Point point=new Point();
	
	static class WritePoint implements Runnable{
		@Override
		public void run() {
			point.move(Math.random(), Math.random());
		}
	}	
	static class ReadPoint implements Runnable{
		@Override
		public void run() {
			point.distanceFromOrigin();
		}
	}
	public static void main(String[] args) throws InterruptedException {
		Thread t1=new Thread(new WritePoint());
		Thread t2=new Thread(new ReadPoint());
		t1.start();
		Thread.sleep(100);
		t2.start();
	}

}
