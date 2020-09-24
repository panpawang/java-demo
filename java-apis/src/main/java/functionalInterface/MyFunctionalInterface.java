package functionalInterface;

/**
 * @program: my-design-patterns
 * @description:函数式接口：有且只有一个抽象方法的接口
 * 接口中可以定义其他方法(默认方法、静态方法、私有方法)，@FunctionalInterface检测接口是否为函数式接口
 * @author: Rui.Zhou
 * @create: 2019-07-01 09:35
 **/

@FunctionalInterface
public interface MyFunctionalInterface {

    public abstract void method();

}
