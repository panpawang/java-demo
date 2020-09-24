package methodRefence;

/**
 * @program: my-design-patterns
 * @description:
 * @author: Rui.Zhou
 * @create: 2019-07-11 16:04
 **/
@FunctionalInterface
public interface Calcable {

    //定义一个抽象方法，传递一个整数，对整数进行绝对值计算并返回
    int calsAbs(int num);

}
