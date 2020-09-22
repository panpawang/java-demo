package thread.Interruput;

/**
 * 中断状态可以检测，并在应用上作出相应
 * 如果应用不相应中断，则T1永远不会退出
 * @author Administrator
 *
 */
public class InterruputThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(){
            @Override
            public void run(){
                while(true){
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("Interruted!");
                        break;
                    }
                    Thread.yield();
                }
            }
        };
        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
    }
}
