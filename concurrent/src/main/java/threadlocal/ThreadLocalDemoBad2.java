package threadlocal;

public class ThreadLocalDemoBad2 {

    static class Container {
        int num;
    }
    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<Container> tl = new ThreadLocal<>();
        tl.set(new Container()); // 先set下ThreadLocal

        //threadlcoal.get获取到线程变量副本之后，不要让其他线程来访问它了，否则就是多线程操作同一个变量，可能造成线程安全问题。
        Container container = tl.get();
        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
                container.num++;
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(tl.get().num);
    }
}
