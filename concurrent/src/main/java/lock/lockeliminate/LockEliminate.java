package lock.lockeliminate;


/**
 * StringBuffer是线程安全的，被synchronized修饰过的，是同步的。我们发现，sb这个引用只会在append方法中使用，不可能被其他线程引用（因为是局部变量，栈私有）
 * 因此，sb是不可能共享资源的，JVM会自动消除StringBuffer对象内部的锁
 * -server -XX:+DoEscapeAnalysis -XX:-EliminateLocks -Xcomp -XX:-BackgroundCompilation -XX:BiasedLockingStartupDelay=0
 * 
 * -server -XX:+DoEscapeAnalysis -XX:+EliminateLocks -Xcomp -XX:-BackgroundCompilation -XX:BiasedLockingStartupDelay=0
 */
public class LockEliminate {
	private static final int CIRCLE = 2000000; 
	public static void main(String args[]) throws InterruptedException {
		long start = System.currentTimeMillis();
		for (int i = 0; i < CIRCLE; i++) {
			craeteStringBuffer("JVM", "Diagnosis");
		}
		long bufferCost = System.currentTimeMillis() - start;
		System.out.println("craeteStringBuffer: " + bufferCost + " ms");
	}

	public static String craeteStringBuffer(String s1, String s2) {
		StringBuffer sb = new StringBuffer();
		sb.append(s1);
		sb.append(s2);
		return sb.toString();
	}
}