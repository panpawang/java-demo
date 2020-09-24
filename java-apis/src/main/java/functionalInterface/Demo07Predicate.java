package functionalInterface;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * @program: my-design-patterns
 * @description: 对某种类型的数据进行判断，从而得到一个布尔值
 * @author: Rui.Zhou
 * @create: 2019-07-03 14:09
 **/
public class Demo07Predicate {

    public static boolean checkString(Object s , Predicate predicate){


        return predicate.test(s);

    }


    /*
    定义一个方法，方法的参数传递一个字符串和两个predicate接口
     */

    public static boolean checkString1(String s, Predicate<String> p1, Predicate<String> p2){

        return p1.and(p2).test(s);
    }

    /*
    集合信息的筛选
     */
    public static ArrayList<String> filter(String[] arr , Predicate<String> pre1, Predicate<String> pre2){

        ArrayList<String> list = new ArrayList<>();


        for (String s : arr) {

            if(pre1.and(pre2).test(s)){
                list.add(s);
            }
        }
        return list;
    }


    public static void main(String[] args) {


        Integer s = 1;

        boolean b = checkString(s, (Object str) -> str instanceof String);

        System.out.println(b);


        String s1 = "aBC";

        boolean checkString1 = checkString1(s1, (String str) -> {
            return str.contains("a");
        }, (String str) -> {
            return str.length() > 2;
        });

        System.out.println(checkString1);


        /*
        集合信息的筛选
         */

        String[] arr = {"迪丽热巴，女","古力娜扎，女","古天乐，男"};


        ArrayList<String> strings = filter(arr, (String str) -> {
            String[] split = str.split("，");

            return split[0].length() > 3;
        }, (String str) -> {
            String[] split = str.split("，");
            return split[1].equalsIgnoreCase("女");
        });

        System.out.println(strings);
    }
}
