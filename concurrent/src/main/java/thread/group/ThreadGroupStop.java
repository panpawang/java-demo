package thread.group;

public class ThreadGroupStop implements Runnable{
	public static void main(String[] args) throws InterruptedException {
		ThreadGroup tg=new ThreadGroup("PrintGroup");
		Thread t1=new Thread(tg,new ThreadGroupStop(),"T1");
		Thread t2=new Thread(tg,new ThreadGroupStop(),"T2");
		t1.start();
		t2.start();
		Thread.sleep(3000);
		tg.stop();
	}

	@Override
	public void run() {
		String groupAndName=Thread.currentThread().getThreadGroup().getName()
				+"-"+Thread.currentThread().getName();
		while(true){
			System.out.println("I am "+groupAndName);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
