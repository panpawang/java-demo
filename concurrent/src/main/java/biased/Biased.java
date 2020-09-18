package biased;

import java.util.List;
import java.util.Vector;

/**
 * -XX:BiasedLockingStartupDelay=0
 * 偏向锁开启：默认启动后5秒生效
 * 原理：1、锁对象中有markword标志位，当前线程、锁的状态
 * ｜当前线程｜锁的时间戳｜年龄｜锁的标示位｜是否锁定
 * @author Geym
 *
 */
public class Biased {
	public static List<Integer> numberList =new Vector<Integer>();
	public static void main(String[] args) {
		long begin=System.currentTimeMillis();
		int count=0;
		int startnum=0;
		while(count<10000000){
			numberList.add(startnum);
			startnum+=2;
			count++;
		}
		long end=System.currentTimeMillis();
		System.out.println(end-begin);
	}
}
