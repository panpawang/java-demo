package methodRefence;

/**
 * @program: my-design-patterns
 * @description:
 * @author: Rui.Zhou
 * @create: 2019-07-09 17:10
 **/
public class Demo01Printable {


    //参数传递printable接口
    public static void print(Printable printable){
        printable.print("hello world");
    }

    public static void main(String[] args) {

        print(s -> System.out.println(s));


        /*
    分析：lambda表达式的目的，打印参数传递的字符串

    注意：
        1.System.out和println都是已经存在的
        所以我们可以使用方法引用来优化lambda表达式
        可以使用System.out直接调用print方法
     */

        print(System.out::println);

    }



}
