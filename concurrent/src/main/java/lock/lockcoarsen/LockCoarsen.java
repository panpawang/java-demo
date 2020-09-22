package lock.lockcoarsen;

/**
 * 锁粗化：如果一系列操作都对同一个对象反复加锁和解锁，
 * 如上代码中连续的append方法就属于这个类情况，
 * 如果虚拟机探测到有这样一串零碎的操作都对同一个对象加锁，将会把加锁同步范围扩展（粗化）到整个操作序列外
 * 上代码中就扩展到for循环操作操作之后，只需一次加锁就可以了
 */
public class LockCoarsen {
	public static Object lock=new Object();
	public static final int CIRCLE=10000000;
	
	public static void main(String[] args) {

	
		long begintime=System.currentTimeMillis();
		for(int i=0;i<CIRCLE;i++){
			synchronized(lock){
				
			}
		}
		long endtime=System.currentTimeMillis();
		System.out.println("sync in loop:"+(endtime-begintime));
		
		long begintime1=System.currentTimeMillis();
		synchronized(lock){
			for(int i=0;i<CIRCLE;i++){
				
			}
		}
		long endtime1=System.currentTimeMillis();
		System.out.println("sync out loop:"+(endtime1-begintime1));

	}

}
