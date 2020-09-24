package methodRefence;

/**
 * @program: my-design-patterns
 * @description: 定义一个创建数组的函数式接口
 * @author: Rui.Zhou
 * @create: 2019-07-15 11:14
 **/
@FunctionalInterface
public interface ArrayBuilder {


    //定义一个创建int类型数组的方法，参数传入数组长度

    int[] buildArray(int length);


}
