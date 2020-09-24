package functionalInterface;

/**
 * @program: my-design-patterns
 * @description:
 * @author: Rui.Zhou
 * @create: 2019-07-01 16:30
 **/
public class Demo01Runable {

    //定义一个方法，方法的参数使用函数式接口runable
    static void startThread(Runnable run){
        new Thread(run).start();
    }

    public static void main(String[] args) {
        //调用startThread方法，那么我们可以传递接口匿名内部类
        startThread(()-> System.out.println(Thread.currentThread().getName()+"---->线程启动"));

    }


}
