package thread.create;

public class CreateThread3 implements Runnable {
	public static void main(String[] args) {
		Thread t1=new Thread(new CreateThread3());
		t1.start();
	}

	@Override
	public void run() {
		System.out.println("Oh, I am Runnable");
	}
}
