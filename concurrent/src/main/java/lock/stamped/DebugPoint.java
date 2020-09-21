package lock.stamped;

import java.util.concurrent.locks.StampedLock;

public class DebugPoint {
	private double x, y;
	private final StampedLock sl = new StampedLock();

	void move(double deltaX, double deltaY) { // an exclusively locked method
		long stamp = sl.writeLock();
		System.out.println("Thread:"+Thread.currentThread().getName()+" get the lock");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		try {
			x += deltaX;
			y += deltaY;
		} finally {
			sl.unlockWrite(stamp);
		}
	}

	double distanceFromOrigin() { // A read-only method
		long stamp = sl.tryOptimisticRead();
		double currentX = x, currentY = y;
		if (!sl.validate(stamp)) {
			stamp = sl.readLock();
			try {
				currentX = x;
				currentY = y;
			} finally {
				sl.unlockRead(stamp);
			}
		}
		return Math.sqrt(currentX * currentX + currentY * currentY);
	}

	void moveIfAtOrigin(double newX, double newY) { // upgrade
		// Could instead start with optimistic, not read mode
		long stamp = sl.readLock();
		try {
			while (x == 0.0 && y == 0.0) {
				long ws = sl.tryConvertToWriteLock(stamp);
				if (ws != 0L) {
					stamp = ws;
					x = newX;
					y = newY;
					break;
				} else {
					sl.unlockRead(stamp);
					stamp = sl.writeLock();
				}
			}
		} finally {
			sl.unlock(stamp);
		}
	}
}
