package functionalInterface;

import java.util.function.Function;

/**
 * @program: my-design-patterns
 * @description: 根据一个类型的数据得到另一个类型的数据，前者成为前置条件，后者称为后置条件
 *
 * Function接口中的默认方法andThen进行组合操作
 * @author: Rui.Zhou
 * @create: 2019-07-03 15:12
 **/
public class Demo08Function {

    public static void change(String str , Function<String, Integer> fun){

        Integer apply = fun.apply(str);

        System.out.println(apply);
    }

    /*
    andThen
     */

    public static void change1(String str, Function<String, Integer> fun, Function<Integer, String> fun2){

        String apply = fun.andThen(fun2).apply(str);

        System.out.println(apply);
    }

    public static void main(String[] args) {

        String s = "1234";

        change(s,str-> Integer.valueOf(str));


        change1(s,str -> Integer.valueOf(str)+10, num -> String.valueOf(num));
    }



}
