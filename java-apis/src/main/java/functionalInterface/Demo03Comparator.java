package functionalInterface;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @program: my-design-patterns
 * @description: 当一个方法的返回值是一个函数式接口，那么可以直接返回一个lambda表达式
 * @author: Rui.Zhou
 * @create: 2019-07-02 15:26
 **/
public class Demo03Comparator {

    //定义一个方法，方法的返回类型使用函数式接口comparator
    public static Comparator<String> getComparator(){
        //方法的返回值是一个接口，那么我们可以返回这个接口的匿名内部类
//        return new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o2.length()-o1.length();
//            }
//        };

//        return (String o1,String o2)->{
//            return o2.length()-o1.length();
//        };

        //继续优化
        return (o1, o2) -> o1.length()-o2.length();
    }

    public static void main(String[] args) {
        //创建一个String类型的数组
        String[] arr = {"aaaa","b","ccccccc"};

        System.out.println(Arrays.toString(arr));

        //调用Arrays的Sort方法对字符串进行排序
        Arrays.sort(arr,(String o1, String o2)->o2.length()-o1.length());

        System.out.println(Arrays.toString(arr));
    }
}
