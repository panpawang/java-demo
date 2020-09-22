package datacorrelation;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Plus implements Runnable {
	public static BlockingQueue<Msg> bq=new LinkedBlockingQueue<Msg>(); 
	@Override
	public void run() {
		while(true){
			try {
				Msg msg=bq.take();
				msg.j=msg.i+msg.j;
				Multiply.bq.add(msg);
			} catch (InterruptedException e) {
			}
		}
	}
}
