package functionalInterface;

import java.util.function.Consumer;

/**
 * @program: my-design-patterns
 * @description: 消费型接口，泛型执行什么类型，就可以使用accept方法消费什么样的类型
 * @author: Rui.Zhou
 * @create: 2019-07-02 17:13
 **/
public class Demo05Consumer {

    public static void method(String name, Consumer<String> con){

        con.accept(name);
    }



    public static void main(String[] args) {
        Consumer<String> consumer = (String name) -> {
            //对传递的字符串进行消费
            System.out.println(name);

            //对字符串进行反转
            String reName = new StringBuilder(name).reverse().toString();

            System.out.println(reName);
        };

        consumer.accept("张三");


//        method("张三", );


    }


}
