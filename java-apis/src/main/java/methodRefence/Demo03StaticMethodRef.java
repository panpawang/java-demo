package methodRefence;

/**
 * @program: my-design-patterns
 * @description: 通过类名使用静态的成员方法
 * @author: Rui.Zhou
 * @create: 2019-07-11 16:03
 **/
public class Demo03StaticMethodRef {
    //定义一个方法，方法的参数传递要计算绝对值的整数和函数式接口
    public static int method(int num, Calcable calcable){
        return   calcable.calsAbs(num);
    }

    public static void main(String[] args) {

        int number = method(-10, num -> Math.abs(num));

        System.out.println(number);

        //使用方法引用优化

        int number2 = method(-10, Math::abs);

        System.out.println(number2);
    }



}
