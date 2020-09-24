package functionalInterface;

import java.util.stream.Stream;

/**
 * @program: my-design-patterns
 * @description: showLog方法存在无论等级是否为1，都回去拼接字符串的问题
 * @author: Rui.Zhou
 * @create: 2019-07-01 11:25
 **/
public class DemoLogger {
    //定义一个根据日志级别显示日志信息的方法

    public static void showLog(int level, String msg){


        if(level == 1){
            System.out.println(msg);
        }
    }

    public static void main(String[] args) {

        String msg1 = "hello";
        String msg2 = "world";

        showLog(1,msg1+msg2);



        String a = "a";

        Stream.of("a","b","c").filter(s -> s.equals(a)).forEach(System.out::println);

    }



}
