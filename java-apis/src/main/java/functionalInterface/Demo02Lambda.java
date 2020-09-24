package functionalInterface;

/**
 * @program: my-design-patterns
 * @description: 使用lambda优化日志案例，lambda特点：延迟加载
 * @author: Rui.Zhou
 * @create: 2019-07-01 16:03
 **/
public class Demo02Lambda {

    //定义一个根据日志级别显示日志信息的方法

    public static void showLog(int level, MessageBuilder mb){


        if(level == 1){
            System.out.println(mb.builderMessage());
        }
    }

    public static void main(String[] args) {


        String msg1 = "hello";
        String msg2 = "world";

        showLog(1,()-> msg1+msg2);
    }

    /*
    使用lambda表达式进行参数传递，仅仅是把参数传递到showlog方法中
    只有满足前面的条件，才会调用接口MessageBuilder中的方法才会进行字符串的拼接
     */


}
