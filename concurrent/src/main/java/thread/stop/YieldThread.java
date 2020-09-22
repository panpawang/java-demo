package thread.stop;

public class YieldThread {
	volatile static int i=0;
	volatile static int j=0;
	public static void main(String[] args) throws InterruptedException {
		Thread t1=new Thread(){
			@Override
			public void run(){
				while(true){
				    i++;
				    Thread.yield();
				}
			}
		};
		Thread t2=new Thread(){
			@Override
			public void run(){
				while(true){
					j++;
					Thread.yield();
				}
			}
		};
		t1.start();
		t2.start();
		Thread.sleep(2000);
		System.out.println("i="+i);
		System.out.println("j="+j);
		t1.stop();
		t2.stop();
	}
}
