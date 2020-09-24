package methodRefence;

/**
 * @program: my-design-patterns
 * @description: 数组的构造器引用
 * @author: Rui.Zhou
 * @create: 2019-07-15 11:16
 **/
public class Demo04ConsMethdRef {

    /*
    定义个一个方法，方法参数传入创建数组的长度和ArrayBuilder接口
     */

    public static int[] createArray(int length, ArrayBuilder builder){

        return builder.buildArray(length);

    }

    public static void main(String[] args) {


        //调用createArray,传入数组的长度和lambda表达式
        int[] array1 = createArray(10, length -> new int[length]);

        System.out.println(array1.length);

        //使用方法引用优化lambada表达式

        int[] array2 = createArray(10, int[]::new);

        System.out.println(array2.length);
    }
}
