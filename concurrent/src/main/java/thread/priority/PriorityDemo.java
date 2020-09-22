package thread.priority;


public class PriorityDemo {
    public static class HightPriority extends Thread{
        static int count=0;
        public void run(){
            while(true){
                synchronized(PriorityDemo.class){
                    count++;
                    if(count>10000000){
                        System.out.println("HightPriority is complete");
                        break;
                    }
                }
            }
        }
    }
    public static class LowPriority extends Thread{
        static int count=0;
        public void run(){
            while(true){
                synchronized(PriorityDemo.class){
                    count++;
                    if(count>10000000){
                        System.out.println("LowPriority is complete");
                        break;
                    }
                }
            }
        }
    }
    
    /**
     * HightPriority先完成的次数多，但是 不保证
     * 
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread high=new HightPriority();
        LowPriority low=new LowPriority();
        high.setPriority(Thread.MAX_PRIORITY);
        low.setPriority(Thread.MIN_PRIORITY);
        low.start();
        high.start();
    }
}
