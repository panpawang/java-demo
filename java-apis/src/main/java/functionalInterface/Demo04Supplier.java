package functionalInterface;

import java.util.function.Supplier;

/**
 * @program: my-design-patterns
 * @description: Supplier<T> 接口称为生产型接口，指定接口的泛型是什么，那么接口中的get（）方法就会生成什么类型的数据
 * @author: Rui.Zhou
 * @create: 2019-07-02 16:48
 **/
public class Demo04Supplier {

    //定义一个方法，方法的参数传递Supplier接口

    public static String getString(Supplier<String> sup){
        return  sup.get();
    }

    //定义个方法，用于获取int数组中的元素最大值
    public static Integer getMax(Supplier<Integer> sup){
        return sup.get();
    };

    public static void main(String[] args) {


        String string = getString(() -> {
            //生产一个字符串并返回
            return "hello";
        });

        System.out.println(string);

        int[] arr= {1,2,0,-12,88};

        Integer max1 = getMax(() -> {

            //获取数组中元素的最大值并返回
            int max = arr[0];

            for (int i : arr) {

                if (i > max) {
                    max = i;
                }
            }
            return max;
        });

        System.out.println(max1);
    }



}
