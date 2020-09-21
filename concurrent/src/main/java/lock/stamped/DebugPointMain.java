package lock.stamped;

public class DebugPointMain {
	static DebugPoint p = new DebugPoint();

	public static class WriteTask implements Runnable {
		@Override
		public void run() {
			p.move(Math.random(), Math.random());

		}
	}

	public static class ReadTask implements Runnable {
		@Override
		public void run() {
			p.distanceFromOrigin();
		}
	}
	
	public static void main(String[] args) {
		Thread writeThead=new Thread(new WriteTask());
		writeThead.start();
		for(int i=0;i<3;i++){
			Thread readThead=new Thread(new ReadTask());
			readThead.start();
		}
		
		for(int i=0;i<3;i++){
			Thread readThead=new Thread(new WriteTask());
			readThead.start();
		}

		
	}

}
