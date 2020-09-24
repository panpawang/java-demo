package functionalInterface;

import java.util.function.Consumer;

/**
 * @program: my-design-patterns
 * @description: 需要两个consumer，可以把两个consumer接口进行组合,再对数据进行消费
 *例如：
 * Consumer<String> con1
 * Consumer<String> con2
 * String s = "hello"
 * con1.accept(s)
 * con2.accept(s)
 *
 * con1.andThen(con2).accept(s);谁写前面谁先消费
 *
 * @author: Rui.Zhou
 * @create: 2019-07-02 21:37
 **/
public class Demo06AndThen {

    //定义一个方法，方法的参数传递一个字符串和两个consumer接口

    public static void method(String s , Consumer<String> con1 , Consumer<String> con2){

        con1.andThen(con2).accept(s);

    }

    public static void main(String[] args) {
        Consumer<String> con1 = (String s) -> {
            s.toUpperCase();
            System.out.println(s);
        };

        Consumer<String> con2 = (String s) -> {
            s.toLowerCase();
            System.out.println(s);
        };
        con1.andThen(con2).accept("hello");


        method("Hello",(t)->{
                    System.out.println(t.toUpperCase());
                },
                (t)->{
                    System.out.println(t.toLowerCase());
                });


        String[] arr = {"迪丽热巴，女","古力娜扎，女","古天乐，男"};

        Consumer<String> con3 = (String s) -> {
            String[] name = s.split("，");

            System.out.print("姓名：" + name[0]+"。");
        };

        Consumer<String> con4 = (String s) -> {
            String[] age = s.split("，");

            System.out.println("性别：" + age[1]);
        };

        for (String s : arr) {

            con3.andThen(con4).accept(s);
        }
    }



}
