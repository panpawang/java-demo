package methodRefence;

/**
 * @program: my-design-patterns
 * @description:定义一个打印的函数式接口
 * @author: Rui.Zhou
 * @create: 2019-07-09 17:09
 **/

@FunctionalInterface
public interface Printable {

    //定义一个打印字符串的抽象方法
    void print(String s);
}
