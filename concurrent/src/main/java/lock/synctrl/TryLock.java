package synctrl;

import java.util.concurrent.locks.ReentrantLock;

public class TryLock implements Runnable {
	public static ReentrantLock lock1 = new ReentrantLock();
	public static ReentrantLock lock2 = new ReentrantLock();
	int lock;

	public TryLock(int lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		if (lock == 1) {
			while (true) {
				if (lock1.tryLock()) {
					try {
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
						}
						if (lock2.tryLock()) {
							try {
								System.out.println(Thread.currentThread()
										.getId() + ":My Job done");
								return;
							} finally {
								lock2.unlock();
							}
						}
					} finally {
						lock1.unlock();
					}
				}
			}
		} else {
			while (true) {
				if (lock2.tryLock()) {
					try {
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
						}
						if (lock1.tryLock()) {
							try {
								System.out.println(Thread.currentThread()
										.getId() + ":My Job done");
								return;
							} finally {
								lock1.unlock();
							}
						}
					} finally {
						lock2.unlock();
					}
				}
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		TryLock r1 = new TryLock(1);
		TryLock r2 = new TryLock(2);
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
	}
}
