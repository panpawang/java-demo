package lock.readwritelock;

import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class TestReadWriteLock {
	private static final int MAX_THREADS = 2000;
	private static final int TASK_COUNT = 4000;
	private static Lock lock=new ReentrantLock();
	private static ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();
	private static Lock readLock = readWriteLock.readLock();
	private static Lock writeLock = readWriteLock.writeLock();
	
	java.util.Random rand=new java.util.Random();
	
	private int value;
	
	public class ReadWriteThread implements Runnable{
		protected String name;
		
		public ReadWriteThread(){
		}
		public ReadWriteThread(String name){
			this.name=name;
		}
		@Override
		public void run() {
			try {
				handleRead();
				int v=rand.nextInt(100);
				if(v<10)
					handleWrite(v);
				Thread.sleep(rand.nextInt(100));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public class ReadWriteThread2 implements Runnable{
		protected String name;
		
		public ReadWriteThread2(){
		}
		public ReadWriteThread2(String name){
			this.name=name;
		}
		@Override
		public void run() {
			try {
				handleRead2();
				int v=rand.nextInt(100);
				if(v<10)
					handleWrite2(v);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public class CounterPoolExecutor extends ThreadPoolExecutor{
		private AtomicInteger count =new AtomicInteger(0);
		public long startTime=0;
		public String funcname="";
		public CounterPoolExecutor(int corePoolSize, int maximumPoolSize,
				long keepAliveTime, TimeUnit unit,
				BlockingQueue<Runnable> workQueue) {
			super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		}
		
		protected void afterExecute(Runnable r, Throwable t) { 
			int l=count.addAndGet(1);
			if(l==TASK_COUNT){
				System.out.println(funcname+" spend time:"+(System.currentTimeMillis()-startTime));
				
			}
		}
	}
	
	public Object handleRead() throws InterruptedException{
		try{
			lock.lock();
			Thread.sleep(1);
			return value;
		}finally{
		lock.unlock();
		}
	}
	
	public void handleWrite(int index) throws InterruptedException{
		try{
			lock.lock();
			Thread.sleep(1);
			value=index;
		}finally{
		lock.unlock();
		}
	}
	
	public Object handleRead2() throws InterruptedException{
		try{
			readLock.lock();
			Thread.sleep(1);
			return value;
		}finally{
			readLock.unlock();
		}
	}
	
	public void handleWrite2(int index) throws InterruptedException{
		try{
			writeLock.lock();
			Thread.sleep(1);
			value=index;
		}finally{
			writeLock.unlock();
		}
	}
	
	@Test
	public void testLock() throws InterruptedException {
	
		CounterPoolExecutor exe=new CounterPoolExecutor(MAX_THREADS, MAX_THREADS,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

		long starttime=System.currentTimeMillis();
		exe.startTime=starttime;
		exe.funcname="testLock";
		for(int i=0;i<TASK_COUNT;i++)
			exe.submit(new ReadWriteThread());
		
		Thread.sleep(100000);
	}
	
	@Test
	public void testLock2() throws InterruptedException {
	
		CounterPoolExecutor exe=new CounterPoolExecutor(MAX_THREADS, MAX_THREADS,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

		long starttime=System.currentTimeMillis();
		exe.startTime=starttime;
		exe.funcname="testLock2";
		for(int i=0;i<TASK_COUNT;i++)
			exe.submit(new ReadWriteThread2());
		
		Thread.sleep(100000);
	}

}
